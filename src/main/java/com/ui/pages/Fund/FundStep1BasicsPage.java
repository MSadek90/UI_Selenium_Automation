package com.ui.pages.Fund;

import com.ui.base.BasePage;
import com.ui.models.pojo.Fund.Step1FundBasicsPojo;
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
 * Page Object for Step 1: Fund Basics
 */
public class FundStep1BasicsPage extends BasePage {

    // ===================== LOCATORS =====================

    // --- General Info ---
    private final By fundCodeInput = By.xpath("//input[@placeholder='Enter fund code']");

    // Custom dropdown trigger: Fund Type
    private final By fundTypeDropdownTrigger = By.xpath(
            "//p[text()='Fund Type']/following-sibling::div//input[contains(@class,'dropdown-search')]");

    // Text inputs: Fund Names
    private final By fundNameEnInput = By.xpath("//input[@placeholder='Enter fund Name (EN)']");
    private final By fundNameArInput = By.xpath("//input[@placeholder='Enter fund Name (AR)']");

    // Custom dropdown trigger: Fund Structure
    private final By fundStructureDropdownTrigger = By.xpath(
            "//p[text()='Fund Structure']/following-sibling::div//input[contains(@class,'dropdown-search')]");

    // Choices.js dropdown: Fund Currency
    private final By fundCurrencyChoicesDiv = By.xpath(
            "//p[contains(@class,'form-label') and contains(text(),'Fund currency')]/following-sibling::div[contains(@class,'choices')]");

    // Date pickers (Element Plus)
    private final By inceptionDateInput = By.xpath(
            "//p[text()='Inception Date']/following-sibling::div//input[contains(@class,'el-input__inner')]");
    private final By endDateInput = By.xpath(
            "//p[text()='End Date']/following-sibling::div//input[contains(@class,'el-input__inner')]");

    // Multi-select: Fund Manager
    private final By fundManagerDropdownTrigger = By.xpath(
            "//p[text()='Fund manager']/following-sibling::div//input");

    // Multi-select: Fund Broker
    private final By fundBrokerDropdownTrigger = By.xpath(
            "//p[text()='Fund broker']/following-sibling::div//input");

    // Choices.js dropdown: Fund Category
    private final By fundCategoryChoicesDiv = By.xpath(
            "//p[contains(@class,'form-label') and contains(text(),'Fund category')]/following-sibling::div[contains(@class,'choices')]");

    // --- Fund Capital Details ---
    private final By initialCapitalInput = By.xpath("//input[@placeholder='Enter Initial Capital']");
    private final By aggregateCapitalInput = By.xpath("//input[@placeholder='Enter aggregate capital']");
    private final By unitPriceInput = By.xpath("//input[@placeholder='Enter Unite Price']");

    // --- External Parties (Multi-select) ---
    private final By custodianDropdownTrigger = By.xpath(
            "//p[text()='Custodian']/following-sibling::div//input");
    private final By financialAuditorDropdownTrigger = By.xpath(
            "//p[text()='Financial Auditor']/following-sibling::div//input");

    // --- Finance Settings ---
    private final By cutOffDateInput = By.xpath(
            "//p[text()='Cut Off Date']/following-sibling::div//input[contains(@class,'el-input__inner')]");
    private final By calculateLevelDropdownTrigger = By.xpath(
            "//p[text()='Calculate Level']/following-sibling::div//input[contains(@class,'dropdown-search')]");
    private final By annualDaysDropdownTrigger = By.xpath(
            "//p[text()='Annual Days']/following-sibling::div//input[contains(@class,'dropdown-search')]");

    // Buttons
    private final By nextButton = By.cssSelector("button.main__btn.submit-btn");

    // ===================== CONSTRUCTOR =====================

    public FundStep1BasicsPage(WebDriver driver) {
        super(driver);
    }

    // ===================== MAIN FORM METHOD =====================

    public void fillStep1Form(Step1FundBasicsPojo data) {
        logger.info("Filling Step 1: Fund Basics Form");
        enterFundCode(data.getFundCode());
        selectFundType(data.getFundType());
        enterFundNames(data.getFundNameEn(), data.getFundNameAr());
        selectFundStructure(data.getFundStructure());
        selectFundCurrency(data.getFundCurrency());
        enterDates(data.getInceptionDate(), data.getEndDate());
        selectFundManager(data.getFundManager());
        selectFundBroker(data.getFundBroker());
        selectFundCategory(data.getFundCategory());
        enterCapitalDetails(data.getInitialCapital(), data.getAggregateCapital(), data.getUnitPrice());
        selectExternalParties(data.getCustodian(), data.getFinancialAuditor());
        fillFinanceSettings(data.getCutOffDate(), data.getCalculateLevel(), data.getAnnualDays());
    }

