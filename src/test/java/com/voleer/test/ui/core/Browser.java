package com.voleer.test.ui.core;

import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;

public class Browser implements IBrowser {

    private final WebDriver driver ;

    public Browser() {
        this.driver = ThreadStorage.getDriver();
    }

    /**
     * Navigates to a given page and returns that page
     * @param c
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T extends  Navigatable> T navigateTo(Class<T> c) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T page =  c.getDeclaredConstructor().newInstance();
        maximizeWindow();
        navigateTo(page.getPageUrl());
        return page;
    }

    private void maximizeWindow(){
        this.driver.manage().window().maximize();
    }

    private void navigateTo(String url){
        this.driver.get(url);
    }

    /**
     * gets the current Browser Url
     * @return
     */
    public String getUrl(){
        return driver.getCurrentUrl();
    }

    /**
     * determines if cookie exists in the browser
     * @param name
     * @return
     */
    public boolean isCookieExist(String name){
        return driver.manage().getCookies().stream().anyMatch(c->c.getName().equals(name));
    }



}
