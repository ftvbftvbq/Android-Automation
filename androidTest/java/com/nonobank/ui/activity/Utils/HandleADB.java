package com.nonobank.ui.activity.Utils;

/**
 * Created by edison on 16/5/18.
 */
public class HandleADB {
    public void executeCommand(String[] args) {
        ProcessBuilder pb = new ProcessBuilder(args);
        Process pc;
        try {
            pc = pb.start();
            pc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}