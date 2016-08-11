package com.nonobank.ui.activity.TestCases.PositiveTest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nonobank.R;
import com.nonobank.ui.activity.CheckLoginActivity;
import com.nonobank.ui.activity.HomeActivity;
import com.nonobank.ui.activity.LoginActivity;
import com.nonobank.ui.activity.Utils.GencellPhoneNum;
import com.nonobank.ui.activity.Utils.LogUtil;
import com.nonobank.ui.activity.Utils.ReadExcel;
import com.nonobank.ui.activity.Utils.SQLUtils;
import com.nonobank.ui.activity.borrows.BorrowAuthenticationActivity;
import com.nonobank.ui.activity.camera.RecordVideoActivity;
import com.nonobank.ui.activity.webview.WebViewBaseActivity;
import com.robotium.solo.By;
import com.robotium.solo.Solo;


/**
 * Created by edison on 16/3/30.
 */
public class YJBorrow_test extends ActivityInstrumentationTestCase2<HomeActivity> {

    private Solo solo;
    private String info1, info2, info3;
    private HomeActivity homePage;
    private RadioButton BorrowItem;
    private LinearLayout BorrowInitBtn;
    private ImageView BackImg;
    private EditText PhoneEditText0, PhoneEditText1,VPassEditText0, InputName, InputCard, InputSchool;
    private RelativeLayout SelectEdu, SelectYear;
    private TextView loginOther, EnsureBtn1, EnsureBtn2;
    private Button NextButton0, LoginButton1, NextBtn, ConfirmBtn;
    String[] TestResults;

