package com.nonobank.ui.activity.TestCases.PositiveTest;


import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nonobank.R;
import com.nonobank.ui.activity.CheckLoginActivity;
import com.nonobank.ui.activity.HomeActivity;
import com.nonobank.ui.activity.RegisterActivity;
import com.nonobank.ui.activity.Utils.GencellPhoneNum;
import com.nonobank.ui.activity.Utils.LogUtil;
import com.nonobank.ui.activity.Utils.ReadExcel;
import com.nonobank.ui.activity.gesture.LockSetupActivity;
import com.robotium.solo.Solo;

/**
 * Created by edison on 16/3/1.
 */
public class Register_test extends ActivityInstrumentationTestCase2<HomeActivity> {
    private Solo solo;
    private HomeActivity homePage;
    private EditText mPhone,SafeCode,CheckNum,Password;
    private TextView GetcheckNum;
    private Button NextButton,Reg,LoginOutBtn;
    private RelativeLayout MyIcon;
    private TextView RegBtn;
    String[] TestResults;


    @SuppressWarnings("deprecation")
    public Register_test() {
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

    public void test_Register() throws Exception {
        GencellPhoneNum cellPhone = new GencellPhoneNum();

        LogUtil Logger = new LogUtil();
        Logger.configLog("mxdRegister_log4j.log");

        ReadExcel TestData = new ReadExcel();
        TestData.getTestDataFile("/storage/emulated/0/mxdRegTestData.csv");
        TestResults = new String[TestData.rowCount];

        for(int i =1; i<TestData.rowCount; i++) {
            Logger.gLogger.debug("登录进入检查手机号界面");
            solo.waitForActivity(HomeActivity.class, 3000);
            solo.sleep(5000);

            solo.clickOnText("我");
            solo.sleep(5000);

            RegBtn = (TextView) solo.getView(R.id.register);
            solo.clickOnView(RegBtn);
            solo.sleep(3000);

            Logger.gLogger.debug("输入手机号");
            mPhone = (EditText) solo.getView(R.id.tel_num);
            NextButton = (Button) solo.getView(R.id.check_next);

            solo.enterText(mPhone, TestData.FirstValue[i].toString());
            //cellPhone.getTel()
            solo.clickOnView(NextButton);
            solo.sleep(5000);

            Logger.gLogger.debug("进入注册页面");
            solo.waitForActivity(RegisterActivity.class, 5000);
            solo.sleep(3000);

            SafeCode = (EditText) solo.getView(R.id.et_safe_code);
            GetcheckNum = (TextView) solo.getView(R.id.get_msg_checknum);
            CheckNum = (EditText) solo.getView(R.id.msg_checkNum);
            Password = (EditText) solo.getView(R.id.password);

            Logger.gLogger.debug("填写图形验证码");
            solo.enterText(SafeCode, "0615");

            Logger.gLogger.debug("获取验证码");
            solo.clickOnView(GetcheckNum);
            solo.sleep(1000);
//        if (solo.searchText("发送成功") == true) {
                Logger.gLogger.debug("填写验证码");
                solo.sleep(5000);
                solo.enterText(CheckNum, "0615");

                Logger.gLogger.debug("读取EXCEL数据，输入密码");
                solo.enterText(Password, TestData.SecondValue[i].toString());
                solo.clickOnView(Password);
                solo.sleep(3000);

                Logger.gLogger.debug("点击完成注册按钮");
                Reg = (Button) solo.getView(R.id.register);
                solo.clickOnView(Reg);
                solo.sleep(3000);

                if (solo.searchText(TestData.ExpectedResult[i]) == true) {
                    TestResults[i] = "Pass";
                } else if (solo.searchText(TestData.ExpectedResult[i]) == false) {
                    TestResults[i] = "Failed";
            }

            solo.waitForActivity(LockSetupActivity.class, 3000);
            solo.drag(146,567,570,570,1);
            solo.sleep(3000);
            solo.drag(146,567,570,570,1);
            solo.sleep(3000);

//            solo.clickOnText("我");
            solo.sleep(10000);
            MyIcon = (RelativeLayout) solo.getView(R.id.ccc);
            solo.clickOnView(MyIcon);
            solo.sleep(3000);

            LoginOutBtn = (Button) solo.getView(R.id.login_out);
            solo.clickOnView(LoginOutBtn);
            solo.sleep(3000);
            solo.clickOnView(solo.getView(Button.class, 0));
            solo.sleep(3000);
            solo.clickOnText("借钱");
            solo.sleep(10000);

        }
        TestData.writeResults(TestResults, "mxdRegTestResult.txt");
    }


    protected void tearDown() throws Exception {
        solo.sleep(3000);
//        solo.finishOpenedActivities();
        super.tearDown();
    }

}
