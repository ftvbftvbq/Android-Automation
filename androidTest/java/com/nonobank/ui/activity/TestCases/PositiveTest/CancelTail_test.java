package com.nonobank.ui.activity.TestCases.PositiveTest;

import android.provider.Settings;
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
import com.nonobank.ui.activity.camera.RecordVideoActivity;
import com.nonobank.ui.activity.webview.WebViewBaseActivity;
import com.robotium.solo.By;
import com.robotium.solo.Solo;

/**
 * Created by edison on 16/7/11.
 */
public class CancelTail_test extends ActivityInstrumentationTestCase2<HomeActivity> {
    private Solo solo;
    private HomeActivity homePage;
    private RadioButton BorrowItem;
    private LinearLayout BorrowInitBtn;
    private EditText PhoneEditText0,VPassEditText0;
    private TextView loginOther;
    private Button NextButton0, LoginButton1;
    String[] TestResults;

    @SuppressWarnings("deprecation")
    public CancelTail_test() {
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

    public void test_CancelTail() throws Exception {
        LogUtil Logger = new LogUtil();
        Logger.configLog("MXD_CancelTail_log4j.log");
        ReadExcel TestData = new ReadExcel();
        TestData.getTestDataFile("/storage/emulated/0/mxdCancelTailTestData.csv");

        TestResults = new String[TestData.rowCount];
        for (int i = 1; i < TestData.rowCount; i++) {
            Logger.gLogger.debug("数据库取消初审");
            String url = "jdbc:mysql://192.168.4.13:3306/db_nono";
            String user = "nonobank";
            String password = "B67891234bc34dsdfd12h";

            SQLUtils sqlu = new SQLUtils(url, user, password);

            sqlu.SelectINuserInfo("select id from user_info where mobile_num="+TestData.FirstValue[i].toString());
            sqlu.SelectINBorrrow("select id from borrows where p_id=83 and user_id="+ sqlu.userId +" order by create_time desc limit 1");
            sqlu.UpdateInborrowsArchive("update borrows_archive set is_audit=5 where bo_id="+sqlu.id);
            sqlu.UpdateInborrows("update borrows set bo_is_success=3 where id="+sqlu.id);
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

            Logger.gLogger.debug("点击应急借按钮进行再次借款操作");
            BorrowInitBtn = (LinearLayout) solo.getView(R.id.emergency_borrow);
            solo.clickOnView(BorrowInitBtn);
            solo.waitForActivity(WebViewBaseActivity.class, 3000);
            solo.sleep(10000);

            Logger.gLogger.debug("进行借贷操作");
            solo.typeTextInWebElement(By.id("inputMoney"), "100");
            solo.clickOnWebElement(By.id("selectMonth"));
            solo.clickOnText("1个月");
            solo.clickOnWebElement(By.id("selectBorrowUserTo"));
            solo.clickOnText("消费购物");
            solo.clickOnWebElement(By.id("btnNext"));

            Logger.gLogger.debug("进行完善资料操作");
            solo.waitForActivity(WebViewBaseActivity.class, 3000);
            solo.sleep(10000);
            solo.clickOnWebElement(By.id("btnOk"));
            solo.sleep(10000);


            //确认提交
            Logger.gLogger.debug("进入录制视频界面");
            solo.clickOnWebElement(By.id("btnOk"));
            solo.sleep(30000);
            solo.waitForActivity(RecordVideoActivity.class, 10000);

            Logger.gLogger.debug("进入录制视频界面");
            solo.waitForActivity(RecordVideoActivity.class, 10000);
            solo.sleep(2000);

            solo.clickOnView(solo.getView(R.id.buttonDefaultPositive));
            solo.sleep(2000);
            solo.clickOnView(solo.getView(R.id.start));
            solo.sleep(70000);
            solo.clickOnView(solo.getView(R.id.preview));
            solo.sleep(3000);
            solo.clickOnView(solo.getView(R.id.upload));
            solo.sleep(50000);
            solo.waitForActivity(WebViewBaseActivity.class, 3000);
            solo.sleep(5000);

            if (solo.waitForWebElement(By.id("runStep1"),5000,false) == true) {
                TestResults[i] = "Pass";
            }else if (solo.waitForWebElement(By.id("runStep1"),5000,false) == false){
                TestResults[i] = "Failed";
            }
        }
        TestData.writeResults(TestResults, "mxdCancelTailTestResult.txt");
    }

    protected void tearDown() throws Exception {
        solo.sleep(3000);
        solo.finishOpenedActivities();
        super.tearDown();
    }
}
