package me.zero.detector.scan.scanner;

import me.zero.detector.data.*;
import me.zero.detector.scan.ClassScanner;

import java.lang.instrument.Instrumentation;

/**
 * Created by Brady on 1/3/2017.
 */
public class AgentScanner implements ClassScanner {

    private static final MethodInfo[] agentMethods = new MethodInfo[] {
            new MethodInfo("agentmain", void.class, String.class),
            new MethodInfo("agentmain", void.class, String.class, Instrumentation.class),
            new MethodInfo("premain", void.class, String.class),
            new MethodInfo("premain", void.class, String.class, Instrumentation.class)
    };

    @Override
    public boolean scan(Class<?> clazz) {
        if (clazz == null) return false;
        ClassData data = ClassPool.getPool().getClassData(clazz);
        if (data == null) return false;

        for (MethodInfo agent : agentMethods) {
            if (data.getMethod(agent) != null) return true;
        }

        return false;
    }
}