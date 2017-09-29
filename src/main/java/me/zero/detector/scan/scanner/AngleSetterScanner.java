package me.zero.detector.scan.scanner;

import me.zero.detector.scan.ClassScanner;
import me.zero.detector.scan.ScanResult;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;

/**
 * @author Brady
 * @since 9/28/2017 6:47 AM
 */
public final class AngleSetterScanner implements ClassScanner {

    @Override
    public List<ScanResult> scan(ClassNode cn) {

        // noinspection unchecked
        ((List<MethodNode>) cn.methods).forEach(method -> {
            method.instructions.toArray();
            // Find patterns
        });

        return null;
    }
}
