package com.greetz.ft.d2m.pages.Ucome;

import com.greetz.ft.d2m.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * Created by vache on 7/12/17.
 */
public class UcomBiznesTV extends Page {

    @FindBy(css = "#container > header > div > div > aside.left.fl > nav > ul > li:nth-child(2) > a")
    private WebElement wbBiznes;

    @FindBy(css = "#corporate-home > div.wrapper > ul > li.ver-top-box.tc.box-4 > a > span.home-menu-right > span")
    private WebElement wbTV;

    @FindBy(css = "#corporate-inner > div.corporate-tab-box > div:nth-child(1) > div.tab-box-2.fl > div:nth-child(2) > p.fs17.fb.gcg")
    private WebElement wbFullChatchUp;

    @FindBy(css = "#corporate-inner > div.corporate-tab-box > div:nth-child(2) > div.tab-box-2.fl > div:nth-child(2) > p.fs17.fb.gcg")
    private WebElement wbFull;

    @FindBy(css = "#corporate-inner > div.corporate-tab-box > div:nth-child(3) > div.tab-box-2.fl > div:nth-child(2) > p.fs17.fb.gcg")
    private WebElement wbSelect;

    @FindBy(css = "#container > header > div > div > aside.clear-fix.right.fr > div.fl.login-menu-out.pr > span")
    private WebElement wbLogin;

    @FindBy(css = "#container > header > div > div > aside.clear-fix.right.fr > div.fl.login-menu-out.pr > div > ul > li:nth-child(1) > a")
    private WebElement wbMobile;

    @FindBy(css = "#phone_num")
    private WebElement wbFonNumber;

    @FindBy(css = "#pass")
    private WebElement wbPassword;

    @FindBy(css = "#pt")
    private WebElement wbCaptcha;

    @FindBy(css = "#log-in")
    private WebElement wbGo;

    @FindBy(css = "#status-description")
    private WebElement wbErorPrice;

    @FindBy(css = "#container > header > div > div > aside.left.fl > nav > ul > li:nth-child(3) > a")
    private WebElement wbHelp;

    @FindBy(css = "#products-inner > aside.fl.right > div > div > div > div:nth-child(2) > a:nth-child(4)")
    private WebElement wbOnlineHelp;

    @FindBy(css = "#chat-box > h3")
    private WebElement wbPriseHelpTitle;

    @FindBy(id = "login")
    private WebElement wbLoginName;

    @FindBy(id = "password")
    private WebElement wbLoginPassword;

    @FindBy(css = "#subject_id")
    private WebElement wbCharSubjectButton;

    @FindBy(css = "#b-login")
    private WebElement wbSubmitButton;


    //FullChetchUp ֊ իրական արժեքն է։
    public String strPriceFullChetchUp = "Դ 9.500/ամիս";

    //Full֊ի իրական արժեքն է։
    public String strPriceFull = "Դ 8.500/ամիս";

    //Select֊ի իրական արժեքն է։
    public String strPriceSelect = "Դ 2.500/ամիս";

    //Error֊ի իրական արժեքն է։
    public String strErrorPrice = "Մուտքը ձախողվեց, խնդրում ենք կրկին փորձել:";

    //HelpTitle֊ի իրական արժեքն է։
    public String strHelpTitlePrice = "Բարի գալուստ Ucom առցանց օգնություն:";

    public void open() {
        super.open("", "Անհատներ - Ucom.am");
    }


    //Մտնում է բիզնես բաժինը։
    public void enterBiznesPage() {
        wbBiznes.click();
    }

    //Սեղմում է TV կոճակը։
    public void clickTV() {
        wbTV.click();
    }

    //Վերցնւմ է առաջին փաթեթի արժեքը։
    public String getTVFullChatchUpPrice() {
        return wbFullChatchUp.getText();
    }

    //Համեմատում է առաջին փաթեթի արժեքը strPriceFullChetchUp֊ի հետ:
    public void comparePriceFullCatchUp() {
        Assert.assertEquals(getTVFullChatchUpPrice(), strPriceFullChetchUp, " Փաթեթի արժեքը  սխալ է");}

    //Վեռցնում է երկրորդ փաթեթի արժեքը։
    public String getTvFullPrice() {
        return wbFull.getText();
    }

    //Համեմատում է երկրորդ փաթեթի արժեքը strPriceFull֊ի հետ։
    public void comparePriceFull() {
        Assert.assertEquals(getTvFullPrice(), strPriceFull, "Փաթեթի արժեքը սծալ է");
    }

    //Վերցնում է երորդ փաթեթի արժեքը։
    public String getTVSelectPrice() {
        return wbSelect.getText();
    }

   //Համեմատում է երորդ փաթեթի արժեքը strPriceSelect֊ի հետ։
    public void comparePriceSelect() {
        Assert.assertEquals(getTVSelectPrice(), strPriceSelect, "Փաթեթի արժեքը  սխալ է");}

    //Սեղմում է «Մուտք» կոճակը
    public void clickLoginButton() {
        wbLogin.click();
    }

    //Սեղմում է «Շարժական» Կոճակը։
    public void clickMobileButton() {
        wbMobile.click();
    }

    //Լրացնում  է Հեռախսահամարը։
    public void addFonNumber(){
        wbFonNumber.sendKeys("95401319");}

    //Լրացնում  է գաղտնաբառը։
    public void addPassword() {
        wbPassword.sendKeys("Valodik");
    }

    //Լրացնում է Captcha֊ի դաշտը։
    public void addCaptch() {
        wbCaptcha.sendKeys("123456");
    }

    //Սեղմում է «Մուտք» Կոճակը։
    public void clickGoButton() {
        wbGo.click();
    }

    //Վերցնում է ձախողման արժեքը։
    public String getErrorPrice(){
       return wbErorPrice.getText();
    }

    //Համեմատում է ձախողման արժեքը։
    public void comparePriceError() {
        Assert.assertEquals(getErrorPrice(),strErrorPrice,"Ձախողման վերնագիռը սխալ է");
    }

    //Սեղմում է «ՕԳՆՈՒԹՅՈՒՆ» կոճակը։
    public void clickHelpButton() {
        wbHelp.click();
    }

    //Սեղմում է «Առցանց Օգնություն» կոճակը։
    public void clickOnlineHelpButton() {
        wbOnlineHelp.click();
    }

    //Վերցնում է «Օգնության» Էջի վերնագիրը։
    public String getPriceHelpTitle() {
        return wbPriseHelpTitle.getText();
    }

    //Համեմատում է getPriceHelpTitle֊ը։
    public void comparePriceHelpTitle() {
        Assert.assertEquals(strHelpTitlePrice,getPriceHelpTitle(),"Օգնության վերնագիրը սխալ է");}

    //Լրացնում է «Ծածկանունը»
    public void addLoginName()  {
        //Քցում է Iframe֊ի մեջ։
        switchToIframe(0);
        wbLoginName.sendKeys("Valodik");
    }

    //Լրացնում է «Գաղտնաբարը»
    public void addLoginPassword() {
        wbLoginPassword.sendKeys("Valodik");
    }

    //Ընտրում է «Հաշվեկշրի Հարցում»
    public void clickBalanceInquiryButton() {
        selectDropDownItemByName(wbCharSubjectButton, "Հաշվեկշռի հարցում");
    }

    //Սեխմում է «Մուտք»
    public void clickSubmitButton() {
        wbSubmitButton.click();
    }


}