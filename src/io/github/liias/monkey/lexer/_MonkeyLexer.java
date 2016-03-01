/* The following code was generated by JFlex 1.4.3 on 2.03.16 1:09 */

package io.github.liias.monkey.lexer;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import static io.github.liias.monkey.psi.MonkeyTypes.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 2.03.16 1:09 from the specification file
 * <tt>C:/Users/madis/Projects/monkey/src/io/github/liias/monkey/lexer/_MonkeyLexer.flex</tt>
 */
public class _MonkeyLexer implements FlexLexer {
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\1\1\2\1\1\1\1\1\1\22\0\1\1\1\70\1\16"+
    "\1\0\1\5\1\71\1\65\1\20\1\56\1\57\1\4\1\66\1\60"+
    "\1\67\1\13\1\3\1\10\11\6\1\23\1\21\1\62\1\64\1\63"+
    "\1\22\1\0\1\75\1\74\1\12\1\15\1\77\1\14\2\5\1\104"+
    "\2\5\1\7\2\5\1\103\1\107\1\100\1\76\1\105\1\5\1\106"+
    "\1\102\1\5\1\11\2\5\1\54\1\17\1\55\1\72\1\5\1\0"+
    "\1\26\1\47\1\24\1\43\1\37\1\30\1\44\1\46\1\34\1\5"+
    "\1\50\1\25\1\42\1\32\1\35\1\101\1\5\1\36\1\27\1\33"+
    "\1\31\1\41\1\40\1\45\1\51\1\5\1\52\1\61\1\53\1\73"+
    "\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\1\1\2\1\3\1\4\1\5\1\6\2\7\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\21\6\1\16\1\17"+
    "\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27"+
    "\1\30\1\31\1\32\1\33\1\34\1\35\1\36\1\37"+
    "\3\6\1\40\1\41\1\42\1\43\1\44\1\45\1\0"+
    "\1\1\1\46\1\47\1\1\1\0\1\50\3\0\1\51"+
    "\3\6\1\52\16\6\1\53\1\6\1\54\7\6\1\55"+
    "\4\6\1\56\1\57\1\60\1\61\1\62\1\63\1\64"+
    "\1\65\1\66\1\67\1\70\1\71\3\6\1\72\1\0"+
    "\4\6\1\73\5\6\1\74\3\6\1\75\1\6\1\76"+
    "\10\6\1\77\2\6\1\100\5\6\1\72\1\6\1\101"+
    "\12\6\1\102\1\103\1\6\1\104\2\6\1\105\1\106"+
    "\7\6\1\107\1\6\1\110\1\111\1\112\3\6\1\113"+
    "\2\6\1\114\1\6\1\115\3\6\1\116\3\6\1\117"+
    "\1\120\1\121\1\6\1\122\1\123\2\6\1\124\1\6"+
    "\1\125\1\6\1\126\1\6\1\127\2\6\1\130\1\6"+
    "\1\131\1\132\1\133\1\134\2\6\1\135";

