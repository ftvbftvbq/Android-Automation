package com.nonobank.ui.activity.Suites.loginSuites;

import com.nonobank.ui.activity.TestCases.PositiveTest.Login_test;

import junit.framework.TestSuite;

/**
 * Created by edison on 16/3/1.
 */
public class Login_Suite {
    public static TestSuite getTestSuite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(Login_test.class);
        return suite;
    }
}
