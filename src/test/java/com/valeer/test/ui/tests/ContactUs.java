package com.valeer.test.ui.tests;

import com.voleer.test.ui.core.*;
import com.voleer.test.ui.pages.MigrationDiscoverySolutionPage;
import com.voleer.test.ui.pages.components.ContactSalesForm;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Listeners(com.voleer.test.ui.core.TestListeners.class)
@Guice(modules=GuiceDI.class)
public class ContactUs extends BaseTest {

    @Inject
    IBrowser browser;

    @Test
    public void submitContactUsForm() throws Exception {
        Browser browser = new Browser();
        String url = browser.navigateTo(MigrationDiscoverySolutionPage.class)
                .contactSalesForm()
                .fillOutForm("fName", "lName", "noMail@nomail.com","United States", "Florida", "000000000","SDET", true, "message" )
                .submitForm()
                .waitForUrlToChange()
                .getUrl();

        Boolean isCookieExist = browser.isCookieExist("mkto_trk");

        Assert.assertEquals(url, "https://voleer.com/contact-sales-redirect");
        Assert.assertFalse(isCookieExist);
    }

    @Test
    public void submitEmptyForm() throws Exception {
        List<String> errors = new Browser().navigateTo(MigrationDiscoverySolutionPage.class)
                .contactSalesForm()
                .submitForm()
                .getErrorMessages();

        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0), "This field is required.");


    }

    @Test
    public void emptyFields() throws Exception {
        SoftAssert softAssert = new SoftAssert();
        List<List<String>> errors = new ArrayList<>();
        ContactSalesForm form =  new Browser().navigateTo(MigrationDiscoverySolutionPage.class).contactSalesForm();

        errors.add(form.submitForm().getErrorMessages());
        errors.add(form.withFirstName("first Name").submitForm().getErrorMessages());
        errors.add(form.withLastName("Last Name").submitForm().getErrorMessages());
        errors.add(form.withEmail("email@nomail.com").submitForm().getErrorMessages());
        errors.add(form.withCountry("United States").submitForm().getErrorMessages());
        errors.add(form.withPhoneNumber("123-45-6789").submitForm().getErrorMessages());
        errors.add(form.withJobTitle("SDET").submitForm().getErrorMessages());
        errors.add(form.doYourAgreeToTermsAndConditions(true).submitForm().getErrorMessages());

        for(int i= 0;i<errors.size();i++){
            //email has a different error message then the other fields
            if(i==2){
                softAssert.assertEquals(errors.get(i).get(0), "Must be valid email.\n" + "example@yourdomain.com");
                softAssert.assertEquals(errors.get(i).size(),1);
            }else{
                softAssert.assertEquals(errors.get(i).get(0), "This field is required.");
                softAssert.assertEquals(errors.get(i).size(),1);
            }
        }

        softAssert.assertAll();

    }

    @Test
    public void fieldsMaxLength() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        SoftAssert softAssert = new SoftAssert();
        List<List<Integer>> max = new ArrayList<>();
        ContactSalesForm form =  new Browser().navigateTo(MigrationDiscoverySolutionPage.class).contactSalesForm();

        int maxValue = form.getFirstNameFieldMaxLength();
        int text = form.withFirstName(RandomDataGenerator.randomString(maxValue+1)).getFirstName().length();
        max.add(new ArrayList<>(Arrays.asList(maxValue, text)));

        int maxLN = form.getLastNameFieldMaxLength();
        int ln = form.withLastName(RandomDataGenerator.randomString(maxLN+1)).getLastName().length();
        max.add(new ArrayList<>(Arrays.asList(maxLN, ln)));

        int maxEmail = form.getEmailFieldMaxLength();
        int email = form.withEmail(RandomDataGenerator.randomString(maxEmail+1)).getEmail().length();
        max.add(new ArrayList<>(Arrays.asList(maxEmail, email)));

        int maxPN = form.getPhoneNumberFieldMaxLength();
        int pn= form.withPhoneNumber(RandomDataGenerator.randomString(maxPN+1)).getPhoneNumber().length();
        max.add(new ArrayList<>(Arrays.asList(maxPN, pn)));

        int maxJt = form.getJobTitleFieldMaxLength();
        int jt = form.withJobTitle(RandomDataGenerator.randomString(maxJt+1)).getJobTitle().length();
        max.add(new ArrayList<>(Arrays.asList(maxValue, jt)));

        max.forEach((field->{
            softAssert.assertEquals(field.get(0), field.get(1));
        }));

        softAssert.assertAll();

    }

    @Test
    public void emailIncorrectFormat() throws Exception {
        List<String> errorMessages = new Browser().navigateTo(MigrationDiscoverySolutionPage.class)
                .contactSalesForm()
                .fillOutForm("fName", "lName", "noMail@nomail","United States", "Florida", "000-00-0000","SDET", true, "message" )
                .submitForm()
                .getErrorMessages();

        Assert.assertEquals(errorMessages.size(), 1);
        Assert.assertEquals(errorMessages.get(0), "Invalid Input");

    }

    @Test
    public void phoneNumberIncorrectFormat() throws Exception {
        List<String> errorMessages = new Browser().navigateTo(MigrationDiscoverySolutionPage.class)
                .contactSalesForm()
                .fillOutForm("fName", "lName", "noMail@nomail.com","United States", "Florida", "+3(000)-00-0000","SDET", true, "message" )
                .submitForm()
                .getErrorMessages();

        Assert.assertEquals(errorMessages.size(), 1);
        Assert.assertEquals(errorMessages.get(0), "Must be a phone number.\n" + "503-555-1212");

    }
    @Test
    public void submitWithoutTermsOfUse() throws Exception {
        String url = new Browser().navigateTo(MigrationDiscoverySolutionPage.class)
                .contactSalesForm()
                .fillOutForm("fName", "lName", "noMail@nomail.com","United States", "Florida", "000000000","SDET", false, "message" )
                .submitForm()
                .waitForUrlToChange()
                .getUrl();


        Assert.assertEquals(url, "https://voleer.com/contact-sales-redirect");

    }

}