    @SuppressWarnings("deprecation")
    public YJBorrow_test() {
        super("com.nonobank", HomeActivity.class);
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

    /**
     * @throws Exception
     */
    public void testYJBorrow() throws Exception {
        GencellPhoneNum  cellPhone1 = new GencellPhoneNum();
        GencellPhoneNum  cellPhone2 = new GencellPhoneNum();
        GencellPhoneNum  cellPhone3 = new GencellPhoneNum();
        LogUtil Logger = new LogUtil();
        Logger.configLog("mxdYJIBorrow_log4j.log");

        ReadExcel TestData = new ReadExcel();
        TestData.getTestDataFile("/storage/emulated/0/mxdYJBorrowTestData.csv");

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

            solo.waitForActivity(HomeActivity.class, 3000);
            BorrowItem = (RadioButton) solo.getView(R.id.iv_borrow);
            solo.clickOnView(BorrowItem);
            solo.sleep(3000);

            Logger.gLogger.debug("点击应急借按钮开始进行借贷操作");
            BorrowInitBtn = (LinearLayout) solo.getView(R.id.emergency_borrow);
            solo.clickOnView(BorrowInitBtn);
            solo.waitForActivity(BorrowAuthenticationActivity.class, 3000);

            Logger.gLogger.debug("进行学籍认证操作");
            InputName = (EditText) solo.getView(R.id.name);
            InputCard = (EditText) solo.getView(R.id.id_card);
            InputSchool = (EditText) solo.getView(R.id.school);
            SelectEdu = (RelativeLayout) solo.getView(R.id.choose_education);
            SelectYear = (RelativeLayout) solo.getView(R.id.choose_year);
            solo.enterText(InputSchool, "上海大学");

            solo.clickOnView(SelectEdu);
            solo.sleep(3000);
            EnsureBtn1 = (TextView) solo.getView(R.id.view_rate_query_wheel_textViewSure);
            solo.clickOnView(EnsureBtn1);
            solo.sleep(3000);
            solo.clickOnView(SelectYear);
            solo.sleep(3000);
            EnsureBtn2 = (TextView) solo.getView(R.id.view_rate_query_wheel_textViewSure);
            solo.clickOnView(EnsureBtn2);
            solo.sleep(3000);
            NextBtn = (Button) solo.getView(R.id.next);
            solo.clickOnView(NextBtn);

            solo.waitForDialogToOpen(3000);
            ConfirmBtn = (Button) solo.getView(R.id.confirm);
            solo.clickOnView(ConfirmBtn);
            solo.sleep(3000);
            solo.waitForActivity(WebViewBaseActivity.class, 3000);
            solo.sleep(3000);

            Logger.gLogger.debug("进行借贷操作");
            solo.typeTextInWebElement(By.id("inputMoney"), "100");
            solo.sleep(3000);
            solo.clickOnWebElement(By.id("selectMonth"));
            solo.sleep(3000);
            solo.clickOnText("1个月");
            solo.sleep(3000);
            solo.clickOnWebElement(By.id("selectBorrowUserTo"));
            solo.sleep(3000);
            solo.clickOnText("消费购物");
            solo.sleep(3000);
            solo.clickOnWebElement(By.id("btnNext"));
            solo.sleep(3000);

            Logger.gLogger.debug("进行完善资料操作");
            solo.waitForActivity(WebViewBaseActivity.class, 3000);
            solo.sleep(10000);
            solo.clickOnWebElement(By.id("btnOk"));
            solo.sleep(10000);


            //填写家庭地址
            Logger.gLogger.debug("进行家庭地址信息完善操作");
            solo.waitForActivity(WebViewBaseActivity.class, 3000);
            solo.sleep(3000);
            solo.clickOnWebElement(By.id("btnEditAddress"));
            solo.sleep(2000);
            solo.clickOnWebElement(By.id("inputArea"));
            solo.clickOnWebElement(By.textContent("确定"));
            solo.clickOnWebElement(By.id("inputAddress"));
            solo.typeTextInWebElement(By.id("inputAddress"), "12334232322342342424");

            solo.clickOnWebElement(By.id("btnOk"));
            solo.sleep(5000);
            solo.waitForActivity(WebViewBaseActivity.class, 3000);



            //填写联系人信息
            Logger.gLogger.debug("进行联系人信息完善操作");
            solo.waitForActivity(WebViewBaseActivity.class, 3000);
            info1 = "0" + System.currentTimeMillis();
            solo.sleep(3000);
            solo.clickOnWebElement(By.id("btnEditContact"));
            solo.typeTextInWebElement(By.id("inputParentName"), info1);
            solo.sleep(2000);
            solo.typeTextInWebElement(By.id("inputParentNum"), cellPhone1.getTel());
            solo.sleep(3000);

            info2 = "0" + System.currentTimeMillis();
            solo.typeTextInWebElement(By.id("inputAssistantName"), info2);
            solo.sleep(3000);
            solo.typeTextInWebElement(By.id("inputAssistantNum"), cellPhone2.getTel());
            solo.sleep(3000);

            info3 = "0" + System.currentTimeMillis();
            solo.typeTextInWebElement(By.id("inputFriendName"), info3);
            solo.sleep(3000);
            solo.typeTextInWebElement(By.id("inputFriendNum"), cellPhone3.getTel());
            solo.sleep(3000);

            solo.clickOnWebElement(By.id("btnOk"));
            solo.sleep(5000);
            solo.waitForActivity(WebViewBaseActivity.class, 3000);

            //身份证信息
            Logger.gLogger.debug("进行身份证信息完善资料操作");
            solo.waitForActivity(WebViewBaseActivity.class, 3000);
            String url = "jdbc:mysql://192.168.4.13:3306/db_nono";
            String user = "nonobank";
            String password = "B67891234bc34dsdfd12h";

            SQLUtils sqlu = new SQLUtils(url, user, password);
            sqlu.SelectINuserInfo("select id from user_info where mobile_num="+TestData.FirstValue[i].toString());
            solo.sleep(20000);
            String SQL1 = "insert into user_file_audit_info (user_id, file_type_id, title,is_auditing,assign_time) values ("+ sqlu.userId + ",1,'身份证',0,DATE_FORMAT(now(),\"%y-%m-%d 10:00:00\"))";
            System.out.println("SQL: +" + SQL1);
            sqlu.InsertInUserFileAuditInfo(SQL1);
            solo.sleep(20000);

            sqlu.SelectINuserFileAuditInfo("select id from user_file_audit_info where user_id =" + sqlu.userId);
            solo.sleep(20000);
            String SQL2 = "insert into user_file_info (file_audit_id, file_name,file_path,remark) values ("+ sqlu.FileAuditId + ",'cid.png',CONCAT(unix_timestamp()*1000,'cid.png'),'身份证照片')";
            System.out.println("SQL: +" + SQL2);
            sqlu.InsertInUserFileInfo(SQL2);
            solo.sleep(20000);

            solo.clickOnWebElement(By.id("btnEditCID"));
            solo.sleep(3000);
            BackImg = (ImageView) solo.getView(R.id.iv_finish);
            solo.clickOnView(BackImg);

            solo.clickOnView(BorrowInitBtn);
            solo.sleep(20000);
            solo.clickOnWebElement(By.id("btnOk"));
            solo.sleep(20000);

            Logger.gLogger.debug("进入录制视频界面");
            solo.clickOnWebElement(By.id("btnOk"));
            solo.sleep(30000);

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
        TestData.writeResults(TestResults, "mxdYJBorrowTestResult.txt");
    }


    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
