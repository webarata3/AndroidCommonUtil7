package link.webarata3.common.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class NumberUtilTest {
    @Test
    public void formatIntで123の場合() throws Exception {
        assertThat(NumberUtil.formatInt("123"), is(123));
    }

    @Test
    public void formatIntでマイナス123の場合() throws Exception {
        assertThat(NumberUtil.formatInt("-123"), is(-123));
    }

    @Test
    public void formatIntでnullの場合() throws Exception {
        assertThat(NumberUtil.formatInt(null), is(nullValue()));
    }

    @Test
    public void formatIntで1点0の場合() throws Exception {
        assertThat(NumberUtil.formatInt("1.0"), is(nullValue()));
    }
}
