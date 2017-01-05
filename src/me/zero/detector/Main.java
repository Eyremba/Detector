package me.zero.detector;

import me.zero.detector.data.ClassPool;
import me.zero.detector.scan.scanner.AgentScanner;

import java.lang.instrument.Instrumentation;

/**
 * Created by Brady on 1/3/2017.
 */
public class Main {

    public static void agentmain(String args, Instrumentation instrumentation) {
        instrumentation.addTransformer(null, false);
    }

    public static void main(String[] args) {
        ClassPool.init();
        System.out.println(new AgentScanner().scan(Main.class));
    }
}
