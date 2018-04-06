package me.zero.detector.agent;

import me.zero.detector.data.ClassPool;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Brady
 * @since 9/27/2017 11:36 AM
 */
public final class DetectorAgent {

    public static void agentmain(String args, Instrumentation instrumentation) {
        List<Class> loadedClasses = new ArrayList<>(Arrays.asList(instrumentation.getAllLoadedClasses()));
        loadedClasses.removeIf(c -> !isTarget(c));

        PoolLoader transformer = new PoolLoader();
        instrumentation.addTransformer(transformer);
        try {
            instrumentation.retransformClasses(loadedClasses.toArray(new Class[0]));
        } catch (UnmodifiableClassException e) {
            e.printStackTrace();
        }
        instrumentation.removeTransformer(transformer);
    }

    private static boolean isTarget(Class<?> c) {
        return true;
    }

    private static final class PoolLoader implements ClassFileTransformer {

        @Override
        public final byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
            // In-case there are some weird access errors with directly reading
            // the Class, we can use a transformer to get the bytecode directly.
            ClassPool.getPool().loadClass(classBeingRedefined, classfileBuffer);
            return classfileBuffer;
        }
    }
}
