package me.zero.detector.inject;

import me.zero.detector.util.OperatingSystem;
import me.zero.detector.util.manage.type.Loadable;
import static me.zero.detector.util.OperatingSystem.*;

/**
 * Created by Brady on 1/6/2017.
 */
public class AttachLib implements Loadable {

    @Override
    public void load() {
        OperatingSystem os = OperatingSystem.getOperatingSystem();
        if (os == UNKNOWN)
            return;

    }
}
