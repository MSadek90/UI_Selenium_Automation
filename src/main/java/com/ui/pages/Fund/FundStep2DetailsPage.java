package com.ui.pages.Fund;

import com.ui.base.BasePage;
import com.ui.models.pojo.Fund.Step2FundDetailsPojo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.ui.utils.ClickAction.click;
import static com.ui.utils.Scrolling.scrollToElement;

/**
 * Page Object for Step 2: Fund Details
 * Handles Dividend Policy, Risk Classification, Subscription & Redemption
 * Details.
 */
public class FundStep2DetailsPage extends BasePage {

    // ===================== LOCATORS =====================

    // --- General Info ---
    // Custom dropdown: Dividend Policy
    private final By dividendPolicyDropdownTrigger = By.xpath(
            "//p[text()='Dividend Policy']/following-sibling::div//input[contains(@class,'dropdown-search')]");

    // Custom dropdown: Risk Classification
    private final By riskClassificationDropdownTrigger = By.xpath(
            "//p[text()='Risk classification']/following-sibling::div//input[contains(@class,'dropdown-search')]");

    // --- Subscription Details ---
    private final By minSubscriptionCapitalInput = By.xpath(
            "//input[@placeholder='Enter minimum Subscription Capital']");
    private final By minAdditionalSubscriptionInput = By.xpath(
            "//input[@placeholder='Enter minimum additional subscription amount']");

    // --- Redemption Details ---
    private final By minRedemptionAmountInput = By.xpath(
            "//input[@placeholder='Enter minimum Redemption Amount']");

    // --- Buttons ---
    private final By nextButton = By.cssSelector("button.main__btn");
    private final By previousButton = By.cssSelector("button.gray__btn");

    // ===================== CONSTRUCTOR =====================

    public FundStep2DetailsPage(WebDriver driver) {
        super(driver);
    }

    // ===================== MAIN FORM METHOD =====================

