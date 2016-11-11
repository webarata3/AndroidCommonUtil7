package link.webarata3.dro.common.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import link.webarata3.dro.common.enums.LineBreakType;

@RunWith(Enclosed.class)
public class StringUtilTest {

    @RunWith(Theories.class)
    public static class isEmptyのテスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture(null, true), new Fixture("", true), new Fixture("ABC", false),
                new Fixture("あ", false) };

        @Theory
        public void isEmpty(Fixture fixture) throws Exception {
            assertThat(StringUtil.isEmpty(fixture.str), is(fixture.expected));
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

    @RunWith(Theories.class)
    public static class isNotEmptyのテスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture(null, false), new Fixture("", false), new Fixture("ABC", true),
                new Fixture("あ", true) };

        @Theory
        public void isNotEmpty(Fixture fixture) throws Exception {
            assertThat(StringUtil.isNotEmpty(fixture.str), is(fixture.expected));
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

    @RunWith(Theories.class)
    public static class trimLeftのテスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture(null, null), // nullのテスト
                new Fixture(" あいうえお", "あいうえお"), // 半角ブランクのテスト
                new Fixture("　あいうえお", "あいうえお"), // 全角ブランクのテスト
                new Fixture("\tあいうえお", "あいうえお"), // タブのテスト
                new Fixture("\nあいうえお", "あいうえお"), // LFのテスト
                new Fixture("\rあいうえお", "あいうえお"), // CRのテスト
                new Fixture(" 　\t\r\nあいうえお", "あいうえお"), // 混ざった場合のテスト
                new Fixture("あいうえお 　\t\r\n", "あいうえお 　\t\r\n"), // 右Trimが行われないこと
                new Fixture(" 　\t\r\n", ""), // すべての文字が太陽の場合
                new Fixture("", "") // 空文字の場合
        };

        @Theory
        public void trimLeft(Fixture fixture) throws Exception {
            assertThat(StringUtil.trimLeft(fixture.str), is(fixture.expected));
        }

        public static class Fixture {
            String str;
            String expected;

            Fixture(String str, String expected) {
                this.str = str;
                this.expected = expected;
            }
        }
    }

    @RunWith(Theories.class)
    public static class trimRightのテスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture(null, null), // nullのテスト
                new Fixture("あいうえお ", "あいうえお"), // 半角ブランクのテスト
                new Fixture("あいうえお　", "あいうえお"), // 全角ブランクのテスト
                new Fixture("あいうえお\t", "あいうえお"), // タブのテスト
                new Fixture("あいうえお\n", "あいうえお"), // LFのテスト
                new Fixture("あいうえお\r", "あいうえお"), // CRのテスト
                new Fixture("あいうえお 　\t\r\n", "あいうえお"), // 混ざった場合のテスト
                new Fixture(" 　\t\r\nあいうえお", " 　\t\r\nあいうえお"), // 右Trimが行われないこと
                new Fixture(" 　\t\r\n", ""), // すべての文字が太陽の場合
                new Fixture("", "") // 空文字の場合
        };

        @Theory
        public void trimRight(Fixture fixture) throws Exception {
            assertThat(StringUtil.trimRight(fixture.str), is(fixture.expected));
        }

        public static class Fixture {
            String str;
            String expected;

            Fixture(String str, String expected) {
                this.str = str;
                this.expected = expected;
            }
        }
    }

    @RunWith(Theories.class)
    public static class trimのテスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture(null, null), // nullのテスト
                new Fixture(" あいうえお ", "あいうえお"), // 半角ブランクのテスト
                new Fixture("　あいうえお　", "あいうえお"), // 全角ブランクのテスト
                new Fixture("\tあいうえお\t", "あいうえお"), // タブのテスト
                new Fixture("\nあいうえお\n", "あいうえお"), // LFのテスト
                new Fixture("\rあいうえお\r", "あいうえお"), // CRのテスト
                new Fixture(" 　\t\r\nあいうえお 　\t\r\n", "あいうえお"), // 混ざった場合のテスト
                new Fixture(" 　\t\r\n", ""), // すべての文字が太陽の場合
                new Fixture("", "") // 空文字の場合
        };

        @Theory
        public void trim(Fixture fixture) throws Exception {
            assertThat(StringUtil.trim(fixture.str), is(fixture.expected));
        }

        public static class Fixture {
            String str;
            String expected;

            Fixture(String str, String expected) {
                this.str = str;
                this.expected = expected;
            }
        }
    }

    @RunWith(Theories.class)
    public static class normalizeLineBreakのテスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture("test\r\ntest\ntest\r", LineBreakType.CR, "test\rtest\rtest\r"),
                new Fixture("test\r\ntest\ntest\r", LineBreakType.LF, "test\ntest\ntest\n"),
                new Fixture("test\r\ntest\ntest\r", LineBreakType.CRLF, "test\r\ntest\r\ntest\r\n") };

        @Theory
        public void normalizeLineBreak(Fixture fixture) throws Exception {
            assertThat(StringUtil.normalizeLineBreak(fixture.str, fixture.lineBreakType), is(fixture.expected));
        }

        public static class Fixture {
            String str;
            LineBreakType lineBreakType;
            String expected;

            Fixture(String str, LineBreakType lineBreakType, String expected) {
                this.str = str;
                this.lineBreakType = lineBreakType;
                this.expected = expected;
            }
        }
    }
}
