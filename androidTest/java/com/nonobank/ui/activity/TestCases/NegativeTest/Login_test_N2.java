package com.nonobank.ui.activity.TestCases.NegativeTest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.nonobank.R;
import com.nonobank.ui.activity.CheckLoginActivity;
import com.nonobank.ui.activity.LoginActivity;
import com.nonobank.ui.activity.Utils.LogUtil;
import com.nonobank.ui.activity.Utils.ReadExcel;
import com.robotium.solo.Solo;

/**
 * Created by edison on 16/7/27.
 */
public class Login_test_N2 extends ActivityInstrumentationTestCase2<CheckLoginActivity> {
    private Solo solo;
    private CheckLoginActivity homePage;
    private EditText mPhone,VPassEditText;
    private TextView ForgetBtn;
    private Button NextButton, LoginButton;
    String[] TestResults;

    @SuppressWarnings("deprecation")
    public Login_test_N2() {
        super("com.nonobank",CheckLoginActivity.class);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo( getInstrumentation(), getActivity());
        solo.assertCurrentActivity("com.nonobank", CheckLoginActivity.class);
        homePage = getActivity();
        solo.sleep(5000);
    }

    public void test_Login_test_N2() {
        LogUtil Logger = new LogUtil();
        Logger.configLog("mxdLoginN2_log4j.log");

        ReadExcel TestData = new ReadExcel();
        TestData.getTestDataFile("/storage/emulated/0/mxdLoginN2TestData.csv");
        TestResults = new String[TestData.rowCount];

        for (int i = 1; i < TestData.rowCount; i++) {
            Logger.gLogger.debug("登录进入检查手机号界面");
            solo.waitForActivity(CheckLoginActivity.class, 3000);
            solo.sleep(3000);

            Logger.gLogger.debug("输入手机号");
            mPhone = (EditText) solo.getView(R.id.tel_num);
            NextButton = (Button) solo.getView(R.id.check_next);

            solo.enterText(mPhone,TestData.FirstValue[i].toString());
            solo.clickOnView(NextButton);
            solo.sleep(5000);

            Logger.gLogger.debug("进入登录页面");
            solo.waitForActivity(LoginActivity.class, 5000);
            solo.sleep(3000);

            Logger.gLogger.debug("进入短信验证登录页面");
            ForgetBtn = (TextView) solo.getView(R.id.forget_pwd);
            solo.clickOnView(ForgetBtn);
            solo.sleep(3000);

            Logger.gLogger.debug("输入短信验证码");
            VPassEditText = (EditText) solo.getView(R.id.msg_checkNum);
            solo.enterText(VPassEditText,"0615");
            solo.clickOnView(VPassEditText);

            Logger.gLogger.debug("点击登录按钮");
            LoginButton = (Button) solo.getView(R.id.checkNum_login);
            solo.clickOnView(LoginButton);
            solo.sleep(5000);

            Logger.gLogger.debug("验证是否登录成功, 成功则写入Pass");
            if (solo.searchText(TestData.ExpectedResult[i]) == true) {
                TestResults[i] = "Pass";
            } else if(solo.searchText(TestData.ExpectedResult[i]) == false) {
                TestResults[i] = "Failed";
            }

        }
        TestData.writeResults(TestResults, "mxdLoginN2TestResult.txt");
    }

    protected void tearDown() throws Exception {
        solo.sleep(3000);
        solo.finishOpenedActivities();
        super.tearDown();
    }
}
