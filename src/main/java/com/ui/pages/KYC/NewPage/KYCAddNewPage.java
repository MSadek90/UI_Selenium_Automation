package com.ui.pages.KYC.NewPage;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ui.base.BasePage;
import com.ui.models.pojo.KYC.WrapperClasses.KycLabelNestedPojo;
import com.ui.utils.ClickAction;
import com.ui.utils.Scrolling;
import com.ui.utils.SendKeys;


public class KYCAddNewPage  extends BasePage {

    public KYCAddNewPage(WebDriver driver) {
        super(driver);
    }


    // Add KYC Page Form Locators
    private final By pageTypeDropdown =        By.xpath("//select[contains(@name,'type') or contains(@id,'type')] | //div[contains(text(),'Select page type')]");
    private final By customerTypeOption =      By.xpath("//option[contains(text(),'Customer')] | //div[contains(text(),'Customer')]");
    private final By companyTypeOption =       By.xpath("//option[contains(text(),'Company')] | //div[contains(text(),'Company')]");
    private final By englishPageTitleField =   By.xpath("//input[contains(@placeholder,'page title in English')]");
    private final By arabicPageTitleField =    By.xpath("//input[contains(@placeholder,'page title in Arabic')]");
    private final By englishDescriptionField = By.xpath("//textarea[contains(@placeholder,'page description in English') or contains(@name,'description_en')]");
    private final By arabicDescriptionField =  By.xpath("//textarea[contains(@placeholder,'page description in Arabic') or contains(@name,'description_ar')]");
    private final By addByNewInputButton = By.cssSelector("button[class='add-input']");
  
    

//(
/// //

    /*
           To use them when pass Ids from json files
     */
    
      public KYCAddNewPage getPageId(String kycPageId) {
        return this;
       }


    
       public KYCAddNewPage getQuestionId(String questionId) {
        return this;
       }


       public KYCAddNewPage getOptionId(String optionId) {
        return this;
       }


    /*
         Select Page Type from Dropdown
     */
    public KYCAddNewPage selectPageType(String pageType) {
        ClickAction.click(driver, pageTypeDropdown);
        if (pageType.equalsIgnoreCase("Customer")) {
            ClickAction.click(driver, customerTypeOption);
        }
        else if (pageType.equalsIgnoreCase("Company")) {
            ClickAction.click(driver, companyTypeOption);
        }
        return this;
    }



    /*
       Fill KYC Page Form
     */

       public KYCAddNewPage fillEnglishName(KycLabelNestedPojo englishName) {
        SendKeys.sendKeysBySequenceCharacters(driver, englishPageTitleField, englishName.getEn());
        return this;
       }

         public KYCAddNewPage fillArabicName(KycLabelNestedPojo arabicName) {
          SendKeys.sendKeysBySequenceCharacters(driver, arabicPageTitleField, arabicName.getAr());
          return this;
         }


            public KYCAddNewPage fillEnglishDescription(KycLabelNestedPojo englishDescription) {
            SendKeys.sendKeysBySequenceCharacters(driver, englishDescriptionField, englishDescription.getEn());
            return this;
            }

            public KYCAddNewPage fillArabicDescription(KycLabelNestedPojo arabicDescription) {
            SendKeys.sendKeysBySequenceCharacters(driver, arabicDescriptionField, arabicDescription.getAr());
            return this;
            }



            /*
                To get The last active question
            */

           
           public WebElement getLastActiveQuestion(){

            List <WebElement> questionCards = driver.findElements(By.cssSelector(".question-card.open"));

            return questionCards.get(questionCards.size() - 1); 

           }





            /*
                 First (Question) >> Default 
             */

            public KYCAddNewPage fillEnglishQuestion(String englishQuestion) {
                By input = By.xpath("//input[@placeholder='Enter English Input Title']");
                SendKeys.sendKeys(driver, input, englishQuestion);
                return this;
            }

            public KYCAddNewPage fillArabicQuestion(String arabicQuestion) {
                By input = By.xpath("//input[@placeholder='Enter Arabic Input Title']");
                SendKeys.sendKeys(driver, input, arabicQuestion);
                return this;
            }
    




            /*
               Options
             */

      public KYCAddNewPage fillEnglishOption(String englishOption, int index) {
        String xpath = "(//input[@placeholder='Enter English Option'])[" + (index + 1) + "]";
        By input = By.xpath(xpath);
        SendKeys.sendKeys(driver, input, englishOption);
        return this;

    }


          public KYCAddNewPage fillArabicOption(String arabicOption, int index) {
            String xpath = "(//input[@placeholder='Enter Arabic Option'])[" + (index + 1) + "]";
            By input = By.xpath(xpath);
            SendKeys.sendKeys(driver, input, arabicOption);
            return this;

        }

            /*
              click icon to open Nested Options
            */

            public KYCAddNewPage clickAddNestedQuestionsInOption(int index) {
                ClickAction.clickUsingJavaScript(driver, By.xpath("(//img[@alt='nested'])[" + (index + 1) + "]"));
                return this;
            }

            /*
              click Add Option Button
            */

            public KYCAddNewPage clickAddOptionButtonForMultiQuestions(int index) {
                By addOptionBtn = By.xpath("(//span[contains(text(),'Add Option')])[" + (index + 1) + "]");
                //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", driver.findElement(addOptionBtn));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(addOptionBtn));
                return this;
            }

            /*
            Select Dropdown List Type Question
             */
            public KYCAddNewPage DropdownListTypeQuestion(int index) throws InterruptedException {
                ClickAction.clickUsingJavaScript(driver, By.xpath("(//div[contains(@class,'align-items-center gap-3')]//div[@class='choices__inner'])[" + (index) + "]"));
                ClickAction.click(driver, By.xpath("(//div[contains(@id,'type-item-choice-2')])[" + (index + 1) + "]"));
                return this;
            }

            /*
            Add new input field
            */
            public KYCAddNewPage clickAddByNewInputButton() {
                ClickAction.clickUsingJavaScript(driver, addByNewInputButton);
                return this;
            }
            

            /*
               Click on Eye Icon
             */

               public KYCAddNewPage clickOnEyeIcon(int index) {
                ClickAction.clickUsingJavaScript(driver, By.xpath("(//img[@alt='hide'])[" + (index + 1) + "]"));
                return this;
               }

}