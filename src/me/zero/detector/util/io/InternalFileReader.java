package me.zero.detector.util.io;

/**
 * Created by Brady on 1/5/2017.
 */
public class InternalFileReader {

    public static String read(String path) {
        return IOUtils.getStringFromStream(ClassLoader.getSystemResourceAsStream(path));
    }
}
