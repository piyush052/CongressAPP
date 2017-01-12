package hexa.webandrioz.com.congress.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by gipsy_danger on 26/11/16.
 */

public class DateConversion {
    public String DateConversion(String str) {
        String res="";
        // 2016-09-29
        String [] s=str.split("-");
        switch (s[1]){
            case "01":res="Jan "+s[2]+","+s[0];break;
            case "02":res="Feb "+s[2]+","+s[0];break;
            case "03":res="Mar "+s[2]+","+s[0];break;
            case "04":res="Apr "+s[2]+","+s[0];break;
            case "05":res="May "+s[2]+","+s[0];break;
            case "06":res="Jun "+s[2]+","+s[0];break;
            case "07":res="Jul "+s[2]+","+s[0];break;
            case "08":res="Aug "+s[2]+","+s[0];break;
            case "09":res="Sep "+s[2]+","+s[0];break;
            case "10":res="Oct "+s[2]+","+s[0];break;
            case "11":res="Nov "+s[2]+","+s[0];break;
            case "12":res="Dec "+s[2]+","+s[0];break;
            default: res=str;

        }

        return res;
    }
    public int term(String start, String end) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = myFormat.format(new Date());
        int x = 0;
        try {
            Date date1 = myFormat.parse(start);
            Date date2 = myFormat.parse(end);
            Date current = myFormat.parse(currentDateandTime);
            long diff = date2.getTime() - date1.getTime();
            long div = current.getTime() - date1.getTime();
            x = (int) ((TimeUnit.DAYS.convert(div, TimeUnit.MILLISECONDS)*100)/ TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            Log.e("Days: " , ""+TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            Log.e("Days: " , ""+TimeUnit.DAYS.convert(div, TimeUnit.MILLISECONDS));
            Log.e("Days: " , ""+x);
        } catch (ParseException e) {
            e.printStackTrace();


        }
        return x;

    }
}

