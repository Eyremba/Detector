package me.zero.detector.scan.scanner;

import me.zero.detector.scan.ScanResult;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Brady
 * @since 1/5/2017 12:00 PM
 */
public final class ConstructorStringScanner extends StringScanner {

    @Override
    public final List<ScanResult> scan(ClassNode cn) {
        // noinspection unchecked
        MethodNode init = ((List<MethodNode>) cn.methods).stream().filter(method -> method.name.equals("<init>")).findFirst().orElse(null);
        if (init == null)
            return new ArrayList<>();

        return Arrays.stream(init.instructions.toArray())
                .filter(insn -> insn instanceof LdcInsnNode).map(insn -> (LdcInsnNode) insn)
                .filter(insn -> insn.cst instanceof String && BAD_STRINGS.contains(((String) insn.cst).toLowerCase().replace(" ", "")))
                .map(insn -> ScanResult.method(init, insn))
                .collect(Collectors.toList());
    }
}
