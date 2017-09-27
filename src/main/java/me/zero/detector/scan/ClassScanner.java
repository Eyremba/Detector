package me.zero.detector.scan;

import org.objectweb.asm.tree.ClassNode;

import java.util.List;

/**
 * Created by Brady on 1/3/2017.
 */
public interface ClassScanner {

    List<ScanResult> scan(ClassNode cn);
}
