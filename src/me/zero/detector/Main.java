package me.zero.detector;

import me.zero.detector.scan.scanner.ConsStringScanner;
import test.TestObj;

import java.lang.instrument.Instrumentation;

/**
 * Created by Brady on 1/3/2017.
 */
public class Main extends TestObj {

    // Testing Stuff
    public Main() { super("lol"); }
    public static void agentmain(String args, Instrumentation instrumentation) {}

    public static void main(String[] args) {
        System.out.println(new ConsStringScanner().scan(Main.class));
    }
}
