package com.ui.models.pojo.Fund;

import java.util.List;

/**
 * POJO for Step 4: NAV Configuration.
 * Contains NAV frequency and time zone settings.
 */
public class Step4NavPojo {

    private String navFrequency; // "Daily", "Weekly", "Monthly"
    private String timeZone; // e.g., "Asia/Riyadh"
    private String cutOffTime; // e.g., "14:00" — Element Plus time picker
    private List<String> weekendDays; // e.g., ["Friday", "Saturday"]
    private List<String> navHolidays; // e.g., ["6 october", "hrt"] — multi-select checkbox

    public String getNavFrequency() {
        return navFrequency;
    }

    public void setNavFrequency(String navFrequency) {
        this.navFrequency = navFrequency;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getCutOffTime() {
        return cutOffTime;
    }

    public void setCutOffTime(String cutOffTime) {
        this.cutOffTime = cutOffTime;
    }

    public List<String> getWeekendDays() {
        return weekendDays;
    }

    public void setWeekendDays(List<String> weekendDays) {
        this.weekendDays = weekendDays;
    }

    public List<String> getNavHolidays() {
        return navHolidays;
    }

    public void setNavHolidays(List<String> navHolidays) {
        this.navHolidays = navHolidays;
    }
}
