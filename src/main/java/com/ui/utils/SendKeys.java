package com.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SendKeys {


    /**
     * func: send keys to an element
     * @Param: WebDriver driver, By locator, String text
     * @return: void
     * Steps: 
     * 1. Wait for element to be visible
     * 2. Send keys to the element
     */
    public static void sendKeys(WebDriver driver, By locator, String text) {
        WaitUtils.waitForElementToBeVisible(driver, locator);  
        if(text == null){
             throw new RuntimeException("Text to send cannot be null");
        }
        driver.findElement(locator).sendKeys(text);
    }


    /*
       The same function but for WebElement not Locator
     */

    public static void sendKeytoElement(WebDriver driver, WebElement locator, String text) {
        WaitUtils.waitForElementToBeVisible(driver, locator);  
        if(text == null){
             throw new RuntimeException("Text to send cannot be null");
        }
        locator.sendKeys(text);
    }


    public static void sendKeysBySequenceCharacters (WebDriver driver, By locator, CharSequence... keysToSend) {
        WaitUtils.waitForElementToBeVisible(driver, locator);
        if(keysToSend == null){
             throw new RuntimeException("Keys to send cannot be null");
        }
        driver.findElement(locator).sendKeys(keysToSend);
    }

    /**
     * func: send keys to an element
     * @Param: WebDriver driver, By locator, int timeoutSeconds, String text
     * @return: void
     * Steps: 
     * 1. Wait for element to be visible
     * 2. Send keys to the element
     */
    public static void sendKeys(WebDriver driver, By locator, int timeoutSeconds, String text) {
        WaitUtils.waitForElementToBeVisible(driver, locator);
        if(text == null){
             throw new RuntimeException("Text to send cannot be null");
        }  
        driver.findElement(locator).sendKeys(text);
    }

    


}   