/* The following code was generated by JFlex 1.4.1 on 11/5/09 5:29 PM */

package org.jetbrains.yaml.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.yaml.YAMLTokenTypes;

/* Auto generated File */

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 11/5/09 5:29 PM from the specification file
 * <tt>/home/oleg/work/IDEA/tools/lexer/../../plugins/yaml/src/org/jetbrains/yaml/lexer/yaml.flex</tt>
 */
public class _YAMLLexer implements FlexLexer, YAMLTokenTypes {
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int VALUE_OR_KEY = 2;
  public static final int YYINITIAL = 0;
  public static final int VALUE = 1;
  public static final int INDENT_VALUE = 3;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\2\1\1\25\0\1\6\1\0\1\11\1\3\3\0\1\12"+
    "\3\0\1\24\1\17\1\21\2\0\12\5\1\7\3\0\1\22\1\20"+
    "\1\0\32\4\1\15\1\10\1\16\1\0\1\4\1\0\32\4\1\13"+
    "\1\23\1\14\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\4\0\1\1\1\2\1\3\1\4\1\1\1\5\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\2\1\1\14\1\5"+
    "\1\15\1\16\1\7\1\11\1\13\3\1\1\17\1\20"+
    "\1\21\1\0\1\22\2\23\2\0\1\15\1\1\1\15"+
    "\1\16\1\1\1\16\2\24\2\25\2\0\1\22\1\26"+
    "\1\0\1\15\1\0\1\16";

  private static int [] zzUnpackAction() {
    int [] result = new int[55];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\25\0\52\0\77\0\124\0\124\0\151\0\176"+
    "\0\223\0\124\0\124\0\124\0\124\0\124\0\124\0\124"+
    "\0\250\0\275\0\322\0\275\0\347\0\374\0\275\0\275"+
    "\0\275\0\u0111\0\u0126\0\u013b\0\u0150\0\124\0\u0165\0\223"+
    "\0\u017a\0\124\0\u018f\0\u01a4\0\275\0\u01b9\0\u01ce\0\275"+
    "\0\u01e3\0\u01f8\0\u020d\0\124\0\u0222\0\124\0\u0237\0\u024c"+
    "\0\u013b\0\u017a\0\124\0\u0261\0\124\0\u0276\0\u028b";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[55];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\5\1\6\1\7\1\10\1\11\1\5\1\7\1\12"+
    "\3\5\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
    "\3\5\1\22\1\6\1\23\1\10\2\22\1\23\1\24"+
    "\1\22\1\25\1\26\1\13\1\27\1\15\1\30\1\17"+
    "\1\31\1\22\1\32\1\33\2\22\1\6\1\23\1\10"+
    "\1\34\1\22\1\23\1\24\1\22\1\25\1\26\1\13"+
    "\1\27\1\15\1\30\1\17\1\31\1\22\1\32\1\33"+
    "\1\22\1\35\1\36\1\37\3\35\1\37\16\35\27\0"+
    "\1\7\3\0\1\7\16\0\1\10\1\0\23\10\4\0"+
    "\3\40\1\41\11\0\1\40\4\0\1\42\1\43\3\0"+
    "\1\43\12\0\1\44\3\0\1\22\1\0\1\45\1\0"+
    "\2\22\1\45\5\22\1\0\1\22\2\0\5\22\2\0"+
    "\1\23\3\0\1\23\16\0\1\25\1\46\1\25\1\46"+
    "\4\25\1\47\1\50\2\25\1\46\1\25\2\46\5\25"+
    "\1\26\1\51\1\26\1\51\4\26\1\52\1\26\1\53"+
    "\1\26\1\51\1\26\2\51\5\26\1\0\1\54\1\55"+
    "\3\0\1\55\17\0\1\56\1\57\3\0\1\57\12\0"+
    "\1\60\2\0\1\60\1\22\1\0\1\45\1\0\2\34"+
    "\1\61\1\41\4\22\1\0\1\22\2\0\1\22\1\34"+
    "\3\22\1\35\1\0\23\35\2\0\1\37\3\0\1\37"+
    "\16\0\1\62\1\0\23\62\2\0\1\43\3\0\1\43"+
    "\37\0\1\63\3\0\10\46\1\64\1\65\13\46\1\25"+
    "\1\0\1\25\1\46\10\25\1\46\1\25\2\46\5\25"+
    "\10\51\1\66\1\51\1\67\12\51\1\26\1\0\1\26"+
    "\1\51\10\26\1\51\1\26\2\51\5\26\1\22\1\0"+
    "\1\45\1\0\2\22\1\45\3\22\1\26\1\22\1\0"+
    "\1\22\2\0\5\22\2\0\1\55\3\0\1\55\20\0"+
    "\1\57\3\0\1\57\17\0\1\56\1\57\3\0\1\57"+
    "\16\0\1\46\1\0\23\46\1\51\1\0\23\51\12\0"+
    "\1\51\12\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[672];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;
  private static final char[] EMPTY_BUFFER = new char[0];
  private static final int YYEOF = -1;
  private static java.io.Reader zzReader = null; // Fake

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\4\0\2\11\3\1\7\11\1\3\10\1\2\3\2\1"+
    "\1\11\1\1\1\0\1\7\1\15\1\5\2\0\6\1"+
    "\1\15\1\5\1\15\1\5\1\2\1\0\1\5\1\11"+
    "\1\0\1\11\1\0\1\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[55];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** this buffer may contains the current text array to be matched when it is cheap to acquire it */
  private char[] zzBufferArray;

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  private int valueIndent = 0;
  private boolean afterEOL = false;
  private IElementType valueTokenType = null;
    
  private char previousChar() {
    return getChar(-1);
  }

  private char getChar(final int offset) {
    final int loc = getTokenStart()  + offset;
    return 0 <= loc && loc < zzBuffer.length() ? zzBuffer.charAt(loc) : (char) -1;
  }



  public _YAMLLexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public _YAMLLexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 66) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart(){
    return zzStartRead;
  }

