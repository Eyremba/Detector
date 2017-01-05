package me.zero.detector.scan.scanner;

import me.zero.detector.data.*;
import me.zero.detector.scan.ClassScanner;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceMethodVisitor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.instrument.Instrumentation;

/**
 * Created by Brady on 1/3/2017.
 */
public class AgentScanner extends ClassScanner {

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
            if (data.getMethod(agent) != null) {
                InsnList list = data.getMethod(agent).getInstructions();
                System.out.println(data.getMethod(agent).getNode().name);
                for(int i = 0; i < list.size(); i++){
                    System.out.print(insnToString(list.get(i)));
                }
                return true;
            }
        }

        return false;
    }

    public static String insnToString(AbstractInsnNode insn){
        insn.accept(mp);
        StringWriter sw = new StringWriter();
        printer.print(new PrintWriter(sw));
        printer.getText().clear();
        return sw.toString();
    }

    private static Printer printer = new Textifier();
    private static TraceMethodVisitor mp = new TraceMethodVisitor(printer);
}