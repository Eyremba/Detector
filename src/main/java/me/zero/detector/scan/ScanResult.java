package me.zero.detector.scan;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * This is a basic way to display results, only temporary
 * until a more advanced system is put into place.
 *
 * @author Brady
 * @since 9/27/2017 3:02 PM
 */
public interface ScanResult {

    String getResult();

    static ScanResult method(MethodNode mn) {
        return () -> "M_" + mn.name + mn.desc;
    }

    static ScanResult method(MethodNode mn, AbstractInsnNode insn) {
        return () -> "M_" + mn.name + mn.desc + "@INSN_" + mn.instructions.indexOf(insn);
    }

    static ScanResult annotation(AnnotationNode an) {
        return () -> "A_" + an.desc;
    }

    static ScanResult annotation(AnnotationNode an, Object value) {
        return () -> "A_" + an.desc + "@VALUE_" + an.values.indexOf(value);
    }
}
