package link.webarata3.dro.common.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class RegexUtilTest {
    @Test
    public void getPatternでキャッシュされること() {
        Pattern pattern = RegexUtil.getPattern(RegexUtil.EM_KATAKANA);
        assertThat(RegexUtil.getPattern(RegexUtil.EM_KATAKANA), is(pattern));
    }

    @RunWith(Theories.class)
    public static class 全角カタカナのテスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture("アイウエオ", true), new Fixture("あいうえお", false),
                new Fixture("\u30A0", true), // 全角カタカナで一番最初の文字
                new Fixture("\u309F", false), // 全角カタカナで1番最初の文字の1つ前の文字
                new Fixture("\u30FF", true), // 全角カタカナで一番最後の文字
                new Fixture("\u3100", false) // 全角カタカナで1番最後の文字の1つ後の文字
        };

        @Theory
        public void perfectMatch(Fixture fixture) throws Exception {
            assertThat(RegexUtil.perfectMatch(RegexUtil.EM_KATAKANA, fixture.str), is(fixture.expected));
        }

        static class Fixture {
            String str;
            boolean expected;

            Fixture(String str, boolean expected) {
                this.str = str;
                this.expected = expected;
            }
        }
    }
}
