package com.voleer.test.ui.pages.components;

import com.voleer.test.ui.core.ThreadStorage;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

public class ContactSalesForm extends BaseComponent{
    private final CookieBanner cookieBanner;
    private final By inpFirstName = locateElementByCSS("#FirstName");
    private final By divErrorMessage = locateElementByCSS("div.mktoError");
    private final By inpLastName = locateElementByCSS("#LastName");
    private final By inpEmail = locateElementByCSS("#Email");
    private final By selCountry = locateElementByCSS("#Country");
    private final By selState = locateElementByCSS("#State");
    private final By inpPhoneNumber = locateElementByCSS("#Phone");
    private final By inpJobTitle = locateElementByCSS("#Title");
    private final By rdoTerms = locateElementByCSS(" [name='optInMarketingRequested']");
    private final By txtMessage = locateElementByCSS("#Contact_Form_Message_Voleer__c");
    private final By btnSend = locateElementByCSS(" .mktoButton");



    public ContactSalesForm() {
        super(By.cssSelector(".sqs-block-content>form.mktoForm"));
        this.cookieBanner = new CookieBanner();
    }

    public ContactSalesForm withFirstName(String fName){
        super.enterText(inpFirstName, fName);
        return this;
    }

    public int getFirstNameFieldMaxLength(){
        return Integer.parseInt(super.getElementAttributeValue(inpFirstName,"maxlength"));
    }

    public String getFirstName(){
        return getElementText(inpFirstName);
    }


    public int getLastNameFieldMaxLength(){
        return Integer.parseInt(super.getElementAttributeValue(inpLastName,"maxlength"));
    }

    public String getLastName(){
        return getElementText(inpLastName);
    }

    public int getEmailFieldMaxLength(){
        return Integer.parseInt(super.getElementAttributeValue(inpEmail,"maxlength"));
    }

    public String getEmail(){
        return getElementText(inpEmail);
    }

    public int getPhoneNumberFieldMaxLength(){
        return Integer.parseInt(super.getElementAttributeValue(inpPhoneNumber,"maxlength"));
    }

    public String getPhoneNumber(){
        return getElementText(inpPhoneNumber);
    }

    public int getJobTitleFieldMaxLength(){
        return Integer.parseInt(super.getElementAttributeValue(inpJobTitle,"maxlength"));
    }

    public String getJobTitle(){
        return super.getElementText(inpJobTitle);
    }


    public ContactSalesForm withLastName(String lName){
        super.enterText(inpLastName, lName);
        return this;
    }

    public ContactSalesForm withEmail(String email){
        super.enterText(inpEmail, email);
        return this;
    }

    public ContactSalesForm withCountry(String country){
        super.selectValueFromDropDown(selCountry, country);
        return this;
    }

    public ContactSalesForm withState(String state){
        super.selectValueFromDropDown(selState, state);
        return this;
    }

    public ContactSalesForm withPhoneNumber(String phoneNumber){
        super.enterText(inpPhoneNumber, phoneNumber);
        return this;
    }

    public ContactSalesForm withJobTitle(String jobTitle){
        super.enterText(inpJobTitle, jobTitle);
        return this;
    }

    public ContactSalesForm doYourAgreeToTermsAndConditions(Boolean isAgree) throws Exception {
        boolean isSelected = isSelected(rdoTerms);
        if(isAgree && !isSelected||!isAgree && isSelected){
            if(isVisible(rdoTerms)){
                clickElement(rdoTerms);
            }else{
                clickViaJS(rdoTerms);
            }
        }
        return this;
    }

    public ContactSalesForm withMessage(String message){
        super.enterText(txtMessage, message);
        return this;
    }

    public ContactSalesForm submitForm(){
        if(cookieBanner.isBannerDisplayed()){
            cookieBanner.closeBanner();
        }
        super.clickElement(btnSend);
        return this;
    }

    public ContactSalesForm waitForUrlToChange(){
        super.waitForNewUrl();
        return this;
    }
    public ContactSalesForm fillOutForm(String fName, String lName, String email, String country, String state, String phoneNumber, String jobTitle, boolean terms, String message) throws Exception {
          return this.withFirstName(fName)
                .withLastName(lName)
                .withEmail(email)
                .withCountry(country)
                .withState(state)
                .withPhoneNumber(phoneNumber)
                .withJobTitle(jobTitle)
                .doYourAgreeToTermsAndConditions(terms)
                .withMessage(message);
    }

    public List<String> getErrorMessages(){
        List<String> errors = new ArrayList<>();
        super.findElements(divErrorMessage).forEach((error)->{
            errors.add(getElementText(error));
        });

        return errors;
    }


}
