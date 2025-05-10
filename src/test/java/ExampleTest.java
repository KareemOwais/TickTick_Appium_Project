
import Pages.Task;
import Pages.TasksPage;

import org.testng.annotations.Test;


public class ExampleTest {
    @Test
    public void Test1() throws InterruptedException {

        TasksPage tasksPage = new TasksPage();

        tasksPage.ChooseList("tt");
        tasksPage.editTask("Test2");
        Task.EditTaskName("Kareem's Task");
        Task.SetPriority("High");
        Task.AddTag("Testing123");
        Task.RemoveTag("Project");
        Task.Add_Descrioption("This is a test description");
        Task.Exit_Task();
        tasksPage.clickTaskCheckbox("Test3");
        tasksPage.clickTaskCheckbox("Kareem's Task");
        tasksPage.CreateTask("Test Task", "This is a test task");
        tasksPage.clickTaskCheckbox("Test Task");
    }
}

