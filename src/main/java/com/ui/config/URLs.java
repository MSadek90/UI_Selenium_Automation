package com.ui.config;

/**
 * URL constants for different pages
 */
public class URLs {
    
    public static final String LOGIN_URL = EnvConfig.BASE_URL + "/login";
    public static final String DASHBOARD_URL = EnvConfig.BASE_URL + "/dashboard";
    public static final String UNITS_URL = EnvConfig.BASE_URL + "/units";
    public static final String FINANCE_URL = EnvConfig.BASE_URL + "/finance";
    public static final String REAL_ESTATE_FUND_URL = EnvConfig.BASE_URL + "/real-estate-fund";
    public static final String KYC_MANAGEMENT_URL = EnvConfig.BASE_URL + "/kyc-management";
    
    private URLs() {
        // Private constructor to prevent instantiation
    }
}
