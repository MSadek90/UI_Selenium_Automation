package com.ui.config;

/**
 * Environment configuration constants
 */
public class EnvConfig {
    
    public static final String ENVIRONMENT = System.getProperty("env", "test");
    public static final String BASE_URL = getBaseUrl();
    
    private static String getBaseUrl() {
        switch (ENVIRONMENT.toLowerCase()) {
            case "dev":
                return "https://dev.saaf.neop.co";
            case "staging":
                return "https://staging.saaf.neop.co";
            case "test":
                return "https://saaf.neop.co";
            case "prod":
                return "https://prod.saaf.neop.co";
            default:
                return "https://saaf.neop.co";
        }
    }
    
    private EnvConfig() {
        //singleton pattern
        // Private constructor to prevent instantiation
    }
}
