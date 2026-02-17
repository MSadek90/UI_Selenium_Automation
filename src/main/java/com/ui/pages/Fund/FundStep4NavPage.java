package com.ui.pages.Fund;

import com.ui.base.BasePage;
import com.ui.models.pojo.Fund.Step4NavPojo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.ui.utils.ClickAction.click;
import static com.ui.utils.Scrolling.scrollToElement;

/**
 * Page Object for Step 4: NAV Configuration.
 *
 * Fields:
 * 1. NAV Frequency — searched__dropdown (Daily / Weekly / Monthly)
 * HTML: <input readonly class="dropdown-search" placeholder="Select NAV
 * Frequency">
 * Options: <button class="dropdown-item">Daily</button> etc.
 *
 * 2. Time Zone — Choices.js dropdown (#time_zone)
 * HTML: <div class="choices"> wrapping <select id="time_zone">
 * Options: <div class="choices__item choices__item--choice">GMT+03:00 -
 * Asia/Riyadh</div>
 *
 * 3. Cut Off Time — Element Plus time picker
 * HTML: <input class="el-input__inner" placeholder="Select cut off time">
 *
 * 4. Weekend Days — Toggle buttons (not_selected / selected)
 * HTML:
 * <div class="selected_days"><button class="not_selected">Friday</button>...
 *
 * 5. NAV Holidays — Multi-select checkbox dropdown
 * HTML: <div class="multi-select-dropdown"> with checkboxes + Apply button
 *
 * 6. Next button — <button class="main__btn submit-btn">Next</button>
 */
public class FundStep4NavPage extends BasePage {

    // ===================== LOCATORS =====================

    // NAV Frequency: searched__dropdown
    // Uses placeholder text for reliable matching
    private final By navFrequencyDropdownTrigger = By.xpath(
            "//input[@placeholder='Select NAV Frequency']");

    // Time Zone: Choices.js dropdown
    // Use the known select ID to locate the Choices.js wrapper
    private final By timeZoneChoicesDiv = By.xpath(
            "//select[@id='time_zone']/ancestor::div[contains(@class,'choices')]");

    // Cut Off Time: Element Plus time picker
    private final By cutOffTimeInput = By.cssSelector(
            "input.el-input__inner[placeholder='Select cut off time']");

    // NAV Holidays: multi-select checkbox dropdown
    private final By navHolidaysInput = By.cssSelector(
            "input[placeholder='Select NAV holidays']");

    // NAV Holidays: Apply button inside the dropdown
    private final By navHolidaysApplyBtn = By.xpath(
            "//div[contains(@class,'multi-select-dropdown')]//div[contains(@class,'actions')]//button[contains(@class,'main__btn')]");

    // Next button
    private final By nextButton = By.className("main__btn");

    // ===================== CONSTRUCTOR =====================

    public FundStep4NavPage(WebDriver driver) {
        super(driver);
    }

    // ===================== MAIN FORM METHODS =====================

    /**
     * Fills the entire Step 4 form.
     */
    public FundStep4NavPage fillStep4Form(Step4NavPojo data) {
        String navFrequency = data.getNavFrequency();
        String timeZone = data.getTimeZone();

        // 1. NAV Frequency
        if (navFrequency != null) {
            logger.info("Selecting NAV Frequency: " + navFrequency);
            selectCustomDropdown(navFrequencyDropdownTrigger, navFrequency);
        }

        // 2. Time Zone
        if (timeZone != null) {
            logger.info("Selecting Time Zone: " + timeZone);
            selectChoicesDropdown(timeZoneChoicesDiv, timeZone);
        }

        // 3. Cut Off Time
        String cutOffTime = data.getCutOffTime();
        if (cutOffTime != null) {
            logger.info("Setting Cut Off Time: " + cutOffTime);
            setTimePicker(cutOffTimeInput, cutOffTime);
        }

        // 4. Weekend Days
        List<String> weekendDays = data.getWeekendDays();
        if (weekendDays != null && !weekendDays.isEmpty()) {
            logger.info("Selecting Weekend Days: " + weekendDays);
            for (String day : weekendDays) {
                selectWeekendDay(day);
            }
        }

        // 5. NAV Holidays (Officially holidays)
        List<String> navHolidays = data.getNavHolidays();
        if (navHolidays != null && !navHolidays.isEmpty()) {
            logger.info("Selecting NAV Holidays: " + navHolidays);
            selectNavHolidays(navHolidays);
        }

        return this;
    }

    /**
     * Clicks the Next button to proceed to Step 5.
     */
    public void clickNext() {
        logger.info("Clicking Next on Step 4 NAV...");
        scrollToElement(driver, nextButton);
        jsClick(nextButton);
        logger.info("Step 4 NAV — Next clicked.");
    }

    // ===================== HELPER METHODS =====================

    /**
     * Handles custom searched__dropdown components.
     * Options are <button class="dropdown-item"> inside
     * <div class="dropdown-menu">.
     * Clicks the trigger to open, then clicks the matching button option.
     */
    private void selectCustomDropdown(By triggerLocator, String optionText) {
        try {
            scrollToElement(driver, triggerLocator);
            click(driver, triggerLocator);
            Thread.sleep(500); // Wait for dropdown to open

            // Target only the currently VISIBLE dropdown (class 'show')
            By optionLocator = By.xpath(
                    "//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//button[contains(@class,'dropdown-item') and normalize-space()='"
                            + optionText + "']");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
            jsClick(option);
            logger.info("Selected dropdown option: " + optionText);
        } catch (Exception e) {
            logger.error("Failed to select '" + optionText + "' from dropdown: " + e.getMessage());
        }
    }

