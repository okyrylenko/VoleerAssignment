package com.voleer.test.ui.pages.components;

import com.voleer.test.ui.core.ThreadStorage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CookieBanner extends BaseComponent {
    private final By btnClose = locateElementByCSS("button");

    public CookieBanner() {
        super(By.cssSelector("div.sqs-cookie-banner-v2"));
    }

    public boolean isBannerDisplayed(){
        return isVisible();
    }

    public void closeBanner(){
        clickElement(btnClose);
    }
}
