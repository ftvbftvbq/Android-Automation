package com.nonobank.ui.activity.Suites.RegisterSuites;

import com.nonobank.ui.activity.TestCases.PositiveTest.Register_test;

import junit.framework.TestSuite;

/**
 * Created by edison on 16/3/1.
 */
public class Register_Suite {
    public static TestSuite getTestSuite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(Register_test.class);
        return suite;
    }
}