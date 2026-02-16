package com.ui.models.pojo.Fund;

import java.util.List;

/**
 * POJO for Step 2: Fund Details
 */
public class Step2FundDetailsPojo {

    // General Info
    private String dividendPolicy;
    private String riskClassification;

    // Subscription Details
    private String minSubscriptionCapital;
    private String minAdditionalSubscription;
    private List<String> subscriptionAllocationDays;

    // Redemption Details
    private String minRedemptionAmount;
    private List<String> redemptionAllocationDays;
    private String redemptionType; // "Partial", "Fully", or "Both"

    // Getters and Setters
    public String getDividendPolicy() {
        return dividendPolicy;
    }

    public void setDividendPolicy(String dividendPolicy) {
        this.dividendPolicy = dividendPolicy;
    }

    public String getRiskClassification() {
        return riskClassification;
    }

    public void setRiskClassification(String riskClassification) {
        this.riskClassification = riskClassification;
    }

    public String getMinSubscriptionCapital() {
        return minSubscriptionCapital;
    }

    public void setMinSubscriptionCapital(String minSubscriptionCapital) {
        this.minSubscriptionCapital = minSubscriptionCapital;
    }

    public String getMinAdditionalSubscription() {
        return minAdditionalSubscription;
    }

    public void setMinAdditionalSubscription(String minAdditionalSubscription) {
        this.minAdditionalSubscription = minAdditionalSubscription;
    }

    public List<String> getSubscriptionAllocationDays() {
        return subscriptionAllocationDays;
    }

    public void setSubscriptionAllocationDays(List<String> subscriptionAllocationDays) {
        this.subscriptionAllocationDays = subscriptionAllocationDays;
    }

    public String getMinRedemptionAmount() {
        return minRedemptionAmount;
    }

    public void setMinRedemptionAmount(String minRedemptionAmount) {
        this.minRedemptionAmount = minRedemptionAmount;
    }

    public List<String> getRedemptionAllocationDays() {
        return redemptionAllocationDays;
    }

    public void setRedemptionAllocationDays(List<String> redemptionAllocationDays) {
        this.redemptionAllocationDays = redemptionAllocationDays;
    }

    public String getRedemptionType() {
        return redemptionType;
    }

    public void setRedemptionType(String redemptionType) {
        this.redemptionType = redemptionType;
    }
}
