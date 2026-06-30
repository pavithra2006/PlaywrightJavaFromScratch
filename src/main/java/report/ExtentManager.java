package report;

import com.aventstack.extentreports.ExtentTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

//cant be inherited
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentManager {
    private static final ThreadLocal<ExtentTest> extTest = new ThreadLocal<>();

    public static ExtentTest getTest() { // making it default so that these methods cant be used outside this package-- making framework more robust
        return extTest.get();
    }

    static void setTest(ExtentTest ext) {
        if (Objects.nonNull(ext))
            extTest.set(ext);
    }

   public static void unloadTest() {
        extTest.remove();
    }

}
