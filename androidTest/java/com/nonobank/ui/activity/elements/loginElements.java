package com.nonobank.ui.activity.elements;

import android.widget.Button;
import android.widget.EditText;

import com.nonobank.R;
import com.robotium.solo.Solo;

/**
 * Created by edison on 16/3/1.
 */
public class loginElements {

    private Solo solo;
    private EditText mPhoneEditText,VPassEditText, mPhoneEditText0;
    private Button LoginButton,NextButton;

    public loginElements(Solo solo){
        this.solo = solo;
        initViews();
    }

    /**
     */
    private void initViews(){
        solo.sleep(1500);
        mPhoneEditText0 = (EditText) solo.getCurrentActivity().findViewById(R.id.tel_num);

        NextButton = (Button) solo.getCurrentActivity().findViewById(R.id.check_next);

        mPhoneEditText = (EditText) solo.getCurrentActivity().findViewById(R.id.tel_phone);

        VPassEditText = (EditText) solo.getCurrentActivity().findViewById(R.id.password);

        LoginButton = (Button) solo.getCurrentActivity().findViewById(R.id.bt_login);
    }

    /**
     * @return
     */
    public EditText getMphoneEditText(){
        return mPhoneEditText;
    }

    /**
     * @param text
     */
    public EditText getvPasswordEditText(){
        return VPassEditText;
    }
    /**
     * @param text
     */

    public Button getLoginButton(){
        return LoginButton;
    }

    /**
     * @param text
     */
    public void enterPhone(String text){
        solo.enterText(mPhoneEditText, text);
    }

    public void enterCheckPhone(String text){
        solo.enterText(mPhoneEditText0, text);
    }

    public void clearPhone(){
        solo.clearEditText(mPhoneEditText);
    }
    /**
     * ��������
     * @param text
     */
    public void enterPassword(String text){
        solo.enterText(VPassEditText, text);
    }

    public void clickOnPasswordView(){
        solo.clickOnView(VPassEditText);
    }

    public void clearPassword(){
        solo.clearEditText(VPassEditText);
    }
    /**
     * �����¼��ť
     */
    public void clickLoginButton(){
        solo.clickOnView(LoginButton);
    }

    public void clickNextButton(){
        solo.clickOnView(NextButton);
    }
}
