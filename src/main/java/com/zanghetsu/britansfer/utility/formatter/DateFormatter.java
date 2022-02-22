package com.zanghetsu.britansfer.utility.formatter;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateFormatter {

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public Date validator(String string){
        Date date;
        try{
            date = formatter.parse(string);
        } catch (ParseException e){
            return null;
        }
        return date;
    }


}
