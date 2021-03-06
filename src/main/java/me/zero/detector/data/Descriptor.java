package me.zero.detector.data;

/**
 * @author Brady
 * @since 1/5/2017 12:00 PM
 */
public final class Descriptor {

    private Descriptor() {}

    public static String getDescriptorForClass(Class c) {
        if (c.isPrimitive()) {
            if (c == byte.class)
                return "B";
            if (c == char.class)
                return "C";
            if (c == double.class)
                return "D";
            if (c == float.class)
                return "F";
            if (c == int.class)
                return "I";
            if (c == long.class)
                return "J";
            if (c == short.class)
                return "S";
            if (c == boolean.class)
                return "Z";
            if (c == void.class)
                return "V";
            throw new RuntimeException("Unrecognized primitive " + c);
        }
        if (c.isArray()) return c.getName().replace('.', '/');
        return ('L' + c.getName() + ';').replace('.', '/');
    }

    public static String getJvmName(Class<?> c) {
        return c.getCanonicalName().replace('.', '/') + ".class";
    }

    public static String getDescriptor(Class<?> returnType, Class<?>... classes) {
        StringBuilder s = new StringBuilder("(");
        for (Class c : classes)
            s.append(getDescriptorForClass(c));
        s.append(')');
        return s + getDescriptorForClass(returnType);
    }
}