    /**
     * Handles Choices.js select dropdowns (#time_zone).
     *
     * This is a plain scrollable dropdown (no search input).
     * 1. Click the container to open the dropdown list
     * 2. Locate the option by its data-value attribute
     * 3. Scroll the option into view within the dropdown
     * 4. Click the option
     *
     * Supports both formats in JSON:
     * - Full display text: "GMT+02:00 - Africa/Cairo"
     * - Just the value: "Africa/Cairo"
     */
    private void selectChoicesDropdown(By choicesDivLocator, String optionValue) {
        try {
            // Extract timezone ID if full display text was provided
            // e.g., "GMT+02:00 - Africa/Cairo" -> "Africa/Cairo"
            String dataValue = optionValue;
            if (optionValue.contains(" - ")) {
                dataValue = optionValue.substring(optionValue.indexOf(" - ") + 3);
            }

            // 1. Click the Choices.js container to open the dropdown
            scrollToElement(driver, choicesDivLocator);
            WebElement choicesDiv = driver.findElement(choicesDivLocator);
            choicesDiv.click();
            Thread.sleep(1000); // Wait for dropdown list to fully render

            // 2. Find the option by data-value attribute and scroll into view
            By optionLocator = By.cssSelector(
                    "div.choices__list--dropdown div[data-value='" + dataValue + "']");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(optionLocator));

            // 3. Scroll the option into view within the dropdown scroll container
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});", option);
            Thread.sleep(300);

            // 4. Click the option
            option.click();

            logger.info("Selected Time Zone: " + optionValue);

        } catch (Exception e) {
            logger.error("Failed to select '" + optionValue + "' from Choices.js dropdown: " + e.getMessage());
        }
    }

    /**
     * Sets an Element Plus time picker value using JavaScript.
     * Time format from JSON: HH:mm (e.g., "14:00")
     *
     * Uses the same native-input-setter + event dispatch approach that works
     * reliably with Vue.js / Element Plus controlled inputs.
     */
    private void setTimePicker(By timeInputLocator, String time) {
        try {
            scrollToElement(driver, timeInputLocator);
            WebElement timeInput = driver.findElement(timeInputLocator);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "var input = arguments[0];" +
                            "input.focus();" +
                            "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;"
                            +
                            "nativeInputValueSetter.call(input, arguments[1]);" +
                            "input.dispatchEvent(new Event('input', { bubbles: true }));" +
                            "input.dispatchEvent(new Event('change', { bubbles: true }));" +
                            "input.blur();",
                    timeInput, time);

            logger.info("Set Cut Off Time: " + time);

        } catch (Exception e) {
            logger.error("Failed to set cut off time '" + time + "': " + e.getMessage());
        }
    }

    /**
     * Clicks a Weekend Day toggle button.
     * Buttons are inside <div class="selected_days"> with text matching the day
     * name.
     * Clicking toggles the class between 'not_selected' and 'selected'.
     */
    private void selectWeekendDay(String dayName) {
        try {
            By dayButton = By.xpath(
                    "//div[contains(@class,'selected_days')]//button[normalize-space()='" + dayName + "']");
            scrollToElement(driver, dayButton);
            jsClick(dayButton);
            logger.info("Selected weekend day: " + dayName);
        } catch (Exception e) {
            logger.error("Failed to select weekend day '" + dayName + "': " + e.getMessage());
        }
    }

    /**
     * Handles the NAV Holidays multi-select checkbox dropdown.
     * Flow: open dropdown -> check each matching holiday -> click Apply.
     *
     * Checkboxes are inside <div class="scroll-area"> with labels containing
     * <span> text matching the holiday name.
     */
    private void selectNavHolidays(List<String> holidays) {
        try {
            // Open the dropdown
            scrollToElement(driver, navHolidaysInput);
            jsClick(navHolidaysInput);
            Thread.sleep(500); // Wait for dropdown to open

            // Check each holiday by label text
            for (String holiday : holidays) {
                By checkboxLabel = By.xpath(
                        "//div[contains(@class,'multi-select-dropdown')]//div[contains(@class,'scroll-area')]" +
                                "//label[.//span[normalize-space()='" + holiday + "']]");
                List<WebElement> labels = driver.findElements(checkboxLabel);

                if (!labels.isEmpty()) {
                    jsClick(labels.get(0));
                    logger.info("Checked NAV Holiday: " + holiday);
                } else {
                    logger.error("NAV Holiday '" + holiday + "' not found in dropdown");
                }
            }

            // Click Apply to confirm selection
            jsClick(navHolidaysApplyBtn);
            logger.info("NAV Holidays — Apply clicked.");

        } catch (Exception e) {
            logger.error("Failed to select NAV Holidays: " + e.getMessage());
        }
    }

    /**
     * JavaScript click — bypasses Vue.js element interception issues.
     */
    private void jsClick(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /**
     * JavaScript click on a WebElement directly.
     */
    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
