package com.greetz.ft.d2m;

import com.greetz.ft.d2m.factory.WebDriverFactory;
import com.greetz.ft.d2m.pages.PageBuilder;
import com.greetz.ft.d2m.pages.Ucome.UcomBiznesTV;
import com.greetz.ft.d2m.pages.home.HomePage;
import com.greetz.ft.d2m.utils.SauceLabsListener;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners({SauceLabsListener.class})
public abstract class BaseTest implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {

    private static final String SAUCE_NAME = "sauce";
    private static final Configuration configuration = Configuration.instance();
    /**
     * all the pages
     */
    protected HomePage homePage;
    protected UcomBiznesTV ucomBiznesTV;
    private   ThreadLocal<WebDriver> driver;


    @DataProvider(name = "browsers")
    public static Object[][] browser() {
        return new Object[][]{
                {"chrome", "latest"}
        };
    }

    @Override
    public String getSessionId() {
        final WebDriver driver = webDriver();
        if (driver instanceof RemoteWebDriver) {
            return ((RemoteWebDriver) driver).getSessionId().toString();
        } else {
            return null;
        }
    }

    protected WebDriver webDriver() {
        return driver.get();
    }

    @Override
    public SauceOnDemandAuthentication getAuthentication() {
        final SauceOnDemandAuthentication sauceOnDemandAuthentication = new SauceOnDemandAuthentication();
        sauceOnDemandAuthentication.setUsername(configuration.getUsername());
        sauceOnDemandAuthentication.setAccessKey(configuration.getAccessKey());
        return sauceOnDemandAuthentication;
    }

    @BeforeTest
    public void createPages() {
        driver = new ThreadLocal<>();
        driver.set(WebDriverFactory.createWebDriver());
        homePage = PageBuilder.page(HomePage.class, webDriver()).build();
        ucomBiznesTV = PageBuilder.page(UcomBiznesTV.class, webDriver()).build();
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowser() {
        if (webDriver() != null) {
            webDriver().close();
        }
    }
}


