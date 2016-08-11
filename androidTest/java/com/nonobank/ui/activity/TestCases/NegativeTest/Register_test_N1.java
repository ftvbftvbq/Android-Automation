package com.nonobank.ui.activity.TestCases.NegativeTest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nonobank.R;
import com.nonobank.ui.activity.CheckLoginActivity;
import com.nonobank.ui.activity.RegisterActivity;
import com.nonobank.ui.activity.Utils.GencellPhoneNum;
import com.nonobank.ui.activity.Utils.LogUtil;
import com.nonobank.ui.activity.Utils.ReadExcel;
import com.nonobank.ui.activity.gesture.LockSetupActivity;
import com.robotium.solo.Solo;

/**
 * Created by edison on 16/7/19.
 */
public class Register_test_N1 extends ActivityInstrumentationTestCase2<CheckLoginActivity> {
    private Solo solo;
    private CheckLoginActivity homePage;
    private EditText mPhone,SafeCode,CheckNum,Password;
    private TextView GetcheckNum;
    private Button NextButton,Reg;
    String[] TestResults;

    @SuppressWarnings("deprecation")
    public Register_test_N1() {
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

    public void test_RegisterN1() {
        GencellPhoneNum cellPhone = new GencellPhoneNum();

        LogUtil Logger = new LogUtil();
        Logger.configLog("mxdRegisterN1_log4j.log");

        ReadExcel TestData = new ReadExcel();
        TestData.getTestDataFile("/storage/emulated/0/mxdRegN1TestData.csv");

        TestResults = new String[TestData.rowCount];
        for(int i =1; i<TestData.rowCount; i++) {
            Logger.gLogger.debug("登录进入检查手机号界面");
            solo.waitForActivity(CheckLoginActivity.class, 3000);
            solo.sleep(5000);

            Logger.gLogger.debug("输入手机号");
            mPhone = (EditText) solo.getView(R.id.tel_num);
            NextButton = (Button) solo.getView(R.id.check_next);

            solo.enterText(mPhone, cellPhone.getTel());
            solo.clickOnView(NextButton);
            solo.sleep(5000);

            Logger.gLogger.debug("进入注册页面");
            solo.waitForActivity(RegisterActivity.class, 5000);
            solo.sleep(3000);

            SafeCode = (EditText) solo.getView(R.id.et_safe_code);
            GetcheckNum = (TextView) solo.getView(R.id.get_msg_checknum);

            Logger.gLogger.debug("填写图形验证码");
            solo.enterText(SafeCode, TestData.FirstValue[i].toString());

            Logger.gLogger.debug("获取验证码");
            solo.clickOnView(GetcheckNum);
//            solo.sleep(1000);

            if (solo.searchText(TestData.ExpectedResult[i]) == true) {
                TestResults[i] = "Pass";
            } else if (solo.searchText(TestData.ExpectedResult[i]) == false) {
                TestResults[i] = "Failed";
            }
            solo.goBack();
            solo.clearEditText(mPhone);
        }

        TestData.writeResults(TestResults, "mxdRegN1TestResult.txt");
    }


    protected void tearDown() throws Exception {
        solo.sleep(3000);
        solo.finishOpenedActivities();
        super.tearDown();
    }

}