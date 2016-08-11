package com.nonobank.ui.activity.Utils;

import android.content.Context;
import android.os.Environment;
import android.telephony.TelephonyManager;
import com.nonobank.ui.activity.Utils.ReadCode;
import java.io.File;

/**
 * Created by edison on 16/7/7.
 */
public class SplitSMScode {
    public String codeResult;
    public void SMScode(String URL) {
        try {
            ReadCode SMScode = new ReadCode();
            String fileContent = SMScode.read(Environment.getExternalStorageDirectory() + File.separator + URL);

            int startIndex = fileContent.indexOf("：");

            int lastIndex = fileContent.lastIndexOf("，");
            String Result = fileContent.substring(startIndex, lastIndex);

            codeResult = Result.replace("：", "");

            System.out.println("Result = " + codeResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
