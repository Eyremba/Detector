package me.zero.detector.scan;

import me.zero.detector.scan.scanner.AgentScanner;
import me.zero.detector.scan.scanner.ConsStringScanner;
import me.zero.detector.util.manage.Manager;

/**
 * Created by Brady on 1/5/2017.
 */
public class Scanners extends Manager<ClassScanner> {

    private Scanners() {
        this.add(
                new AgentScanner(),
                new ConsStringScanner()
        );
    }
}
