package Factory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.time.Duration;

public class WebDriverFactory {
    protected static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);
    private static AndroidDriver driver;
    private static AppiumDriverLocalService service;
    private static final String DEVICE_UDID = System.getenv().getOrDefault("ANDROID_UDID", "YOUR_DEVICE_UDID");


    public static AndroidDriver InitDriver() {

        try {
            ForceClosePackage("com.ticktick.task");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid(DEVICE_UDID)
                .setAutoGrantPermissions(true)
                .setNoReset(true)
                .setPlatformName("Android")
                .setAppWaitForLaunch(true)
                .setAppPackage("com.ticktick.task")
                .setAppActivity("com.ticktick.task.activity.MeTaskActivity");

        try {
            driver = new AndroidDriver(
                    new URI("http://127.0.0.1:4723").toURL(), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            logger.info("Driver initialized successfully");
            return driver;
        } catch (Exception e) {
            logger.error("Error initializing driver: {}", e.getMessage());
            return null;
        }
    }
    public static AndroidDriver getDriver () {
        if (driver == null) {
            driver = InitDriver();
            return driver ;
        }
        else
            return driver;

    }
    public static void closeDriver () {
        if (driver != null) {
            driver.quit();
        }
        service.stop();
    }
    private static void ForceClosePackage (String packageName) throws IOException, InterruptedException {
        new ProcessBuilder("adb", "shell", "am", "force-stop", packageName).start().waitFor();
        logger.info("Force closed package: {}", packageName);
    }
}
