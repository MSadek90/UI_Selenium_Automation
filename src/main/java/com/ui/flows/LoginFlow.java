package com.ui.flows;

import org.openqa.selenium.WebDriver;

import com.ui.models.pojo.Login.LoginPojo;
import com.ui.pages.LoginPage;
import com.ui.pages.VerificationPage;
import com.ui.utils.LoggerUtil;

public class LoginFlow {

    private static final LoggerUtil logger = LoggerUtil.getLogger(LoginFlow.class);

    private final LoginPage loginPage;

    public LoginFlow(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
    }

    public void loginExecution(LoginPojo testCase) {

        logger.info("Starting loginExecution for user: "
                + testCase.getUsername());

        loginPage.openUrl();
        loginPage.enterUsername(testCase.getUsername());
        loginPage.enterPassword(testCase.getPassword());
        loginPage.clickLogin();

        logger.info("Login action clicked for user: " + testCase.getUsername());

        // Handle OTP Verification
        VerificationPage verificationPage = new VerificationPage(loginPage.getDriver());
        if (verificationPage.isDisplayed()) {
            logger.info("Verification Page displayed. Entering OTP.");
            String otp = testCase.getOtp() != null ? testCase.getOtp() : "111111";
            verificationPage.enterOtp(otp);
            verificationPage.clickCheck();
        }

        logger.info("Login Flow completed for user: " + testCase.getUsername());
    }
}
