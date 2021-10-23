package com.voleer.test.ui.core;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


public class BaseTest {

    @BeforeMethod
    @Parameters("browser")
    public void before(String browser, ITestContext context){
        context.setAttribute("browser", browser);
        if(browser.equals("chrome")){
            ThreadStorage.setDriver(new ChromeDriver());
        }else if(browser.equals("firefox")){
            ThreadStorage.setDriver(new FirefoxDriver());
        }else{
            throw new InvalidArgumentException("the browser provided is " + browser + ". Accepting only chrome and firefox");
        }
    }

    @AfterMethod
    public void after(){
        ThreadStorage.getDriver().close();
    }
}
