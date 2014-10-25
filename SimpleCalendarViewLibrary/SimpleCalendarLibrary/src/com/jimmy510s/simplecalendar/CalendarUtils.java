package com.jimmy510s.simplecalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * This class has the base utilities that the calendar needs
 * 
 * @author jimmy510s
 *
 */
public class CalendarUtils
{

	/**
	 * Calculates the number of days the are before the start of the given calendar object (given month)
	 * 
	 * @param a_calendar -> given month
	 * @return l_result -> the number of days
	 */
	public static int getDaysAfterStartOfWeek(Calendar a_calendar)
	{
		int l_result = -1;
		Calendar l_tempCal = Calendar.getInstance();
		l_tempCal.setTimeInMillis(a_calendar.getTimeInMillis());
		l_tempCal.set(Calendar.DAY_OF_MONTH, 1);
		int l_day = l_tempCal.get(Calendar.DAY_OF_WEEK);

		switch (l_day)
		{
			case Calendar.MONDAY:
				l_result = 0;
				break;
			case Calendar.TUESDAY:
				l_result = 1;
				break;
			case Calendar.WEDNESDAY:
				l_result = 2;
				break;
			case Calendar.THURSDAY:
				l_result = 3;
				break;
			case Calendar.FRIDAY:
				l_result = 4;
				break;
			case Calendar.SATURDAY:
				l_result = 5;
				break;
			case Calendar.SUNDAY:
				l_result = 6;
				break;
		}
		return l_result;
	}

	/**
	 * Calculates the number of days that the given month has
	 * 
	 * @param a_calendar -> given month
	 * @return number of days
	 */
	@SuppressWarnings("static-access")
    public static int getDaysOfMonth(Calendar a_calendar)
	{
		Calendar l_tempCal = Calendar.getInstance();
		l_tempCal.setTimeInMillis(a_calendar.getTimeInMillis());
		l_tempCal.set(Calendar.DAY_OF_MONTH, 1);
		return l_tempCal.getActualMaximum(l_tempCal.DAY_OF_MONTH);
	}


	/**
	 * Calculates the number of days that the previous moth of the given one has
	 * 
	 * @param a_calendar -> given month
	 * @return days of the previous month
	 */
	public static int getDaysOfPreviousMonth(Calendar a_calendar)
	{
		Calendar l_tempCal = Calendar.getInstance();
		l_tempCal.setTimeInMillis(a_calendar.getTimeInMillis());
		l_tempCal.add(Calendar.MONTH, -1);
		return getDaysOfMonth(l_tempCal);
	}

	/**
	 * Draws the given button to be displayed as a weekend button.
	 * 
	 * @param a_btn -> button to be drawn
	 * @param a_calendar -> given month
	 * @param a_isOutOfMonth -> boolean that specifies if the date is for the current moth or the previous 
	 * 							or the next one
	 */
	public static void drawWeekends(SimpleCalendarButton a_btn, Calendar a_calendar,boolean a_isOutOfMonth)
	{
		Calendar l_tempCal = Calendar.getInstance();
		l_tempCal.setTimeInMillis(a_calendar.getTimeInMillis());

		if (l_tempCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || l_tempCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
		{
			a_btn.isWeekend(a_isOutOfMonth);
		}
	}
	
	/**
	 * Converts the given calendar object to a string according to one predefined format
	 * 
	 * @param a_givenDate -> given month
	 * @return String -> calendar object formated to string
	 */
	public static String dateForCalendarControl(Calendar a_givenDate)
	{
		String l_format = "yyyyMMdd";
		
		SimpleDateFormat l_sdf = new SimpleDateFormat(l_format, Locale.getDefault());
		return l_sdf.format(a_givenDate.getTime());
	}
	
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static int getScreenDimensions(Context a_context, boolean a_width)
	{
		int width;
		int height;
		WindowManager wm = (WindowManager) a_context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		try
		{
			display.getSize(size);
			width = size.x;
			height = size.y;
		}
		catch (java.lang.NoSuchMethodError ignore)
		{
			width = display.getWidth();
			height = display.getHeight();
		}

		if (a_width)
			return width;
		else
			return height;
	}
	
	/**
	 * Convert the given calendar object (month) to a string to be placed as the title of the calendar
	 * 
	 * @param a_calendar -> given calendar
	 * @return String -> title of the calendar
	 */
	public static String getCalendarControlsTitle(Calendar a_calendar)
	{
		SimpleDateFormat l_sdf = new SimpleDateFormat("MMM yyyy", Locale.getDefault());
		return l_sdf.format(a_calendar.getTime());
	}
}
