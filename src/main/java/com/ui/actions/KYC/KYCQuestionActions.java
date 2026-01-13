package com.ui.actions.KYC;



import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
                    .DropdownListTypeQuestion()
                    .fillEnglishQuestion(kycQuestionPojo.getLabel().getEn())
                    .fillArabicQuestion(kycQuestionPojo.getLabel().getAr());

    }

    
    private void addOptions(KycOptionPojo kycOptionPojo){

        kycAddNewPage
        .fillEnglishOption(kycOptionPojo.getLabel().getEn())
        .fillArabicOption(kycOptionPojo.getLabel().getAr());
    }


    private void clickEyeIcon(){
        kycAddNewPage
        .clickOnEyeIcon();
    }


    private void addNewoption()
    {
        kycAddNewPage
        .clickAddOptionButtonForMultiQuestions();
    }


    private void addNewQuestionbutton(){
        kycAddNewPage
        .clickAddByNewInputButton();
    }

    private void clickAddNestedQuestionsInOptions(){
        kycAddNewPage
        .clickAddNestedQuestionsInOption();
    }



    
   


    /*

     Main Function To add Questions Per page 

    */
    

     int counter = 1, counter2 = 1, counter3 = 1;
        

    public void addQuestionsPerPage(List<KycQuestionPojo>questions) throws InterruptedException{
        

        
        // Loop on questions with questionTemp (Locators Start from 1)
           
        for( KycQuestionPojo question : questions){
            
        System.out.println("counter2: " + counter2++);

            //Fill Label en/ar for each question
            fillOneQuestionMainFields(question);             
               
            


            
            List <KycOptionPojo> options = question.getOptions();
            System.out.println("size of options in question["+question.getQuestionId()+"]is: " + options.size());
            //Loop on Options
            for(int optionIndex = 0 ; optionIndex < options.size() ; optionIndex++){

            
                System.out.println("counter: " + counter++);
                //Fill Label en/ar for each option
                addOptions(options.get(optionIndex));

                
                if (optionIndex < options.size()-1) {

                    addNewoption();
                }


                

                //Nested questions in Options
                if (options.get(optionIndex).getQuestions() != null) {
                    
                    
                    
                    clickAddNestedQuestionsInOptions();


                   
                    addQuestionsPerPage(options.get(optionIndex).getQuestions());
                }
                
        
              
            }
            clickEyeIcon();
            
            System.out.println("counter3: " + counter3++);

    
            

          
                //addNewQuestionbutton();
                //clickEyeIcon(index);
            
        }    
        

    }              
}                
