package com.nonobank.ui.activity.TestCases.PositiveTest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.nonobank.R;
import com.nonobank.ui.activity.HomeActivity;
import com.nonobank.ui.activity.Utils.LogUtil;
import com.nonobank.ui.activity.Utils.ReadExcel;
import com.robotium.solo.Solo;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by edison on 16/7/25.
 */
public class Recharge_test extends ActivityInstrumentationTestCase2<HomeActivity> {
    private Solo solo;
    private HomeActivity homePage;
    private EditText RechargeMoney,RepayPassword,TextMsg, Paypwd,ConfirmPaypwd ;
    private TextView TotalBalance, RechargeBtn;
    private Button ConfirmPaypwdBtn,ConfirmBtn, RechargeConfirmBtn, PayPassBtn;
    String[] TestResults;

    @SuppressWarnings("deprecation")
    public Recharge_test() {
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

    public void test_Recharge () throws Exception {
        LogUtil Logger = new LogUtil();
        Logger.configLog("MXD_Recharge_log4j.log");
        ReadExcel TestData = new ReadExcel();
        TestData.getTestDataFile("/storage/emulated/0/mxdRechargeTestData.csv");

        TestResults = new String[TestData.rowCount];
        for (int i = 1; i < TestData.rowCount; i++) {
            Logger.gLogger.debug("进入主页面开始进行登录操作");
            solo.waitForActivity(HomeActivity.class, 3000);
            solo.drag(146,567,570,570,1);
            solo.sleep(5000);
            solo.clickOnText("我");

            Logger.gLogger.debug("预期充值后的总额");
            TotalBalance = (TextView) solo.getView(R.id.account_left_yuan);

            NumberFormat nf = NumberFormat.getInstance();
            float First = 0;
            try {
                First = nf.parse(TotalBalance.getText().toString()).floatValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("钱＋＋" + First);
            float Second = Float.parseFloat(TestData.FirstValue[i].toString());
            float expectedCash = First + Second;

            Logger.gLogger.debug(First);
            Logger.gLogger.debug(Second);
            Logger.gLogger.debug(expectedCash);


            Logger.gLogger.debug("进入充值页面");
            RechargeBtn = (TextView) solo.getView(R.id.recharge);
            solo.clickOnView(RechargeBtn);
            solo.sleep(3000);

            Logger.gLogger.debug("开始充值操作");
            RechargeMoney = (EditText) solo.getView(R.id.recharge_money);
            solo.enterText(RechargeMoney,TestData.FirstValue[i].toString());
            solo.sleep(3000);
            ConfirmBtn = (Button) solo.getView(R.id.confirm);
            solo.sleep(3000);
            solo.clickOnView(ConfirmBtn);
            solo.sleep(3000);

            Logger.gLogger.debug("设置支付密码");
            PayPassBtn = (Button) solo.getView(R.id.buttonDefaultPositive);
            solo.sleep(3000);
            solo.clickOnView(PayPassBtn);
            solo.sleep(3000);

            Logger.gLogger.debug("设置支付密码界面");
            Paypwd = (EditText) solo.getView(R.id.pay_pwd);
            ConfirmPaypwd = (EditText) solo.getView(R.id.confirm_paypwd);
            solo.sleep(3000);
            solo.enterText(Paypwd,TestData.SecondValue[i].toString());
            solo.enterText(ConfirmPaypwd,TestData.SecondValue[i].toString());
            solo.sleep(3000);
            ConfirmPaypwdBtn = (Button) solo.getView(R.id.confirm);
            solo.sleep(3000);
            solo.clickOnView(ConfirmPaypwdBtn);
            solo.sleep(10000);
            solo.clickOnView(ConfirmBtn);

            Logger.gLogger.debug("输入支付密码");
            RepayPassword = (EditText) solo.getView(R.id.repay_password);
            solo.enterText(RepayPassword,TestData.SecondValue[i].toString());
            solo.clickOnView(RepayPassword);
            solo.sleep(3000);
            solo.clickOnText("确认");
            solo.sleep(3000);

            Logger.gLogger.debug("输入验证码");
            TextMsg = (EditText) solo.getView(R.id.et_text_msg);
            solo.enterText(TextMsg,"0615");
            solo.sleep(3000);
            RechargeConfirmBtn = (Button) solo.getView(R.id.btn_recharge_confirm);
            solo.sleep(3000);
            solo.clickOnView(RechargeConfirmBtn);
            solo.sleep(10000);

            Logger.gLogger.debug("判断是否出现充值成功");
            Logger.gLogger.debug(solo.searchText(TestData.ExpectedResult[i]));
            solo.waitForActivity(HomeActivity.class, 3000);
            solo.sleep(5000);

            NumberFormat nf1 = NumberFormat.getInstance();
            float CurrentCash = 0;
            try {
                CurrentCash = nf1.parse(TotalBalance.getText().toString()).floatValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            assertEquals("金额和预期金额一致", expectedCash, CurrentCash);

            if (solo.searchText(TestData.ExpectedResult[i]) == true) {
                TestResults[i] = "Pass";
            } else if (solo.searchText(TestData.ExpectedResult[i]) == false) {
                TestResults[i] = "Failed";
            }
        }
        TestData.writeResults(TestResults, "mxdRechargeTestResult.txt");
    }

    protected void tearDown() throws Exception {
        solo.sleep(3000);
        solo.finishOpenedActivities();
        super.tearDown();
    }
}