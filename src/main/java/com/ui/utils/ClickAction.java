package com.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ClickAction {



      /**
       * func: click on an element
       * @Param: WebDriver driver, By locator
       * @return: void
       * Steps: 
       * 1. Wait for element to be clickable
       * 2. Click on the element
       */
     public static void click(WebDriver driver, By locator) {
          WaitUtils.waitForElementToBeClickable(driver, locator);
          driver.findElement(locator).click();
    }


     public static void click(WebDriver driver, WebElement locator) {
          WaitUtils.waitForElementToBeClickable(driver, locator);
          locator.click();
    }


    /**
    * func: double click on an element
    * @Param: WebDriver driver, By locator
    * @return: void
    * Steps: 
    * 1. Wait for element to be clickable
    * 2. Double click on the element
     */
    public static void doubleClick(WebDriver driver, By locator) {
        WaitUtils.waitForElementToBeClickable(driver, locator);
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(locator)).perform();
    }   

    /**
    * func: right click on an element
    * @Param: WebDriver driver, By locator
    * @return: void
    * Steps: 
    * 1. Wait for element to be clickable
    * 2. Right click on the element
    */
    public static void rightClick(WebDriver driver, By locator) {
        WaitUtils.waitForElementToBeClickable(driver, locator);
        Actions actions = new Actions(driver);
        actions.contextClick(driver.findElement(locator)).perform();
    }

    /**
    * func: drag and drop an element
    * @Param: WebDriver driver, By locator
    * @return: void
    * Steps: 
    * 1. Wait for element to be clickable
    * 2. Drag and drop the element
    */
    public static void DragAndDrop(WebDriver driver, By locator) {
        WaitUtils.waitForElementToBeClickable(driver, locator);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(driver.findElement(locator), null).perform();
    }  

      /**
    * func: hover over an element
    * @Param: WebDriver driver, By locator
    * @return: void
    * Steps: 
    * 1. Wait for element to be clickable
    * 2. Hover over the element
    */       
    public static void hoverOver(WebDriver driver, By locator) {
        WaitUtils.waitForElementToBeClickable(driver, locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator)).perform();
    }       



   

   
    /**
    * func: click on an element using javascript
    * @Param: WebDriver driver, By locator
    * @return: void
    * Steps: 
    * 1. Wait for element to be clickable
    * 2. Click on the element using javascript
    */
    public static void  clickUsingJavaScript(WebDriver driver, By locator) {
        WaitUtils.waitForElementToBeClickable(driver, locator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(locator));
    }   


    public static void  clickUsingJavaScriptWithElement(WebDriver driver, WebElement locator) {
        WaitUtils.waitForElementToBeClickable(driver, locator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", locator);
    }   


    
}