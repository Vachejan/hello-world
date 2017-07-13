package com.greetz.ft.d2m.Ucome.UcomLoginTest.UcomHelp;

import com.greetz.ft.d2m.BaseTest;
import org.testng.annotations.Test;

/**
 * Created by vache on 7/13/17.
 */
public class UcomeHelpTest  extends BaseTest {
    @Test
    public void testUcomBiznesTV() throws Exception {

        //Մտնում է «Ucom.am» կայքը։
        ucomBiznesTV.open();

        //Սեղմում է «ՕԳՆՈՒԹՅՈՒՆ» կոճակը։
        ucomBiznesTV.clickHelpButton();

        //Սեղմում է «Առցանց Օգնություն» կոճակը։
        ucomBiznesTV.clickOnlineHelpButton();

        //Վերցնում է «Օգնություն» էջի վերնագիրը։
        ucomBiznesTV.getPriceHelpTitle();

        //Համեմատուն է strHelpTitlePrice ֊ի հետ։
        ucomBiznesTV.comparePriceHelpTitle();

        //Լրացնում է «Ծածկանուն»
        ucomBiznesTV.addLoginName();

        //Լրացնում է «Ծածկանունը»
        ucomBiznesTV.addLoginPassword();

        //Ընտրում է «Հաշվեկշրի Հարցում»
        ucomBiznesTV.clickBalanceInquiryButton();

        //Սեղմում է «Մուտք» կոճակը
        ucomBiznesTV.clickSubmitButton();


    }
}
