package com.nonobank.ui.activity.TestCases.PositiveTest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nonobank.R;
import com.nonobank.ui.activity.CheckLoginActivity;
import com.nonobank.ui.activity.LoginActivity;
import com.nonobank.ui.activity.Utils.LogUtil;
import com.nonobank.ui.activity.Utils.ReadExcel;
import com.nonobank.ui.activity.gesture.LockSetupActivity;
import com.robotium.solo.Solo;

/**
 * Created by edison on 16/4/8.
 */
public class LoginforBorrow_test extends ActivityInstrumentationTestCase2<CheckLoginActivity> {
    private Solo solo;
    private CheckLoginActivity homePage;
    private RadioButton myIcon;
    private EditText mPhone,mPhoneEditText,VPassEditText;
    private TextView LoginLinkBtn;
    private Button NextButton, LoginButton, LogoutBtn1,LogoutBtn2, DefaultPositiveBtn;
    private RelativeLayout settingBtn;
    String[] TestResults;

    @SuppressWarnings("deprecation")
    public LoginforBorrow_test() {
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

    public void test_LoginforBorrow() {
        LogUtil Logger = new LogUtil();
        Logger.configLog("mxdLoginforBorrow_log4j.log");

        ReadExcel TestData = new ReadExcel();
        TestData.getTestDataFile("/storage/emulated/0/mxdLoginforBorrowData.csv");

        TestResults = new String[TestData.rowCount];
        for(int i =1; i<TestData.rowCount; i++) {
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


                Logger.gLogger.debug("读取EXCEL数据，输入密码");
                VPassEditText = (EditText) solo.getView(R.id.password);

                LoginButton = (Button) solo.getView(R.id.bt_login);

                solo.clickOnView(VPassEditText);
                solo.clearEditText(VPassEditText);
                solo.enterText(VPassEditText,TestData.SecondValue[i].toString());
                solo.clickOnView(VPassEditText);

                Logger.gLogger.debug("点击登录按钮");
                solo.clickOnView(LoginButton);
                solo.sleep(2000);
                solo.waitForActivity(LockSetupActivity.class, 3000);
                solo.drag(146,567,570,570,1);
                solo.sleep(3000);
                solo.drag(146,567,570,570,1);
                solo.sleep(3000);

        Logger.gLogger.debug("验证是否登录成功, 成功则写入Pass");
         if (solo.searchText(TestData.ExpectedResult[i]) == true) {
                    TestResults[i] = "Pass";
            } else if(solo.searchText(TestData.ExpectedResult[i]) == false) {
                    TestResults[i] = "Failed";
            }

        }
        TestData.writeResults(TestResults, "mxdLoginforBorrowTestResult.txt");
    }

    protected void tearDown() throws Exception {
        solo.sleep(3000);
        solo.finishOpenedActivities();
        super.tearDown();
    }
}