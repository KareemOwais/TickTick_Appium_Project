
import Factory.WebDriverFactory;
import Interactions.Button;
import Interactions.Label;
import Interactions.Textbox;
import Pages.Task;
import Pages.TasksPage;
import Utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.Test;


public class ExampleTest {
    @Test
    public void Test1() throws InterruptedException {

        TasksPage tasksPage = new TasksPage();
        tasksPage.editTask("Test1");
        Task.SetPriority("High");
        String Pir = Task.getSelectedPriority();
        System.out.println(Pir);

    }
}

