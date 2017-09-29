package me.zero.detector.data;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Brady
 * @since 1/4/2017 12:00 PM
 */
public final class ClassPool {

    /**
     * Test Var
     */
    private static boolean debug = true;

    /**
     * Instance of the ClassPool
     */
    private static ClassPool pool;
    private ClassPool() {}

    /**
     * List of loaded ClassNodes in the pool
     */
    private final List<ClassNode> classes = new ArrayList<>();

    public final void loadClass(Class<?> clazz, byte[] bytecode) {
        if (pool.getNode(clazz) != null)
            pool.classes.add(getClassNode(new ClassReader(bytecode)));
    }

    public final ClassNode getNode(Class<?> clazz) {
        if (debug) {
            try {
                InputStream in = ClassLoader.getSystemResourceAsStream(Descriptor.getJvmName(clazz));
                return getClassNode(new ClassReader(in));
            } catch (IOException e) {
                return null;
            }
        }

        for (ClassNode data : this.classes) {
            if (Descriptor.getJvmName(clazz).equals(data.name)) {
                return data;
            }
        }

        return null;
    }

    private static ClassNode getClassNode(ClassReader reader) {
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);
        return classNode;
    }

    public static ClassPool getPool() {
        if (pool == null)
            pool = new ClassPool();

        return ClassPool.pool;
    }
}
