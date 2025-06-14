package Interactions;
import Factory.WebDriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class Element {
    protected By Locator ;
    protected static final Logger logger = LoggerFactory.getLogger(Element.class);
    protected String Description ;
    protected AndroidDriver driver;
    // Constructor
    protected Element(By Locator) {
        this.Locator = Locator;
        this.driver = WebDriverFactory.getDriver();
    }
    public By getLocator() {
        return Locator;
    }

}