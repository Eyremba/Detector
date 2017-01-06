package me.zero.detector.util;

/**
 * Created by Brady on 1/6/2017.
 */
public enum OperatingSystem {

    MAC, WINDOWS, UNKNOWN;

    public static OperatingSystem getOperatingSystem() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) return WINDOWS;
        if (os.contains("mac")) return MAC;
        return UNKNOWN;
    }
}