  private static int [] zzUnpackAction() {
    int [] result = new int[228];
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
    "\0\0\0\110\0\220\0\330\0\u0120\0\u0168\0\u01b0\0\u01f8"+
    "\0\u0240\0\u0288\0\u02d0\0\110\0\110\0\110\0\u0318\0\u0360"+
    "\0\u03a8\0\u03f0\0\u0438\0\u0480\0\u04c8\0\u0510\0\u0558\0\u05a0"+
    "\0\u05e8\0\u0630\0\u0678\0\u06c0\0\u0708\0\u0750\0\u0798\0\110"+
    "\0\110\0\110\0\110\0\110\0\110\0\110\0\u07e0\0\110"+
    "\0\110\0\u0828\0\u0870\0\u08b8\0\u0900\0\u0948\0\u0990\0\110"+
    "\0\110\0\u09d8\0\u0a20\0\u0a68\0\u0ab0\0\u0af8\0\110\0\110"+
    "\0\110\0\110\0\u0240\0\110\0\110\0\u0b40\0\u0b88\0\u0288"+
    "\0\110\0\u0bd0\0\u02d0\0\u0c18\0\110\0\u0c60\0\u0ca8\0\u0cf0"+
    "\0\u0168\0\u0d38\0\u0d80\0\u0dc8\0\u0e10\0\u0e58\0\u0ea0\0\u0ee8"+
    "\0\u0f30\0\u0f78\0\u0fc0\0\u1008\0\u1050\0\u1098\0\u10e0\0\u0168"+
    "\0\u1128\0\u0168\0\u1170\0\u11b8\0\u1200\0\u1248\0\u1290\0\u12d8"+
    "\0\u1320\0\u0168\0\u1368\0\u13b0\0\u13f8\0\u1440\0\110\0\110"+
    "\0\110\0\110\0\110\0\110\0\110\0\110\0\110\0\110"+
    "\0\110\0\110\0\u1488\0\u14d0\0\u1518\0\u0af8\0\u1560\0\u15a8"+
    "\0\u15f0\0\u1638\0\u1680\0\u0168\0\u16c8\0\u1710\0\u1758\0\u17a0"+
    "\0\u17e8\0\u0168\0\u1830\0\u1878\0\u18c0\0\u0168\0\u1908\0\u0168"+
    "\0\u1950\0\u1998\0\u19e0\0\u1a28\0\u1a70\0\u1ab8\0\u1b00\0\u1b48"+
    "\0\u0168\0\u1b90\0\u1bd8\0\u0168\0\u1c20\0\u1c68\0\u1cb0\0\u1cf8"+
    "\0\u1d40\0\110\0\u1d88\0\u0168\0\u1dd0\0\u1e18\0\u1e60\0\u1ea8"+
    "\0\u1ef0\0\u1f38\0\u1f80\0\u1fc8\0\u2010\0\u2058\0\u0168\0\u0168"+
    "\0\u20a0\0\u0168\0\u20e8\0\u2130\0\u0168\0\u0168\0\u2178\0\u21c0"+
    "\0\u2208\0\u2250\0\u2298\0\u22e0\0\u2328\0\u0168\0\u2370\0\u0168"+
    "\0\u0168\0\u0168\0\u23b8\0\u2400\0\u2448\0\u0168\0\u2490\0\u24d8"+
    "\0\u0168\0\u2520\0\u0168\0\u2568\0\u25b0\0\u25f8\0\u0168\0\u2640"+
    "\0\u2688\0\u26d0\0\u0168\0\u0168\0\u0168\0\u2718\0\u0168\0\u0168"+
    "\0\u2760\0\u27a8\0\u0168\0\u27f0\0\u0168\0\u2838\0\u0168\0\u2880"+
    "\0\u0168\0\u28c8\0\u2910\0\u0168\0\u2958\0\u0168\0\u0168\0\u0168"+
    "\0\u0168\0\u29a0\0\u29e8\0\u0168";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[228];
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
    "\1\2\2\3\1\4\1\5\1\6\1\7\1\6\1\10"+
    "\2\6\1\11\2\6\1\12\1\2\1\13\1\14\1\15"+
    "\1\16\1\17\1\6\1\20\1\21\1\22\1\23\1\24"+
    "\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34"+
    "\1\35\2\6\1\36\1\37\2\6\1\40\1\41\1\42"+
    "\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52"+
    "\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62"+
    "\5\6\1\63\2\6\1\64\2\6\111\0\2\3\110\0"+
    "\1\65\1\66\57\0\1\67\26\0\1\70\60\0\1\71"+
    "\30\0\6\6\1\0\2\6\6\0\26\6\22\0\14\6"+
    "\6\0\1\7\1\72\1\7\2\0\1\73\1\74\1\75"+
    "\7\0\1\72\2\0\1\74\12\0\1\75\52\0\1\7"+
    "\1\72\1\7\1\76\1\0\1\73\1\74\1\75\7\0"+
    "\1\72\2\0\1\74\12\0\1\75\1\0\1\76\50\0"+
    "\1\77\1\0\1\77\77\0\16\100\1\101\1\102\70\100"+
    "\17\103\1\104\1\105\67\103\5\0\6\6\1\0\2\6"+
    "\6\0\1\6\1\106\1\107\6\6\1\110\14\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\3\6\1\111"+
    "\2\6\1\112\17\6\22\0\14\6\5\0\6\6\1\0"+
    "\2\6\6\0\7\6\1\113\4\6\1\114\11\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\2\6\1\115"+
    "\2\6\1\116\2\6\1\117\1\120\14\6\22\0\14\6"+
    "\5\0\6\6\1\0\2\6\6\0\3\6\1\121\22\6"+
    "\22\0\14\6\5\0\6\6\1\0\2\6\6\0\2\6"+
    "\1\122\2\6\1\123\5\6\1\124\12\6\22\0\14\6"+
    "\5\0\6\6\1\0\2\6\6\0\12\6\1\125\7\6"+
    "\1\126\2\6\1\127\22\0\14\6\5\0\6\6\1\0"+
    "\2\6\6\0\4\6\1\130\1\6\1\131\17\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\12\6\1\132"+
    "\13\6\22\0\14\6\5\0\6\6\1\0\2\6\6\0"+
    "\13\6\1\133\12\6\22\0\14\6\5\0\6\6\1\0"+
    "\2\6\6\0\1\6\1\134\4\6\1\135\12\6\1\136"+
    "\4\6\22\0\14\6\5\0\6\6\1\0\2\6\6\0"+
    "\22\6\1\137\3\6\22\0\14\6\5\0\6\6\1\0"+
    "\2\6\6\0\2\6\1\140\23\6\22\0\14\6\5\0"+
    "\6\6\1\0\2\6\6\0\11\6\1\141\14\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\11\6\1\142"+
    "\1\6\1\143\12\6\22\0\14\6\5\0\6\6\1\0"+
    "\2\6\6\0\2\6\1\144\5\6\1\145\15\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\12\6\1\146"+
    "\13\6\22\0\14\6\61\0\1\147\2\0\1\150\106\0"+
    "\1\151\1\152\107\0\1\153\1\154\106\0\1\155\1\0"+
    "\1\156\105\0\1\157\2\0\1\160\104\0\1\161\107\0"+
    "\1\162\30\0\6\6\1\0\2\6\6\0\26\6\22\0"+
    "\1\6\1\163\12\6\5\0\6\6\1\0\2\6\6\0"+
    "\26\6\22\0\7\6\1\164\4\6\5\0\6\6\1\0"+
    "\2\6\6\0\26\6\22\0\12\6\1\165\1\6\2\65"+
    "\1\0\105\65\4\166\1\167\103\166\6\0\1\76\1\0"+
    "\1\76\1\0\1\76\1\0\2\76\6\0\1\76\1\0"+
    "\1\76\1\0\1\76\6\0\1\76\3\0\1\76\3\0"+
    "\1\76\24\0\2\76\1\0\1\76\16\0\1\77\1\0"+
    "\1\77\3\0\1\74\1\75\12\0\1\74\12\0\1\75"+
    "\44\0\2\100\1\0\105\100\2\103\1\0\105\103\5\0"+
    "\6\6\1\0\2\6\6\0\2\6\1\170\23\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\3\6\1\171"+
    "\3\6\1\172\16\6\22\0\14\6\5\0\6\6\1\0"+
    "\2\6\6\0\6\6\1\173\17\6\22\0\14\6\5\0"+
    "\6\6\1\0\2\6\6\0\17\6\1\174\6\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\2\6\1\175"+
    "\23\6\22\0\14\6\5\0\6\6\1\0\2\6\6\0"+
    "\10\6\1\176\15\6\22\0\14\6\5\0\6\6\1\0"+
    "\2\6\6\0\1\6\1\177\24\6\22\0\14\6\5\0"+
    "\6\6\1\0\2\6\6\0\6\6\1\200\17\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\6\6\1\201"+
    "\17\6\22\0\14\6\5\0\6\6\1\0\2\6\6\0"+
    "\12\6\1\202\13\6\22\0\14\6\5\0\6\6\1\0"+
    "\2\6\6\0\10\6\1\203\15\6\22\0\14\6\5\0"+
    "\6\6\1\0\2\6\6\0\7\6\1\204\16\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\1\6\1\205"+
    "\24\6\22\0\14\6\5\0\6\6\1\0\2\6\6\0"+
    "\14\6\1\206\11\6\22\0\14\6\5\0\6\6\1\0"+
    "\2\6\6\0\5\6\1\207\17\6\1\210\22\0\14\6"+
    "\5\0\6\6\1\0\2\6\6\0\12\6\1\211\13\6"+
    "\22\0\14\6\5\0\6\6\1\0\2\6\6\0\26\6"+
    "\22\0\5\6\1\212\6\6\5\0\6\6\1\0\2\6"+
    "\6\0\3\6\1\213\22\6\22\0\14\6\5\0\6\6"+
    "\1\0\2\6\6\0\7\6\1\214\16\6\22\0\14\6"+
    "\5\0\6\6\1\0\2\6\6\0\3\6\1\215\22\6"+
    "\22\0\14\6\5\0\6\6\1\0\2\6\6\0\5\6"+
    "\1\216\20\6\22\0\14\6\5\0\6\6\1\0\2\6"+
    "\6\0\7\6\1\217\16\6\22\0\14\6\5\0\6\6"+
    "\1\0\2\6\6\0\10\6\1\220\15\6\22\0\14\6"+
    "\5\0\6\6\1\0\2\6\6\0\12\6\1\221\13\6"+
    "\22\0\14\6\5\0\6\6\1\0\2\6\6\0\17\6"+
    "\1\222\6\6\22\0\14\6\5\0\6\6\1\0\2\6"+
    "\6\0\4\6\1\223\21\6\22\0\14\6\5\0\6\6"+
    "\1\0\2\6\6\0\3\6\1\224\22\6\22\0\14\6"+
    "\5\0\6\6\1\0\2\6\6\0\17\6\1\225\6\6"+
    "\22\0\14\6\5\0\6\6\1\0\2\6\6\0\13\6"+
    "\1\226\12\6\22\0\14\6\5\0\6\6\1\0\2\6"+
    "\6\0\26\6\22\0\2\6\1\227\11\6\5\0\6\6"+
    "\1\0\2\6\6\0\26\6\22\0\10\6\1\230\3\6"+
    "\5\0\6\6\1\0\2\6\6\0\26\6\22\0\13\6"+
    "\1\231\3\166\1\232\1\167\103\166\5\0\6\6\1\0"+
    "\2\6\6\0\3\6\1\233\22\6\22\0\14\6\5\0"+
    "\6\6\1\0\2\6\6\0\13\6\1\234\12\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\1\235\25\6"+
    "\22\0\14\6\5\0\6\6\1\0\2\6\6\0\3\6"+
    "\1\236\3\6\1\237\16\6\22\0\14\6\5\0\6\6"+
    "\1\0\2\6\6\0\7\6\1\240\16\6\22\0\14\6"+
    "\5\0\6\6\1\0\2\6\6\0\7\6\1\241\16\6"+
    "\22\0\14\6\5\0\6\6\1\0\2\6\6\0\3\6"+
    "\1\242\22\6\22\0\14\6\5\0\6\6\1\0\2\6"+
    "\6\0\1\243\25\6\22\0\14\6\5\0\6\6\1\0"+
    "\2\6\6\0\2\6\1\244\23\6\22\0\14\6\5\0"+
    "\6\6\1\0\2\6\6\0\6\6\1\245\17\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\10\6\1\246"+
    "\15\6\22\0\14\6\5\0\6\6\1\0\2\6\6\0"+
    "\1\6\1\247\24\6\22\0\14\6\5\0\6\6\1\0"+
    "\2\6\6\0\13\6\1\250\12\6\22\0\14\6\5\0"+
    "\6\6\1\0\2\6\6\0\11\6\1\251\14\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\13\6\1\252"+
    "\12\6\22\0\14\6\5\0\6\6\1\0\2\6\6\0"+
    "\7\6\1\253\16\6\22\0\14\6\5\0\6\6\1\0"+
    "\2\6\6\0\5\6\1\254\20\6\22\0\14\6\5\0"+
    "\6\6\1\0\2\6\6\0\13\6\1\255\12\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\16\6\1\256"+
    "\7\6\22\0\14\6\5\0\6\6\1\0\2\6\6\0"+
    "\13\6\1\257\12\6\22\0\14\6\5\0\6\6\1\0"+
    "\2\6\6\0\1\6\1\260\24\6\22\0\14\6\5\0"+
    "\6\6\1\0\2\6\6\0\5\6\1\261\20\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\2\6\1\262"+
    "\23\6\22\0\14\6\5\0\6\6\1\0\2\6\6\0"+
    "\17\6\1\263\6\6\22\0\14\6\5\0\6\6\1\0"+
    "\2\6\6\0\2\6\1\264\23\6\22\0\14\6\5\0"+
    "\6\6\1\0\2\6\6\0\26\6\22\0\3\6\1\265"+
    "\10\6\5\0\6\6\1\0\1\6\1\266\6\0\26\6"+
    "\22\0\14\6\5\0\6\6\1\0\2\6\6\0\26\6"+
    "\22\0\3\6\1\267\10\6\5\0\6\6\1\0\2\6"+
    "\6\0\3\6\1\270\22\6\22\0\14\6\5\0\6\6"+
    "\1\0\2\6\6\0\22\6\1\271\3\6\22\0\14\6"+
    "\5\0\6\6\1\0\2\6\6\0\7\6\1\272\16\6"+
    "\22\0\14\6\5\0\6\6\1\0\2\6\6\0\10\6"+
    "\1\273\15\6\22\0\14\6\5\0\6\6\1\0\2\6"+
    "\6\0\10\6\1\274\15\6\22\0\14\6\5\0\6\6"+
    "\1\0\2\6\6\0\1\275\25\6\22\0\14\6\5\0"+
    "\6\6\1\0\2\6\6\0\13\6\1\276\12\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\7\6\1\277"+
    "\16\6\22\0\14\6\5\0\6\6\1\0\2\6\6\0"+
    "\1\6\1\300\24\6\22\0\14\6\5\0\6\6\1\0"+
    "\2\6\6\0\20\6\1\301\5\6\22\0\14\6\5\0"+
    "\6\6\1\0\2\6\6\0\15\6\1\302\10\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\14\6\1\303"+
    "\11\6\22\0\14\6\5\0\6\6\1\0\2\6\6\0"+
    "\2\6\1\304\23\6\22\0\14\6\5\0\6\6\1\0"+
    "\2\6\6\0\12\6\1\305\13\6\22\0\14\6\5\0"+
    "\6\6\1\0\2\6\6\0\6\6\1\306\17\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\13\6\1\307"+
    "\12\6\22\0\14\6\5\0\6\6\1\0\2\6\6\0"+
    "\1\6\1\310\24\6\22\0\14\6\5\0\6\6\1\0"+
    "\2\6\6\0\5\6\1\311\20\6\22\0\14\6\5\0"+
    "\6\6\1\0\2\6\6\0\13\6\1\312\12\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\24\6\1\313"+
    "\1\6\22\0\14\6\5\0\6\6\1\0\2\6\6\0"+
    "\26\6\22\0\4\6\1\314\7\6\5\0\6\6\1\0"+
    "\2\6\6\0\26\6\22\0\2\6\1\315\11\6\5\0"+
    "\6\6\1\0\2\6\6\0\6\6\1\316\17\6\22\0"+
    "\14\6\5\0\6\6\1\0\2\6\6\0\1\317\25\6"+
    "\22\0\14\6\5\0\6\6\1\0\2\6\6\0\22\6"+
    "\1\320\3\6\22\0\14\6\5\0\6\6\1\0\2\6"+
    "\6\0\10\6\1\321\15\6\22\0\14\6\5\0\6\6"+
    "\1\0\2\6\6\0\1\6\1\322\24\6\22\0\14\6"+
    "\5\0\6\6\1\0\2\6\6\0\13\6\1\323\12\6"+
    "\22\0\14\6\5\0\6\6\1\0\2\6\6\0\6\6"+
    "\1\324\17\6\22\0\14\6\5\0\6\6\1\0\2\6"+
    "\6\0\6\6\1\325\17\6\22\0\14\6\5\0\6\6"+
    "\1\0\2\6\6\0\17\6\1\326\6\6\22\0\14\6"+
    "\5\0\6\6\1\0\2\6\6\0\13\6\1\327\12\6"+
    "\22\0\14\6\5\0\6\6\1\0\2\6\6\0\1\6"+
    "\1\330\24\6\22\0\14\6\5\0\6\6\1\0\2\6"+
    "\6\0\6\6\1\331\17\6\22\0\14\6\5\0\6\6"+
    "\1\0\2\6\6\0\5\6\1\332\20\6\22\0\14\6"+
    "\5\0\6\6\1\0\2\6\6\0\11\6\1\333\14\6"+
    "\22\0\14\6\5\0\6\6\1\0\2\6\6\0\25\6"+
    "\1\334\22\0\14\6\5\0\6\6\1\0\2\6\6\0"+
    "\1\335\25\6\22\0\14\6\5\0\6\6\1\0\2\6"+
    "\6\0\3\6\1\336\22\6\22\0\14\6\5\0\6\6"+
    "\1\0\2\6\6\0\7\6\1\337\16\6\22\0\14\6"+
    "\5\0\6\6\1\0\2\6\6\0\13\6\1\340\12\6"+
    "\22\0\14\6\5\0\6\6\1\0\2\6\6\0\6\6"+
    "\1\341\17\6\22\0\14\6\5\0\6\6\1\0\2\6"+
    "\6\0\13\6\1\342\12\6\22\0\14\6\5\0\6\6"+
    "\1\0\2\6\6\0\11\6\1\343\14\6\22\0\14\6"+
    "\5\0\6\6\1\0\2\6\6\0\4\6\1\344\21\6"+
    "\22\0\14\6";

  private static int [] zzUnpackTrans() {
    int [] result = new int[10800];
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
    "\1\1\1\11\11\1\3\11\21\1\7\11\1\1\2\11"+
    "\6\1\2\11\5\1\4\11\1\0\2\11\2\1\1\0"+
    "\1\11\3\0\1\11\41\1\14\11\4\1\1\0\42\1"+
    "\1\11\112\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[228];
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

  /* user code: */
  public _MonkeyLexer() {
    this((java.io.Reader)null);
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public _MonkeyLexer(java.io.Reader in) {
    this.zzReader = in;
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
    while (i < 182) {
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

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL)
            zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 6: 
          { return IDENTIFIER;
          }
        case 94: break;
        case 58: 
          { return BLOCK_COMMENT;
          }
        case 95: break;
        case 62: 
          { return TRY;
          }
        case 96: break;
        case 18: 
          { return LPAREN;
          }
        case 97: break;
        case 59: 
          { return AND;
          }
        case 98: break;
        case 78: 
          { return WHILE;
          }
        case 99: break;
        case 54: 
          { return SUBEQ;
          }
        case 100: break;
        case 74: 
          { return CONST;
          }
        case 101: break;
        case 67: 
          { return TRUE;
          }
        case 102: break;
        case 17: 
          { return RBRACKET;
          }
        case 103: break;
        case 29: 
          { return PERCENT;
          }
        case 104: break;
        case 82: 
          { return STATIC;
          }
        case 105: break;
        case 50: 
          { return AMPEQ;
          }
        case 106: break;
        case 13: 
          { return COLON;
          }
        case 107: break;
        case 10: 
          { return STRING_B;
          }
        case 108: break;
        case 25: 
          { return AMP;
          }
        case 109: break;
        case 79: 
          { return BREAK;
          }
        case 110: break;
        case 24: 
          { return EQ;
          }
        case 111: break;
        case 71: 
          { return VOID;
          }
        case 112: break;
        case 38: 
          { return DOUBLELITERAL;
          }
        case 113: break;
        case 64: 
          { return HAS;
          }
        case 114: break;
        case 21: 
          { return BAR;
          }
        case 115: break;
        case 89: 
          { return EXTENDS;
          }
        case 116: break;
        case 56: 
          { return BANGEQ;
          }
        case 117: break;
        case 53: 
          { return PLUSPLUS;
          }
        case 118: break;
        case 30: 
          { return CARET;
          }
        case 119: break;
        case 85: 
          { return RETURN;
          }
        case 120: break;
        case 81: 
          { return SUPER;
          }
        case 121: break;
        case 3: 
          { return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 122: break;
        case 76: 
          { return USING;
          }
        case 123: break;
        case 9: 
          { return STRING_A;
          }
        case 124: break;
        case 16: 
          { return LBRACKET;
          }
        case 125: break;
        case 46: 
          { return BARBAR;
          }
        case 126: break;
        case 73: 
          { return CATCH;
          }
        case 127: break;
        case 20: 
          { return COMMA;
          }
        case 128: break;
        case 37: 
          { return LONGLITERAL;
          }
        case 129: break;
        case 86: 
          { return MODULE;
          }
        case 130: break;
        case 26: 
          { return PLUS;
          }
        case 131: break;
        case 84: 
          { return NATIVE;
          }
        case 132: break;
        case 68: 
          { return TYPE;
          }
        case 133: break;
        case 52: 
          { return PLUSEQ;
          }
        case 134: break;
        case 49: 
          { return EQEQ;
          }
        case 135: break;
        case 66: 
          { return NULL;
          }
        case 136: break;
        case 22: 
          { return LT;
          }
        case 137: break;
        case 83: 
          { return SWITCH;
          }
        case 138: break;
        case 34: 
          { return SLASHEQ;
          }
        case 139: break;
        case 72: 
          { return CLASS;
          }
        case 140: break;
        case 70: 
          { return ENUM;
          }
        case 141: break;
        case 8: 
          { return DOT;
          }
        case 142: break;
        case 33: 
          { return MULTI_LINE_COMMENT_START;
          }
        case 143: break;
        case 90: 
          { return DEFAULT;
          }
        case 144: break;
        case 45: 
          { return DO;
          }
        case 145: break;
        case 47: 
          { return CARETEQ;
          }
        case 146: break;
        case 7: 
          { return INTLITERAL;
          }
        case 147: break;
        case 11: 
          { return SEMI;
          }
        case 148: break;
        case 75: 
          { return FALSE;
          }
        case 149: break;
        case 15: 
          { return RBRACE;
          }
        case 150: break;
        case 80: 
          { return BAREQ;
          }
        case 151: break;
        case 61: 
          { return NEW;
          }
        case 152: break;
        case 23: 
          { return GT;
          }
        case 153: break;
        case 41: 
          { return CHARLITERAL;
          }
        case 154: break;
        case 51: 
          { return AMPAMP;
          }
        case 155: break;
        case 27: 
          { return SUB;
          }
        case 156: break;
        case 35: 
          { return MULTI_LINE_COMMENT_END;
          }
        case 157: break;
        case 87: 
          { return HIDDEN;
          }
        case 158: break;
        case 57: 
          { return PERCENTEQ;
          }
        case 159: break;
        case 91: 
          { return CONTINUE;
          }
        case 160: break;
        case 44: 
          { return OR;
          }
        case 161: break;
        case 14: 
          { return LBRACE;
          }
        case 162: break;
        case 31: 
          { return TILDE;
          }
        case 163: break;
        case 63: 
          { return VAR;
          }
        case 164: break;
        case 92: 
          { return FUNCTION;
          }
        case 165: break;
        case 4: 
          { return SLASH;
          }
        case 166: break;
        case 77: 
          { return THROW;
          }
        case 167: break;
        case 69: 
          { return ELSE;
          }
        case 168: break;
        case 40: 
          { return STRING;
          }
        case 169: break;
        case 43: 
          { return IF;
          }
        case 170: break;
        case 5: 
          { return STAR;
          }
        case 171: break;
        case 93: 
          { return INSTANCEOF;
          }
        case 172: break;
        case 55: 
          { return SUBSUB;
          }
        case 173: break;
        case 32: 
          { return SINGLE_LINE_COMMENT;
          }
        case 174: break;
        case 88: 
          { return FINALLY;
          }
        case 175: break;
        case 2: 
          { return com.intellij.psi.TokenType.BAD_CHARACTER;
          }
        case 176: break;
        case 1: 
          { return FLOATLITERAL;
          }
        case 177: break;
        case 60: 
          { return FOR;
          }
        case 178: break;
        case 48: 
          { return EQGT;
          }
        case 179: break;
        case 65: 
          { return CASE;
          }
        case 180: break;
        case 19: 
          { return RPAREN;
          }
        case 181: break;
        case 36: 
          { return STAREQ;
          }
        case 182: break;
        case 28: 
          { return BANG;
          }
        case 183: break;
        case 12: 
          { return QUES;
          }
        case 184: break;
        case 42: 
          { return AS;
          }
        case 185: break;
        case 39: 
          { return HEX_LITERAL;
          }
        case 186: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return null;
          }
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