    public void clickNext() {
        logger.info("Clicking Next button");
        scrollToElement(driver, nextButton);
        click(driver, nextButton);
    }

    // ===================== INDIVIDUAL ACTIONS =====================

    public FundStep1BasicsPage enterFundCode(String code) {
        if (code == null)
            return this;
        logger.info("Entering Fund Code: " + code);
        scrollToElement(driver, fundCodeInput);
        WebElement el = driver.findElement(fundCodeInput);
        el.clear();
        el.sendKeys(code);
        return this;
    }

    public FundStep1BasicsPage selectFundType(String type) {
        if (type == null)
            return this;
        logger.info("Selecting Fund Type: " + type);
        selectCustomDropdown(fundTypeDropdownTrigger, type);
        return this;
    }

    public FundStep1BasicsPage enterFundNames(String en, String ar) {
        if (en != null) {
            logger.info("Entering Fund Name EN: " + en);
            scrollToElement(driver, fundNameEnInput);
            WebElement elEn = driver.findElement(fundNameEnInput);
            elEn.clear();
            elEn.sendKeys(en);
        }
        if (ar != null) {
            logger.info("Entering Fund Name AR: " + ar);
            scrollToElement(driver, fundNameArInput);
            WebElement elAr = driver.findElement(fundNameArInput);
            elAr.clear();
            elAr.sendKeys(ar);
        }
        return this;
    }

    public FundStep1BasicsPage selectFundStructure(String structure) {
        if (structure == null)
            return this;
        logger.info("Selecting Fund Structure: " + structure);
        selectCustomDropdown(fundStructureDropdownTrigger, structure);
        return this;
    }

    public FundStep1BasicsPage selectFundCurrency(String currency) {
        if (currency == null)
            return this;
        logger.info("Selecting Fund Currency: " + currency);
        selectChoicesDropdown(fundCurrencyChoicesDiv, currency);
        return this;
    }

    public FundStep1BasicsPage enterDates(String inception, String end) {
        if (inception != null) {
            logger.info("Entering Inception Date: " + inception);
            setDateBySendKeys(inceptionDateInput, inception);
        }
        if (end != null) {
            logger.info("Entering End Date: " + end);
            setDateBySendKeys(endDateInput, end);
        }
        return this;
    }

    public FundStep1BasicsPage selectFundManager(String manager) {
        if (manager == null)
            return this;
        logger.info("Selecting Fund Manager: " + manager);
        if (manager.equalsIgnoreCase("Select All")) {
            selectMultiSelectAll(fundManagerDropdownTrigger);
        } else {
            selectMultiSelectOption(fundManagerDropdownTrigger, manager);
        }
        return this;
    }

    public FundStep1BasicsPage selectFundBroker(String broker) {
        if (broker == null)
            return this;
        logger.info("Selecting Fund Broker: " + broker);
        if (broker.equalsIgnoreCase("Select All")) {
            selectMultiSelectAll(fundBrokerDropdownTrigger);
        } else {
            selectMultiSelectOption(fundBrokerDropdownTrigger, broker);
        }
        return this;
    }

    public FundStep1BasicsPage selectFundCategory(String category) {
        if (category == null)
            return this;
        logger.info("Selecting Fund Category: " + category);
        selectChoicesDropdown(fundCategoryChoicesDiv, category);
        return this;
    }

    public FundStep1BasicsPage enterCapitalDetails(String initial, String aggregate, String price) {
        if (initial != null) {
            logger.info("Entering Initial Capital: " + initial);
            scrollToElement(driver, initialCapitalInput);
            WebElement el = driver.findElement(initialCapitalInput);
            el.clear();
            el.sendKeys(initial);
        }
        if (aggregate != null) {
            logger.info("Entering Aggregate Capital: " + aggregate);
            scrollToElement(driver, aggregateCapitalInput);
            WebElement el = driver.findElement(aggregateCapitalInput);
            el.clear();
            el.sendKeys(aggregate);
        }
        if (price != null) {
            logger.info("Entering Unit Price: " + price);
            scrollToElement(driver, unitPriceInput);
            WebElement el = driver.findElement(unitPriceInput);
            el.clear();
            el.sendKeys(price);
        }
        return this;
    }

