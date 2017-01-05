package me.zero.detector.data;

import java.lang.reflect.Method;

/**
 * Created by Brady on 1/4/2017.
 */
public class MethodInfo {

    private String name;
    private String descriptor;

    public MethodInfo(String name, String descriptor) {
        this.name = name;
        this.descriptor = descriptor;
    }

    public MethodInfo(String name, Class<?> returnType, Class<?>... parameterTypes) {
        this(name, Descriptor.getDescriptor(returnType, parameterTypes));
    }

    public String getName() {
        return this.name;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public static MethodInfo from(Method method) {
        return new MethodInfo(method.getName(), Descriptor.getMethodDescriptor(method));
    }

    public boolean matches(MethodInfo other) {
        return this.getName().equalsIgnoreCase(other.getName()) && this.getDescriptor().equalsIgnoreCase(other.getDescriptor());
    }
}
