package com.ui.tests.Fund;

import com.ui.base.BaseTest;
import com.ui.flows.Fund.FundCreationFlow;
import com.ui.models.pojo.Fund.Step1FundBasicsPojo;
import com.ui.models.pojo.Fund.Step2FundDetailsPojo;
import com.ui.models.pojo.Fund.Step3UserGroupPojo;
import com.ui.models.pojo.Fund.Step4NavPojo;
import com.ui.utils.DataLoader.FundLoadData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FundCreationTest extends BaseTest {

    @Test(description = "Verify successful creation of a new fund (all steps)")
    public void testCreateNewFund() throws InterruptedException {
        // Load all step data
        Step1FundBasicsPojo step1Data = FundLoadData.getStep1Data();
        Assert.assertNotNull(step1Data, "Failed to load Step 1 test data!");

        Step2FundDetailsPojo step2Data = FundLoadData.getStep2Data();
        Assert.assertNotNull(step2Data, "Failed to load Step 2 test data!");

        List<Step3UserGroupPojo> step3Data = FundLoadData.getStep3Data();
        Assert.assertNotNull(step3Data, "Failed to load Step 3 test data!");

        Step4NavPojo step4Data = FundLoadData.getStep4Data();
        Assert.assertNotNull(step4Data, "Failed to load Step 4 test data!");

        // Initialize Flow and execute the full fund creation
        FundCreationFlow fundFlow = new FundCreationFlow(getDriver());
        fundFlow.createFund(step1Data, step2Data, step3Data, step4Data);

        // Final verification point (e.g., success message or fund appears in list)
    }
}
