package org.bukkit.util;

import java.nio.channels.FileChannel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Class containing file utilities
 */

public class FileUtil {

    /**
     * This method copies one file to another location
     *
     * @param inFile the source filename
     * @param outFile the target filename
     * @return true on success
     */

    public static boolean copy(File inFile, File outFile) {
        if (!inFile.exists()) {
            return false;
        }

        FileInputStream ins = null;
        FileOutputStream outs = null;
        FileChannel in = null;
        FileChannel out = null;

        try {
            ins = new FileInputStream(inFile);
            outs = new FileOutputStream(outFile);
            in = ins.getChannel();
            out = outs.getChannel();

            long pos = 0;
            long size = in.size();

            while (pos < size) {
                pos += in.transferTo(pos, 10 * 1024 * 1024, out);
            }
        } catch (IOException ioe) {
            return false;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                ins.close();
                outs.close();
            } catch (IOException ioe) {
                return false;
            }
        }

        return true;

    }
}
