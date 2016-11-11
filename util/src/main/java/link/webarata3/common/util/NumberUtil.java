package link.webarata3.common.util;

/**
 * 数値関連のユーティリティクラス
 * 
 * @author webarata3
 *
 */
public abstract class NumberUtil {
    /**
     * 文字列をIntegerに変換する。変換出来ない場合にはnullを返す。
     * 
     * @param value
     *            変換する文字列
     * @return 変換した数値
     */
    public static Integer formatInt(String value) {
        if (value == null) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
