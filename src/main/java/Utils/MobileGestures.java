package Utils;

import Factory.WebDriverFactory;
import Pages.TasksPage;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MobileGestures
{
    protected static final Logger logger = LoggerFactory.getLogger(MobileGestures.class);
    public static void swipeLeft()
    {
        TasksPage.Listmenu.click();

        // Element to swipe
        WebElement firstImage = WebDriverFactory.getDriver().findElement(By.id("com.ticktick.task:id/recyclerView"));

        // Swipe left
        WebDriverFactory.getDriver().executeScript("mobile: swipeGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) firstImage).getId(),
                        "direction", "left",
                        "percent", 0.75
                ));
        logger.info("Swiped left on element: {}", firstImage);
    }
    //this Method needs to be updated
    public static void dragAndDrop()
    {
        // The element to drag
        WebElement source = WebDriverFactory.getDriver().findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.ticktick.task:id/list\"]/android.view.ViewGroup[3]"));

        // The drag
        WebDriverFactory.getDriver().executeScript("mobile: dragGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) source).getId(),
                        "endX", 570,
                        "endY", 1200
                ));
    }
    public static void rotateLandscape()
    {
        DeviceRotation landscape = new DeviceRotation(0, 0, 90);
        WebDriverFactory.getDriver().rotate(landscape);
        logger.info("Rotated to landscape mode");
    }

    public static void rotatePortrait()
    {
        DeviceRotation portrait = new DeviceRotation(0, 0, 0);
        WebDriverFactory.getDriver().rotate(portrait);
        logger.info("Rotated to portrait mode");
    }
    public static void PressEnter(){
        ((AndroidDriver) WebDriverFactory.getDriver()).pressKey(new KeyEvent(AndroidKey.ENTER));
        logger.info("Press Enter");
    }
    public static void HideKeyboard(){
        ((AndroidDriver) WebDriverFactory.getDriver()).hideKeyboard();
        logger.info("Hide Keyboard");
    }
    public static void BackButton(){
        ((AndroidDriver) WebDriverFactory.getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
        logger.info("Press Back Button");
    }
}
