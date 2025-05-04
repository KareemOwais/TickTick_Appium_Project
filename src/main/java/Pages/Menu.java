package Pages;

import Interactions.Button;
import io.appium.java_client.AppiumBy;

public class Menu {

    private static final Button TasksButton = new Button(AppiumBy.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.ticktick.task:id/container\"])[1]"), "Tasks Button");
    private static final Button CalendarButton = new Button(AppiumBy.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.ticktick.task:id/container\"])[2]"), "Calendar Button");
    private static final Button FocusButton = new Button(AppiumBy.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.ticktick.task:id/container\"])[3]"),"Focus Button");
    private static final Button HabitsButton = new Button(AppiumBy.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.ticktick.task:id/container\"])[4]"),"Habits Button");
    private static final Button SettingsButton = new Button(AppiumBy.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.ticktick.task:id/container\"])[5]"), "SettingsButton");

    public static void NavigateTo(String Button){
        switch (Button){
            case "Tasks" :
                TasksButton.click();
                break;
            case "Calendar":
                CalendarButton.click();
                break;
            case "Focus" :
                FocusButton.click();
                break;
            case "Habits":
                HabitsButton.click();
                break;
            case "Settings":
                SettingsButton.click();
                break;
            default:
                break;
        }
    }

}
