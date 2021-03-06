// Copyright 2000-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package git4idea.revert

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.util.containers.ContainerUtil
import com.intellij.vcs.log.VcsLogDataKeys
import com.intellij.vcs.log.util.VcsLogUtil.MAX_SELECTED_COMMITS
import git4idea.GitUtil.getRepositoryManager
import git4idea.config.GitVcsSettings
import git4idea.i18n.GitBundle

class GitRevertAction : DumbAwareAction() {
  override fun update(e: AnActionEvent) {
    super.update(e)

    val project = e.project
    val log = e.getData(VcsLogDataKeys.VCS_LOG)
    if (project == null || log == null) {
      e.presentation.isEnabledAndVisible = false
      return
    }
    val repositoryManager = getRepositoryManager(project)

    val commits = ContainerUtil.getFirstItems(log.selectedShortDetails, MAX_SELECTED_COMMITS)

    e.presentation.text = GitBundle.message("action.Git.Revert.In.Log.template.text", commits.size)

    if (commits.isEmpty()) {
      e.presentation.isEnabledAndVisible = false
      return
    }

    // commits from mixed roots
    if (commits.any { repositoryManager.getRepositoryForRootQuick(it.root) == null }) {
      e.presentation.isEnabledAndVisible = false
      return
    }

    // reverting merge commit is not allowed
    if (commits.any { it.parents.size > 1 }) {
      e.presentation.isVisible = true
      e.presentation.isEnabled = false
      e.presentation.description = GitBundle.message("action.description.cant.revert.merge.commit")
      return
    }

    e.presentation.isEnabledAndVisible = true
  }

  override fun actionPerformed(e: AnActionEvent) {
    val project = e.project!!
    val log = e.getRequiredData(VcsLogDataKeys.VCS_LOG)
    val repositoryManager = getRepositoryManager(project)

    log.requestSelectedDetails rsd@{ commits ->
      if (commits.any { repositoryManager.getRepositoryForRootQuick(it.root) == null }) return@rsd
      if (commits.any { it.parents.size > 1 }) return@rsd

      object : Task.Backgroundable(project, GitBundle.message("progress.title.reverting.n.commits", commits.size)) {
        override fun run(indicator: ProgressIndicator) {
          GitRevertOperation(project, commits, true).execute()
        }
      }.queue()
    }
  }
}
