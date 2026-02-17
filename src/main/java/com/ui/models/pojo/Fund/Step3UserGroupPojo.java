package com.ui.models.pojo.Fund;

/**
 * POJO for Step 3: A single User Group entry.
 * Step 3 JSON is a list of these objects (multiple groups can be added).
 */
public class Step3UserGroupPojo {

    private String userGroup;
    private String investorNationality; // "For Saudi Nationals Only", "Non saudi", "All"

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getInvestorNationality() {
        return investorNationality;
    }

    public void setInvestorNationality(String investorNationality) {
        this.investorNationality = investorNationality;
    }
}
