package com.greetz.ft.d2m.pages;

import com.greetz.ft.d2m.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Base Page Class with methods and general UI elements
 *
 * @author arsen.
 */
public abstract class Page {

    protected static final By HAMB_MENU_SELECTOR = By.cssSelector("[data-qa-ref=humburger-menu]");
    protected static final By PAGE_HEADER_SELECTOR = By.cssSelector("[data-qa-ref=page-title]");
    protected static final By NOTIF_BAR_SELECTOR = By.cssSelector("[data-qa-ref=notification-bar]");
    protected static final By DIALOG_SELECTOR = By.cssSelector("[data-qa-ref=edit-popup-title]");

    @FindBy(css = "[data-qa-ref=profile-icon]")
    private WebElement profileIcon;

    @FindBy(css = "[data-qa-ref=hamburger-items]")
    private List<WebElement> hamburgerItems;

    @FindBy(css = "[data-qa-ref=confirm-popup-ok-button]")
    protected WebElement confirmPopupOkBtn;

    @FindBy(css = "[data-qa-ref=confirm-popup]")
    protected WebElement confirmPopup;

    private WebDriver webDriver;
    private String siteUrl;
    private int maxDelayTime;

    protected Page() {

    }

    public void switchToSecondTab(){
        ArrayList<String> arrListTabs = new ArrayList<String> (webDriver.getWindowHandles());
        webDriver.close();
        webDriver.switchTo().window(arrListTabs.get(1));
    }

    /**
     * @param element elements which Xpath we would like to receive
     * @return Xpath of the current element
     */
    public static String getXPathFromElement(WebElement element) {
        String elementDescription = element.toString();
        return elementDescription.substring(elementDescription.lastIndexOf("-> ") + 9, elementDescription.lastIndexOf("]"));
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public void setMaxDelayTime(int maxDelayTime) {
        this.maxDelayTime = maxDelayTime;
    }

    protected void open(String pagePath) {
        open(pagePath, null, maxDelayTime, false);
    }

    protected void open(String pagePath, String pageTitle) {
        open(pagePath, pageTitle, maxDelayTime, true);
    }

    protected void open(String pagePath, String pageTitle, Integer maxDelayTime) {
        open(pagePath, pageTitle, maxDelayTime, true);
    }

    public void clickOnProfileIcon() {
        clickOnButton(profileIcon);
        waitElement(1);
    }

    public void clickOnHamburgerItem(String hamburgerItemText) {
        clickOnProfileIcon();
        for (WebElement item : hamburgerItems) {
            if (item.getText().contains(hamburgerItemText)) {
                clickOnButton(item);
                return;
            }
        }

    }

    private void open(String pagePath, String pageTitle, Integer maxDelayTime, boolean checkTitle) {
        webDriver.manage().window().maximize();
        webDriver.get(siteUrl + pagePath);
        final WebDriverWait wait = new WebDriverWait(webDriver, maxDelayTime);
        if (checkTitle) {
            wait.until(ExpectedConditions.titleContains(pageTitle));
        }
    }

    /**
     * @param element
     * @param text
     */
    protected void type(WebElement element, String text) throws InterruptedException {
        //clickOnButton(element);
    	new Actions(webDriver).moveToElement(element).perform();
        element.clear();
        element.sendKeys(text);
        Thread.sleep(500);
    }

    /**
     * @param element
     */
    protected void submit(WebElement element) {
        element.submit();
    }

  /*  protected void clickOnButton(WebElement element) {
        new WebDriverWait(webDriver, 15).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }*/
    
    protected void clickOnButton(WebElement element) {
        new WebDriverWait(webDriver, 15).until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].style.border='4px solid yellow'", element);
        element.click();
    }

