package com.ui.config;

/**
 * Browser configuration constants
 */
public class BrowserConfig {
    
    public static final String BROWSER = System.getProperty("browser", "chrome");
    public static final boolean HEADLESS = Boolean.parseBoolean(System.getProperty("headless", "false"));
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 20;
    public static final int PAGE_LOAD_TIMEOUT = 30;
    
    private BrowserConfig() {
        // Private constructor to prevent instantiation
    }
}
