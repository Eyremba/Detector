package me.zero.detector.data;

import java.lang.reflect.Method;

/**
 * Created by Brady on 1/5/2017.
 */
public class Descriptor {

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
            throw new RuntimeException("Unrecognized primitive "+c);
        }
        if (c.isArray()) return c.getName().replace('.', '/');
        return ('L' + c.getName() + ';').replace('.', '/');
    }

    public static String getDescriptor(Class<?> returnType, Class<?>... classes) {
        String s = "(";
        for (Class c : classes)
            s += getDescriptorForClass(c);
        s += ')';
        return s + getDescriptorForClass(returnType);
    }

    public static String getMethodDescriptor(Method m) {
        return getDescriptor(m.getReturnType(), m.getParameterTypes());
    }
}
