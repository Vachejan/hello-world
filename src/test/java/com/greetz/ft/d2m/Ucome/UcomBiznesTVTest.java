package com.greetz.ft.d2m.Ucome;

import com.greetz.ft.d2m.BaseTest;
import org.testng.annotations.Test;

/**
 * Created by vache on 7/12/17.
 */
public class UcomBiznesTVTest extends BaseTest {

    @Test
    public void testUcomBiznesTV() throws Exception {
        //Բացում ե Ucom.am կայքը։
        ucomBiznesTV.open();

        //Մտնում է Բիզնես բաժինը։
        ucomBiznesTV.enterBiznesPage();

        //Մտնում է TV բաժինը։
        ucomBiznesTV.clickTV();
        //Վեռցնում է արաջին փաթեթի արժեքը։
        ucomBiznesTV.getTVFullChatchUpPrice();
        //Համեմատում է։
        ucomBiznesTV.comparePriceFullCatchUp();
        //Վերցնում է երկրորդ փաթեթի արժեքը:
        ucomBiznesTV.getTvFullPrice();
        //Համեմատում է։
        ucomBiznesTV.comparePriceFull();
        //Վերցնում է երորդ փաթեթի արժեքը:
        ucomBiznesTV.getTVSelectPrice();
        //Համեմատում է։
        ucomBiznesTV.comparePriceSelect();

    }
}
