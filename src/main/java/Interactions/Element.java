package Interactions;
import Factory.WebDriverFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class Element {
    public By Locator ;
    protected static final Logger logger = LoggerFactory.getLogger(Element.class);
    String Description ;
    protected AndroidDriver driver;
    // Constructor
    protected Element(By Locator) {
        this.Locator = Locator;
        this.driver = WebDriverFactory.getDriver();
    }

}