package com.nonobank.ui.activity.BaseTest;

import android.os.PowerManager;
import android.test.ActivityInstrumentationTestCase2;

import com.nonobank.ui.activity.CheckLoginActivity;
import com.nonobank.ui.activity.UIHelper;
import com.nonobank.ui.activity.Utils.NetWorkUtil;
import com.robotium.solo.Solo;


/**
 * Created by edison on 16/3/1.
 */
public class LoginActivityTest extends  ActivityInstrumentationTestCase2<CheckLoginActivity> {
    private Solo solo;
    private PowerManager.WakeLock wakeScreenObject = null;
    protected UIHelper uiHelper = null;

    public LoginActivityTest() {
        super(CheckLoginActivity.class);
        // TODO Auto-generated constructor stub
    }


    /**
     * ��дsetUp�����쳣���񣬽�ͼ����
     *
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        // TODO Auto-generated method stub
        try {
            super.setUp();
            init();
        } catch (Throwable tr) {
            solo.takeScreenshot(this.getClass().getSimpleName());
            throw new SetUpException(tr);
        }
    }

    /**
     *  ��дtearDown,�����쳣���񣬽�ͼ����
     *
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        // TODO Auto-generated method stub
        try {
            if (wakeScreenObject != null) {
                wakeScreenObject.release();
                wakeScreenObject = null;
            }
            solo.finishOpenedActivities();
            uiHelper = null;
            super.tearDown();
        } catch (Throwable th) {
            solo.takeScreenshot(this.getClass().getSimpleName());
            throw new TearDownException(th);
        }

    }


    private void init() {
        solo = new Solo(getInstrumentation(), getActivity());
        uiHelper = new UIHelper(solo);

        solo.unlockScreen();

        NetWorkUtil.setAirplaneModeOffAndNetworkOn(getInstrumentation().getTargetContext());
    }

    /**
     *
     * @throws Throwable
     */
    @Override
    public void runBare() throws Throwable {
        try {
            super.runBare();
        } catch (SetUpException smte) {
            this.tearDown();
            throw smte;
        } catch (RunTestException rte) {
            this.tearDown();
            throw rte;
        } catch (TearDownException tde) {
            this.tearDown();
            throw tde;
        }
    }

    /**
     *
     * @throws Throwable
     */
    @Override
    protected void runTest() throws Throwable {
        // TODO Auto-generated method stub
        try {
            super.runTest();
        } catch (Throwable th) {
            solo.takeScreenshot(this.getClass().getSimpleName());
            throw new RunTestException(th);
        }
    }

    /**
     */
    class SetUpException extends Exception {
        private static final long serialVersionUID = 1L;

        SetUpException(Throwable e) {
            super("Error in BasicTestCase.setUp()!", e);
        }
    }

    class RunTestException extends Exception {
        private static final long serialVersionUID = 2L;

        RunTestException(Throwable e) {
            super("Error in BasicTestCase.runTest()", e);
        }
    }

    class TearDownException extends Exception {
        private static final long serialVersionUID = 3L;

        TearDownException(Throwable e) {
            super("Error in MultiTestCase.tearDown()", e);
        }
    }
}