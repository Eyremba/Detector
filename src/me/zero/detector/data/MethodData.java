package me.zero.detector.data;

import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

/**
 * Created by Brady on 1/3/2017.
 */
public class MethodData {

    private MethodNode node;
    private MethodInfo info;
    private InsnList instructions;

    protected MethodData(MethodNode node, MethodInfo info, InsnList instructions) {
        this.node = node;
        this.info = info;
        this.instructions = instructions;
    }

    public MethodNode getNode() {
        return this.node;
    }

    public MethodInfo getInfo() {
        return this.info;
    }

    public InsnList getInstructions() {
        return this.instructions;
    }
}
