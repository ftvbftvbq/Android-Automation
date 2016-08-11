package com.nonobank.ui.activity.Utils;
import java.util.Random;


/**
 * Created by edison on 16/4/6.
 */
public class GenBnakNo {
    public static String [] prefix = {"622202100112","436742121737","622848003857"};

    public int getOSum(int oSum){
        oSum = oSum * 2;
        if(oSum < 10){
            return oSum;
        }else{
            int m = oSum%10;

            return (oSum - m)/10 + m;
        }
    }

    public String getBankCard(String prefix){
        Random random = new Random();
        String str = prefix;
        for(int i=0;i<6;i++){
            str += String.valueOf(random.nextInt(10));
        }

        int sum = 0;

        for(int i=1;i<19;i++){

            int v = Character.getNumericValue(str.charAt(i-1));

            if(i%2 == 0){
                sum += getOSum(v);
            }else{
                sum += v;
            }
        }

        str += String.valueOf(10 - sum%10);
        return str;
    }

    public String getBankCard(){
        Random random = new Random();

        String str = prefix[random.nextInt(2)];
        for(int i=0;i<6;i++){
            str += String.valueOf(random.nextInt(10));
        }

        int sum = 0;

        for(int i=1;i<19;i++){

            int v = Character.getNumericValue(str.charAt(i-1));

            if(i%2 == 0){
                sum += getOSum(v);
            }else{
                sum += v;
            }
        }

        str += String.valueOf(10 - sum%10);
        return str;
    }

    }
