package com.greetz.ft.d2m.utils;

import com.greetz.ft.d2m.Configuration;
import com.saucelabs.testng.SauceOnDemandTestListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

/**
 * Created by hocks on 11-4-2017.
 */
public class SauceLabsListener extends SauceOnDemandTestListener {

    private final boolean isRunningOnSauceLabs = !Configuration.instance().getIsLocal();

    @Override
    public void onStart(ITestContext testContext) {
        if (isRunningOnSauceLabs) {
            super.onStart(testContext);
        }
    }

    /**
     * @param result
     */
    @Override
    public void onTestStart(ITestResult result) {
        if (isRunningOnSauceLabs) {
            super.onTestStart(result);
        }
    }

    /**
     * @param tr
     */
    @Override
    public void onTestFailure(ITestResult tr) {
        if (isRunningOnSauceLabs) {
            super.onTestFailure(tr);
        }
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        if (isRunningOnSauceLabs) {
            super.onTestSuccess(tr);
        }
    }

}
