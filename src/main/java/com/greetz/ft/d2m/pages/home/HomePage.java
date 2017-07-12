package com.greetz.ft.d2m.pages.home;

import com.greetz.ft.d2m.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * Created by arsen on 11/28/16.
 */
public class HomePage extends Page {

    public void open() {
        super.open("", "W3Schools Online Web Tutorials");
    }

    @FindBy(css = "#mySidenav > div > a:nth-child(6)")
    private WebElement wbBootstarp;

    @FindBy(css = "#main > h1")
    private WebElement wbCopy;

    @FindBy(css = "#main > div:nth-child(3) > a.w3-right.w3-btn")
    private WebElement wbNext;

    @FindBy(css = "#main > h1")
    private WebElement wbNewCopy;

    @FindBy(css = "#topnav > div > div.w3-bar.w3-left > a:nth-child(14)")
    private WebElement wbSerch;

    @FindBy(css = "#gsc-i-id1")
    private WebElement wbAddText;

    @FindBy(css = "#___gcse_0 > div > div > form > table.gsc-search-box > tbody > tr > td.gsc-search-button > input")
    private WebElement wbSerchText;

    @FindBy(css = "#___gcse_0 > div > div > div.gsc-results-wrapper-overlay.gsc-results-wrapper-visible > div.gsc-wrapper > div.gsc-resultsbox-visible > div > div > div.gsc-expansionArea > div:nth-child(1) > div.gs-webResult.gs-result > div.gsc-thumbnail-inside > div > a")
    private WebElement wbHtml;

    private String strTitle = "Bootstrap 3 Tutorial";//Eji Vernagir:

    private String strNewTitle = "Bootstrap Get Started";//Nor eji vernagiry


    public void openPage() {
        wbBootstarp.click();
    } //Bacum e Learn Bootstarp ejy:

    public String copyText() {
        return wbCopy.getText();
    } //Vercnum e Vernagri Texty:

    public void compare() {
        Assert.assertEquals(copyText(), strTitle, "Vernagiry Sxal e");
    } //Hamematum e:

    public void next() {
        wbNext.click();
    }//Sexmum e Next:

    public String copyNewText() {
        return wbNewCopy.getText();
    }//Vercnum e nor vernagiry:

    public void newCompare() {
        Assert.assertEquals(copyNewText(), strNewTitle, "Nor Vernagiry Sxal e");
    }//Hamaematum e nor vernagiry:

    public void clickSerch() {
        wbSerch.click();
    }//Sexmum e Sherch:

    public void serchText() {
        wbAddText.sendKeys("Html");
    }//Grum Text:

    public void clickSerchtext() {
        wbSerchText.click();
    }//Sexmum Serch:

    public void clickHtmlBasic(){
        wbHtml.click();
    }//Sexmum E HTML Basik;
}

