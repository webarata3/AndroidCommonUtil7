package link.webarata3.dro.common.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class IoUtilTest {

    public static class ファイルコピー {
        private static String BASE_DIR;

        @BeforeClass
        public static void setUpClass() throws URISyntaxException {
            File file = new File(IoUtil.class.getResource("IoUtilSrc.txt").toURI());
            BASE_DIR = file.getParent();
        }

        @After
        public void tearDown() {
            new File(BASE_DIR + File.separator + "IoUtilDest.txt").delete();
        }

        @Test
        public void copyFileのファイル名の場合のテスト() throws Exception {
            Assert.fail();
            IoUtil.copyFile(BASE_DIR + File.separator + "IoUtilSrc.txt", BASE_DIR + File.separator + "IoUtilDest.txt");

            assertThat(new File(BASE_DIR + File.separator + "IoUtilDest.txt").exists(), is(true));
        }

        @Test
        public void copyFileのstreamの場合テスト() throws Exception {
            IoUtil.copyFile(new FileInputStream(new File(BASE_DIR, "IoUtilSrc.txt")),
                    new FileOutputStream(new File(BASE_DIR, "IoUtilDest.txt")));

            assertThat(new File(BASE_DIR + File.separator + "IoUtilDest.txt").exists(), is(true));
        }
    }
}
