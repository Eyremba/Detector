package me.zero.detector;

import me.zero.detector.data.ClassPool;

import java.lang.instrument.Instrumentation;

/**
 * @author Brady
 * @since 9/27/2017 11:36 AM
 */
public final class DetectorAgent {

    public static void agentmain(String args, Instrumentation instrumentation) {
        ClassPool.init(instrumentation);
    }
}
