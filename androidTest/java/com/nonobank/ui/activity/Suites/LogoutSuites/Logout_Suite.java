package com.nonobank.ui.activity.Suites.LogoutSuites;

import com.nonobank.ui.activity.TestCases.PositiveTest.Logout_test;

import junit.framework.TestSuite;

/**
 * Created by edison on 16/7/28.
 */
public class Logout_Suite {

    public static TestSuite getTestSuite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(Logout_test.class);
        return suite;
    }
}
