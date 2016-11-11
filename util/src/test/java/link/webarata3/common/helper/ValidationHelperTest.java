package link.webarata3.common.helper;

import org.junit.Before;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import link.webarata3.common.enums.LineBreakType;
import link.webarata3.common.enums.TrimType;
import link.webarata3.common.enums.UseEmBlank;
import link.webarata3.common.enums.UseLineBreak;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class ValidationHelperTest {
    @RunWith(Theories.class)
    public static class requiredで両端trimで改行がLFの場合のテスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture(null, false), new Fixture("", false),
                new Fixture(" 　\r\n\t", false), new Fixture("あいうえお", true), new Fixture("あいうえお ", true) };

        private ValidationHelper validationHelper;

        @Before
        public void setUp() {
            validationHelper = ValidationHelper.getInstance(TrimType.BOTH, LineBreakType.LF);
        }

        @Theory
        public void required(Fixture fixture) throws Exception {
            assertThat(validationHelper.required(fixture.str), is(fixture.expected));
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
    public static class requiredでtrimなしで改行がLFの場合のテスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture(null, false), new Fixture(" 　\r\n\t", true),
                new Fixture("", false), new Fixture("あいうえお", true), new Fixture("あいうえお ", true) };

        private ValidationHelper validationHelper;

        @Before
        public void setUp() {
            validationHelper = ValidationHelper.getInstance(TrimType.NONE, LineBreakType.LF);
        }

        @Theory
        public void required(Fixture fixture) throws Exception {
            assertThat(validationHelper.required(fixture.str), is(fixture.expected));
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
    public static class minLengthで両端trimで改行がLFの場合のテスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture(null, 0, true), new Fixture(null, 1, false),
                new Fixture("あああ", 2, true), new Fixture("あああ", 3, true), new Fixture("あああ", 4, false),
                new Fixture(" あああ ", 2, true), new Fixture(" あああ ", 3, true), new Fixture(" あああ ", 4, false),
                new Fixture("あ\r\nあ", 2, true), new Fixture("あ\r\nあ", 3, true), new Fixture("あ\r\nあ", 4, false),
                new Fixture("𠮷野家", 2, true), new Fixture("𠮷野家", 3, true), new Fixture("𠮷野家", 4, false) };

        private ValidationHelper validationHelper;

        @Before
        public void setUp() {
            validationHelper = ValidationHelper.getInstance(TrimType.BOTH, LineBreakType.LF);
        }

        @Theory
        public void minLength(Fixture fixture) throws Exception {
            assertThat(validationHelper.minLength(fixture.str, fixture.minLength), is(fixture.expected));
        }

        static class Fixture {
            String str;
            int minLength;
            boolean expected;

            Fixture(String str, int minLength, boolean expected) {
                this.str = str;
                this.minLength = minLength;
                this.expected = expected;
            }
        }
    }

    @RunWith(Theories.class)
    public static class minLengthでtrimなしで改行がCRLFの場合のテスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture(null, 1, false), new Fixture("   ", 2, true),
                new Fixture("   ", 3, true), new Fixture("   ", 4, false), new Fixture(" a ", 2, true),
                new Fixture(" a ", 3, true), new Fixture(" a ", 4, false) };

        private ValidationHelper validationHelper;

        @Before
        public void setUp() {
            validationHelper = ValidationHelper.getInstance(TrimType.NONE, LineBreakType.CRLF);
        }

        @Theory
        public void minLength(Fixture fixture) throws Exception {
            assertThat(validationHelper.minLength(fixture.str, fixture.minLength), is(fixture.expected));
        }

        static class Fixture {
            String str;
            int minLength;
            boolean expected;

            Fixture(String str, int minLength, boolean expected) {
                this.str = str;
                this.minLength = minLength;
                this.expected = expected;
            }
        }
    }

    @RunWith(Theories.class)
    public static class maxLengthで両端trimで改行がLFの場合のテスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture(null, 1, true), new Fixture("あああ", 2, false),
                new Fixture("あああ", 3, true), new Fixture("あああ", 4, true), new Fixture(" あああ ", 2, false),
                new Fixture(" あああ ", 3, true), new Fixture(" あああ ", 4, true), new Fixture("あ\r\nあ", 2, false),
                new Fixture("あ\r\nあ", 3, true), new Fixture("あ\r\nあ", 4, true), new Fixture("𠮷野家", 2, false),
                new Fixture("𠮷野家", 3, true), new Fixture("𠮷野家", 4, true) };

        private ValidationHelper validationHelper;

        @Before
        public void setUp() {
            validationHelper = ValidationHelper.getInstance(TrimType.BOTH, LineBreakType.LF);
        }

        @Theory
        public void maxLength(Fixture fixture) throws Exception {
            assertThat(validationHelper.maxLength(fixture.str, fixture.maxLength), is(fixture.expected));
        }

        static class Fixture {
            String str;
            int maxLength;
            boolean expected;

            Fixture(String str, int maxLength, boolean expected) {
                this.str = str;
                this.maxLength = maxLength;
                this.expected = expected;
            }
        }
    }

    @RunWith(Theories.class)
    public static class maxLengthでtrimなしで改行がCRLFの場合のテスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture(null, 1, true), new Fixture("   ", 2, false),
                new Fixture("   ", 3, true), new Fixture("   ", 4, true), new Fixture(" a ", 2, false),
                new Fixture(" a ", 3, true), new Fixture(" a ", 4, true) };

        private ValidationHelper validationHelper;

        @Before
        public void setUp() {
            validationHelper = ValidationHelper.getInstance(TrimType.NONE, LineBreakType.CRLF);
        }

        @Theory
        public void maxLength(Fixture fixture) throws Exception {
            assertThat(validationHelper.maxLength(fixture.str, fixture.maxLength), is(fixture.expected));
        }

        static class Fixture {
            String str;
            int maxLength;
            boolean expected;

            Fixture(String str, int maxLength, boolean expected) {
                this.str = str;
                this.maxLength = maxLength;
                this.expected = expected;
            }
        }
    }

    @RunWith(Theories.class)
    public static class isIntで両端trimで改行がLFの場合のテスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture("123", true), new Fixture("-123", true),
                new Fixture("1.0", false), new Fixture("", false), new Fixture(null, false), new Fixture(" 123 ", true),
                new Fixture(" -123 ", true), new Fixture(" 1.0 ", false) };

        private ValidationHelper validationHelper;

        @Before
        public void setUp() {
            validationHelper = ValidationHelper.getInstance(TrimType.BOTH, LineBreakType.LF);
        }

        @Theory
        public void isInt(Fixture fixture) throws Exception {
            assertThat(validationHelper.isInt(fixture.str), is(fixture.expected));
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
    public static class isEmKatakanaのテスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture(null, UseEmBlank.ALLOW, UseLineBreak.ALLOW, true),
                new Fixture("", UseEmBlank.ALLOW, UseLineBreak.ALLOW, true),
                new Fixture("アイウエオ", UseEmBlank.ALLOW, UseLineBreak.DISALLOW, true),
                new Fixture("アイウエ　オ", UseEmBlank.DISALLOW, UseLineBreak.DISALLOW, false),
                new Fixture("アイウエ　オ", UseEmBlank.ALLOW, UseLineBreak.DISALLOW, true),
                new Fixture("アイウエ　\rオ", UseEmBlank.ALLOW, UseLineBreak.ALLOW, true),
                new Fixture("ｱｲｳｴｵ", UseEmBlank.ALLOW, UseLineBreak.DISALLOW, false),
                new Fixture(" アイウエオ ", UseEmBlank.ALLOW, UseLineBreak.DISALLOW, true),
                new Fixture("　アイウエオ　", UseEmBlank.ALLOW, UseLineBreak.DISALLOW, true) };

        private ValidationHelper validationHelper;

        @Before
        public void setUp() {
            validationHelper = ValidationHelper.getInstance(TrimType.BOTH, LineBreakType.LF);
        }

        @Theory
        public void isEmKatakana(Fixture fixture) throws Exception {
            assertThat(validationHelper.isEmKatakana(fixture.str, fixture.useEmBlank, fixture.useLineBreak),
                    is(fixture.expected));
        }

        static class Fixture {
            String str;
            UseEmBlank useEmBlank;
            UseLineBreak useLineBreak;
            boolean expected;

            Fixture(String str, UseEmBlank useEmBlank, UseLineBreak useLineBreak, boolean expected) {
                this.str = str;
                this.useEmBlank = useEmBlank;
                this.useLineBreak = useLineBreak;
                this.expected = expected;
            }
        }
    }
}
