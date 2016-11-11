package link.webarata3.common.enums;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TrimTypeTest {
    @Test
    public void trimType_NONEの場合() throws Exception {
        assertThat(TrimType.NONE.trim(" あいうえお "), is(" あいうえお "));
    }

    @Test
    public void trimType_BOTHの場合() throws Exception {
        assertThat(TrimType.BOTH.trim(" あいうえお "), is("あいうえお"));
    }

    @Test
    public void trimType_LEFTの場合() throws Exception {
        assertThat(TrimType.LEFT.trim(" あいうえお "), is("あいうえお "));
    }

    @Test
    public void trimType_RIGHTの場合() throws Exception {
        assertThat(TrimType.RIGHT.trim(" あいうえお "), is(" あいうえお"));
    }
}
