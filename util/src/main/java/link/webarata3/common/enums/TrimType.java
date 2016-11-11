package link.webarata3.common.enums;

import link.webarata3.common.util.StringUtil;

/**
 * trimの方向を決めるenum
 * 
 * @author webarata3
 *
 */
public enum TrimType {
    NONE, BOTH, LEFT, RIGHT;

    /**
     * 指定のtrimをかける
     * 
     * @param value
     *            trim対象の文字列
     * @return trimした文字列
     */
    public String trim(String value) {
        switch (this) {
        case BOTH:
            return StringUtil.trim(value);
        case LEFT:
            return StringUtil.trimLeft(value);
        case RIGHT:
            return StringUtil.trimRight(value);
        case NONE:
            return value;
        default:
            return value;
        }
    }
}
