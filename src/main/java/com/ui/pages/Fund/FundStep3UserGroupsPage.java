package com.ui.pages.Fund;

import com.ui.base.BasePage;
import com.ui.models.pojo.Fund.Step3UserGroupPojo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.ui.utils.ClickAction.click;
import static com.ui.utils.Scrolling.scrollToElement;

/**
 * Page Object for Step 3: Fund User Groups
 * Handles adding user groups via popup modal.
 */
public class FundStep3UserGroupsPage extends BasePage {

    // ===================== LOCATORS =====================

    // "Add user group" button on the main page
    private final By addUserGroupBtn = By.cssSelector("button.bordered__btn");

    // --- Popup Modal Locators ---
    private final By userGroupDropdownTrigger = By.xpath(
            "//div[contains(@class,'modal-content')]//p[text()='User Group']/following-sibling::div//input[contains(@class,'dropdown-search')]");

    private final By minimumLimitInput = By.xpath(
            "//div[contains(@class,'modal-content')]//input[@placeholder='Enter Minimum limit']");

    private final By maximumLimitInput = By.xpath(
            "//div[contains(@class,'modal-content')]//input[@placeholder='Enter maximum limit']");

    // Investor Nationality radio buttons
    private final By saudiOnlyRadio = By.cssSelector("input#soadi");
    private final By nonSaudiRadio = By.cssSelector("input#residents");
    private final By allRadio = By.cssSelector("input#all");

    // Modal buttons
    private final By addBtn = By
            .xpath("//div[contains(@class,'modal-content')]//button[@type='submit' and contains(@class,'main__btn')]");
    private final By discardBtn = By
            .xpath("//div[contains(@class,'modal-content')]//button[contains(@class,'discard-btn')]");

    // --- Page Buttons ---
    private final By nextButton = By.cssSelector("button.main__btn");
    private final By previousButton = By.cssSelector("button.gray__btn");

    // ===================== CONSTRUCTOR =====================

    public FundStep3UserGroupsPage(WebDriver driver) {
        super(driver);
    }

    // ===================== MAIN FORM METHOD =====================

    /**
     * Adds all user groups from the list, then clicks Next.
     */
    public void fillStep3Form(List<Step3UserGroupPojo> groups) {
        logger.info("Filling Step 3: Fund User Groups");
        if (groups == null || groups.isEmpty()) {
            logger.info("No user groups to add, skipping.");
            return;
        }
        for (Step3UserGroupPojo group : groups) {
            addUserGroup(group);
        }
    }

    public void clickNext() {
        logger.info("Clicking Next button");
        scrollToElement(driver, nextButton);
        click(driver, nextButton);
    }

    public void clickPrevious() {
        logger.info("Clicking Previous button");
        scrollToElement(driver, previousButton);
        click(driver, previousButton);
    }

    // ===================== INDIVIDUAL ACTIONS =====================

    /**
     * Clicks "Add user group" button, fills the popup form, and clicks "Add".
     */
    private void addUserGroup(Step3UserGroupPojo group) {
        try {
            logger.info("Adding user group: " + group.getUserGroup());

            // Click "Add user group" button to open modal
            scrollToElement(driver, addUserGroupBtn);
            click(driver, addUserGroupBtn);
            Thread.sleep(500);

            // Select User Group from dropdown
            if (group.getUserGroup() != null) {
                selectCustomDropdown(userGroupDropdownTrigger, group.getUserGroup());
            }

            // Enter Minimum limit
            if (group.getMinimumLimit() != null) {
                WebElement minEl = driver.findElement(minimumLimitInput);
                minEl.clear();
                minEl.sendKeys(group.getMinimumLimit());
            }

            // Enter Maximum limit
            if (group.getMaximumLimit() != null) {
                WebElement maxEl = driver.findElement(maximumLimitInput);
                maxEl.clear();
                maxEl.sendKeys(group.getMaximumLimit());
            }

            // Select Investor Nationality
            if (group.getInvestorNationality() != null) {
                selectNationality(group.getInvestorNationality());
            }

            // Click "Add" button to save
            click(driver, addBtn);
            Thread.sleep(500);

            logger.info("User group '" + group.getUserGroup() + "' added successfully.");
        } catch (Exception e) {
            logger.error("Failed to add user group '" + group.getUserGroup() + "': " + e.getMessage());
        }
    }

    // ===================== HELPER METHODS =====================

    /**
     * Handles custom dropdown inside the modal.
     * Same approach as Step 1 & Step 2: click trigger → click visible dropdown-item
     * button.
     */
    private void selectCustomDropdown(By triggerLocator, String optionText) {
        try {
            scrollToElement(driver, triggerLocator);
            click(driver, triggerLocator);
            Thread.sleep(500);

            By optionLocator = By.xpath(
                    "//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//button[contains(@class,'dropdown-item') and normalize-space()='"
                            + optionText + "']");
            List<WebElement> options = driver.findElements(optionLocator);

            if (!options.isEmpty()) {
                options.get(0).click();
            } else {
                logger.error("Option '" + optionText + "' not found in dropdown");
            }
        } catch (Exception e) {
            logger.error("Failed to select '" + optionText + "' from dropdown: " + e.getMessage());
        }
    }

    /**
     * Selects the Investor Nationality radio button.
     */
    private void selectNationality(String nationality) {
        By radioLocator;
        switch (nationality) {
            case "For Saudi Nationals Only":
                radioLocator = saudiOnlyRadio;
                break;
            case "Non saudi":
                radioLocator = nonSaudiRadio;
                break;
            case "All":
                radioLocator = allRadio;
                break;
            default:
                logger.error("Unknown nationality option: " + nationality);
                return;
        }
        scrollToElement(driver, radioLocator);
        click(driver, radioLocator);
    }
}
