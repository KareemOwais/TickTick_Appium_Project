
import Pages.Task;
import Pages.TasksPage;

import Utils.MobileGestures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

//import static Factory.WebDriverFactory.driver;


public class ExampleTest {
    protected static final Logger logger = LoggerFactory.getLogger(ExampleTest.class);
    @Test
    public void Test1() throws InterruptedException {
        TasksPage tasksPage = new TasksPage();
        tasksPage.ChooseList("Test1");
        tasksPage.clickTaskCheckbox("Test2");
        tasksPage.editTask("Kareem");
        Task.EditTaskName("Kareem's Task");
        Task.SetPriority("High");
        Task.assertPriorityIsChecked("High Priority");
        Task.AddTag("Testing123");
        Task.RemoveTag("t1");
        Task.Add_Descrioption("This is a test description");
        Task.Exit_Task();
        tasksPage.clickTaskCheckbox("Kareem's Task");
        tasksPage.CreateTask("Test Task", "This is a test task");
    }

    @Test
    public void gestureTest() throws InterruptedException {
        TasksPage tasksPage = new TasksPage();
        MobileGestures.dragAndDrop();
        TasksPage.CheckTaskAsWontDO();
        MobileGestures.rotateLandscape();
        MobileGestures.rotatePortrait();
        TasksPage.multiplpeSelectPriority();
    }



    @Test
    public void invalidTestNonExistentPriority() throws InterruptedException {
        TasksPage tasksPage = new TasksPage();
        tasksPage.ChooseList("invalidTest2");
        tasksPage.assertTaskNameField("invalidTest2");
        tasksPage.clickTaskCheckbox("T2");
        tasksPage.editTask("Yehya");
        Task.EditTaskName("Kareem");
        try
        {
            Task.SetPriority("VeryHigh");
        }
        catch (Exception e)
        {
            Assert.fail("Unexpected exception occurred: " + e.getMessage());

        }
        MobileGestures.BackButton();
        Task.AddTag("Testing123");
        Task.Add_Descrioption("This is a test description");
        Task.Exit_Task();
        tasksPage.clickTaskCheckbox("Kareem's Task");
        tasksPage.CreateTask("Test Task", "This is a test task");
        tasksPage.clickTaskCheckbox("Test Task");

    }
    @Test
    public void invalidTestNonExistentTask() throws InterruptedException {
        TasksPage tasksPage = new TasksPage();
        tasksPage.clickTaskCheckbox("Test2");
        tasksPage.editTask("Kareem");
        Task.EditTaskName("Kareem's Task");
        Task.SetPriority("High");
        Task.assertPriorityIsChecked("High Priority");
        Task.AddTag("Testing123");
        Task.Add_Descrioption("This is a test description");
        Task.Exit_Task();
        // Attempt to interact with a non-existent task
        try
        {
            tasksPage.clickTaskCheckbox("KAREEMS Tasks");
        }
        catch (Exception e)
        {
            // Handle any unexpected exceptions
            Assert.fail("Unexpected exception occurred: " + e.getMessage());
        }
        logger.info("Non-existent task interaction handled as expected.");
    }
}

