package com.nonobank.ui.activity.Utils;

import android.os.Environment;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;

import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * Created by edison on 16/7/6.
 */
public class LogUtil {

    public Logger gLogger;

    public void configLog(String URL) {
        final LogConfigurator logConfigurator = new LogConfigurator();
        logConfigurator.setFileName(Environment.getExternalStorageDirectory() + File.separator + URL);
        // Set the root log level
        logConfigurator.setRootLevel(Level.DEBUG);
        // Set log level of a specific logger
        logConfigurator.setLevel("org.apache", Level.ERROR);
        logConfigurator.configure();

        gLogger = Logger.getLogger(URL);
    }
}
