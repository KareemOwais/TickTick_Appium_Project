package Pages;

import Factory.WebDriverFactory;
import Interactions.Button;
import Interactions.Textbox;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import java.util.List;

public class TasksPage {
    Button AddTask = new Button(AppiumBy.id("com.ticktick.task:id/add_task_btn"));
    Textbox TaskName = new Textbox(AppiumBy.id("com.ticktick.task:id/et_title"), "Task Name");
    Textbox TaskDescription = new Textbox(AppiumBy.id("com.ticktick.task:id/et_content"), "Task Description");
    Button ConfirmTask = new Button(AppiumBy.id("com.ticktick.task:id/iv_save"));
    public static Button ListButton = new Button(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageButton\").instance(0)"));
    public static Button Listmenu = new Button(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageButton\").instance(1)"));
    private WebElement FindTask(String taskName) {
        // Get all task containers
        List<WebElement> containers = WebDriverFactory.getDriver().findElements(AppiumBy.xpath(
                "//androidx.recyclerview.widget.RecyclerView[@resource-id='com.ticktick.task:id/list']" +
                        "/android.view.ViewGroup[@resource-id='com.ticktick.task:id/container']"
        ));

        for (WebElement container : containers) {

                WebElement nameElement = container.findElement(AppiumBy.id("com.ticktick.task:id/title"));
                String name = nameElement.getText();

                if (name.equals(taskName)) {
                   return container;
                }

        }
        return null;
    }
    public void clickTaskCheckbox(String taskName) {
        WebElement task = FindTask(taskName);
        if (task != null) {
            WebElement checkbox = task.findElement(AppiumBy.id("com.ticktick.task:id/checkbox"));
            checkbox.click();
        } else {
            System.out.println("Task not found: " + taskName);
        }
    }
    public void editTask(String taskName) {
        WebElement task = FindTask(taskName);
        if (task != null) {
            task.click();
        } else {
            System.out.println("Task not found: " + taskName);
        }
    }
    public void CreateTask(String Name , String Description){
        AddTask.click();
        TaskName.setText(Name);
        TaskDescription.setText(Description);
        ConfirmTask.click();
    }
    public void ChooseList(String ListName) throws InterruptedException {
        Listmenu.click();
        List<WebElement> containers = WebDriverFactory.getDriver().findElements(AppiumBy.xpath(
                "//androidx.recyclerview.widget.RecyclerView[@resource-id='com.ticktick.task:id/recyclerView']/android.widget.RelativeLayout"
        ));

        for (WebElement container : containers) {
            WebElement nameElement = container.findElement(AppiumBy.id("com.ticktick.task:id/name"));
            String name = nameElement.getText();
            if (name.equals(ListName)) {
                nameElement.click();
                break;
            }
        }
    }
    public void assertTaskNameField(String taskName)
    {
        String actualName = WebDriverFactory.getDriver().findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + taskName + "\")")).getText();
        Assert.assertEquals(actualName, taskName, "Task name does not match the expected value.");
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
    public static void CheckTaskAsWontDO()
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

}
