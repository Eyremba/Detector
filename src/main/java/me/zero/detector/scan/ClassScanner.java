package me.zero.detector.scan;

import org.objectweb.asm.tree.ClassNode;

import java.util.List;

/**
 * @author Brady
 * @since 1/3/2017 12:00 PM
 */
public interface ClassScanner {

    List<ScanResult> scan(ClassNode cn);
}
