package com.nonobank.ui.activity.Suites.AuditingSuites;

import com.nonobank.ui.activity.TestCases.PositiveTest.FallbackFinal_test;
import com.nonobank.ui.activity.TestCases.PositiveTest.FallbackTail_test;
import junit.framework.TestSuite;

/**
 * Created by edison on 16/7/11.
 */
public class ApproveFallback_Suite {

    public static TestSuite getTestSuite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(FallbackTail_test.class);
        suite.addTestSuite(FallbackFinal_test.class);
        return suite;
    }
}


