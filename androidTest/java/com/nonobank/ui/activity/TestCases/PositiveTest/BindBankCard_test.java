package com.nonobank.ui.activity.TestCases.PositiveTest;

import android.support.v7.widget.AppCompatTextView;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nonobank.R;
import com.nonobank.ui.activity.BankcardCertificateActivity;
import com.nonobank.ui.activity.CheckLoginActivity;
import com.nonobank.ui.activity.HomeActivity;
import com.nonobank.ui.activity.LoginActivity;
import com.nonobank.ui.activity.MyBankcardActivity;
import com.nonobank.ui.activity.RealnameCertificateActivity;
import com.nonobank.ui.activity.Utils.GenBnakNo;
import com.nonobank.ui.activity.Utils.GenChineseName;
import com.nonobank.ui.activity.Utils.GenIdCard;
import com.nonobank.ui.activity.Utils.LogUtil;
import com.nonobank.ui.activity.Utils.ReadExcel;
import com.nonobank.ui.view.WheelPopupWindow;
import com.nonobank.ui.view.WheelView;
import com.robotium.solo.Solo;

/**
 * Created by edison on 16/7/21.
 */
public class BindBankCard_test extends ActivityInstrumentationTestCase2<HomeActivity> {
    private Solo solo;
    private HomeActivity homePage;
    private RelativeLayout MyBankcard;
    private EditText Realname, IdNum, PhoneEditText0, VPassEditText0, PhoneNum, MyBankcardId, CheckedNum;
    private TextView loginOther,MyBankcardItem,MsgNumLink;
    private Button NextStepBtn,NextButton0,MyBankcardBtn, LoginButton1, ConfirmBtn;
    private AppCompatTextView BankCardSure;

    String[] TestResults;

    @SuppressWarnings("deprecation")
    public BindBankCard_test() {
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

    public void test_BindBankCard() throws Exception {
        LogUtil Logger = new LogUtil();
        GenBnakNo BankCard = new GenBnakNo();
        Logger.configLog("MXD_BindBankCard_log4j.log");
        ReadExcel TestData = new ReadExcel();
        TestData.getTestDataFile("/storage/emulated/0/mxdBindBankCardTestData.csv");

        TestResults = new String[TestData.rowCount];
        for (int i = 1; i < TestData.rowCount; i++) {
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
            solo.enterText(VPassEditText0, TestData.SecondValue[i].toString());
            solo.clickOnView(VPassEditText0);
            solo.clickOnView(LoginButton1);

            Logger.gLogger.debug("进入设置主界面");
            solo.waitForActivity(HomeActivity.class, 3000);
            solo.sleep(10000);
            solo.clickOnText("我");
            solo.sleep(3000);

            Logger.gLogger.debug("进入设置银行卡");
            MyBankcard = (RelativeLayout) solo.getView(R.id.my_bankcard);
            solo.clickOnView(MyBankcard);
            solo.sleep(10000);

            solo.waitForActivity(MyBankcardActivity.class, 5000);
            solo.sleep(3000);
            MyBankcardBtn = (Button) solo.getView(R.id.add_bankcard);
            solo.clickOnView(MyBankcardBtn);
            solo.sleep(10000);

            Logger.gLogger.debug("进入实名页面");
            solo.waitForActivity(RealnameCertificateActivity.class, 5000);
            GenIdCard IdNo = new GenIdCard();
            GenChineseName ChName = new GenChineseName();

            Realname = (EditText) solo.getView(R.id.realname);
            solo.enterText(Realname, ChName.getChineseName().toString());
            solo.sleep(1000);
            IdNum = (EditText) solo.getView(R.id.id_num);
            solo.enterText(IdNum, IdNo.generate().toString());
            solo.sleep(1000);
            NextStepBtn = (Button) solo.getView(R.id.next);
            solo.clickOnView(NextStepBtn);
            solo.sleep(10000);

            Logger.gLogger.debug("进入设置银行卡添加详细页面");
            solo.waitForActivity(BankcardCertificateActivity.class, 5000);
            solo.sleep(3000);

            Logger.gLogger.debug("输入银行卡号");
            MyBankcardId = (EditText) solo.getView(R.id.bankcard_num);
            solo.enterText(MyBankcardId, BankCard.getBankCard("622848003857").toString());
            solo.sleep(10000);

            Logger.gLogger.debug("选择银行卡");
            MyBankcardItem = (TextView) solo.getView(R.id.choose_tv_bankName);
            solo.clickOnView(MyBankcardItem);
            solo.sleep(2000);

            BankCardSure = (AppCompatTextView) solo.getView(R.id.view_rate_query_wheel_textViewSure);
            solo.clickOnView(BankCardSure);
            solo.sleep(2000);

            Logger.gLogger.debug("输入手机号");
            PhoneNum = (EditText) solo.getView(R.id.phonenum_left);
            solo.enterText(PhoneNum, "13917862410");
            solo.sleep(2000);

            Logger.gLogger.debug("点击获取验证码");
            MsgNumLink = (TextView) solo.getView(R.id.getMsgNum);
            solo.clickOnView(MsgNumLink);
            solo.sleep(2000);

            Logger.gLogger.debug("输入验证码");
            CheckedNum = (EditText) solo.getView(R.id.msg_checkNum);
//                SplitSMScode code =  new SplitSMScode();
//                code.SMScode("verifyCode.txt");
//                solo.enterText(CheckedNum, code.codeResult);
            solo.enterText(CheckedNum, "0615");
            solo.sleep(2000);

            Logger.gLogger.debug("点击确定按钮");
            ConfirmBtn = (Button) solo.getView(R.id.confirm);
            solo.clickOnView(ConfirmBtn);
            solo.sleep(5000);

            Logger.gLogger.debug("判断是否添加成功");
            if (solo.searchText(TestData.ExpectedResult[i]) == true) {
                TestResults[i] = "Pass";
            }   else if (solo.searchText(TestData.ExpectedResult[i]) == false) {
                TestResults[i] = "Failed";
            }

            solo.goBack();
        }
        TestData.writeResults(TestResults, "mxdBindBankCardTestResult.txt");
    }


    protected void tearDown() throws Exception {
        solo.sleep(3000);
        solo.finishOpenedActivities();
        super.tearDown();
    }
}
