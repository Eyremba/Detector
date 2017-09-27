package me.zero.detector.scan.scanner;

import me.zero.detector.scan.ScanResult;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Brady
 * @since 9/27/2017 2:44 PM
 */
public final class AnnotationStringScanner extends StringScanner {

    @Override
    public final List<ScanResult> scan(ClassNode cn) {
        List<AnnotationNode> allAnnotations = new ArrayList<>();

        if (cn.invisibleAnnotations != null)
            // noinspection unchecked
            allAnnotations.addAll(cn.invisibleAnnotations);

        if (cn.visibleAnnotations != null)
            // noinspection unchecked
            allAnnotations.addAll(cn.visibleAnnotations);

        if (cn.invisibleTypeAnnotations != null)
            // noinspection unchecked
            allAnnotations.addAll(cn.invisibleTypeAnnotations);

        if (cn.visibleTypeAnnotations != null)
            // noinspection unchecked
            allAnnotations.addAll(cn.visibleTypeAnnotations);

        // noinspection unchecked
        return allAnnotations.stream()
                .filter(annotation -> ((List<Object>) annotation.values).stream().filter(value -> BAD_STRINGS.contains(value.toString())).count() > 0)
                .map(ScanResult::annotation)
                .collect(Collectors.toList());
    }
}