    public void fillStep2Form(Step2FundDetailsPojo data) {
        logger.info("Filling Step 2: Fund Details Form");
        selectDividendPolicy(data.getDividendPolicy());
        selectRiskClassification(data.getRiskClassification());
        enterMinSubscriptionCapital(data.getMinSubscriptionCapital());
        enterMinAdditionalSubscription(data.getMinAdditionalSubscription());
        selectSubscriptionAllocationDays(data.getSubscriptionAllocationDays());
        enterMinRedemptionAmount(data.getMinRedemptionAmount());
        selectRedemptionAllocationDays(data.getRedemptionAllocationDays());
        selectRedemptionType(data.getRedemptionType());
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

    public FundStep2DetailsPage selectDividendPolicy(String policy) {
        if (policy == null)
            return this;
        logger.info("Selecting Dividend Policy: " + policy);
        selectCustomDropdown(dividendPolicyDropdownTrigger, policy);
        return this;
    }

    public FundStep2DetailsPage selectRiskClassification(String risk) {
        if (risk == null)
            return this;
        logger.info("Selecting Risk Classification: " + risk);
        selectCustomDropdown(riskClassificationDropdownTrigger, risk);
        return this;
    }

    public FundStep2DetailsPage enterMinSubscriptionCapital(String amount) {
        if (amount == null)
            return this;
        logger.info("Entering Minimum Subscription Capital: " + amount);
        scrollToElement(driver, minSubscriptionCapitalInput);
        WebElement el = driver.findElement(minSubscriptionCapitalInput);
        el.clear();
        el.sendKeys(amount);
        return this;
    }

    public FundStep2DetailsPage enterMinAdditionalSubscription(String amount) {
        if (amount == null)
            return this;
        logger.info("Entering Minimum Additional Subscription: " + amount);
        scrollToElement(driver, minAdditionalSubscriptionInput);
        WebElement el = driver.findElement(minAdditionalSubscriptionInput);
        el.clear();
        el.sendKeys(amount);
        return this;
    }

    public FundStep2DetailsPage selectSubscriptionAllocationDays(List<String> days) {
        if (days == null || days.isEmpty())
            return this;
        logger.info("Selecting Subscription Allocation Days: " + days);
        selectDayButtons("Subscription Allocation", days);
        return this;
    }

    public FundStep2DetailsPage enterMinRedemptionAmount(String amount) {
        if (amount == null)
            return this;
        logger.info("Entering Minimum Redemption Amount: " + amount);
        scrollToElement(driver, minRedemptionAmountInput);
        WebElement el = driver.findElement(minRedemptionAmountInput);
        el.clear();
        el.sendKeys(amount);
        return this;
    }

    public FundStep2DetailsPage selectRedemptionAllocationDays(List<String> days) {
        if (days == null || days.isEmpty())
            return this;
        logger.info("Selecting Redemption Allocation Days: " + days);
        selectDayButtons("Redemption Allocation", days);
        return this;
    }

    public FundStep2DetailsPage selectRedemptionType(String type) {
        if (type == null)
            return this;
        logger.info("Selecting Redemption Type: " + type);
        selectRadioButton(type);
        return this;
    }

    // ===================== HELPER METHODS =====================

    /**
     * Handles custom dropdown components (Dividend Policy, Risk Classification).
     * Options are <button class="dropdown-item"> inside
     * <div class="dropdown-menu">.
     * Same approach as Fund Type in Step 1.
     */
    private void selectCustomDropdown(By triggerLocator, String optionText) {
        try {
            scrollToElement(driver, triggerLocator);

            // Use JavaScript click since the input is readonly and may not pass clickable
            // check
            WebElement trigger = driver.findElement(triggerLocator);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", trigger);
            Thread.sleep(500);

            // Target only the currently VISIBLE dropdown (class 'show')
            By optionLocator = By.xpath(
                    "//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//button[contains(@class,'dropdown-item') and normalize-space()='"
                            + optionText + "']");
            List<WebElement> options = driver.findElements(optionLocator);

            // Fallback: if not found, try clicking the parent div instead
            if (options.isEmpty()) {
                WebElement parent = trigger.findElement(By.xpath("./.."));
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", parent);
                Thread.sleep(500);
                options = driver.findElements(optionLocator);
            }

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
     * Handles day selection buttons (Subscription Allocation / Redemption
     * Allocation).
     * Finds the section by heading text, then clicks the day buttons for desired
     * days.
     * Days that are already selected (class 'selected_day') will be left as-is if
     * they match.
     * Days not in the desired list that are selected will be deselected.
     */
    private void selectDayButtons(String sectionHeading, List<String> desiredDays) {
        try {
            // Find all day buttons within the section identified by its heading
            By sectionLocator = By.xpath(
                    "//h6[contains(text(),'" + sectionHeading + "')]/parent::div");
            scrollToElement(driver, sectionLocator);

            WebElement section = driver.findElement(sectionLocator);
            List<WebElement> dayButtons = section.findElements(By.tagName("button"));

            for (WebElement btn : dayButtons) {
                String dayName = btn.getText().trim();
                boolean isSelected = btn.getAttribute("class").contains("selected_day");
                boolean shouldBeSelected = desiredDays.contains(dayName);

                if (shouldBeSelected && !isSelected) {
                    scrollToElement(driver, btn);
                    btn.click();
                } else if (!shouldBeSelected && isSelected) {
                    scrollToElement(driver, btn);
                    btn.click();
                }
            }
        } catch (Exception e) {
            logger.error("Failed to select days for '" + sectionHeading + "': " + e.getMessage());
        }
    }

    /**
     * Selects a radio button for redemption type (Partial, Fully, Both).
     */
    private void selectRadioButton(String labelText) {
        try {
            By radioLocator = By.xpath(
                    "//label[normalize-space()='" + labelText + "']/preceding-sibling::input[@type='radio'] | " +
                            "//label[normalize-space()='" + labelText + "']");

            List<WebElement> elements = driver.findElements(radioLocator);
            if (!elements.isEmpty()) {
                for (WebElement el : elements) {
                    if (el.getTagName().equals("input")) {
                        scrollToElement(driver, el);
                        el.click();
                        return;
                    }
                }
                // Fallback: click the label
                scrollToElement(driver, elements.get(0));
                elements.get(0).click();
            } else {
                logger.error("Radio button '" + labelText + "' not found");
            }
        } catch (Exception e) {
            logger.error("Failed to select radio button '" + labelText + "': " + e.getMessage());
        }
    }
}
