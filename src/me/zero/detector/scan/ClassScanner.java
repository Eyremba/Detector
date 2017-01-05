package me.zero.detector.scan;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by Brady on 1/3/2017.
 */
public abstract class ClassScanner {

    /**
    public final boolean scan(Class<?> clazz) {
        try {
            InputStream in = ClassLoader.getSystemResourceAsStream(clazz.getCanonicalName().replace(".", "/") + ".class");
            ClassReader cr = new ClassReader(in);
            ClassNode classNode = new ClassNode();

            PrintWriter printWriter = new PrintWriter(System.out);
            TraceClassVisitor traceClassVisitor = new TraceClassVisitor(printWriter);

            cr.accept(traceClassVisitor, 2);
            cr.accept(new ClassVisitor(Opcodes.ASM4){
                @Override
                public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                    System.err.println("method name: " + name);
                    System.err.println("return type: " + Type.getReturnType(desc));
                    System.err.println("argument types: " + Arrays.toString(Type.getArgumentTypes(desc)));
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }
            }, 0);
            cr.accept(classNode, 0);
            return this.scan(clazz, classNode, traceClassVisitor);
        } catch (IOException e) {
            return false;
        }
    }
     */

    public abstract boolean scan(Class<?> clazz);
}
