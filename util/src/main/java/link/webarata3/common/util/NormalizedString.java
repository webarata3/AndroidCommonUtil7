package link.webarata3.common.util;

import java.text.Normalizer;

/**
 * 正規化された文字列を扱うクラス
 * 
 * @author webarata3
 *
 */
public class NormalizedString {
    private final String originalString;
    private final String normalizedString;

    /**
     * コンストラクタ
     * 
     * @param originalString
     *            オリジナルの文字列
     */
    public NormalizedString(String originalString) {
        this.originalString = originalString;
        this.normalizedString = Normalizer.normalize(this.originalString, Normalizer.Form.NFC);
    }

    /**
     * オリジナルの文字列を返す
     * 
     * @return オリジナルの文字列
     */
    public String getOriginalString() {
        return originalString;
    }

    /**
     * 文字数を返す
     * 
     * @return 文字数
     */
    public int length() {
        return normalizedString.codePointCount(0, normalizedString.length());
    }

    /**
     * 指定された位置の文字を返す
     * 
     * @param index
     *            インデックス
     * @return 文字
     * @throws IndexOutOfBoundsException
     *             文字列の範囲外へのアクセスの場合
     */
    public String charAt(int index) {
        if (index <= -1 || index >= length()) {
            throw new IndexOutOfBoundsException("index: " + index);
        }

        int codePoint;
        for (int i = 0; i < originalString.length(); i++) {
            codePoint = normalizedString.codePointAt(i);
            if (i == index) {
                break;
            }
            if (codePoint > 0xFFFF) {
                index++;
            }
        }
        return new String(new int[] { normalizedString.codePointAt(index) }, 0, 1);
    }
}
