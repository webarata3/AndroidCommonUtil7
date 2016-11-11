package link.webarata3.common.enums;

/**
 * 改行のタイプのEnum
 * 
 * @author webarata3
 *
 */
public enum LineBreakType {
    CR("\r"), LF("\n"), CRLF("\r\n"), NONE("");

    private String physicalString;

    /**
     * 改行コードの実際の文字列を取得する
     * 
     * @return 改行コードの実際の文字列
     */
    public String getPhysicalString() {
        return physicalString;
    }

    private LineBreakType(String physicalString) {
        this.physicalString = physicalString;
    }
}
