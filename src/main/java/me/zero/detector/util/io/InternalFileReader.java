package me.zero.detector.util.io;

/**
 * @author Brady
 * @since 1/5/2017 12:00 PM
 */
public class InternalFileReader {

    public static String read(String path) {
        return IOUtils.getStringFromStream(ClassLoader.getSystemResourceAsStream(path));
    }
}
