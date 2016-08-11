package com.nonobank.ui.activity.Suites.AuditingSuites;

import com.nonobank.ui.activity.TestCases.PositiveTest.CancelFinal_test;
import com.nonobank.ui.activity.TestCases.PositiveTest.CancelTail_test;
import junit.framework.TestSuite;

/**
 * Created by edison on 16/7/11.
 */
public class ApproveCancel_Suite {
    public static TestSuite getTestSuite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(CancelTail_test.class);
        suite.addTestSuite(CancelFinal_test.class);
        return suite;
    }
}




