package Interactions;

import io.appium.java_client.AppiumBy;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import static Utils.WaitUtils.waitForElementToBeVisible;

public class Textbox extends Element {
    // Constructor
    public Textbox(By Locator , String Description) {
        super(Locator);
        this.Description = Description;
    }
    public Textbox(AppiumBy Locator) {
        super(Locator);
    }

    public void setText(String text) {
        try {
            WebElement element = waitForElementToBeVisible(driver, Locator);
            element.sendKeys(text);
            logger.info("Set text {} {} {}" , text , "' in element: " , Description);
        } catch (NoSuchElementException e) {
            logger.error("Textbox not found: {}" , Description, e);
        } catch (TimeoutException e) {
            logger.error("Textbox not visible in time: {}" , Description, e);
        } catch (Exception e) {
            logger.error("Unexpected error while setting text in textbox: {}" ,Description, e);
        }
    }
    public String GetText(){
        try {
            WebElement element = waitForElementToBeVisible(driver, Locator);
            String text = element.getText();
            logger.info("Get text {} {} {}" , text , "' in element: " , Description);
            return text;
        } catch (NoSuchElementException e) {
            logger.error("Textbox not found: {}" , Description, e);
        } catch (TimeoutException e) {
            logger.error("Textbox not visible in time: {}" , Description, e);
        } catch (Exception e) {
            logger.error("Unexpected error while getting text from textbox: {}" ,Description, e);
        }
        return null;

    }
    public void ClearText(){
        WebElement element = waitForElementToBeVisible(driver, Locator);
        element.clear();
    }

}