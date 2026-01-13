package com.ui.pages.KYC.NewPage;

import java.util.List;

import org.openqa.selenium.By;
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
    private final By addNestedInOption = By.xpath("(//img[@alt='nested'])[1]");
    

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
                WebElement input = getLastActiveQuestion().findElement(By.xpath(".//input[@placeholder='Enter English Input Title']"));
                SendKeys.sendKeytoElement(driver, input, englishQuestion);
                return this;
            }

            public KYCAddNewPage fillArabicQuestion(String arabicQuestion) {
                WebElement input = getLastActiveQuestion().findElement(By.xpath(".//input[@placeholder='Enter Arabic Input Title']"));
                SendKeys.sendKeytoElement(driver, input, arabicQuestion);
                return this;
            }





            /*
            Options
             */

            public KYCAddNewPage fillEnglishOption(String englishOption) {
               List<WebElement> options = getLastActiveQuestion().findElements(By.xpath(".//input[@placeholder='Enter English Option']"));
               SendKeys.sendKeytoElement(driver, options.get(options.size() - 1), englishOption);
                return this;
            }

            public KYCAddNewPage fillArabicOption(String arabicOption) {
               List<WebElement> options = getLastActiveQuestion().findElements(By.xpath(".//input[@placeholder='Enter Arabic Option']"));
               SendKeys.sendKeytoElement(driver, options.get(options.size() - 1), arabicOption);
                return this;
            }


            /*
              click icon to open Nested Options
            */

            public KYCAddNewPage clickAddNestedQuestionsInOption() {
                ClickAction.clickUsingJavaScriptWithElement(driver, getLastActiveQuestion().findElement(By.xpath(".//img[@alt='nested']")));
                return this;
            }

            /*
              click Add Option Button
            */

            public KYCAddNewPage clickAddOptionButtonForMultiQuestions() {
                Scrolling.scrollToElement(driver, getLastActiveQuestion().findElement(By.xpath(".//span[contains(text(),'Add Option')]")));
                ClickAction.clickUsingJavaScriptWithElement(driver, getLastActiveQuestion().findElement(By.xpath(".//span[contains(text(),'Add Option')]")));
                return this;
            }

            /*
            Select Dropdown List Type Question
             */
            public KYCAddNewPage DropdownListTypeQuestion() throws InterruptedException {
                ClickAction.clickUsingJavaScriptWithElement(driver, getLastActiveQuestion().findElement(By.xpath(".//div[contains(@class,'align-items-center gap-3')]//div[@class='choices__inner']")));
                ClickAction.click(driver, getLastActiveQuestion().findElement(By.xpath(".//div[contains(@id,'type-item-choice-2')]")));
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

               public KYCAddNewPage clickOnEyeIcon() {
                ClickAction.clickUsingJavaScriptWithElement(driver, getLastActiveQuestion().findElement(By.xpath(".//img[@alt='hide']")));
                return this;
               }
}