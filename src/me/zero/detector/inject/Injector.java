package me.zero.detector.inject;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Brady on 1/6/2017.
 */
public class Injector {

    private static final String VANILLA = "net.minecraft.client.Main";
    private static final String FORGE = "net.minecraft.launchwrapper.Launch";

    private VirtualMachineDescriptor target;

    public List<VirtualMachineDescriptor> discover() {
        return new ArrayList<>(VirtualMachine.list()).stream().filter(this::isGoodVM).collect(Collectors.toList());
    }

    private boolean isGoodVM(VirtualMachineDescriptor vm) {
        return vm.displayName().startsWith(FORGE) || vm.displayName().startsWith(VANILLA);
    }
}
