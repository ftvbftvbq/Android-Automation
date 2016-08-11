package com.nonobank.ui.activity.Suites.BorrowSuites;

import com.nonobank.ui.activity.TestCases.NegativeTest.DEBorrow_test_N1;
import junit.framework.TestSuite;

/**
 * Created by edison on 16/7/27.
 */
public class DEBorrow_N1_Suite {
    public static TestSuite getTestSuite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(DEBorrow_test_N1.class);
        return suite;
    }
}
