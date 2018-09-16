import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.time.Duration;


/**
 * @author Adam Ostrozlik
 * @version 1.0
 * @since 16.09.2018
 **/
@DisplayName("Basic test pack")
@Execution(ExecutionMode.CONCURRENT)
public class Basics {

    @BeforeAll
    public static void init() {
        System.out.println("Before all");
    }

    @BeforeEach
    public void initBeforeEach() {
        System.out.println("Before each");
    }

    @Test
    @DisplayName("Example test")
    public void test1() {
        Assertions.assertEquals(1,1, "Some message");
        Assertions.assertAll("Assert all",
                () -> Assertions.assertEquals(1,1),
                () -> Assertions.assertEquals(1,1));
        Assertions.assertThrows(NullPointerException.class, () -> {
            throw new NullPointerException("Test nullptr exception");
        });
    }

    @RepeatedTest(10)
    public void testTimeout() {
        String msg = Assertions.assertTimeout(Duration.ofMillis(1000), () -> {
            Thread.sleep(995);
            return "Hello World";
        });
    }
    @EnabledOnOs(OS.WINDOWS)
    @EnabledOnJre(JRE.JAVA_8)
    @EnabledIf("2 * 3 == 6")
    @Tag("Assume test")
    public void assume() {
        Assumptions.assumeTrue(true);
        System.out.println("Rest of the test");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("After each");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("After all");
    }

}