  public final int getTokenEnd(){
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end,int initialState){
    zzBuffer = buffer;
    zzBufferArray = com.intellij.util.text.CharArrayUtil.fromSequenceWithoutCopying(buffer);
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzPushbackPos = 0;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  // For Demetra compatibility
  public void reset(CharSequence buffer, int initialState){
    zzBuffer = buffer;
    zzBufferArray = null; 
    zzCurrentPos = zzMarkedPos = zzStartRead = 0;
    zzPushbackPos = 0;
    zzAtEOF = false;
    zzAtBOL = true;
    zzEndRead = buffer.length();
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position <tt>pos</tt> from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBufferArray != null ? zzBufferArray[zzStartRead+pos]:zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;
    char[] zzBufferArrayL = zzBufferArray;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;
    int zzPushbackPosL = zzPushbackPos = -1;
    boolean zzWasPushback;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = zzLexicalState;

      zzWasPushback = false;

      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++]:zzBufferL.charAt(zzCurrentPosL++);
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            zzPushbackPos = zzPushbackPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            zzPushbackPosL = zzPushbackPos;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++]:zzBufferL.charAt(zzCurrentPosL++);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 2) == 2 )
            zzPushbackPosL = zzCurrentPosL;

          if ( (zzAttributes & 1) == 1 ) {
            zzWasPushback = (zzAttributes & 4) == 4;
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;
      if (zzWasPushback)
        zzMarkedPos = zzPushbackPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 22: 
          { return DOCUMENT_MARKER;
          }
        case 23: break;
        case 11: 
          { yybegin(YYINITIAL);
                                    return QUESTION;
          }
        case 24: break;
        case 20: 
          { yybegin(INDENT_VALUE);
                                    //System.out.println("Started SCALAR_TEXT state");
                                    valueIndent = 0; // initialization
                                    afterEOL = false;
                                    valueTokenType = SCALAR_TEXT;
                                    yypushback(1);
          }
        case 25: break;
        case 4: 
          { return COMMENT;
          }
        case 26: break;
        case 16: 
          { afterEOL = true;
                                            //System.out.println("Matched EOL:");
                                            if (valueIndent < 0) {
                                                yybegin(YYINITIAL);
                                                //System.out.println("return to initial state");
                                            }
                                            else if (valueIndent == 0) {
                                                valueIndent --;
                                            }
                                            return EOL;
          }
        case 27: break;
        case 21: 
          { yybegin(INDENT_VALUE);
                                    //System.out.println("Started SCALAR_LIST state");
                                    valueIndent = 0; // initialization
                                    afterEOL = false;
                                    valueTokenType = SCALAR_LIST;
                                    yypushback(yylength());
          }
        case 28: break;
        case 14: 
          { yybegin(YYINITIAL);
                                    return SCALAR_STRING;
          }
        case 29: break;
        case 8: 
          { yybegin(YYINITIAL);
                                    return LBRACKET;
          }
        case 30: break;
        case 15: 
          { if (afterEOL){
                                                yypushback(yylength());
                                                yybegin(YYINITIAL);
                                                //System.out.println("return to initial state");

                                            } else {
                                                afterEOL = false;
                                                if (valueIndent < 0) {
                                                    //System.out.println("Matched TEXT:" + yytext());
                                                    return TEXT;
                                                }
                                                //System.out.println("Matched ValueContext:" + yytext());
                                                return valueTokenType;
                                            }
          }
        case 31: break;
        case 10: 
          { yybegin(YYINITIAL);
                                    return COMMA;
          }
        case 32: break;
        case 6: 
          { yybegin(YYINITIAL);
                                    return LBRACE;
          }
        case 33: break;
        case 2: 
          { yybegin(YYINITIAL);
                                    return EOL;
          }
        case 34: break;
        case 7: 
          { return RBRACE;
          }
        case 35: break;
        case 17: 
          { afterEOL = false;
                                            //System.out.println("Matched WHITESPACE:" + yytext());
                                            final int matched = yylength();
                                            if (valueIndent < 0){
                                                valueIndent = matched;
                                                //System.out.println("Indent selected:" + valueIndent);
                                            }
                                            else if (valueIndent > matched) {
                                                yybegin(YYINITIAL);
                                                //System.out.println("return to initial state");
                                            }
                                            return previousChar() == '\n' ? INDENT : WHITESPACE;
          }
        case 36: break;
        case 1: 
          { return TEXT;
          }
        case 37: break;
        case 3: 
          { final char prev = previousChar();
                                    return prev == (char)-1 || prev == '\n' ? INDENT : WHITESPACE;
          }
        case 38: break;
        case 5: 
          { yybegin(YYINITIAL);
                                    return COLON;
          }
        case 39: break;
        case 13: 
          { yybegin(YYINITIAL);
                                    return SCALAR_DSTRING;
          }
        case 40: break;
        case 19: 
          { yybegin(VALUE_OR_KEY);
                                    return SEQUENCE_MARKER;
          }
        case 41: break;
        case 18: 
          { yybegin(VALUE);
                                    return SCALAR_KEY;
          }
        case 42: break;
        case 9: 
          { return RBRACKET;
          }
        case 43: break;
        case 12: 
          { return WHITESPACE;
          }
        case 44: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
            return null;
          }
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
