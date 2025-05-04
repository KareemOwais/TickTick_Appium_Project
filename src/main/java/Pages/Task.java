package Pages;

import Factory.WebDriverFactory;
import Interactions.Button;
import Interactions.Textbox;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Task {
    private static final Textbox EditName = new Textbox(AppiumBy.id("com.ticktick.task:id/edit_text") , "Edit task name");
    private static final Button Priority_Button = new Button(AppiumBy.id("com.ticktick.task:id/priority_toggle_btn") , "Change Priority");
    private static final Button High_Pr = new Button(AppiumBy.androidUIAutomator("new UiSelector().text(\"High Priority\")"),"Set High Priority");
    private static final Button Med_Pr = new Button(AppiumBy.androidUIAutomator("new UiSelector().text(\"Medium Priority\")"),"Set High Priority");
    private static final Button Low_Pr = new Button(AppiumBy.androidUIAutomator("new UiSelector().text(\"Low Priority\")"),"Set High Priority");
    private static final Button No_Pr = new Button(AppiumBy.androidUIAutomator("new UiSelector().text(\"No Priority\")"),"Set High Priority");
    //private static final String Check_Priority = "/android.widget.ImageView[@resource-id=\"com.ticktick.task:id/iv_checked\"]"

    public static void EditTaskName(String Name){
        EditName.ClearText();
        EditName.setText(Name);
    }

    public static void SetPriority(String Priority){
        Priority_Button.click();
        switch (Priority){
            case "High":
                High_Pr.click();
                break;
            case "Medium":
                Med_Pr.click();
                break;
            case "Low":
                Low_Pr.click();
                break;
            case "No":
                No_Pr.click();
                break;
            default:
                break;
        }


    }




}
