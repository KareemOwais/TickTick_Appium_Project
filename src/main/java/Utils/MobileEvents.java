package Utils;

import Factory.WebDriverFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class MobileEvents {

    public static void PressEnter(){
        ((AndroidDriver) WebDriverFactory.getDriver()).pressKey(new KeyEvent(AndroidKey.ENTER));
    }
    public static void HideKeyboard(){
        ((AndroidDriver) WebDriverFactory.getDriver()).hideKeyboard();
    }
    public static void BackButton(){
        ((AndroidDriver) WebDriverFactory.getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));

    }
}
