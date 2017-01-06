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

    private MethodData INIT;
    private MethodData CLINIT;

    ClassData(List<MethodData> methods, ClassNode node, String path) {
        this.methods = methods;
        this.node = node;
        this.path = path;

        for (MethodData method : methods) {
            if (method.getInfo().getName().equalsIgnoreCase("<init>"))
                INIT = method;
            if (method.getInfo().getName().equalsIgnoreCase("<clinit>"))
                CLINIT = method;
        }
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

    public MethodData getInit() {
        return this.INIT;
    }

    public MethodData getCLInit() {
        return this.CLINIT;
    }

    public String getClassPath() {
        return this.path;
    }
}
