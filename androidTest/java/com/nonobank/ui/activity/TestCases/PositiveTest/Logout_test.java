package com.nonobank.ui.activity.TestCases.PositiveTest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nonobank.R;
import com.nonobank.ui.activity.CheckLoginActivity;
import com.nonobank.ui.activity.HomeActivity;
import com.nonobank.ui.activity.LoginActivity;
import com.nonobank.ui.activity.Utils.LogUtil;
import com.nonobank.ui.activity.Utils.ReadExcel;
import com.nonobank.ui.activity.Utils.SQLUtils;
import com.robotium.solo.Solo;

/**
 * Created by edison on 16/7/28.
 */
public class Logout_test extends ActivityInstrumentationTestCase2<HomeActivity> {
    private Solo solo;
    private HomeActivity homePage;
    private EditText PhoneEditText0,VPassEditText0;
    private TextView loginOther;
    private Button LoginOutBtn,NextButton0, LoginButton1;
    private RelativeLayout MyIcon;
    String[] TestResults;

    @SuppressWarnings("deprecation")
    public Logout_test() {
        super("com.nonobank",HomeActivity.class);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo( getInstrumentation(), getActivity());
        solo.assertCurrentActivity("com.nonobank", HomeActivity.class);
        homePage = getActivity();
        solo.sleep(5000);
    }

    public void test_Logout() throws Exception {
        LogUtil Logger = new LogUtil();
        Logger.configLog("mxdLogout_log4j.log");
        ReadExcel TestData = new ReadExcel();
        TestData.getTestDataFile("/storage/emulated/0/mxdLogoutTestData.csv");
        TestResults = new String[TestData.rowCount];

        for(int i =1; i<TestData.rowCount; i++) {
            Logger.gLogger.debug("进入主页面开始进行登录操作");
            solo.waitForActivity(HomeActivity.class, 3000);
            loginOther = (TextView) solo.getView(R.id.login_other);
            solo.clickOnView(loginOther);
            solo.waitForActivity(CheckLoginActivity.class, 3000);

            PhoneEditText0 = (EditText) solo.getView(R.id.tel_num);
            NextButton0 = (Button) solo.getView(R.id.check_next);

            solo.enterText(PhoneEditText0, TestData.FirstValue[i].toString());
            solo.clickOnView(NextButton0);

            solo.waitForActivity(LoginActivity.class, 3000);
            VPassEditText0 = (EditText) solo.getView(R.id.password);
            LoginButton1 = (Button) solo.getView(R.id.bt_login);

            solo.clickOnView(VPassEditText0);
            solo.clearEditText(VPassEditText0);
            solo.enterText(VPassEditText0,TestData.SecondValue[i].toString());
            solo.clickOnView(VPassEditText0);
            solo.clickOnView(LoginButton1);
            solo.sleep(10000);

            Logger.gLogger.debug("开始退出操作");
            solo.clickOnText("我");
            solo.sleep(10000);
            MyIcon = (RelativeLayout) solo.getView(R.id.ccc);
            solo.clickOnView(MyIcon);
            solo.sleep(3000);
            LoginOutBtn = (Button) solo.getView(R.id.login_out);
            solo.clickOnView(LoginOutBtn);
            solo.sleep(3000);
            solo.clickOnView(solo.getView(Button.class, 0));
            solo.sleep(3000);
            if (solo.searchText(TestData.ExpectedResult[i]) == true) {
                TestResults[i] = "Pass";
            } else if (solo.searchText(TestData.ExpectedResult[i]) == false) {
                TestResults[i] = "Failed";
            }
            solo.clickOnText("借钱");
            solo.sleep(3000);

            Logger.gLogger.debug("开始删除相关用户表数据");
            String url = "jdbc:mysql://192.168.4.13:3306/db_nono";
            String user = "nonobank";
            String password = "B67891234bc34dsdfd12h";

            SQLUtils sqlu = new SQLUtils(url, user, password);
            sqlu.DeleteInUserInfo("delete from user_info where mobile_num="+TestData.FirstValue[i].toString());
            solo.sleep(20000);
        }
        TestData.writeResults(TestResults, "mxdLogoutTestResult.txt");
    }


    protected void tearDown() throws Exception {
        solo.sleep(3000);
        solo.finishOpenedActivities();
        super.tearDown();
    }

}
