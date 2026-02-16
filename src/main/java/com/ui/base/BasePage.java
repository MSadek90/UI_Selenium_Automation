package com.ui.base;

import org.openqa.selenium.WebDriver;

import com.ui.utils.LoggerUtil;

public class BasePage {

    protected WebDriver driver;
    protected final LoggerUtil logger;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.logger = LoggerUtil.getLogger(this.getClass());
    }
}
