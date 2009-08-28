/*
 * Copyright (c) 2000-2006 JetBrains s.r.o. All Rights Reserved.
 */

package com.intellij.testFramework.fixtures.impl;

import com.intellij.ide.highlighter.ProjectFileType;
import com.intellij.ide.startup.impl.StartupManagerImpl;
import com.intellij.idea.IdeaTestApplication;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.impl.EditorFactoryImpl;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.EmptyRunnable;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.testFramework.PlatformTestCase;
import com.intellij.testFramework.EditorListenerTracker;
import com.intellij.testFramework.builders.ModuleFixtureBuilder;
import com.intellij.testFramework.fixtures.HeavyIdeaTestFixture;
import com.intellij.codeInsight.completion.CompletionProgressIndicator;
import gnu.trove.THashSet;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mike
 */
class HeavyIdeaTestFixtureImpl extends BaseFixture implements HeavyIdeaTestFixture {

  @NonNls private static final String PROJECT_FILE_PREFIX = "temp";
  @NonNls private static final String PROJECT_FILE_SUFFIX = ProjectFileType.DOT_DEFAULT_EXTENSION;

  private Project myProject;
  private final Set<File> myFilesToDelete = new HashSet<File>();
  private IdeaTestApplication myApplication;
  private final Set<ModuleFixtureBuilder> myModuleFixtureBuilders = new THashSet<ModuleFixtureBuilder>();
  private EditorListenerTracker myEditorListenerTracker;

  protected void addModuleFixtureBuilder(ModuleFixtureBuilder builder) {
    myModuleFixtureBuilders.add(builder);
  }

  public void setUp() throws Exception {
    super.setUp();

    initApplication();
    setUpProject();

    myEditorListenerTracker = new EditorListenerTracker();
  }

  public void tearDown() throws Exception {
    ((StartupManagerImpl)StartupManager.getInstance(getProject())).prepareForNextTest();
    checkAllTimersAreDisposed();

    for (ModuleFixtureBuilder moduleFixtureBuilder: myModuleFixtureBuilders) {
      moduleFixtureBuilder.getFixture().tearDown();
    }

    ProjectManagerEx.getInstanceEx().setCurrentTestProject(null);
    ApplicationManager.getApplication().runWriteAction(EmptyRunnable.getInstance()); // Flash posponed formatting if any.
    FileDocumentManager.getInstance().saveAllDocuments();

    doPostponedFormatting(myProject);

    Runnable runnable = new Runnable() {
      public void run() {
        Disposer.dispose(myProject);
      }
    };
    if (ApplicationManager.getApplication().isDispatchThread()) {
      runnable.run();
    } else {
      SwingUtilities.invokeAndWait(runnable);
    }

    for (final File fileToDelete : myFilesToDelete) {
      boolean deleted = FileUtil.delete(fileToDelete);
      assert deleted : "Can't delete "+fileToDelete;
    }

    myApplication.setDataProvider(null);

    EditorFactory editorFactory = EditorFactory.getInstance();
    final Editor[] allEditors = editorFactory.getAllEditors();
    ((EditorFactoryImpl)editorFactory).validateEditorsAreReleased(getProject());
    for (Editor editor : allEditors) {
      editorFactory.releaseEditor(editor);
    }
    assert 0 == editorFactory.getAllEditors().length : "There are unrealeased editors";
    CompletionProgressIndicator.cleanupForNextTest();

    super.tearDown();

    myEditorListenerTracker.checkListenersLeak();
    
  }


  private void setUpProject() throws Exception {
    File projectFile = File.createTempFile(PROJECT_FILE_PREFIX, PROJECT_FILE_SUFFIX);
    myFilesToDelete.add(projectFile);

    LocalFileSystem.getInstance().refreshAndFindFileByIoFile(projectFile);
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    new Throwable(projectFile.getPath()).printStackTrace(new PrintStream(buffer));
    myProject = PlatformTestCase.createProject(projectFile, buffer.toString());

    for (ModuleFixtureBuilder moduleFixtureBuilder: myModuleFixtureBuilders) {
      moduleFixtureBuilder.getFixture().setUp();
    }

    //PropertiesReferenceManager.getInstance(myProject).projectOpened();

    StartupManagerImpl sm = (StartupManagerImpl)StartupManager.getInstance(myProject);
    sm.runStartupActivities();
    sm.runPostStartupActivities();

    ProjectManagerEx.getInstanceEx().setCurrentTestProject(myProject);
  }

  private void initApplication() throws Exception {
    myApplication = IdeaTestApplication.getInstance();
    myApplication.setDataProvider(new MyDataProvider());
  }

  public Project getProject() {
    assert myProject != null : "setUp() should be called first";
    return myProject;
  }

  public Module getModule() {
    Module[] modules = ModuleManager.getInstance(getProject()).getModules();
    return modules.length == 0 ? null : modules[0];
  }

  private class MyDataProvider implements DataProvider {
    @Nullable
    public Object getData(@NonNls String dataId) {
      if (dataId.equals(DataConstants.PROJECT)) {
        return myProject;
      }
      else if (dataId.equals(DataConstants.EDITOR) || dataId.equals(OpenFileDescriptor.NAVIGATE_IN_EDITOR.getName())) {
        return FileEditorManager.getInstance(myProject).getSelectedTextEditor();
      }
      else {
        return null;
      }
    }
  }

  public PsiFile addFileToProject(@NonNls String rootPath, @NonNls final String relativePath, @NonNls final String fileText) throws IOException {
    final VirtualFile[] roots = ModuleRootManager.getInstance(getModule()).getSourceRoots();
    final VirtualFile suggested = LocalFileSystem.getInstance().findFileByPath(rootPath);
    final VirtualFile root;
    if (roots.length == 0 || roots[0].getParent() == null || Arrays.asList(roots).contains(suggested)) {
      // no real module in fixture
      root = suggested;
    } else {
      root = roots[0];
    }
    final VirtualFile[] virtualFile = new VirtualFile[1];
    final VirtualFile dir = VfsUtil.createDirectories(root.getPath() + "/" + StringUtil.getPackageName(relativePath, '/'));

    new WriteCommandAction.Simple(getProject()) {
      protected void run() throws Throwable {
        virtualFile[0] = dir.createChildData(this, StringUtil.getShortName(relativePath, '/'));
        VfsUtil.saveText(virtualFile[0], fileText);
      }
    }.execute();
    return PsiManager.getInstance(getProject()).findFile(virtualFile[0]);
  }
}
