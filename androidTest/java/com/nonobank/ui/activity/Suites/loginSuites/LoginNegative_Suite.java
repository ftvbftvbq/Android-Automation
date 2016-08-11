package com.nonobank.ui.activity.Suites.loginSuites;

import com.nonobank.ui.activity.TestCases.NegativeTest.Login_test_N1;
import com.nonobank.ui.activity.TestCases.NegativeTest.Login_test_N2;

import junit.framework.TestSuite;

/**
 * Created by edison on 16/7/19.
 */
public class LoginNegative_Suite {
    public static TestSuite getTestSuite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(Login_test_N1.class);
        suite.addTestSuite(Login_test_N2.class);
        return suite;
    }
}
