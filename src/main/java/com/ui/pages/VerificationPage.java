package com.ui.pages;

import com.ui.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.ui.utils.SendKeys.sendKeys;
import static com.ui.utils.ClickAction.click;
import static com.ui.utils.WaitUtils.waitForElementToBeVisible;

/**
 * Page Object for OTP Verification
 */
public class VerificationPage extends BasePage {

    private final By checkButton = By.xpath("//button[contains(text(),'Check')]");
    private final By verificationTitle = By.xpath("//h1[contains(text(),'Verification code')]");

    public VerificationPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Check if verification page is displayed
     */
    public boolean isDisplayed() {
        try {
            waitForElementToBeVisible(driver, verificationTitle);
            return driver.findElement(verificationTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Enter 6-digit OTP
     */
    public void enterOtp(String otp) {
        if (otp == null || otp.length() != 6) {
            throw new IllegalArgumentException("OTP must be exactly 6 digits");
        }

        logger.info("Entering OTP: " + otp);

        char[] digits = otp.toCharArray();
        for (int i = 0; i < 6; i++) {
            By otpField = By.id("otp-" + i);
            sendKeys(driver, otpField, String.valueOf(digits[i]));
        }
    }

    /**
     * Click Check button
     */
    public void clickCheck() {
        logger.info("Clicking Check button");
        click(driver, checkButton);
    }
}
