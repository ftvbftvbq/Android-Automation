package com.nonobank.ui.activity.Suites.RechargeWithdrawSuites;

import com.nonobank.ui.activity.TestCases.PositiveTest.BindBankCard_test;
import com.nonobank.ui.activity.TestCases.PositiveTest.Recharge_test;
import com.nonobank.ui.activity.TestCases.PositiveTest.Withdraw_test;

import junit.framework.TestSuite;

/**
 * Created by edison on 16/7/25.
 */
public class RechargeWithdraw_Suite {
    public static TestSuite getTestSuite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(BindBankCard_test.class);
        suite.addTestSuite(Recharge_test.class);
        suite.addTestSuite(Withdraw_test.class);
        return suite;
    }
}
