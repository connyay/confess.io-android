package com.connyay.confess.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Common {

    public static String dateFormat(String dateIn) {
	SimpleDateFormat fromJson = new SimpleDateFormat("yyyy-MM-dd");
	int dayNum = Integer.parseInt(dateIn.substring(9, 10));
	String dayNumberSuffix = getDayNumberSuffix(dayNum);
	SimpleDateFormat correctFormat = new SimpleDateFormat("MMM d'"
		+ dayNumberSuffix + "', yyyy");
	String formattedDate;
	try {
	    formattedDate = correctFormat.format(fromJson.parse(dateIn
		    .substring(0, 10)));
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    formattedDate = "Problem with date :[";
	}
	return formattedDate;

    }

    private static String getDayNumberSuffix(int day) {
	if (day >= 11 && day <= 13) {
	    return "th";
	}
	switch (day % 10) {
	case 1:
	    return "st";
	case 2:
	    return "nd";
	case 3:
	    return "rd";
	default:
	    return "th";
	}
    }
}
