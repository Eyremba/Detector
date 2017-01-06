package me.zero.detector.data;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Created by Brady on 1/4/2017.
 */
public class ClassPool {

    private static ClassPool pool;

    private final List<ClassData> classes = new ArrayList<>();

    private static boolean debug = true;

    public static void init(Instrumentation instrumentation) {
        if (pool != null) return;
        pool = new ClassPool();

        for (Class<?> clazz : instrumentation.getAllLoadedClasses()) {
            ClassData data = genClassData(clazz);
            if (data != null)
                pool.classes.add(data);
        }
    }

    public ClassData getClassData(Class<?> clazz) {
        if (debug) {
            return genClassData(clazz);
        } else {
            for (ClassData data : this.classes) {
                if (data.getClassPath().equalsIgnoreCase(clazz.getCanonicalName().replace(".", "/") + ".class")) {
                    return data;
                }
            }
        }
        return null;
    }

    private static ClassData genClassData(Class<?> clazz) {
        try {
            String classPath = clazz.getCanonicalName().replace(".", "/") + ".class";
            InputStream in = ClassLoader.getSystemResourceAsStream(classPath);
            ClassReader reader = new ClassReader(in);
            ClassNode classNode = new ClassNode();

            reader.accept(classNode, 0);

            List<MethodData> methods = new ArrayList<>();

            for (MethodNode method : (List<MethodNode>) classNode.methods) {
                methods.add(new MethodData(method, new MethodInfo(method.name, method.desc), method.instructions));
            }

            return new ClassData(methods, classNode, classPath);
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