    public FundStep1BasicsPage selectExternalParties(String custodian, String auditor) {
        if (custodian != null) {
            logger.info("Selecting Custodian: " + custodian);
            if (custodian.equalsIgnoreCase("Select All")) {
                selectMultiSelectAll(custodianDropdownTrigger);
            } else {
                selectMultiSelectOption(custodianDropdownTrigger, custodian);
            }
        }
        if (auditor != null) {
            logger.info("Selecting Financial Auditor: " + auditor);
            if (auditor.equalsIgnoreCase("Select All")) {
                selectMultiSelectAll(financialAuditorDropdownTrigger);
            } else {
                selectMultiSelectOption(financialAuditorDropdownTrigger, auditor);
            }
        }
        return this;
    }

    public FundStep1BasicsPage fillFinanceSettings(String cutOff, String level, String days) {
        if (cutOff != null) {
            logger.info("Entering Cut Off Date: " + cutOff);
            setDateBySendKeys(cutOffDateInput, cutOff);
        }
        if (level != null) {
            logger.info("Selecting Calculate Level: " + level);
            selectCustomDropdown(calculateLevelDropdownTrigger, level);
        }
        if (days != null) {
            logger.info("Selecting Annual Days: " + days);
            selectCustomDropdown(annualDaysDropdownTrigger, days);
        }
        return this;
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

            // Target only the currently VISIBLE dropdown (class 'show')
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
     * Sets an Element Plus date picker value using JavaScript.
     * Date format from JSON: YYYY-MM-DD (e.g., "2026-12-12")
     *
     * Regular sendKeys does NOT work with Element Plus / Vue.js controlled inputs
     * because Vue's reactivity system doesn't detect raw DOM value changes.
     * Instead, we use JavaScript to:
     * 1. Find and focus the input
     * 2. Use the native input value setter (bypasses Vue getter/setter)
     * 3. Dispatch 'input' and 'change' events to notify Vue
     * 4. Blur the input to close the date picker popup
     */
    private void setDateBySendKeys(By dateInputLocator, String date) {
        try {
            scrollToElement(driver, dateInputLocator);
            WebElement dateInput = driver.findElement(dateInputLocator);

            // Use JavaScript to set value and trigger Vue reactivity
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
                    dateInput, date);

            logger.info("Set date: " + date);

        } catch (Exception e) {
            logger.error("Failed to set date '" + date + "': " + e.getMessage());
        }
    }

    /**
     * Handles Choices.js select dropdowns (#currency, #category).
     * Clicks the choices container to open options, then clicks the matching item.
     */
    private void selectChoicesDropdown(By choicesDivLocator, String optionText) {
        try {
            scrollToElement(driver, choicesDivLocator);
            WebElement choicesDiv = driver.findElement(choicesDivLocator);
            choicesDiv.click();

            By optionLocator = By.xpath(
                    "//div[contains(@class,'choices__list--dropdown')]" +
                            "//div[contains(@class,'choices__item--choice') and normalize-space()='" + optionText
                            + "']");
            List<WebElement> options = driver.findElements(optionLocator);

            if (options.isEmpty()) {
                optionLocator = By.xpath(
                        "//div[contains(@class,'choices__list--dropdown')]" +
                                "//div[contains(@class,'choices__item--choice') and contains(normalize-space(),'"
                                + optionText + "')]");
                options = driver.findElements(optionLocator);
            }

            if (!options.isEmpty()) {
                options.get(0).click();
            } else {
                logger.error("Option '" + optionText + "' not found in choices dropdown");
            }
        } catch (Exception e) {
            logger.error("Failed to select '" + optionText + "' from choices dropdown: " + e.getMessage());
        }
    }

