package com.ui.models.pojo.Login;

/**
 * Test Case model for Login tests
 */
public class LoginPojo {

    private String testCaseId;
    private String testCaseName;
    private String description;
    private String username;
    private String password;
    private String expectedResult;
    private boolean shouldPass;
    private String errorMessage;
    private String otp;

    public LoginPojo() {
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public boolean isShouldPass() {
        return shouldPass;
    }

    public void setShouldPass(boolean shouldPass) {
        this.shouldPass = shouldPass;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "LoginTestCase{" +
                "testCaseId='" + testCaseId + '\'' +
                ", testCaseName='" + testCaseName + '\'' +
                ", description='" + description + '\'' +
                ", username='" + username + '\'' +
                ", expectedResult='" + expectedResult + '\'' +
                ", shouldPass=" + shouldPass +
                '}';
    }
}
