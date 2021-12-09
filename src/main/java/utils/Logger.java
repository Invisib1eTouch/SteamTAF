package utils;

import org.apache.logging.log4j.LogManager;

public class Logger {

    public static final org.apache.logging.log4j.Logger log;

    static {
        log = LogManager.getLogger();
    }
}
