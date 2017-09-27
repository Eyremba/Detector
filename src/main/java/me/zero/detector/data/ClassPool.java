package me.zero.detector.data;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.ClassNode;

import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brady on 1/4/2017.
 */
public class ClassPool {

    private static ClassPool pool;
    private final List<ClassNode> classes = new ArrayList<>();
    private static boolean debug = true;

    public static void init(Instrumentation instrumentation) {
        if (pool != null) return;
        pool = new ClassPool();

        for (Class<?> clazz : instrumentation.getAllLoadedClasses()) {
            ClassNode data = getClassNode(clazz);
            if (data != null)
                pool.classes.add(data);
        }
    }

    public final ClassNode getNode(Class<?> clazz) {
        if (debug) {
            return getClassNode(clazz);
        } else {
            for (ClassNode data : this.classes) {
                if (data.name.equalsIgnoreCase(clazz.getCanonicalName().replace(".", "/"))) {
                    return data;
                }
            }
        }
        return null;
    }

    private static ClassNode getClassNode(Class<?> clazz) {
        try {
            String classPath = clazz.getCanonicalName().replace(".", "/") + ".class";
            InputStream in = ClassLoader.getSystemResourceAsStream(classPath);
            ClassReader reader = new ClassReader(in);
            ClassNode classNode = new ClassNode();

            reader.accept(classNode, 0);
            return classNode;
        } catch (Exception e) {
            System.out.println("Unable to read class: " + clazz.getCanonicalName());
            e.printStackTrace();
        }
        return null;
    }

    public static ClassPool getPool() {
        if (pool == null && debug)
            pool = new ClassPool();

        return ClassPool.pool;
    }
}
