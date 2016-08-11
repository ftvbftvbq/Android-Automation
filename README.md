Android-Automation
==================

Android Automation:

1. 使用robotium进行自动化，跨进程使用adbForRobotium

2. 在gradle中的配置：
3. 
 defaultConfig {

...
        testInstrumentationRunner 'com.nonobank.ui.activity.Runners.runner1'
        
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


3. 在AndroidManifest.xml中的配置：

    //自动化测试用
    <uses-permission android:name="android.permission.RECEIVE_SMS"/>
    <uses-permission android:name="android.permission.READ_SMS"/>
    <uses-permission android:name="android.permission.BROADCAST_SMS"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
...

  <application
...

        //自动化测试用
        <instrumentation android:name="com.nonobank.ui.activity.Runners.runner1"
            android:targetPackage="com.nonobank" />

        <instrumentation android:name="com.nonobank.ui.activity.Runners.runner1.CommonRunner"
            android:targetPackage="com.nonobank" />

        //自动化测试用,调用的源码下的添加的一个监听类
        <receiver android:name=".receiver.SMSReceiver">
            <intent-filter android:priority="2147483647">
                <action android:name="android.provider.Telephony.SMS_RECEIVED" />
            </intent-filter>
        </receiver>
   </application>
