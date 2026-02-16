package com.ui.actions.KYC;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ui.flows.KYC.KycAddNewPageFlow;
import com.ui.models.pojo.KYC.KycOptionPojo;
import com.ui.models.pojo.KYC.KycQuestionPojo;
import com.ui.pages.KYC.NewPage.KYCAddNewPage;


public class KYCQuestionActions {
    

   
    


    WebDriver driver;
    KYCAddNewPage kycAddNewPage = new KYCAddNewPage(driver);


    /*
      Constructor
     */
    
    public KYCQuestionActions(KYCAddNewPage Page) {
        this.kycAddNewPage = Page;
    }



    /*

    private helper functions 
    
    */
    private void fillOneQuestionMainFields(KycQuestionPojo kycQuestionPojo) throws InterruptedException{

          kycAddNewPage
                    .fillEnglishQuestion(kycQuestionPojo.getLabel().getEn())
                    .fillArabicQuestion(kycQuestionPojo.getLabel().getAr());

    }


    private void addOptions(KycOptionPojo kycOptionPojo,int index) throws InterruptedException{

        kycAddNewPage
        .fillEnglishOption(kycOptionPojo.getLabel().getEn(), index)
        .fillArabicOption(kycOptionPojo.getLabel().getAr(), index);
    }


    private void clickEyeIcon( int index){
        kycAddNewPage
        .clickOnEyeIcon(index);
    }


    private void addNewoption(int index)
    {
        kycAddNewPage
        .clickAddOptionButtonForMultiQuestions(index);
    }


    private void addNewQuestionbutton(){
        kycAddNewPage
        .clickAddByNewInputButton();
    }

    private void clickAddNestedQuestionsInOptions(int index){
        kycAddNewPage
        .clickAddNestedQuestionsInOption(index);
    }



    
   


    /*

     Main Function To add Questions Per page 

    */
    

     int counter = 1, counter2 = 1, counter3 = 1;
        

    public void addQuestionsPerPage(List<KycQuestionPojo>questions) throws InterruptedException{
        


       

        // Loop on questions with questionTemp (Locators Start from 1)
           
        for( KycQuestionPojo question : questions){
            


            System.out.println("counter2: " + counter2++);

            kycAddNewPage.DropdownListTypeQuestion(counter2 - 1);
            //Fill Label en/ar for each question
            fillOneQuestionMainFields(question);
           


            List <KycOptionPojo> options = question.getOptions();
            System.out.println("size of options in question["+question.getQuestionId()+"]is: " + options.size());
            //Loop on Options
            
        for(int optionIndex = 0 ; optionIndex < options.size() ; optionIndex++){

           

            System.out.println("counter: " + counter++);
            System.out.println("optionIndex is: " + optionIndex);
            System.out.println("optionSize is: " + options.size());

            //Fill Label en/ar for each option
            addOptions(options.get(optionIndex), optionIndex);

           
            if (optionIndex < options.size() - 1) {

                addNewoption(optionIndex);
            }

                //Nested questions in Options
                if (options.get(optionIndex).getQuestions() != null) {

                clickAddNestedQuestionsInOptions(optionIndex);

                addQuestionsPerPage(options.get(optionIndex).getQuestions());
            }
            
            
        }

        //clickEyeIcon(updateQuestion);
        System.out.println("counter3: " + counter3++);
        //addNewQuestionbutton();
        //clickEyeIcon(index);
        
    }    
        

    
}              

}                
