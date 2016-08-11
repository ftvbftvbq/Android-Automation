package com.nonobank.ui.activity.Runners;

import com.nonobank.ui.activity.Suites.AuditingSuites.ApproveCancel_Suite;
import com.nonobank.ui.activity.Suites.AuditingSuites.ApproveFallback_Suite;
import com.nonobank.ui.activity.Suites.AuditingSuites.ApproveReject_Suite;
import com.nonobank.ui.activity.Suites.AuditingSuites.PassApprove_Suite;
import com.nonobank.ui.activity.Suites.BorrowSuites.DEBorrow_N1_Suite;
import com.nonobank.ui.activity.Suites.BorrowSuites.DEBorrow_Suite;
import com.nonobank.ui.activity.Suites.BorrowSuites.YJBorrow_Suite;
import com.nonobank.ui.activity.Suites.CCYYSuites.CSYY_Suite;
import com.nonobank.ui.activity.Suites.LogoutSuites.Logout_Suite;
import com.nonobank.ui.activity.Suites.RechargeWithdrawSuites.RechargeWithdraw_Suite;
import com.nonobank.ui.activity.Suites.RegisterSuites.RegisterNegative_Suite;
import com.nonobank.ui.activity.Suites.RegisterSuites.Register_Suite;
import com.nonobank.ui.activity.Suites.loginSuites.LoginNegative_Suite;
import junit.framework.TestSuite;

/**
 * Created by edison on 16/3/29.
 */
public class runner1 extends CommonRunner {

    @Override
    public TestSuite getAllTests() {
        // TODO Auto-generated method stub
        TestSuite suite = new TestSuite();
        /**
         * add suites
         */
//        suite.addTest(RegisterNegative_Suite.getTestSuite());
//        suite.addTest(Register_Suite.getTestSuite());
//        suite.addTest(LoginNegative_Suite.getTestSuite());
//        suite.addTest(RechargeWithdraw_Suite.getTestSuite());
//        suite.addTest(CSYY_Suite.getTestSuite());
//        suite.addTest(YJBorrow_Suite.getTestSuite());
//        suite.addTest(DEBorrow_N1_Suite.getTestSuite());
        suite.addTest(ApproveCancel_Suite.getTestSuite());
        suite.addTest(ApproveFallback_Suite.getTestSuite());
        suite.addTest(ApproveReject_Suite.getTestSuite());
        suite.addTest(PassApprove_Suite.getTestSuite());
//        suite.addTest(DEBorrow_Suite.getTestSuite());
//        suite.addTest(Logout_Suite.getTestSuite());
        return suite;
    }

}
