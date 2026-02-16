package com.ui.models.pojo.Fund;

/**
 * POJO for Step 3: A single User Group entry.
 * Step 3 JSON is a list of these objects (multiple groups can be added).
 */
public class Step3UserGroupPojo {

    private String userGroup;
    private String minimumLimit;
    private String maximumLimit;
    private String investorNationality; // "For Saudi Nationals Only", "Non saudi", "All"

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getMinimumLimit() {
        return minimumLimit;
    }

    public void setMinimumLimit(String minimumLimit) {
        this.minimumLimit = minimumLimit;
    }

    public String getMaximumLimit() {
        return maximumLimit;
    }

    public void setMaximumLimit(String maximumLimit) {
        this.maximumLimit = maximumLimit;
    }

    public String getInvestorNationality() {
        return investorNationality;
    }

    public void setInvestorNationality(String investorNationality) {
        this.investorNationality = investorNationality;
    }
}
