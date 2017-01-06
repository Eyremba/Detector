package me.zero.detector.util.io;

import java.io.InputStream;

/**
 * Created by Brady on 1/5/2017.
 */
public class InternalFileReader {

    public static String read(String path) {
        InputStream input = ClassLoader.getSystemResourceAsStream(path);
        return IOUtils.getStringFromStream(input);
    }
}
