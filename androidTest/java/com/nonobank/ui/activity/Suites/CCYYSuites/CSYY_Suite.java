package com.nonobank.ui.activity.Suites.CCYYSuites;

import com.nonobank.ui.activity.TestCases.PositiveTest.CSYYRecharge_test;
import com.nonobank.ui.activity.TestCases.PositiveTest.CSYYWithdraw_test;

import junit.framework.TestSuite;

/**
 * Created by edison on 16/7/26.
 */
public class CSYY_Suite {
    public static TestSuite getTestSuite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(CSYYRecharge_test.class);
        suite.addTestSuite(CSYYWithdraw_test.class);
        return suite;
    }
}
