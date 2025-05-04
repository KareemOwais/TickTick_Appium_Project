package Pages;

import Factory.WebDriverFactory;
import Interactions.Button;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import java.util.List;

public class TasksPage {
    //Button Task_info = new Button(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.ticktick.task:id/title\" and @text=\"Test2\"]"));
    Button Lists = new Button(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"com.ticktick.task:id/toolbar\"]/android.widget.ImageButton"), "Lists");

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

}
