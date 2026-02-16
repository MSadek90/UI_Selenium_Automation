package com.ui.pages.Fund;

import com.ui.base.BasePage;
import com.ui.models.pojo.Fund.Step1FundBasicsPojo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
            Thread.sleep(500);

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
     * Sets an Element Plus date picker value by clicking through the calendar UI.
     * Date format from JSON: DD/MM/YYYY (e.g., "12/12/2026")
     *
     * Scopes all selectors to the currently VISIBLE popup panel to avoid
     * conflicts when multiple date pickers exist on the same page.
     */
    private void setDateBySendKeys(By dateInputLocator, String date) {
        try {
            // Parse the date (DD/MM/YYYY)
            String[] parts = date.split("/");
            int targetDay = Integer.parseInt(parts[0]);
            int targetMonth = Integer.parseInt(parts[1]);
            int targetYear = Integer.parseInt(parts[2]);

            // Dismiss any previously open popup
            clickBody();
            Thread.sleep(300);

            // Click the input to open the date picker popup
            scrollToElement(driver, dateInputLocator);
            click(driver, dateInputLocator);
            Thread.sleep(500);

            // Find the currently visible popup panel
            WebElement activePanel = findVisibleDatePanel();
            if (activePanel == null) {
                logger.error("Date picker popup not found for: " + date);
                return;
            }

            // --- Navigate to the correct YEAR ---
            int maxAttempts = 20;
            for (int i = 0; i < maxAttempts; i++) {
                int displayedYear = extractYear(activePanel);
                if (displayedYear == targetYear)
                    break;
                if (displayedYear < targetYear) {
                    activePanel.findElement(By.cssSelector(".el-picker-panel__icon-btn.d-arrow-right")).click();
                } else {
                    activePanel.findElement(By.cssSelector(".el-picker-panel__icon-btn.d-arrow-left")).click();
                }
                Thread.sleep(200);
            }

            // --- Navigate to the correct MONTH ---
            for (int i = 0; i < maxAttempts; i++) {
                int displayedMonth = extractMonth(activePanel);
                if (displayedMonth == targetMonth)
                    break;
                if (displayedMonth < targetMonth) {
                    activePanel.findElement(By.cssSelector(".el-picker-panel__icon-btn.arrow-right")).click();
                } else {
                    activePanel.findElement(By.cssSelector(".el-picker-panel__icon-btn.arrow-left")).click();
                }
                Thread.sleep(200);
            }

            // --- Click the target DAY (scoped to active panel) ---
            List<WebElement> dayCells = activePanel.findElements(By.xpath(
                    ".//td[contains(@class,'available')]//span[contains(@class,'el-date-table-cell__text') and text()='"
                            + targetDay + "']"));

            if (!dayCells.isEmpty()) {
                dayCells.get(0).click();
                logger.info("Selected date: " + date);
            } else {
                logger.error("Day " + targetDay + " not found in date picker");
            }

            // Wait for popup to close after day selection
            Thread.sleep(300);

        } catch (Exception e) {
            logger.error("Failed to set date '" + date + "': " + e.getMessage());
        }
    }

    /** Find the currently visible Element Plus date picker panel */
    private WebElement findVisibleDatePanel() {
        List<WebElement> panels = driver.findElements(By.cssSelector(".el-picker-panel.el-date-picker"));
        for (WebElement panel : panels) {
            if (panel.isDisplayed()) {
                return panel;
            }
        }
        return null;
    }

    /** Extract year number from the active panel's header labels */
    private int extractYear(WebElement panel) {
        List<WebElement> labels = panel.findElements(By.cssSelector(".el-date-picker__header-label"));
        for (WebElement label : labels) {
            String text = label.getText().trim();
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException ignored) {
            }
        }
        return -1;
    }

    /** Extract month index (1-12) from the active panel's header labels */
    private int extractMonth(WebElement panel) {
        String[] monthNames = { "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December" };
        List<WebElement> labels = panel.findElements(By.cssSelector(".el-date-picker__header-label"));
        for (WebElement label : labels) {
            String text = label.getText().trim();
            for (int i = 0; i < monthNames.length; i++) {
                if (text.equalsIgnoreCase(monthNames[i])) {
                    return i + 1;
                }
            }
        }
        return -1;
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
     * Handles multi-select dropdowns.
     * Opens dropdown, clicks "Select All" checkbox, then clicks "Apply".
     */
    private void selectMultiSelectAll(By triggerLocator) {
        try {
            scrollToElement(driver, triggerLocator);
            click(driver, triggerLocator);

            By selectAllLocator = By.xpath(
                    "//label[contains(normalize-space(),'Select All')] | " +
                            "//span[contains(normalize-space(),'Select All')] | " +
                            "//div[contains(@class,'checkbox') and contains(normalize-space(),'Select All')]");

            List<WebElement> selectAllItems = driver.findElements(selectAllLocator);
            if (!selectAllItems.isEmpty()) {
                selectAllItems.get(selectAllItems.size() - 1).click();
            }

            By applyLocator = By.xpath("//button[normalize-space()='Apply']");
            List<WebElement> applyBtns = driver.findElements(applyLocator);
            if (!applyBtns.isEmpty()) {
                applyBtns.get(applyBtns.size() - 1).click();
            }

            clickBody();
        } catch (Exception e) {
            logger.error("Failed to select ALL options: " + e.getMessage());
        }
    }

    /**
     * Handles multi-select dropdowns - selects a single specific option.
     */
    private void selectMultiSelectOption(By triggerLocator, String optionText) {
        try {
            scrollToElement(driver, triggerLocator);
            click(driver, triggerLocator);

            By optionLocator = By.xpath(
                    "//label[contains(normalize-space(),'" + optionText + "')] | " +
                            "//span[contains(normalize-space(),'" + optionText + "')]");
            List<WebElement> options = driver.findElements(optionLocator);
            if (!options.isEmpty()) {
                options.get(0).click();
            }

            By applyLocator = By.xpath("//button[normalize-space()='Apply']");
            List<WebElement> applyBtns = driver.findElements(applyLocator);
            if (!applyBtns.isEmpty()) {
                applyBtns.get(applyBtns.size() - 1).click();
            }

            clickBody();
        } catch (Exception e) {
            logger.error("Failed to select '" + optionText + "' from multi-select: " + e.getMessage());
        }
    }

    /** Click body to dismiss any open popups/dropdowns */
    private void clickBody() {
        try {
            driver.findElement(By.tagName("body")).click();
        } catch (Exception ignored) {
        }
    }
}