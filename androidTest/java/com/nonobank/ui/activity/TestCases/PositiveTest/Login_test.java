package com.nonobank.ui.activity.TestCases.PositiveTest;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nonobank.R;
import com.nonobank.ui.activity.BaseTest.LoginActivityTest;
import com.nonobank.ui.activity.CheckLoginActivity;
import com.nonobank.ui.activity.HomeActivity;
import com.nonobank.ui.activity.LoginActivity;
import com.nonobank.ui.activity.MyAccountActivity;
import com.nonobank.ui.activity.Utils.LogUtil;
import com.nonobank.ui.activity.Utils.ReadExcel;
import com.robotium.solo.Solo;


/**
 * Created by edison on 16/3/1.
 */
public class Login_test extends LoginActivityTest {
    private Solo solo;
    private RadioButton myIcon;
    private EditText mPhoneEditText, VPassEditText;
    private TextView LoginLinkBtn;
    private Button LoginButton, LogoutBtn1, LogoutBtn2, DefaultPositiveBtn;
    private RelativeLayout settingBtn;
    private CheckLoginActivity homePage;
    String[] TestResults;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void test_Login() {
        LogUtil Logger = new LogUtil();
        Logger.configLog("mxdLogin_log4j.log");

        ReadExcel TestData = new ReadExcel();
        TestData.getTestDataFile("/storage/emulated/0/mxdLoginTestData.csv");

        TestResults = new String[TestData.rowCount];
        for(int i =1; i<TestData.rowCount; i++) {
        Logger.gLogger.debug("读取EXCEL数据，输入手机号");
        uiHelper.getElementsLoginActivity().enterCheckPhone(TestData.FirstValue[i].toString());
//        uiHelper.getElementsLoginActivity().enterCheckPhone("13918304909");
        uiHelper.getElementsLoginActivity().clickNextButton();
        solo.waitForActivity(LoginActivity.class, 5000);

        Logger.gLogger.debug("读取EXCEL数据，输入密码");
        uiHelper.getElementsLoginActivity().clearPassword();
//        uiHelper.getElementsLoginActivity().enterPassword("it789123");
        uiHelper.getElementsLoginActivity().enterPassword(TestData.SecondValue[i].toString());
        uiHelper.getElementsLoginActivity().clickOnPasswordView();

        Logger.gLogger.debug("点击登录按钮");
        uiHelper.getElementsLoginActivity().clickLoginButton();

        Logger.gLogger.debug("验证是否登录成功, 成功则写入Pass");

        if (uiHelper.getSolo().searchText(TestData.ExpectedResult[i]) == true) {
                TestResults[i] = "Pass";
            } else if (uiHelper.getSolo().searchText(TestData.ExpectedResult[i]) == false) {
                TestResults[i] = "Failed";
        }
        uiHelper.getSolo().sleep(3000);
        solo.waitForActivity(HomeActivity.class, 5000);

        myIcon = (RadioButton) solo.getView(R.id.iv_my);
        solo.clickOnView(myIcon);
        solo.sleep(3000);

        solo.getCurrentActivity();
        settingBtn = (RelativeLayout) solo.getView(R.id.ccc);
        solo.clickOnView(settingBtn);
        solo.sleep(3000);

        solo.getCurrentActivity();
        solo.waitForActivity(MyAccountActivity.class, 5000);
        uiHelper.getSolo().sleep(3000);
        LogoutBtn1 = (Button) solo.getView(R.id.login_out);
        solo.clickOnView(LogoutBtn1);
        solo.sleep(3000);

        solo.getCurrentActivity();
        LogoutBtn2 = solo.getView(Button.class, 0);
        solo.clickOnView(LogoutBtn2);
        solo.sleep(3000);

        DefaultPositiveBtn = (Button) solo.getView(R.id.buttonDefaultPositive);
        solo.clickLongOnView(DefaultPositiveBtn);
        solo.sleep(5000);

        solo.waitForActivity(HomeActivity.class, 5000);
        LoginLinkBtn = (TextView) solo.getView(R.id.login);
        solo.clickLongOnView(LoginLinkBtn);
        solo.sleep(3000);
        solo.getCurrentActivity();
        settingBtn = (RelativeLayout) solo.getView(R.id.ccc);
        solo.clickOnView(settingBtn);
        solo.sleep(3000);
        solo.waitForActivity(CheckLoginActivity.class, 3000);

        }
        TestData.writeResults(TestResults, "mxdLoginTestResult.txt");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
