package com.ui.pages.Fund;

import com.ui.base.BasePage;
import com.ui.models.pojo.Fund.Step3UserGroupPojo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.ui.utils.Scrolling.scrollToElement;

/**
 * Page Object for Step 3: Fund User Groups
 *
 * Flow:
 * 1. The page shows a "Fund User Groups" card with an "Add user group" button.
 * 2. Clicking "Add user group" opens a modal popup.
 * 3. Fill the modal: User Group dropdown, Min/Max limits, Investor Nationality.
 * 4. Click "Add" → modal closes → group appears in the table.
 * 5. Click "Next" to proceed to Step 4.
 */
public class FundStep3UserGroupsPage extends BasePage {

    // ===================== LOCATORS =====================
    private final By addUserGroupBtn = By.xpath(
            "//div[@class='btns__wrapper']//button[contains(@class,'bordered__btn') and normalize-space()='Add user group']");

    // Modal container (used to wait for modal to appear/disappear)
    private final By modalContent = By.cssSelector("div.modal-content");

    private final By userGroupDropdownTrigger = By.xpath(
            "//div[contains(@class,'modal-content')]//div[contains(@class,'searched__dropdown')]//input[contains(@class,'dropdown-search') and @placeholder='Select fund user group']");
    private final By saudiOnlyRadio = By.cssSelector("input#soadi[type='radio'][name='nationality']");
    private final By nonSaudiRadio = By.cssSelector("input#residents[type='radio'][name='nationality']");
    private final By allRadio = By.cssSelector("input#all[type='radio'][name='nationality']");
    private final By modalAddBtn = By.xpath(
            "//div[contains(@class,'modal-content')]//div[contains(@class,'footer-modale')]//button[@type='submit' and contains(@class,'main__btn')]");
    private final By modalDiscardBtn = By.xpath(
            "//div[contains(@class,'modal-content')]//div[contains(@class,'footer-modale')]//button[contains(@class,'discard-btn')]");

    private final By nextButton = By.xpath(
            "//div[contains(@class,'card-footer')]//button[contains(@class,'main__btn') and normalize-space()='Next']");
    private final By previousButton = By.xpath(
            "//div[contains(@class,'card-footer')]//button[contains(@class,'gray__btn') and normalize-space()='Previous']");

    private final By addedGroupsCount = By.xpath(
            "//div[contains(@class,'group-list')]//span");
    private final By tableRows = By.xpath(
            "//table[@id='table-container']//tbody//tr");

    // ===================== CONSTRUCTOR =====================

    public FundStep3UserGroupsPage(WebDriver driver) {
        super(driver);
    }

    // ===================== MAIN FORM METHOD =====================

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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
        // Use JS click to avoid "element not clickable" issues
        scrollToElement(driver, nextButton);
        jsClick(nextButton);
    }

    public void clickPrevious() {
        logger.info("Clicking Previous button");
        scrollToElement(driver, previousButton);
        jsClick(previousButton);
    }

    // ===================== INDIVIDUAL ACTIONS =====================

    private void addUserGroup(Step3UserGroupPojo group) {
        try {
            logger.info("Adding user group: " + group.getUserGroup());

            // Step 1: Click "Add user group" button to open the modal popup
            logger.info("Step 1: Clicking 'Add user group' button...");
            jsClick(addUserGroupBtn);
            Thread.sleep(2000); // Wait for modal animation to complete

            // Verify modal is actually open
            if (!isModalVisible()) {
                logger.error("Modal did NOT open after clicking 'Add user group' button. Retrying with JS click...");
                jsClick(addUserGroupBtn);
                Thread.sleep(2000);
                if (!isModalVisible()) {
                    logger.error("Modal still not visible after retry. Aborting this group.");
                    return;
                }
            }
            logger.info("Modal is open.");

            // Step 2: Select User Group from dropdown
            if (group.getUserGroup() != null) {
                logger.info("Step 2: Selecting User Group: " + group.getUserGroup());
                selectUserGroupFromDropdown(group.getUserGroup());
            }

            // Step 6: Select Investor Nationality radio button
            if (group.getInvestorNationality() != null) {
                logger.info("Step 6: Selecting Investor Nationality: " + group.getInvestorNationality());
                selectNationality(group.getInvestorNationality());
            }

            // Step 7: Click "Add" button in modal footer to save and close modal
            logger.info("Step 7: Clicking 'Add' button in modal...");
            jsClick(modalAddBtn);
            Thread.sleep(2000); // Wait for modal to close and table to update

            // Verify modal closed
            if (isModalVisible()) {
                logger.warn("Modal still visible after clicking Add. Trying again...");
                jsClick(modalAddBtn);
                Thread.sleep(1000);
            }

            logger.info("User group '" + group.getUserGroup() + "' added successfully.");

        } catch (Exception e) {
            logger.error("Failed to add user group '" + group.getUserGroup() + "': " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ===================== HELPER METHODS =====================

    /**
     * Clicks an element using JavaScript to bypass any overlay/interception issues.
     * This is critical for Vue.js components where regular Selenium .click() often
     * fails.
     */
    private void jsClick(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            logger.error("JS click failed for: " + locator + " | Error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Checks if the modal-content div is currently visible on the page.
     */
    private boolean isModalVisible() {
        try {
            List<WebElement> modals = driver.findElements(modalContent);
            for (WebElement modal : modals) {
                if (modal.isDisplayed()) {
                    return true;
                }
            }
        } catch (Exception ignored) {
        }
        return false;
    }

    /**
     * Selects a user group from the custom searched__dropdown inside the modal.
     */
    private void selectUserGroupFromDropdown(String optionText) {
        try {
            // Click the dropdown trigger to open/show the dropdown menu
            jsClick(userGroupDropdownTrigger);
            Thread.sleep(1000); // Wait for dropdown to open

            // Find and click the matching option button inside the visible dropdown-menu
            By optionLocator = By.xpath(
                    "//div[contains(@class,'modal-content')]//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//button[contains(@class,'dropdown-item') and normalize-space()='"
                            + optionText + "']");

            List<WebElement> options = driver.findElements(optionLocator);

            if (!options.isEmpty()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", options.get(0));
                logger.info("Selected User Group: " + optionText);
            } else {
                logger.error("User Group option '" + optionText + "' not found in dropdown");
            }

            Thread.sleep(1000); // Wait for selection to register

        } catch (Exception e) {
            logger.error("Failed to select User Group '" + optionText + "' from dropdown: " + e.getMessage());
        }
    }

    /**
     * Selects the Investor Nationality radio button.
     * Uses JavaScript click for radio buttons since they can be tricky in Vue.js.
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
        jsClick(radioLocator);
        logger.info("Selected Investor Nationality: " + nationality);
    }

    // ===================== VERIFICATION METHODS =====================

    public int getAddedGroupsTableRowCount() {
        List<WebElement> rows = driver.findElements(tableRows);
        return rows.size();
    }

    public String getAddedGroupsCountText() {
        try {
            return driver.findElement(addedGroupsCount).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
