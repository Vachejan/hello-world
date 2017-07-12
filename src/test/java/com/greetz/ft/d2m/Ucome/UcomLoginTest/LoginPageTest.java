package com.greetz.ft.d2m.Ucome.UcomLoginTest;

import com.greetz.ft.d2m.BaseTest;
import org.testng.annotations.Test;

/**
 * Created by vache on 7/12/17.
 */
public class LoginPageTest extends BaseTest {
    @Test
    public void testUcomBiznesTV() throws Exception {

        //Բացում ե Ucom.am կայքը։
        ucomBiznesTV.open();

        //Սեղմում է ««Մուտք»» կոճակը։
        ucomBiznesTV.clickLoginButton();

        //Սեղմում է ««Շարժական կոճակը։
        ucomBiznesTV.clickMobileButton();

        //Մտնում է նոր պատուհան։
        ucomBiznesTV.switchToSecondTab();

        //Ավելացնում է հերախոսահամար
        ucomBiznesTV.addFonNumber();

        //Ավելացնում է գաղտնաբար։
        ucomBiznesTV.addPassword();

        //Ավելացնում է Captcha:
        ucomBiznesTV.addCaptch();

        //Սեղմում է «Մուտք» կոճակը։
        ucomBiznesTV.clickGoButton();

        //Վերցնում է արժեքը։
        ucomBiznesTV.getErrorPrice();

        //Համեմատուն է։
        ucomBiznesTV.comparePriceError();

    }
}
