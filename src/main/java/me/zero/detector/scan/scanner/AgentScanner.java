package me.zero.detector.scan.scanner;

import me.zero.detector.scan.ClassScanner;
import me.zero.detector.scan.ScanResult;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.objectweb.asm.Opcodes.ACC_STATIC;

/**
 * @author Brady
 * @since 1/5/2017 12:00 PM
 */
public final class AgentScanner implements ClassScanner {

    private static final List<String> AGENT_METHODS = Arrays.asList(
            "agentmain(Ljava/lang/String;)V",
            "agentmain(Ljava/lang/String;Ljava/lang/instrument/Instrumentation;)V",
            "premain(Ljava/lang/String;)V",
            "premain(Ljava/lang/String;Ljava/lang/instrument/Instrumentation;)V"
    );

    @Override
    public final List<ScanResult> scan(ClassNode cn) {
        // noinspection unchecked
        return ((List<MethodNode>) cn.methods).stream()
                .filter(method -> (method.access & ACC_STATIC) != 0 && AGENT_METHODS.contains(method.name + method.desc))
                .map(ScanResult::method)
                .collect(Collectors.toList());
    }
}
