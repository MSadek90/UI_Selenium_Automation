package com.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.ui.config.BrowserConfig;

import java.time.Duration;
/**
 * Utility class for wait operations
 */
public class WaitUtils {
    
    /**
     * func: wait for element to be visible
     * param: WebDriver driver, By locator
     * return: void
     * Steps: 
     * 1. Wait for element to b e visible
     */
    public static void waitForElementToBeVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BrowserConfig.EXPLICIT_WAIT));

         try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
         }catch (Exception e){
            System.out.println("Element is not visible " + locator);
            throw new RuntimeException("Element is not visible: " + locator);
         }
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BrowserConfig.EXPLICIT_WAIT));

         try{
            wait.until(ExpectedConditions.visibilityOf(locator));
         }catch (Exception e){
            System.out.println("Element is not visible " + locator);
            throw new RuntimeException("Element is not visible: " + locator);
         }
    }
    
    /**
     * func: wait for element to be clickable
     * @Param: WebDriver driver, By locator
     * return: void
     * Steps: 
     * 1. Wait for element to be clickable
     */
    public static void waitForElementToBeClickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BrowserConfig.EXPLICIT_WAIT));
        try{
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }catch (Exception e){
            System.out.println("Element is not clickable " + locator);
            throw new RuntimeException("Element is not clickable: " + locator);
        }
    }


    public static void waitForElementToBeClickable(WebDriver driver, WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BrowserConfig.EXPLICIT_WAIT));
        try{
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }catch (Exception e){
            System.out.println("Element is not clickable " + locator);
            throw new RuntimeException("Element is not clickable: " + locator);
        }
    }
    
    /**
     * func: wait for element to be present
     * @Param: WebDriver driver, By locator
     * return: void
     * Steps: 
     * 1. Wait for element to be present
     */
    public static void waitForElementToBePresent(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BrowserConfig.EXPLICIT_WAIT));
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }catch (Exception e){
            System.out.println("Element is not present " + locator);
            throw new RuntimeException("Element is not present: " + locator);
        }
    }
    
    /**
     * func: wait for element to be invisible
     * @Param: WebDriver driver, By locator
     * return: void
     * Steps: 
     * 1. Wait for element to be invisible
     */
    public static void waitForElementToBeInvisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BrowserConfig.EXPLICIT_WAIT));
        try{
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        }catch (Exception e){
            System.out.println("Element is not invisible " + locator);
            throw new RuntimeException("Element is not invisible: " + locator);
        }
    }
    
    /**
     * func: wait for URL to contain text
     * @Param: WebDriver driver, String urlFragment
     * return: void
     * Steps: 
     * 1. Wait for URL to contain text
     */
    public static void waitForUrlContains(WebDriver driver, String urlFragment) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BrowserConfig.EXPLICIT_WAIT));
        try{
            wait.until(ExpectedConditions.urlContains(urlFragment));
        }catch (Exception e){
            System.out.println("URL does not contain " + urlFragment);
            throw new RuntimeException("URL does not contain: " + urlFragment);
        }
    }
    
    /**
     * func: wait for title to contain text
     * @Param: WebDriver driver, String title
     * return: void
     * Steps: 
     * 1. Wait for title to contain text
     */
    public static void waitForTitleContains(WebDriver driver, String title) {
        WebDriverWait wait   = new WebDriverWait(driver, Duration.ofSeconds(BrowserConfig.EXPLICIT_WAIT));
        try{
            wait.until(ExpectedConditions.titleContains(title));
        }catch (Exception e){
            System.out.println("Title does not contain " + title);
            throw new RuntimeException("Title does not contain: " + title);
        }
    }
    
    /**
     * func: custom wait with specified timeout
     * @Param: WebDriver driver, By locator, int timeoutSeconds
     * return: void
     * Steps: 
     * 1. Wait for element to be visible
     *
    public static void waitForElementWithTimeout(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = n  ew WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch (Exception e){
            System.out.println("Element is not visible " + locator);
            throw new RuntimeException("Element is not visible: " + locator);
        }
    }
    
    /**
     * func: hard wait (use sparingly)
     * @Param: int milliseconds
     * return: void
     * Steps: 
     * 1. Wait for specified amount of time
     *
    public static void hardWait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
    */  
}
