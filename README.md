Android-Automation
==================
1. 使用robotium进行自动化，跨进程使用adbForRobotium

2. Android-Automation/androidTest放入app主程序目录，mxdYJBorrowTestData.csv为数据驱动文件，SMSReceiver.java为全局监听类，用来监听实际短信到达。

3. 在gradle中的配置：

android {

 defaultConfig {

...

        testInstrumentationRunner 'com.xxxx.ui.activity.Runners.runner1'
        
    }

    dexOptions {
    
        javaMaxHeapSize '2g' //分包方式
        
    }

    packagingOptions {
    
        exclude 'META-INF/INDEX.LIST' //排除文件冲突
        
    }
}

dependencies {

...

    compile files('libs/android-junit-report-1.5.8.jar')
    
    compile files('libs/android-logging-log4j-1.0.3.jar')
    
    compile files('libs/jxl.jar')
    
    compile files('libs/log4j-1.2.17.jar')
    
    compile files('libs/robotium-solo-5.2.1.jar')
    
    compile files('libs/adbForRobotium.jar')
    
    compile files('libs/mysql-connector-java-5.0.8-bin.jar')
    
    }
    
4. 在AndroidManifest.xml中的配置:

    <?xml version="1.0" encoding="utf-8"?>

<manifest xmlns:android="http://schemas.android.com/apk/res/android" package="com.XXX">

    <uses-permission android:name="android.permission.RECEIVE_SMS"/>
    
    <uses-permission android:name="android.permission.READ_SMS"/>
    
    <uses-permission android:name="android.permission.BROADCAST_SMS"/>
    
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />


  <application
  
        <instrumentation android:name="com.nonobank.ui.activity.Runners.runner1"
        
            android:targetPackage="com.xxxx" />

        <instrumentation android:name="com.xxxx.ui.activity.Runners.runner1.CommonRunner"
        
            android:targetPackage="com.xxxx" />

        //自动化测试用,调用的源码下的添加的一个监听类
        
        <receiver android:name=".receiver.SMSReceiver">
        
            <intent-filter android:priority="2147483647">
            
                <action android:name="android.provider.Telephony.SMS_RECEIVED" />
                
            </intent-filter>
            
        </receiver>
        
   </application>
