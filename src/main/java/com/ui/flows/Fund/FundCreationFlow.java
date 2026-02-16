package com.ui.flows.Fund;

import com.ui.actions.Fund.FundCreationActions;
import com.ui.models.pojo.Fund.Step1FundBasicsPojo;
import com.ui.models.pojo.Fund.Step2FundDetailsPojo;
import com.ui.models.pojo.Fund.Step3UserGroupPojo;
import com.ui.pages.DashBoardPage;
import com.ui.utils.LoggerUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class FundCreationFlow {

    private WebDriver driver;
    private FundCreationActions fundActions;
    private DashBoardPage dashboardPage;
    private static final LoggerUtil logger = LoggerUtil.getLogger(FundCreationFlow.class);

    public FundCreationFlow(WebDriver driver) {
        this.driver = driver;
        this.fundActions = new FundCreationActions(driver);
        this.dashboardPage = new DashBoardPage(driver);
    }

    /**
     * Executes the full Fund Creation flow (all steps)
     * 
     * @throws InterruptedException
     */
    public void createFund(Step1FundBasicsPojo step1Data, Step2FundDetailsPojo step2Data,
            List<Step3UserGroupPojo> step3Data) throws InterruptedException {
        logger.info("========== Starting Fund Creation Flow ==========");

        // Navigate to Create New Fund Page
        dashboardPage.navigateToCreateNewFund();

        // Step 1: Fund Basics
        fillStep1(step1Data);

        Thread.sleep(1000);
        // Scroll to top and wait before starting Step 2
        scrollToTop();
        Thread.sleep(1000);

        // Step 2: Fund Details
        fillStep2(step2Data);

        Thread.sleep(1000);
        // Scroll to top and wait before starting Step 3
        scrollToTop();
        Thread.sleep(1000);

        // Step 3: User Groups
        fillStep3(step3Data);

        // Future steps can be added here (Step 4, etc.)

        logger.info("========== Fund Creation Flow Completed ==========");
    }

    // ===================== PRIVATE STEP METHODS =====================

    private void fillStep1(Step1FundBasicsPojo step1Data) {
        logger.info("--- Step 1: Fund Basics ---");
        fundActions.fillFundBasics(step1Data);
        logger.info("Completed Step 1.");
    }

    private void fillStep2(Step2FundDetailsPojo step2Data) {
        logger.info("--- Step 2: Fund Details ---");
        fundActions.fillFundDetails(step2Data);
        logger.info("Completed Step 2.");
    }

    private void fillStep3(List<Step3UserGroupPojo> step3Data) {
        logger.info("--- Step 3: User Groups ---");
        fundActions.fillUserGroups(step3Data);
        logger.info("Completed Step 3.");
    }

    private void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }
}
