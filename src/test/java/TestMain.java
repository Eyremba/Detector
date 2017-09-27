import com.google.common.collect.ImmutableList;
import me.zero.detector.data.ClassPool;
import me.zero.detector.scan.ClassScanner;
import me.zero.detector.scan.scanner.AgentScanner;
import me.zero.detector.scan.scanner.AnnotationStringScanner;
import me.zero.detector.scan.scanner.ConstructorStringScanner;
import org.objectweb.asm.tree.ClassNode;

import java.lang.instrument.Instrumentation;
import java.util.List;

/**
 * Created by Brady on 1/6/2017.
 */
@TestAnnotation("aimbot")
public class TestMain extends TestObject {

    // Testing Stuff
    public TestMain() { super("aimbot"); }
    public static void agentmain(String args, Instrumentation instrumentation) {}

    public static void main(String[] args) {

        // Node of the class being scanned
        ClassNode node = ClassPool.getPool().getNode(TestMain.class);

        // List of class scanners
        List<ClassScanner> scanners = new ImmutableList.Builder<ClassScanner>()
                .add(new AgentScanner())
                .add(new ConstructorStringScanner())
                .add(new AnnotationStringScanner())
                .build();

        // Scan and print out results
        scanners.stream().map(scanner -> scanner.scan(node)).forEach(results -> results.forEach(result -> System.out.println(result.getResult())));
    }
}
