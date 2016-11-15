package link.webarata3.dro.common.util7;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import link.webarata3.dro.common.util7.enums.LineBreakType;

/**
 * String関連のユーティリティクラス
 * 
 * @author webarata3
 */
public abstract class StringUtil {
    /**
     * 文字列がnullか空文字列かをチェックする
     * 
     * @param value
     *            検査する文字列
     * @return 文字列がnullもしくは空文字列の場合true
     */
    public static boolean isEmpty(@Nullable String value) {
        return value == null || (value.length() == 0);
    }

    /**
     * 文字列がnullや空文字でないことをチェックする
     * 
     * @param value
     *            検査する文字列
     * @return 文字列がnullもしくは空文字列でない場合true
     */
    public static boolean isNotEmpty(@Nullable String value) {
        return !isEmpty(value);
    }

    /**
     * 文字列の左側の空白とそれに準ずる文字の連続を取り除く
     * 
     * @param value
     *            対象の文字列
     * @return 左側をtrimした結果の文字列
     */
    @Nullable
    public static String trimLeft(@Nullable  String value) {
        if (isEmpty(value)) return value;

        int i = 0;
        for (; i < value.length(); i++) {
            if (!Character.isWhitespace(value.charAt(i))) {
                break;
            }
        }

        return value.substring(i);
    }

    /**
     * 文字列の右側の空白とそれに準ずる文字の連続を取り除く
     * 
     * @param value
     *            対象の文字列
     * @return 右側をtrimした結果の文字列
     */
    @Nullable
    public static String trimRight(@Nullable String value) {
        if (isEmpty(value)) return value;

        int i = value.length() - 1;
        for (; i >= 0; i--) {
            if (!Character.isWhitespace(value.charAt(i))) {
                break;
            }
        }

        return value.substring(0, i + 1);
    }

    /**
     * 文字列の両端の空白とそれに準ずる文字の連続を取り除く
     * 
     * @param value
     *            対象の文字列
     * @return 右側をtrimした結果の文字列
     */
    @Nullable
    public static String trim(@Nullable String value) {
        String trimValue = trimLeft(value);
        return trimRight(trimValue);
    }

    /**
     * 改行文字を指定の改行文字に正規化する
     * 
     * @param value
     *            対象の文字列
     * @param lineBreakType
     *            改行文字
     * @return 改行を正規化した文字列
     */
    @Nullable
    public static String normalizeLineBreak(
            @Nullable String value, @NonNull LineBreakType lineBreakType) {
        if (isEmpty(value)) return value;
        // LFに正規化してから、指定の改行コードに正規化する
        value = value.replace(LineBreakType.CRLF.getPhysicalString(), LineBreakType.LF.getPhysicalString());
        value = value.replace(LineBreakType.CR.getPhysicalString(), LineBreakType.LF.getPhysicalString());

        switch (lineBreakType) {
        case CRLF:
            value = value.replace(LineBreakType.LF.getPhysicalString(), LineBreakType.CRLF.getPhysicalString());
            break;
        case CR:
            value = value.replace(LineBreakType.LF.getPhysicalString(), LineBreakType.CR.getPhysicalString());
            break;
        case LF:
            break;
        default:
            break;
        }

        return value;
    }
}
