package link.webarata3.common.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class NormalizedStringTest {
    @RunWith(Theories.class)
    public static class lengthのテスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture("あああ", 3), new Fixture("𠮷野家", 3) };

        @Theory
        public void isEmpty(Fixture fixture) throws Exception {
            NormalizedString str = new NormalizedString(fixture.str);
            assertThat(str.length(), is(fixture.expected));
        }

        static class Fixture {
            String str;
            int expected;

            Fixture(String str, int expected) {
                this.str = str;
                this.expected = expected;
            }
        }
    }

    @RunWith(Theories.class)
    public static class charAtの正常系テスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture("あいう", 0, "あ"), new Fixture("あいう", 1, "い"),
                new Fixture("あいう", 2, "う"), new Fixture("𠮷野家", 0, "𠮷"), new Fixture("𠮷野家", 1, "野"),
                new Fixture("𠮷野家", 2, "家"), new Fixture("野𠮷家", 0, "野"), new Fixture("野𠮷家", 1, "𠮷"),
                new Fixture("野𠮷家", 2, "家"), new Fixture("野𠮷丄", 0, "野"), new Fixture("野𠮷丄", 1, "𠮷"),
                new Fixture("野𠮷丄", 2, "丄") };

        @Theory
        public void charAt(Fixture fixture) throws Exception {
            NormalizedString str = new NormalizedString(fixture.str);
            assertThat(str.charAt(fixture.index), is(fixture.expected));
        }

        static class Fixture {
            String str;
            int index;
            String expected;

            Fixture(String str, int index, String expected) {
                this.str = str;
                this.index = index;
                this.expected = expected;
            }
        }
    }

    @RunWith(Theories.class)
    public static class charAtの異常系テスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture("テスト", -1), new Fixture("テスト", 3), new Fixture("𠮷野家", -1),
                new Fixture("𠮷野家", 3), new Fixture("𠮷野家", 4), };

        @Rule
        public ExpectedException expectedException = ExpectedException.none();

        @Theory
        public void charAt(Fixture fixture) throws Exception {
            expectedException.expect(IndexOutOfBoundsException.class);
            NormalizedString str = new NormalizedString(fixture.str);

            str.charAt(fixture.index);
        }

        static class Fixture {
            String str;
            int index;

            Fixture(String str, int index) {
                this.str = str;
                this.index = index;
            }
        }
    }

    @RunWith(Theories.class)
    public static class getOriginalStringのテスト {
        @DataPoints
        public static Fixture[] PARAMs = { new Fixture("テスト", "テスト", 3), new Fixture("𠮷野家", "𠮷野家", 4), };

        @Theory
        public void charAt(Fixture fixture) throws Exception {
            String actual = new NormalizedString(fixture.str).getOriginalString();

            assertThat(actual, is(fixture.expected));
            assertThat(actual.length(), is(fixture.length));
        }

        static class Fixture {
            String str;
            String expected;
            int length;

            Fixture(String str, String expected, int length) {
                this.str = str;
                this.expected = expected;
                this.length = length;
            }
        }
    }
}
