package com.infoshare.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	
	 private static final DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault());
	 
	 public static String convertDate(Date date){
		return df.format(date);
	 }
	 
	 public static String getCurrentDate(){
		 return df.format(Calendar.getInstance().getTime());
	 }
}
