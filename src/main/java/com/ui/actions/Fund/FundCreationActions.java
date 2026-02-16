package com.ui.actions.Fund;

import com.ui.models.pojo.Fund.Step1FundBasicsPojo;
import com.ui.models.pojo.Fund.Step2FundDetailsPojo;
import com.ui.models.pojo.Fund.Step3UserGroupPojo;
import com.ui.pages.Fund.FundStep1BasicsPage;
import com.ui.pages.Fund.FundStep2DetailsPage;
import com.ui.pages.Fund.FundStep3UserGroupsPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class FundCreationActions {

    private WebDriver driver;
    private FundStep1BasicsPage step1Page;
    private FundStep2DetailsPage step2Page;
    private FundStep3UserGroupsPage step3Page;

    public FundCreationActions(WebDriver driver) {
        this.driver = driver;
        this.step1Page = new FundStep1BasicsPage(driver);
        this.step2Page = new FundStep2DetailsPage(driver);
        this.step3Page = new FundStep3UserGroupsPage(driver);
    }

    /**
     * Steps to fill the first page of Fund Creation
     */
    public void fillFundBasics(Step1FundBasicsPojo step1Data) {
        step1Page.fillStep1Form(step1Data);
        step1Page.clickNext();
    }

    /**
     * Steps to fill the second page of Fund Creation
     */
    public void fillFundDetails(Step2FundDetailsPojo step2Data) {
        step2Page.fillStep2Form(step2Data);
        step2Page.clickNext();
    }

    /**
     * Steps to fill the third page of Fund Creation (User Groups)
     */
    public void fillUserGroups(List<Step3UserGroupPojo> step3Data) {
        step3Page.fillStep3Form(step3Data);
        step3Page.clickNext();
    }
}
