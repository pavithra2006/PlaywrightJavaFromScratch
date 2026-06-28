package report;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentLogger {

    public static void info(String message) {
        ExtentManager.getTest().info(message);
    }
    public static void pass(String message) {
        ExtentManager.getTest().pass(message);

    }
    public static void fail(String message) {
        ExtentManager.getTest().fail(message);
    }
}

