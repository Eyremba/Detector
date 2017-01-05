package me.zero.detector.data;

import org.objectweb.asm.tree.ClassNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brady on 1/4/2017.
 */
public class ClassData {

    private List<MethodData> methods;
    private ClassNode node;
    private String path;

    protected ClassData(List<MethodData> methods, ClassNode node, String path) {
        this.methods = methods;
        this.node = node;
        this.path = path;
    }

    public ClassNode getNode() {
        return this.node;
    }

    public MethodData getMethod(MethodInfo methodInfo) {
        for (MethodData method : methods) {
            MethodInfo info = method.getInfo();
            if (methodInfo.matches(info))
                return method;
        }
        return null;
    }

    public String getClassPath() {
        return this.path;
    }
}
