package com.nonobank.ui.activity.Suites.BorrowSuites;

import com.nonobank.ui.activity.TestCases.PositiveTest.DEBorrow_test;
import junit.framework.TestSuite;

/**
 * Created by edison on 16/7/18.
 */
public class DEBorrow_Suite  {
    public static TestSuite getTestSuite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(DEBorrow_test.class);
        return suite;
    }
}
