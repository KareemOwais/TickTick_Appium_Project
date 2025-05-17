package Factory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.time.Duration;

public class WebDriverFactory {
    protected static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);
    private static AndroidDriver driver;
    private static AppiumDriverLocalService service;
    public static AndroidDriver getDriver() {
        if(driver ==null) {
            service = AppiumDriverLocalService.buildDefaultService();
            service.start();

            UiAutomator2Options options = new UiAutomator2Options()
                    .setUdid("RKCW700AX0W")
                    .setAutoGrantPermissions(true)
                    .setNoReset(true)
                    .setPlatformName("Android")
                    .setAppWaitForLaunch(true);

            try {
                driver = new AndroidDriver(
                        new URI("http://127.0.0.1:4723").toURL(), options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                logger.info("Driver initialized successfully");
                return driver;
            }
            catch (Exception e) {
                logger.error("Error initializing driver: {}", e.getMessage());
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
