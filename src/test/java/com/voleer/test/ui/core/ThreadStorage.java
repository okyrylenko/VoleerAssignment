package com.voleer.test.ui.core;

import org.openqa.selenium.WebDriver;

public class ThreadStorage {

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver d) {
        driver.set(d);
    }

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();


}