    /**
     * @param element
     * @return
     */
    protected boolean isElementPresent(WebElement element) {
        try {
            element.getTagName();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    protected boolean isElementDisplayed(WebElement element) {
        return isElementPresent(element) && element.isDisplayed();
    }

    private boolean isElementTextEqual(WebElement element, String text, boolean ignoreCase) {
        try {
            if (ignoreCase) {
                return StringUtils.equalsIgnoreCase(element.getText(), text);
            } else {
                return StringUtils.equals(element.getText(), text);
            }
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementTextEqual(WebElement element, String text) {
        return isElementTextEqual(element, text, false);
    }

    /**
     * wait By ExpectedCondition
     *
     * @param <T>
     * @param timeout
     * @return
     * @throws InterruptedException
     */
    private <T> T waitByExpectedCondition(
            ExpectedCondition<T> expectedCondition, long timeout) {
        return new WebDriverWait(webDriver, timeout).until(expectedCondition);
    }

    /**
     * @param seconds amount of seconds
     * @param locator elements which is expected
     */
    protected void waitElement(By locator, long seconds) {
        new WebDriverWait(webDriver, seconds).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * @param seconds       amount of seconds
     * @param locator       elements which is expected
     * @param needClickable - boolean value, true if element should be clickable
     */
    protected void waitElement(By locator, long seconds, boolean needClickable) {
        if (needClickable) {
            new WebDriverWait(webDriver, seconds).until(ExpectedConditions.elementToBeClickable(locator));
        } else {
            waitElement(locator, seconds);
        }
    }

    protected void waitElementToBeClickable(WebElement element, long seconds) {
        new WebDriverWait(webDriver, seconds).until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * @param seconds amounts of seconds without any settings
     */
    protected void waitElement(double seconds) {
        try {
            Thread.sleep((int) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void waitForElementsCountUpdated(By elementLocator, int count) {
        ExpectedCondition<Boolean> expectedCondition = d -> {
            boolean isFound = false;
            try {
                    List<WebElement> realElements = webDriver.findElements(elementLocator);
                    System.out.println(realElements.size() + " " + count);
                    if (realElements.size() == count) {
                        isFound = true;
                    }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {

            }

            return isFound;
        };
        waitByExpectedCondition(expectedCondition, 20);
    }

    protected void waitForElementDisplay(By elementLocator) {

        ExpectedCondition<Boolean> expectedCondition = d -> {
            boolean isFound = false;
            try {
                WebElement realElement = webDriver.findElement(elementLocator);
                if (realElement.isDisplayed()) {
                    isFound = true;
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {

            }

            return isFound;
        };

        waitByExpectedCondition(expectedCondition, 25);
    }


    public void waitForPageByTitle(String title) {
        final WebDriverWait wait = new WebDriverWait(webDriver, maxDelayTime);

        wait.until(ExpectedConditions.titleContains(title));

    }

    /**
     * Function to wait for Page opening by page title
     *
     * @param pageTitle
     */
    protected void waitForPageOpening(String pageTitle) {

        ExpectedCondition<Boolean> expectedCondition = d -> {
            boolean isFound = false;
            try {
                WebElement realPageTitle = webDriver.findElement(PAGE_HEADER_SELECTOR);
                if (pageTitle.equals(realPageTitle.getText())) {
                    isFound = true;
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {

            }

            return isFound;
        };

        waitByExpectedCondition(expectedCondition, 20);
    }

    /**
     * function to wait for dialog opening by title
     * @param dialogTitle
     */
    protected void waitForDialogOpening(String dialogTitle) {
        ExpectedCondition<Boolean> expectedCondition = d -> {
            boolean isFound = false;
            try {
                WebElement realDialog = webDriver.findElement(DIALOG_SELECTOR);
                String realTitle = realDialog.getText();
                if (dialogTitle.equals(realTitle)) {
                    isFound = true;
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
            }
            return isFound;
        };
        waitByExpectedCondition(expectedCondition, 20);
    }

    /**
     * Function to wait until input field is filled.
     *
     * @param locator
     * @throws InterruptedException
     */
    protected void waitForInputFilled(By locator) {

        ExpectedCondition<Boolean> expectedCondition = d -> {
            boolean isFound = false;
            try {
                WebElement inputField = webDriver.findElement(locator);
                String text = inputField.getAttribute("value");
                if (!text.isEmpty()) {
                    isFound = true;
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {

            }

            return isFound;
        };

        waitByExpectedCondition(expectedCondition, 15);
    }


    public void waitForEditorLoadingHide() {

        ExpectedCondition<Boolean> expectedCondition = d -> {
            boolean isFound = false;
            try {
                WebElement loadIcon = webDriver.findElement(By.cssSelector("span.b-wait"));
                if (loadIcon != null) {
                    WebDriverWait wait = new WebDriverWait(webDriver, 60);
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.b-wait")));
                    isFound = true;
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {

            }

            return isFound;
        };

        waitByExpectedCondition(expectedCondition, 30);
    }

    /**
     * Opens page by given URL.
     *
     * @param url page address
     */
    public void openPage(String url) {
        webDriver.get(url);
    }

    /**
     * Drag&Drop mechanism for components from the toolbar to the page
     *
     * @param sourceLocator      xpath of element which has to be drag
     * @param destinationLocator xpath of element which determines the the place for dropping
     */
    public void dragAndDrop(String sourceLocator, String destinationLocator) {
        WebElement sourceElement = null;
        WebElement destinationElement = null;
        try {
            webDriver.switchTo().defaultContent();
            sourceElement = webDriver.findElement(By.xpath(sourceLocator));
            moveToToolbarItem(sourceElement);
            waitElement(0.2);
            List<WebElement> iFrame = webDriver.findElements(By.tagName("iframe"));
            webDriver.switchTo().frame(iFrame.get(0));
            destinationElement = webDriver.findElement(By.xpath(destinationLocator));
            Actions builder = new Actions(webDriver);
            webDriver.switchTo().defaultContent();
            Action hold = builder.clickAndHold(sourceElement).build();
            hold.perform();
            waitElement(1);
            Action move = builder.moveToElement(destinationElement).release(destinationElement).build();
            webDriver.switchTo().frame(iFrame.get(0));
            waitElement(1);
            move.perform();
            waitElement(1);
        } catch (StaleElementReferenceException e) {
            System.out.println("Element with " + sourceElement + "or" + destinationElement + "is not attached to the page document "
                    + Arrays.toString(e.getStackTrace()));
        } catch (NoSuchElementException e) {
            System.out.println("Element " + sourceElement + "or" + destinationElement + " was not found in DOM " + Arrays.toString(e.getStackTrace()));
        } catch (Exception e) {
            System.out.println("Error occurred while performing drag and drop operation " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void moveToToolbarItem(WebElement sourceElement) {
        List<WebElement> toolbarItems = webDriver.findElements(By.xpath("//ul[@class='toolbar']//li[@class='toolbar-item']"));
        for (WebElement we : toolbarItems) {
            if (!sourceElement.isDisplayed()) {
                webDriver.findElement(By.xpath("//li[@class='scroll-arrow scroll-right']")).click();
                waitElement(0.1);
            } else {
                break;
            }
        }
    }

    /**
     * Unchecks element in checkbox
     *
     * @param locator xpath to element
     */
    public void unCheck(By locator) {
        if (isElementSelected(locator)) {
            webDriver.findElement(locator).click();
            waitElement(0.3);
        } else {
            System.out.print("Element not found or not selected");
        }
    }

    /**
     * Checks element in checkbox
     *
     * @param element
     */
    protected void check(WebElement element) {
        if (!element.isSelected()) {
            element.click();
            waitElement(0.3);
        } else {
            System.out.print("Element not found or not selected");
        }
    }

    /**
     * Checks whether element is selected or not
     *
     * @param locator xpath to element
     * @return {@code true} if element is selected, {@code false} - otherwise.
     */
    private boolean isElementSelected(By locator) {
        return webDriver.findElement(locator).isSelected();
    }

    /**
     * Switches to specific iFrame.
     *
     * @param index iFrame number
     */
    public void switchToIframe(int index) {
        List<WebElement> iFrame = webDriver.findElements(By.tagName("iframe"));
        webDriver.switchTo().frame(iFrame.get(index));
    }

    /**
     * Close all active tabs before test will be run
     */
    public void closeAllActiveTabs() {
        List<WebElement> closeButtons = webDriver.findElements(By.xpath("//a[@class='hippo-tabs-documents-closeAlert']"));
        for (WebElement we : closeButtons) {
            webDriver.findElement(By.xpath("//a[@class='hippo-tabs-documents-closeAlert']")).click();
            waitElement(0.5);
            webDriver.switchTo().activeElement();
            if (isElementPresent(webDriver.findElement(By.xpath("//input[@value='Discard']")))) {
                webDriver.findElement(By.xpath("//input[@value='Discard']")).click();
                waitElement(1);
            }
            webDriver.switchTo().activeElement();
            waitElement(0.5);
        }
    }

    /**
     * Method returns atribute value for WebElement
     *
     * @param element   WebElement
     * @param attribute Atribute name
     * @return String
     */
    public String getAtribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    /**
     * Go to back
     */
    protected void goToPreviousPage() {
        webDriver.navigate().back();
    }

    /**
     * Get windows handler
     *
     * @return window handler name
     */
    public String getWindowHandler() {
        return webDriver.getWindowHandle();
    }

    /**
     * Close alert
     */
    public void closeAlert() {
        try {
            webDriver.switchTo().alert().dismiss();
        } catch (NoAlertPresentException ignore) {
        }
    }

    /**
     * Switch to other window
     *
     * @param windowHandler window handler name
     */
    public void switchToWindow(String windowHandler) {
        webDriver.switchTo().window(windowHandler);
    }

    /**
     * Get current URL
     *
     * @return URL of current page
     */
    protected URL getCurrentURL() {
        try {
            return new URL(webDriver.getCurrentUrl());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get current URL
     *
     * @return URL of current page
     */
    public String getSingleQueryParam(String paramName) {
        try {
            return Utils.splitQuery(getCurrentURL()).get(paramName).get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeNotifBar() {
        try {
            WebElement notifbar = webDriver.findElement(By.cssSelector("span.b-notification--button"));
            clickOnButton(notifbar);
        } catch (Exception e) {

        }
    }


    /**
     * Select item in dropdown by item name
     *
     * @param element webelement of dropdown
     * @param name    name of item
     */
    protected void selectDropDownItemByName(WebElement element, String name) {
        new Select(element).selectByVisibleText(name);
    }

    /**
     * This method is designed to switch to "Displayed" iframe in web page
     */
    protected void switchToIframe() {
        waitElement(By.xpath("//iframe"), 15);
        List<WebElement> alliFrames = webDriver.findElements(By.xpath("//iframe"));
        for (WebElement iframe : alliFrames) {
            if (iframe.isDisplayed()) {
                webDriver.switchTo().frame(iframe);
                break;
            }
        }
    }

    /**
     * This method is designed to switch to default content
     */
    protected void switchToDefaultContent() {
        webDriver.switchTo().defaultContent();
    }

    public void scrollPageDown(int pixels) {
        ((JavascriptExecutor) webDriver).executeScript("scroll(0, " + pixels + ");");
    }

    public void browserBackButton() {
        webDriver.navigate().back();
    }

    /**
     * Function to return the list of dropdown item's texts
     *
     * @param dropDownElement
     * @return
     */
    protected List<String> getListOfDropDownTexts(WebElement dropDownElement) {
        List<String> listOfDropDownTexts = new ArrayList<>();
        List<WebElement> allOptions = dropDownElement.findElements(By.tagName("option"));
        listOfDropDownTexts.addAll(allOptions.stream().map(WebElement::getText).collect(Collectors.toList()));
        return listOfDropDownTexts;
    }

    protected WebDriver getWebDriver() {
        return this.webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    /**
     * function multiplies gift price to gift amount and returns total gifts' price
     *
     * @param giftPrice
     * @param giftAmount
     * @return
     */
    public String getTotalProductsPrice(String giftPrice, int giftAmount) {
        giftPrice = giftPrice.replace(",", ".");
        NumberFormat formatTotalGiftPrice = new DecimalFormat("#0.00");
        double totalGiftCost = Double.parseDouble(giftPrice) * giftAmount;
        String totalGiftPrice = formatTotalGiftPrice.format(totalGiftCost);
        totalGiftPrice = totalGiftPrice.replace(".", ",");
        return totalGiftPrice;
    }

    public String actionWithToPrices(String price1, String price2, String actionSign) {
        double result = 0;
        price1 = price1.replace(",", ".");
        price2 = price2.replace(",", ".");
        NumberFormat formatPrice = new DecimalFormat("#0.00");
        switch (actionSign) {
            case "*": result = Double.parseDouble(price1) * Double.parseDouble(price2);
                      break;

            case "+": result = Double.parseDouble(price1) + Double.parseDouble(price2);
                      break;

            case "-": result = Double.parseDouble(price1) - Double.parseDouble(price2);
                      break;
        }

        String finalresult = formatPrice.format(result);
        finalresult = finalresult.replace(".", ",");
        return finalresult;
    }

    public void waitForElementAppearsAndHides(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementHides(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public int getRandomNumber(int from, int to) {
        Random random = new Random();
        int number = random.nextInt(to - from + 1 ) + from;
        return number;
    }

}









