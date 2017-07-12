package com.greetz.ft.d2m.factory;

import com.greetz.ft.d2m.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author by arsen on 11/28/16..
 */
public class WebDriverFactory {

    private static final String CHROME_NAME = "chrome";

    private static final String FIREFOX_NAME = "firefox";

    private static final Configuration configuration = Configuration.instance();

    public static WebDriver createWebDriver() {
        final WebDriver driver;

        if (configuration.getIsLocal()) {
            String usedBrowser = configuration.getBrowser();
            if (CHROME_NAME.equals(usedBrowser)) {
                if (System.getProperty("os.name").startsWith("Windows")) {
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                } else {
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                }
                driver = new ChromeDriver();
            } else if (FIREFOX_NAME.equals(usedBrowser)) {
                driver = new FirefoxDriver();
            } else {
                throw new UnsupportedOperationException("Browser name you specified is not supported!");
            }
        } else {
            final String sauceUrl = String.format("http://%s:%s@ondemand.saucelabs.com:80/wd/hub", configuration.getUsername(), configuration.getAccessKey());
            try {
                final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setBrowserName(configuration.getBrowser());
                desiredCapabilities.setVersion(configuration.getBrowserVersion());
                desiredCapabilities.setCapability(CapabilityType.PLATFORM, configuration.getPlatform());
                desiredCapabilities.setCapability("idleTimeout", configuration.getIdleTimeout());
                desiredCapabilities.setCapability("build", configuration.getBuild());
                desiredCapabilities.setCapability("screenResolution", "1920x1080");
                driver = new RemoteWebDriver(new URL(sauceUrl), desiredCapabilities);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        driver.manage().timeouts().implicitlyWait(configuration.getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }
}
