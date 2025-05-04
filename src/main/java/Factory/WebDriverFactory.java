package Factory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URI;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class WebDriverFactory {

    private static AndroidDriver driver;
    private static AppiumDriverLocalService service;

    public static AndroidDriver getDriver() {
        if(driver ==null) {
            service = AppiumDriverLocalService.buildDefaultService();
            service.start();

            UiAutomator2Options options = new UiAutomator2Options()
                    .setUdid("YOUR_DEVICE_UDID")
                    .setAutoGrantPermissions(true)
                    .setNoReset(true)
                    .setPlatformName("Android")
                    .setAppWaitForLaunch(true);

            try {
                driver = new AndroidDriver(
                        new URI("http://127.0.0.1:4723").toURL(), options);


                // Thread.sleep(5000);
                return driver;

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            return driver;
        }
        return null;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
        service.stop();
    }
}
