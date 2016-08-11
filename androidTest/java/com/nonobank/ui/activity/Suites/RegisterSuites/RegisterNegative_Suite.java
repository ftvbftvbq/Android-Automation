package com.nonobank.ui.activity.Suites.RegisterSuites;

import com.nonobank.ui.activity.TestCases.NegativeTest.Register_test_N1;
import junit.framework.TestSuite;

/**
 * Created by edison on 16/7/19.
 */
public class RegisterNegative_Suite {

    public static TestSuite getTestSuite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(Register_test_N1.class);
        return suite;
    }
}