    /**
     * Handles multi-select dropdowns (Fund broker, Custodian, Financial Auditor).
     * Opens dropdown, clicks "Select All" checkbox, then clicks "Apply".
     *
     * HTML structure:
     * <div class="multi-select-dropdown">
     * <div class="dropdown-menu show">
     * <input class="search__input" placeholder="Search by...">
     * <div class="form-check">
     * <input class="form-check-input" type="checkbox" id="selectAll">
     * <label for="selectAll">Select All</label>
     * </div>
     * <div class="scroll-area">...</div>
     * <div class="actions">
     * <button class="gray__btn">Reset</button>
     * <button class="main__btn">Apply</button>
     * </div>
     * </div>
     * </div>
     */
    private void selectMultiSelectAll(By triggerLocator) {
        try {
            // Click trigger to open dropdown
            scrollToElement(driver, triggerLocator);
            jsClick(triggerLocator);

            // Click the "Select All" checkbox inside the visible dropdown
            By selectAllCheckbox = By.xpath(
                    "//div[contains(@class,'multi-select-dropdown')]//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//input[@id='selectAll']");
            List<WebElement> selectAllItems = driver.findElements(selectAllCheckbox);
            if (!selectAllItems.isEmpty()) {
                jsClick(selectAllItems.get(selectAllItems.size() - 1));
                logger.info("Clicked 'Select All' checkbox");
            } else {
                logger.error("'Select All' checkbox not found");
            }

            // Click "Apply" button inside the visible dropdown
            By applyLocator = By.xpath(
                    "//div[contains(@class,'multi-select-dropdown')]//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//div[contains(@class,'actions')]//button[contains(@class,'main__btn')]");
            List<WebElement> applyBtns = driver.findElements(applyLocator);
            if (!applyBtns.isEmpty()) {
                jsClick(applyBtns.get(applyBtns.size() - 1));
                logger.info("Clicked 'Apply' button");
            } else {
                logger.error("'Apply' button not found");
            }

            clickBody();
        } catch (Exception e) {
            logger.error("Failed to select ALL options: " + e.getMessage());
        }
    }

    /**
     * Handles multi-select dropdowns - selects a single specific option by
     * searching.
     *
     * Flow:
     * 1. Click trigger to open dropdown
     * 2. Type in the search__input to filter items
     * 3. Click the checkbox of the first matching item in scroll-area
     * 4. Click "Apply"
     */
    private void selectMultiSelectOption(By triggerLocator, String optionText) {
        try {
            // Step 1: Click trigger to open dropdown
            scrollToElement(driver, triggerLocator);
            jsClick(triggerLocator);

            // Step 2: Type in search input to filter items
            By searchInput = By.xpath(
                    "//div[contains(@class,'multi-select-dropdown')]//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//input[contains(@class,'search__input')]");
            List<WebElement> searchInputs = driver.findElements(searchInput);
            if (!searchInputs.isEmpty()) {
                WebElement searchEl = searchInputs.get(searchInputs.size() - 1);
                searchEl.clear();
                searchEl.sendKeys(optionText);
                logger.info("Typed '" + optionText + "' in search input");
            } else {
                logger.error("Search input not found in dropdown");
            }

            // Step 3: Click the checkbox of the first matching item in scroll-area
            By checkboxLocator = By.xpath(
                    "//div[contains(@class,'multi-select-dropdown')]//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//div[contains(@class,'scroll-area')]//div[contains(@class,'form-check item')]//input[contains(@class,'form-check-input')]");
            List<WebElement> checkboxes = driver.findElements(checkboxLocator);
            if (!checkboxes.isEmpty()) {
                jsClick(checkboxes.get(0));
                logger.info("Clicked checkbox for: " + optionText);
            } else {
                logger.error("No matching item found for '" + optionText + "' after search");
            }

            // Step 4: Click "Apply" button
            By applyLocator = By.xpath(
                    "//div[contains(@class,'multi-select-dropdown')]//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//div[contains(@class,'actions')]//button[contains(@class,'main__btn')]");
            List<WebElement> applyBtns = driver.findElements(applyLocator);
            if (!applyBtns.isEmpty()) {
                jsClick(applyBtns.get(applyBtns.size() - 1));
                logger.info("Clicked 'Apply' button");
            } else {
                logger.error("'Apply' button not found");
            }

            clickBody();
        } catch (Exception e) {
            logger.error("Failed to select '" + optionText + "' from multi-select: " + e.getMessage());
        }
    }

    // ===================== UTILITY METHODS =====================

    /** Click body to dismiss any open popups/dropdowns */
    private void clickBody() {
        try {
            driver.findElement(By.tagName("body")).click();
        } catch (Exception ignored) {
        }
    }

    /** Clicks an element using JavaScript to bypass Vue.js interception issues */
    private void jsClick(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /** Clicks a WebElement using JavaScript */
    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}