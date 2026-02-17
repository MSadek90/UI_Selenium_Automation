package com.ui.utils.DataLoader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ui.models.pojo.Fund.Step1FundBasicsPojo;
import com.ui.models.pojo.Fund.Step2FundDetailsPojo;
import com.ui.models.pojo.Fund.Step3UserGroupPojo;
import com.ui.models.pojo.Fund.Step4NavPojo;
import com.ui.utils.TestDataLoader;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Data Loader for Fund Creation Steps.
 * Automatically generates unique fundCode, fundNameEn, and fundNameAr
 * by appending a timestamp suffix so tests are repeatable without manual
 * changes.
 */
public class FundLoadData {

    private static final Gson gson = new Gson();

    /**
     * Generates a unique suffix based on current timestamp.
     * Format: MMddHHmmss (e.g., "0217014407")
     * This ensures uniqueness per test run.
     */
    private static String generateUniqueSuffix() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmmss"));
    }

    public static Step1FundBasicsPojo getStep1Data() {
        Step1FundBasicsPojo data = TestDataLoader.loadDataFromJsonFiles(
                "src/test/resources/testdata/Fund/step1_basics.json", Step1FundBasicsPojo.class);

        // Auto-generate unique values for fundCode and fund names
        String suffix = generateUniqueSuffix();
        data.setFundCode(data.getFundCode() + suffix);
        data.setFundNameEn(data.getFundNameEn() + " " + suffix);
        data.setFundNameAr(data.getFundNameAr() + " " + suffix);

        return data;
    }

    public static Step2FundDetailsPojo getStep2Data() {
        return TestDataLoader.loadDataFromJsonFiles("src/test/resources/testdata/Fund/step2_details.json",
                Step2FundDetailsPojo.class);
    }

    public static List<Step3UserGroupPojo> getStep3Data() {
        try (FileInputStream file = new FileInputStream("src/test/resources/testdata/Fund/step3_user_groups.json");
                Reader reader = new InputStreamReader(file, StandardCharsets.UTF_8)) {
            Type listType = new TypeToken<List<Step3UserGroupPojo>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load Step 3 Test Data: " + e.getMessage(), e);
        }
    }

    public static Step4NavPojo getStep4Data() {
        return TestDataLoader.loadDataFromJsonFiles("src/test/resources/testdata/Fund/step4_nav.json",
                Step4NavPojo.class);
    }
}
