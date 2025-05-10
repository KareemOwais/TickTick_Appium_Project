package Pages;

import Factory.WebDriverFactory;
import Interactions.Button;
import Interactions.Textbox;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import java.util.List;

public class TasksPage {
    Button AddTask = new Button(AppiumBy.id("com.ticktick.task:id/add_task_btn"));
    Textbox TaskName = new Textbox(AppiumBy.id("com.ticktick.task:id/et_title"), "Task Name");
    Textbox TaskDescription = new Textbox(AppiumBy.id("com.ticktick.task:id/et_content"), "Task Description");
    Button ConfirmTask = new Button(AppiumBy.id("com.ticktick.task:id/iv_save"));
    Button Listmenu = new Button(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageButton\").instance(1)"));
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

}
