package com.nonobank.receiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.SmsMessage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by edison on 16/6/17.
 */
public class SMSReceiver extends BroadcastReceiver {
    private String verifyCode = "";
    public static final String TAG = "SMSReceiver";
    public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
//        Log.i(TAG, "引发接收事件");
        //StringBuilder body=new StringBuilder("");//短信内容
        //StringBuilder sender=new StringBuilder("");//发件人
        //先判断广播消息
        String action = intent.getAction();
//        System.out.println("action = "+action);
        if (SMS_RECEIVED_ACTION.equals(action)) {
            //获取intent参数
            Bundle bundle = intent.getExtras();
            //判断bundle内容
//            System.out.println("bundle = "+bundle);
            if (bundle != null) {
                //取pdus内容,转换为Object[]
                Object[] pdus = (Object[]) bundle.get("pdus");
//                System.out.println("bundle.get(\"pdus\") = "+pdus.length);
                //解析短信
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < messages.length; i++) {
                    byte[] pdu = (byte[]) pdus[i];
                    messages[i] = SmsMessage.createFromPdu(pdu);
                }
                //解析完内容后分析具体参数
                for (SmsMessage msg : messages) {
                    //获取短信内容
                    String content = msg.getMessageBody();// 短信内容
//                    System.out.println("content= "+content );
//                    String sender = msg.getOriginatingAddress();// 发送者
//                    Date date = new Date(msg.getTimestampMillis());// 发送时间（毫秒）
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    String sendTime = sdf.format(date);// 发送时间 标准格式化时间
//                    writeFile("收到来自" + sender + "的短信：\n" + content + "\n时间:" + sendTime);
                    writeFile(content);
                }
            }
        }
    }

    //将短信内容写到SD卡上的文件里，便于将文件pull到PC，这样可方便平台的自动化
    @SuppressLint("SdCardPath")
    public void writeFile(String str) {
        String filePath = Environment.getExternalStorageDirectory() + File.separator + "verifyCode.txt";
        byte[] bytes = str.getBytes();
        try {
            File file = new File(filePath);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
