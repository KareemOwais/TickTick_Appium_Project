package Pages;

import Factory.WebDriverFactory;
import Interactions.Button;
import Interactions.Textbox;
import Utils.MobileGestures;
import Utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class Task {
    protected static final Logger logger = LoggerFactory.getLogger(Task.class);
    private static final Textbox EditName = new Textbox(AppiumBy.id("com.ticktick.task:id/edit_text") , "Edit task name");
    private static final Button EditName_Button = new Button(AppiumBy.id("com.ticktick.task:id/list_item_title"), "Edit Name Button");
    private static final Button Priority_Button = new Button(AppiumBy.id("com.ticktick.task:id/priority_toggle_btn") , "Change Priority");
    private static final Button High_Pr = new Button(AppiumBy.androidUIAutomator("new UiSelector().text(\"High Priority\")"),"Set High Priority");
    private static final Button Med_Pr = new Button(AppiumBy.androidUIAutomator("new UiSelector().text(\"Medium Priority\")"),"Set High Priority");
    private static final Button Low_Pr = new Button(AppiumBy.androidUIAutomator("new UiSelector().text(\"Low Priority\")"),"Set High Priority");
    private static final Button No_Pr = new Button(AppiumBy.androidUIAutomator("new UiSelector().text(\"No Priority\")"),"Set High Priority");
    private static final Button Add_Tag = new Button(AppiumBy.id("com.ticktick.task:id/input_tag"),"Add a tag");
    private static final Button Save_Tags_Button = new Button(AppiumBy.id("android:id/button1"));
    private static final Textbox Tag_Name_Field = new Textbox(AppiumBy.id("com.ticktick.task:id/input_tag_et"), "Add the Tag");
    private static final Button Descp_Button = new Button(AppiumBy.id("com.ticktick.task:id/task_editor_composite"));
    private static final Textbox Descp_TexBox = new Textbox(AppiumBy.id("com.ticktick.task:id/task_editor_composite"),"Description TexBox");
    //private static final String Check_Priority = "/android.widget.ImageView[@resource-id=\"com.ticktick.task:id/iv_checked\"]"

    public static void EditTaskName(String Name){
        EditName_Button.click();
        MobileGestures.HideKeyboard();
        EditName.ClearText();
        EditName.setText(Name);
        MobileGestures.PressEnter();
        logger.info("Task name set to: {}", Name);
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
        logger.info("Task priority set to: {}", Priority);
    }

    public static void RemoveTag(String TagName) throws InterruptedException {
        WebElement Tag = WebDriverFactory.getDriver().findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\""+TagName+"\").instance(1)"));
        if(Tag.isEnabled()){
            Tag.click();
            WaitUtils.waitForElementToBeVisible(WebDriverFactory.getDriver(),AppiumBy.androidUIAutomator("new UiSelector().text(\""+TagName+"\")"));
            WebElement TagCheckBox = WebDriverFactory.getDriver().findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\""+TagName+"\")"));
            if(TagCheckBox.isEnabled()){
                TagCheckBox.click();
                TagCheckBox.click();
                Save_Tags_Button.click();
                logger.info("Tag removed: {}", TagName);
            }
            else
                logger.error("unable to remove the tag: {}", TagName);
        }
        else{
            logger.error("Tag doesn't exsits");
        }
    }

    public static void AddTag(String TagName){
        Add_Tag.click();
        Tag_Name_Field.setText(TagName);
        MobileGestures.PressEnter();
        MobileGestures.HideKeyboard();
        logger.info("Tag added: {}", TagName);
    }
    public static void Exit_Task(){
        MobileGestures.BackButton();
    }

    public static void Add_Descrioption(String Desc){
        Descp_Button.click();
        MobileGestures.HideKeyboard();
        Descp_TexBox.setText(Desc);
        MobileGestures.PressEnter();
        logger.info("Task description set to: {}", Desc);
    }
    public static void SetTaskDate(String Date){
        String Text =EditName.GetText();
        EditName_Button.click();
        EditName.setText(Text+" @"+Date);
        MobileGestures.PressEnter();
        MobileGestures.HideKeyboard();
        logger.info("Task date set to: {}", Date);
    }
    public static void SetNameAndDate(String Name , String Date){
        EditTaskName(Name+" @"+Date);
    }

    public static void assertPriorityIsChecked(String priorityToCheck)
    {
        Priority_Button.click();
        // Store the list of priorities
        List<WebElement> priorities = WebDriverFactory.getDriver()
                .findElements(AppiumBy.className("android.widget.LinearLayout"));

        Boolean flag = false;

        // Loop through all priorities
        for (WebElement priority : priorities)
        {
            // Get the title of the priority
            WebElement title = priority.findElement(AppiumBy.id("com.ticktick.task:id/tv_title"));

            // Get the type of the priority
            String text = title.getText();

            // Check if this priority is checked
            boolean check = !priority
                    .findElements(AppiumBy.id("com.ticktick.task:id/iv_checked"))
                    .isEmpty();

            // Check if this is the right one
            if (text.equals(priorityToCheck) && check)
            {
                //Assert.assertEquals(priorityToCheck, text);
                System.out.println("Expected " + priorityToCheck + " is checked");
                flag = true;
            }
        }
        if(!flag)
        {
            throw new AssertionError("Expected " + priorityToCheck + " , but it was not");
        }
        MobileGestures.BackButton();
    }




}
