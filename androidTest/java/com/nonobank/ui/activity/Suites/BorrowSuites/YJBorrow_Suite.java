package com.nonobank.ui.activity.Suites.BorrowSuites;

import com.nonobank.ui.activity.TestCases.PositiveTest.LoginforBorrow_test;
import com.nonobank.ui.activity.TestCases.PositiveTest.YJBorrow_test;

import junit.framework.TestSuite;

/**
 * Created by edison on 16/3/30.
 */
public class YJBorrow_Suite {
    public static TestSuite getTestSuite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(LoginforBorrow_test.class);
        suite.addTestSuite(YJBorrow_test.class);
        return suite;
    }
}
