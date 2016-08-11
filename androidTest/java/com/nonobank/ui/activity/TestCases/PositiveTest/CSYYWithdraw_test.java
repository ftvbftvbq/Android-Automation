package com.nonobank.ui.activity.TestCases.PositiveTest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nonobank.R;
import com.nonobank.ui.activity.HomeActivity;
import com.nonobank.ui.activity.Utils.LogUtil;
import com.nonobank.ui.activity.Utils.ReadExcel;
import com.robotium.solo.Solo;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by edison on 16/7/26.
 */
public class CSYYWithdraw_test extends ActivityInstrumentationTestCase2<HomeActivity> {
    private Solo solo;
    private HomeActivity homePage;
    private EditText WithdrawMoney,RepayPassword;
    private TextView TotalBalance;
    private Button ConfirmBtn;
    private ImageView WithdrawBtn;
    String[] TestResults;

    @SuppressWarnings("deprecation")
    public CSYYWithdraw_test() {
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

    public void test_CSYYWithdraw () throws Exception {
        LogUtil Logger = new LogUtil();
        Logger.configLog("MXD_Recharge_log4j.log");
        ReadExcel TestData = new ReadExcel();
        TestData.getTestDataFile("/storage/emulated/0/mxdCSYYWithdrawTestData.csv");

        TestResults = new String[TestData.rowCount];
        for (int i = 1; i < TestData.rowCount; i++) {
            Logger.gLogger.debug("进入主页面开始进行登录操作");
            solo.waitForActivity(HomeActivity.class, 3000);
            solo.drag(146,567,570,570,1);
            solo.sleep(5000);
            solo.clickOnText("赚钱");

            Logger.gLogger.debug("预期提现后的总额");
            TotalBalance = (TextView) solo.getView(R.id.invest_money);

            NumberFormat nf = NumberFormat.getInstance();
            float First = 0;
            try {
                First = nf.parse(TotalBalance.getText().toString()).floatValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            System.out.println("钱＋＋" + First);

            float Second = Float.parseFloat(TestData.FirstValue[i].toString());
            float expectedCash = First - Second;
            Logger.gLogger.debug(First);
            Logger.gLogger.debug(Second);
            Logger.gLogger.debug(expectedCash);


            Logger.gLogger.debug("进入提现页面");
            WithdrawBtn = (ImageView) solo.getView(R.id.tixian);
            solo.clickOnView(WithdrawBtn);
            solo.sleep(5000);

            Logger.gLogger.debug("开始提现操作");
            WithdrawMoney = (EditText) solo.getView(R.id.input_extract_money);
            solo.enterText(WithdrawMoney,TestData.FirstValue[i].toString());
            solo.sleep(3000);
            ConfirmBtn = (Button) solo.getView(R.id.extract);
            solo.sleep(3000);
            solo.clickOnView(ConfirmBtn);
            solo.sleep(3000);

            Logger.gLogger.debug("输入支付密码");
            RepayPassword = (EditText) solo.getView(R.id.repay_password);
            solo.enterText(RepayPassword,TestData.SecondValue[i].toString());
            solo.sleep(3000);
            solo.clickOnText("确认");
            solo.sleep(3000);

            Logger.gLogger.debug("判断是否出现提现成功");
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
        TestData.writeResults(TestResults, "mxdCSYYWithdrawTestResult.txt");
    }

    protected void tearDown() throws Exception {
        solo.sleep(3000);
        solo.finishOpenedActivities();
        super.tearDown();
    }
}
