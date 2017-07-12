package com.greetz.ft.d2m.home;

import com.greetz.ft.d2m.BaseTest;
import org.apache.tools.ant.taskdefs.Sleep;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;


/**
 * Created by arsen on 11/28/16.
 */

public class HomePageTest extends BaseTest {

    @Test
    public void testHomePageLogo() throws Exception {
        homePage.open();//Mtnum e https://www.w3schools.com/ ejy;
        homePage.openPage();//Bacum e Bootstrap bajiny;
        homePage.copyText();//Vercnum Vernagiry;
        homePage.compare();//Hamaematum e;
        homePage.next();//Sexmum e Next;
        homePage.copyNewText();//Vercnum vernagiry;
        homePage.newCompare();//Hamematum e;
        homePage.clickSerch();//Sexmum e Serch;
        homePage.serchText();//Avelacnum e Text;
        Thread.sleep(4000);//Spasum 4 Varkyan;
        homePage.clickSerchtext();//Sexmum Serch;
        Thread.sleep(4000);//Spasum 4 Varkyan;
        homePage.clickHtmlBasic();//Sexmum e HTML Basic;


//        homePage.sexmelHomeKnopken();
//        homePage.sexmelYntrelKnopken();


    }
}