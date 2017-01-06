package me.zero.detector.scan.scanner;

import me.zero.detector.data.ClassData;
import me.zero.detector.data.ClassPool;
import me.zero.detector.data.MethodData;
import me.zero.detector.scan.ClassScanner;
import me.zero.detector.util.io.InternalFileReader;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LdcInsnNode;

/**
 * Created by Brady on 1/5/2017.
 */
public class ConsStringScanner implements ClassScanner {

    private static String[] badStrings = InternalFileReader.read("res/strings.txt").split("\n");

    @Override
    public boolean scan(Class<?> clazz) {
        // TODO: Clean this up
        if (clazz == null) return false;
        ClassData data = ClassPool.getPool().getClassData(clazz);
        if (data == null) return false;
        MethodData init = data.getInit();
        if (init == null) return false;

        InsnList instructions = init.getInstructions();
        for (int i = 0; i < instructions.size(); i++) {
            AbstractInsnNode abstractInsnNode = instructions.get(i);
            if (abstractInsnNode.getType() == AbstractInsnNode.LDC_INSN) {
                LdcInsnNode ldcInsnNode = (LdcInsnNode) abstractInsnNode;
                Object meme = ldcInsnNode.cst;
                if (meme instanceof String) {
                    String possibleHeck = ((String) meme).toLowerCase().replace(" ", "");

                    for (int j = 0; j < badStrings.length; j++)
                        if (possibleHeck.equalsIgnoreCase(badStrings[j]))
                            return true;
                }
            }
        }

        return false;
    }
}
