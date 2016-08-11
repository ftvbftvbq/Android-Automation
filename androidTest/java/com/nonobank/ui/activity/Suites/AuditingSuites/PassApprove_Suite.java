package com.nonobank.ui.activity.Suites.AuditingSuites;

import com.nonobank.ui.activity.TestCases.PositiveTest.ApproveFinal_test;
import com.nonobank.ui.activity.TestCases.PositiveTest.ApproveTrail_test;
import junit.framework.TestSuite;

/**
 * Created by edison on 16/7/8.
 */
public class PassApprove_Suite {

    public static TestSuite getTestSuite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(ApproveTrail_test.class);
        suite.addTestSuite(ApproveFinal_test.class);
        return suite;
    }
}
