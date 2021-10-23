package com.voleer.test.ui.pages.components;

import com.voleer.test.ui.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BaseComponent extends BasePage {
    private final By locator;
    private final String componentBelongsToPageUrl;


    public BaseComponent(By locator) {
        this.locator=locator;
        this.componentBelongsToPageUrl = getUrl();
    }


    public void enterText(By child, String text) {
        super.enterText(findNestedElement(this.locator, child ), text);
    }

    public void clickElement(By child){
        super.clickElement(findNestedElement(this.locator, child));
    }

    public void selectValueFromDropDown(By child, String value){
        super.selectValueByText(findNestedElement(this.locator, child), value);
    }

    public boolean isSelected(By child){
        return super.isSelected(findNestedElement(this.locator, child));
    }

    public boolean isVisible(By child){
        return super.isVisible(findNestedElement(this.locator, child));
    }

    public boolean isVisible(){
        return super.isVisible(this.locator);
    }

    public void clickViaJS(By child){
        super.clickViaJS(findNestedElement(this.locator, child));
    }

    public List<WebElement> findElements(By child){
        return super.findElements(this.locator, child);
    }

    public String getElementText(By child){
        return getElementText(findNestedElement(this.locator, child));
    }

    public String getElementAttributeValue(By child, String value){
        return super.getElementAttributeValue(findNestedElement(this.locator, child), value);
    }
    public void waitForNewUrl(){
        super.waitForUrlToChange(this.componentBelongsToPageUrl);
    }


}
