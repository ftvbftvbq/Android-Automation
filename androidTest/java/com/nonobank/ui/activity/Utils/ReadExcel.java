package com.nonobank.ui.activity.Utils;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Created by edison on 16/3/8.
 */
public class ReadExcel {

    Workbook wb;
    WritableWorkbook copy ;
    WritableSheet sheet ;
    Boolean FuntionResult = false;
    Cell rowData[] = null;
    public String[] FirstValue;
    public String[] SecondValue;
    public String[] ExpectedResult;
    public String[] CommentContent;
    public int rowCount = '0';
    WorkbookSettings ws = null;
    Workbook workbook = null;
    Sheet s = null;
    String[] TestResults;

    public  void getTestDataFile(String fileName) {
//        String fileName = "/storage/emulated/0/mxdLoginTestData.csv";
        try {
            File file = new File(fileName);
            if (null != file) {
                Log.i("getTestData", file.length() + "");
            }
            InputStream in = new FileInputStream(file);
            readTestData(in);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readTestData(InputStream fileInputStream) throws Exception {
        ws = null;
        workbook = null;
        s = null;

        int columnCount = '0';
        int totalSheet = 0;
        String firstSheet = "TestCases";
        String secondSheet = "TestScenarios";

        try {
            ws = new WorkbookSettings();
            ws.setLocale(new Locale("en", "EN"));
            workbook = Workbook.getWorkbook(fileInputStream, ws);

            totalSheet = workbook.getNumberOfSheets();
            if (totalSheet > 0) {

                if (!workbook.getSheet(0).getName().equals(firstSheet)) {
                    System.out.println("contents are not fine");
                }

                if (!workbook.getSheet(1).getName().equals(secondSheet)) {
                    System.out.println("contents are not fine");
                }
            } else
                System.out.println("There is not any sheet available.");

            s = workbook.getSheet(1);

            rowCount = s.getRows();
            FirstValue = new String[rowCount]; //First Value array size
            SecondValue = new String[rowCount];//Second Value array size
            ExpectedResult = new String[rowCount];
            CommentContent = new String[rowCount];

            columnCount = s.getColumns();
            rowData = s.getRow(0);

            if (rowData[0].getContents().length() != 0) {
                for (int i = 1; i < rowCount; i++) {

                    rowData = s.getRow(i);

                    if (rowData[5].getContents().equals("Yes")) {
                        System.out.println("Executed: " + rowData[1].getContents());

                        FirstValue[i] = rowData[2].getContents().toString();
                        System.out.println("1: "+FirstValue[i].toString());

                        SecondValue[i] = rowData[3].getContents().toString();
                        System.out.println("2: "+SecondValue[i].toString());

                        ExpectedResult[i] = rowData[4].getContents().toString();
                        System.out.println("3: "+ExpectedResult[i].toString());

                        CommentContent[i] = rowData[6].getContents().toString();

                        System.out.println("4: "+CommentContent[i].toString());
                    } else {
                        System.out.println( "We will skip " + rowData[1].getContents());
                    }
                }
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("SdCardPath")
    public void writeResults(String[] results, String Url) {
        String filePath = Environment.getExternalStorageDirectory() + File.separator + Url;
        System.out.println("filePath: "+ filePath);
        try {
            File file = new File(filePath);
//            file.createNewFile();
            if(file.createNewFile()){
                System.out.println("文件创建成功");
            }
            FileOutputStream fos = new FileOutputStream(file);
            for (int i = 1; i < results.length; i++) {
                fos.write(CommentContent[i].toString().getBytes());
                System.out.println("到了这里");
                System.out.println("HERE"+CommentContent[i].toString().getBytes() + "" );
                fos.write(results[1].toString().getBytes());
                fos.write("\r\n".getBytes());
                System.out.println("HERE2"+results[1].toString().getBytes() + "" );
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}