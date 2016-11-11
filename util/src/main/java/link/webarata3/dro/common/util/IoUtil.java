package link.webarata3.dro.common.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 入出力関連のユーティリティ
 * 
 * @author webarata3
 */
public abstract class IoUtil {
    /**
     * ファイルのコピーをする。<br>
     * コピーしたファイルのサイズのチェックも行う。
     *
     * @param srcFileName
     *            コピー元ファイル名
     * @param destFileName
     *            コピー先ファイル名
     * @throws FileNotFoundException
     *             コピーするファイルが存在しない場合
     * @throws IOException
     *             ファイルが正しくコピーできていない場合
     */
    public static void copyFile(String srcFileName, String destFileName) throws FileNotFoundException, IOException {
        File srcFile = new File(srcFileName);
        File destFile = new File(destFileName);

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFileName);
        } catch (FileNotFoundException e) {
            closeQuietly(fos);
            closeQuietly(fis);
            throw e;
        }
        copyFile(fis, fos);

        if (srcFile.length() != destFile.length()) {
            throw new IOException("Failed to copy full contents from '" + srcFile + "' to '" + destFile + "'");
        }
    }

    /**
     * ファイルのコピーをする。
     * 
     * @param fis
     *            コピー元のStream
     * @param fos
     *            コピー先のStream
     * @throws IOException
     *             ファイルが正しくコピーできない場合。
     */
    public static void copyFile(FileInputStream fis, FileOutputStream fos) throws IOException {
        FileChannel inputChannel = fis.getChannel();
        try {
            FileChannel outputChannel = fos.getChannel();
            try {
                outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
            } finally {
                closeQuietly(outputChannel);
            }
        } finally {
            closeQuietly(inputChannel);
        }
    }

    /**
     * ファイルのクローズ。例外は無視する
     * 
     * @param closeable
     *            クローズするファイル
     */
    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final IOException ioe) {
            // ignore
        }
    }
}
