package com.nonobank.ui.activity.TestCases.PositiveTest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nonobank.R;
import com.nonobank.ui.activity.CheckLoginActivity;
import com.nonobank.ui.activity.HomeActivity;
import com.nonobank.ui.activity.LoginActivity;
import com.nonobank.ui.activity.Utils.LogUtil;
import com.nonobank.ui.activity.Utils.ReadExcel;
import com.nonobank.ui.activity.Utils.SQLUtils;
import com.nonobank.ui.activity.gesture.LockSetupActivity;
import com.nonobank.ui.activity.webview.WebViewBaseActivity;
import com.robotium.solo.By;
import com.robotium.solo.Solo;

/**
 * Created by edison on 16/7/5.
 */
public class ApproveTrail_test extends ActivityInstrumentationTestCase2<HomeActivity> {
    private Solo solo;
    private HomeActivity homePage;
    private RadioButton BorrowItem;
    private LinearLayout BorrowInitBtn;
    private EditText PhoneEditText0,VPassEditText0;
    private TextView loginOther;
    private Button NextButton0, LoginButton1;
    String[] TestResults;

    @SuppressWarnings("deprecation")
    public ApproveTrail_test() {
        super("com.nonobank", HomeActivity.class);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        solo = new Solo(getInstrumentation(), getActivity());

        solo.assertCurrentActivity("com.nonobank", HomeActivity.class);

        homePage = getActivity();

        solo.sleep(5000);
    }

    public void test_ApproveStepTrail () throws Exception {
        LogUtil Logger = new LogUtil();
        Logger.configLog("MXD_ApproveStepTrail_log4j.log");
        ReadExcel TestData = new ReadExcel();
        TestData.getTestDataFile("/storage/emulated/0/mxdApproveStepTrailData.csv");

        TestResults = new String[TestData.rowCount];
        for (int i = 1; i < TestData.rowCount; i++) {
            Logger.gLogger.debug("数据库操作初审通过");
            String url = "jdbc:mysql://192.168.4.13:3306/db_nono";
            String user = "nonobank";
            String password = "B67891234bc34dsdfd12h";

            SQLUtils sqlu = new SQLUtils(url, user, password);
            sqlu.SelectINuserInfo("select id from user_info where mobile_num="+TestData.FirstValue[i].toString());
            sqlu.SelectINBorrrow("select id from borrows where p_id=83 and user_id="+ sqlu.userId +" order by create_time desc limit 1");
            sqlu.UpdateInborrowsArchive("update borrows_archive set is_audit=1, is_confirm=3 where bo_id="+sqlu.id);
            solo.sleep(20000);

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

            solo.waitForActivity(HomeActivity.class, 3000);
            BorrowItem = (RadioButton) solo.getView(R.id.iv_borrow);
            solo.clickOnView(BorrowItem);
            solo.sleep(3000);

            Logger.gLogger.debug("点击应急借按钮进入查看进度页面状态");
            BorrowInitBtn = (LinearLayout) solo.getView(R.id.emergency_borrow);
            solo.clickOnView(BorrowInitBtn);
            solo.waitForActivity(WebViewBaseActivity.class, 3000);
            solo.sleep(10000);

            if (solo.waitForWebElement(By.id("runStep2"),5000,false) == true) {
                TestResults[i] = "Pass";
            }else if (solo.waitForWebElement(By.id("runStep2"),5000,false) == false){
                TestResults[i] = "Failed";
            }
        }
        TestData.writeResults(TestResults, "mxdApproveStepTrailTestResult.txt");
    }

    protected void tearDown() throws Exception {
        solo.sleep(3000);
        solo.finishOpenedActivities();
        super.tearDown();
    }
}