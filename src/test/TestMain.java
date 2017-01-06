package test;

import me.zero.detector.scan.scanner.AgentScanner;
import me.zero.detector.scan.scanner.ConsStringScanner;

import java.lang.instrument.Instrumentation;

/**
 * Created by Brady on 1/6/2017.
 */
public class TestMain extends TestObject {

    // Testing Stuff
    public TestMain() { super("aimbot"); }
    public static void agentmain(String args, Instrumentation instrumentation) { }

    public static void main(String[] args) {
        System.out.println(new AgentScanner().scan(TestMain.class));
        System.out.println(new ConsStringScanner().scan(TestMain.class));
    }
}
