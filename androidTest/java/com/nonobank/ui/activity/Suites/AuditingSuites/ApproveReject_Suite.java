package com.nonobank.ui.activity.Suites.AuditingSuites;

import com.nonobank.ui.activity.TestCases.PositiveTest.RejectFinal_test;
import com.nonobank.ui.activity.TestCases.PositiveTest.RejectTail_test;
import junit.framework.TestSuite;

/**
 * Created by edison on 16/7/11.
 */
public class ApproveReject_Suite {

    public static TestSuite getTestSuite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(RejectTail_test.class);
        suite.addTestSuite(RejectFinal_test.class);
        return suite;
    }
}


