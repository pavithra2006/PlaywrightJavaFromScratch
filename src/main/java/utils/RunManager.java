package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class RunManager {

    private RunManager(){}

    private static final String RUN_ID =
            LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

    public static String getRunId() {
        return RUN_ID;
    }
}