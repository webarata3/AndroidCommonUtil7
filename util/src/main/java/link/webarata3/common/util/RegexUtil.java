package link.webarata3.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正規表現用のユーティリティ
 * 
 * @author webarata3
 */
public abstract class RegexUtil {
    /** 全角カタカナのパターン */
    public static final String EM_KATAKANA = "\\u30A0-\\u30FF";

    /** 全角ブランクのパターン */
    public static final String EM_BLANK = "　";

    /** 改行文字のパターン */
    public static final String LINE_BREAK = "\r\n";

    private static final Map<String, Pattern> PATTERN_CASH = new HashMap<>();

    /**
     * コンパイルされたPatternオブジェクトを取得する。<br>
     * 一度作成したパターンはキャッシュされる
     * 
     * @param regex
     *            パターン
     * @return 取得したパターン
     */
    public static synchronized Pattern getPattern(String regex) {
        Pattern pattern = PATTERN_CASH.get(regex);
        if (pattern == null) {
            pattern = Pattern.compile(regex);
            PATTERN_CASH.put(regex, pattern);
        }
        return pattern;
    }

    /**
     * 検査する文字列がパターンに合致するかを調べる
     * 
     * @param regex
     *            パターン
     * @param input
     *            検査する文字列
     * @return パターンにinputが合致する場合true
     */
    public static boolean find(String regex, CharSequence input) {
        Pattern pattern = getPattern(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    /**
     * パターンに完全一致するかをチェックする
     * 
     * @param regex
     *            パターン（自動で先頭に ^[ 、最後に *]$ がつく）
     * @param input
     *            検査する文字列
     * @return パターンにinputが合致する場合true
     */
    public static boolean perfectMatch(String regex, CharSequence input) {
        return find("^[" + regex + "]*$", input);
    }
}
