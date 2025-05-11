package Utils;

import Factory.WebDriverFactory;
import Pages.TasksPage;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class MobileGestures
{
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
    }

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
    }

    public static void rotatePortrait()
    {
        DeviceRotation portrait = new DeviceRotation(0, 0, 0);
        WebDriverFactory.getDriver().rotate(portrait);
    }

    public static void longGestureWontDo()
    {
        // Element for long press
        WebElement ele = WebDriverFactory.getDriver().findElement(By.xpath("(//android.widget.ImageView[@resource-id=\"com.ticktick.task:id/checkbox\"])[2]"));

        // Long press gesture
        WebDriverFactory.getDriver().executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(),
                        "duration", 2000
                ));

        // Click on Wont Do
        WebDriverFactory.getDriver().findElement(By.xpath("(//android.widget.RadioButton[@resource-id=\"com.ticktick.task:id/checkbox\"])[2]\n")).click();
    }

    public static void multiplpeSelectPriority()
    {
        // Element for long press
        WebElement ele = WebDriverFactory.getDriver().findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.ticktick.task:id/list\"]/android.view.ViewGroup[2]"));

        // Long press gesture
        WebDriverFactory.getDriver().executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(),
                        "duration", 2000
                ));

        // Select multiple tasks
        WebDriverFactory.getDriver().findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.ticktick.task:id/list\"]/android.view.ViewGroup[3]")).click();
        WebDriverFactory.getDriver().findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.ticktick.task:id/list\"]/android.view.ViewGroup[4]")).click();

        // Click More
        WebDriverFactory.getDriver().findElement(By.id("com.ticktick.task:id/more")).click();

        // Click Set Priority
        WebDriverFactory.getDriver().findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Set Priority\")")).click();

        // Click on Low Priority
        WebDriverFactory.getDriver().findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Low Priority\")")).click();
    }
}
