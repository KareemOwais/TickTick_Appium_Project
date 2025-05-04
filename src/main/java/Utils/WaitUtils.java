package Utils;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    // Using AppiumBy for locator
    public static WebElement waitForElementToBePresent(AndroidDriver driver, By Locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until( driver1-> driver1.findElement(Locator));
    }

    // Using AppiumBy for locator
    public static WebElement waitForElementToBeVisible(AndroidDriver driver, By Locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> {
            WebElement element = waitForElementToBePresent(driver, Locator);
            return element.isDisplayed() ? element : null;
        });
    }

    // Using AppiumBy for locator
    public static WebElement waitForElementToBeClickable(AndroidDriver driver, By Locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> {
            WebElement element = waitForElementToBeVisible(driver, Locator);
            return element.isEnabled() ? element : null;
        });
    }

    // Using AppiumBy for handling alerts
    public static void handleAlert(AndroidDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

}
