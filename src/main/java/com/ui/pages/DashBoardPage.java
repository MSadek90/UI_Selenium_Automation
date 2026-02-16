package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ui.base.BasePage;
import com.ui.pages.KYC.ListPages.KYCPagesPage;
import com.ui.utils.ClickAction;
import com.ui.utils.Scrolling;
import com.ui.utils.WaitUtils;

public class DashBoardPage extends BasePage {

    private final By dashboardTilte = By.className("main_title");

    // Sidebar Navigation Locators
    private By kycManagementMenu = By
            .xpath("//span[contains(text(),'KYC Management') or contains(text(),'KYC Managment')]");
    private By kycPagesLink = By.xpath("//a[@href='/dashboard/kyc-pages']");

    public DashBoardPage(WebDriver driver) {
        super(driver);
    }

    /*
     * verify dashboard page presence
     */
    public boolean isDashboardTtileExisted() {
        WaitUtils.waitForElementToBeVisible(driver, dashboardTilte);
        return driver.findElement(dashboardTilte).isDisplayed();
    }

    /*
     * Navigate to KYC Pages Page
     */
    public void clickKycManagment() {
        Scrolling.scrollToElement(driver, kycManagementMenu);
        ClickAction.click(driver, kycManagementMenu);
    }

    public void navigateToCreateNewFund() {
        logger.info("Navigating to Create New Fund Page");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.get("https://saaf.neop.co/dashboard/new-fund");
    }

    public KYCPagesPage clickKycPagesLink() {
        ClickAction.click(driver, kycPagesLink);
        return new KYCPagesPage(driver);
    }

}
