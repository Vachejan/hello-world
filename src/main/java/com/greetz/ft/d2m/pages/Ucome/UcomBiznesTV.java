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

    public String strPriceFullChetchUp = "Դ 9.500/ամիս";
    public String strValue2 = "Դ 8.500/ամիս";
    public String strValue3 = "Դ 2.500/ամիս";

    public void open() {
        super.open("", "Անհատներ - Ucom.am");
    }

    //Մտնում է բիզնես բաժինը։
    public void enterBiznesPage() {
        wbBiznes.click();
    }

    //Սեղմում է կոճակը։
    public void clickTV() {
        wbTV.click();
    }

    //Վեռցնւմ է առաջին փաթեթի արժեքը։
    public String getTVFullChatchUpPrice() {
        return wbFullChatchUp.getText();
    }

    //Համեմատում է արաջին փաթեթի արժեքը strPriceFullChetchUp֊ի հետ:
    public void comparePriceFullCatchUp() {
        Assert.assertEquals(getTVFullChatchUpPrice(), strPriceFullChetchUp, " Փաթեթի արժեքը  սխալ է");
    }

    //Վեռցնում է երկրորդ փաթեթի արժեքը։
    public String uTvFull() {
        return wbFull.getText();
    }

    //Համեմատում է երկրորդ փաթեթի արժեքը strValue2֊ի հետ։
    public void compare1() {
        Assert.assertEquals(uTvFull(), strValue2, "Փաթեթի արժեքը սծալ է");
    }

    //Վեռցնում է երորդ փաթեթի արժեքը։
    public String uTVSelect() {
        return wbSelect.getText();
    }

    ////Համեմատում է երորդ փաթեթի արժեքը strValue3֊ի հետ։
    public void compare2() {
        Assert.assertEquals(uTVSelect(), strValue3, "Փաթեթի արժեքը  սխալ է");
    }
}