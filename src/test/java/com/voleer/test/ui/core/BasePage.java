package com.voleer.test.ui.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class BasePage extends Browser {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;
    private final Actions actions;

    public BasePage() {
        this.driver  = ThreadStorage.getDriver();;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(3));
        this.js = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
    }


    /**
     * wait for element to be in DOM and find it
     * @param locator
     * @return
     */
    protected WebElement findElement(By locator){
        return waitForElementToBeInDOM(locator);
    }

    /**
     * locate and element(Does not find it)
     * @param css
     * @return
     */
    protected By locateElementByCSS(String css){
        return By.cssSelector(css);
    }

    /**
     * enter a text into a text field or any input type that is typable
     * @param element
     * @param text
     */
    protected void enterText(WebElement element, String text){
        element.sendKeys(text);
    }

    /**
     * waits for element to be in DOM and returns an element
     * @param locator
     * @return
     */
    private WebElement  waitForElementToBeInDOM(By locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * wait for element to be clickable and return an element
     * @param element
     * @return
     */
    private WebElement waitForElementToBeClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * wait for nested element to be in DOM and returns it
     * @param parent
     * @param child
     * @return
     */
    protected WebElement findNestedElement(By parent, By child){
        return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, child));
    }

    /**
     * waits for element to be clickable, and perfroms a click
     * @param element
     */
    protected void clickElement(WebElement element){
        waitForElementToBeClickable(element).click();
    }

    /**
     * select a value for a dropdown(select) element type
     * @param element
     * @param value
     */
    protected void selectValueByText(WebElement element, String value){
        new Select(element).selectByValue(value);
    }

    /**
     * determines if element is selected
     * @param element
     * @return
     */
    protected boolean isSelected(WebElement element){
        return element.isSelected();
    }

    /**
     * determines if element is visible
     * @param element
     * @return
     */
    protected boolean isVisible(WebElement element){
        return element.isDisplayed();
    }

    /**
     * determines if element is visible
     * @param locator
     * @return
     */
    protected boolean isVisible(By locator){
            try{
                return isVisible(findElement(locator));
            }catch(NoSuchElementException | TimeoutException ex){
                return false;
            }

    }

    /**
     * clicking on element via JavascriptExecutable interface
     * direct manupulation of the DOM
     * @param element
     */
    protected void clickViaJS(WebElement element){
       js.executeScript("arguments[0].click();", element);
    }

    /**
     * wait for url to be different that the one provided
     * @param url
     */
    protected void waitForUrlToChange(String url){
        wait.until(driver->!driver.getCurrentUrl().equals(url));
    }

    /**
     * wait for at least one of the nested elements to be in DOM and return multiple elements
     * @param parent
     * @param child
     * @return
     */
    protected List<WebElement> findElements(By parent, By child){
        return wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(parent, child));
    }

    /**
     * returns a text of the element
     * @param element
     * @return
     */
    protected String getElementText(WebElement element){
        String text =  element.getText();
        if(text.isEmpty()){
            text = getElementAttributeValue(element, "value");
        }
        return text;
    }

    /**
     * gets a value of supplied element attribute
     * @param element
     * @param attribute
     * @return
     */
    protected String getElementAttributeValue(WebElement element, String attribute){
        return element.getAttribute(attribute);
    }

}
