package com.jimmy510s.simplecalendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;


/**
 * This class has all the functions that each calendar button can do. From here you can change or add the drawables
 * and the properties of the button
 * 
 * @author jimmy510s
 *
 */
public class SimpleCalendarButton extends Button
{		
	public SimpleCalendarButton(Context context)
    {
	    super(context);
    }

	public SimpleCalendarButton(Context context, AttributeSet attrs)
    {
	    super(context, attrs);
    }

	public SimpleCalendarButton(Context context, AttributeSet attrs, int defStyle)
    {
	    super(context, attrs, defStyle);
    }
	
	/**
	 * Draws the button to be displayed as the previous or the next month
	 */
	public void isOutOfMonth()
	{
		super.setBackgroundResource(R.drawable.normal_date_notpressed);
		super.setTextColor(Color.LTGRAY);
		super.setEnabled(false);
	}
	
	/**
	 * Draws the button to be displayed as the current date
	 */
	public void isToday()
	{
		super.setTypeface(null, Typeface.BOLD);
	}

	/**
	 * Draws the button to be displayed as a weekend button. There are two variations of the weekend buttons
	 * so this is we we use the boolean
	 * 
	 * @param a_isForOutsideMonth -> defines of the button is for the current month
	 */
	public void isWeekend(boolean a_isForOutsideMonth)
	{
		super.setBackgroundResource(R.drawable.holiday_selector);
		if(a_isForOutsideMonth)
		{
			super.setTextColor(Color.LTGRAY);
		}
		else
		{
			super.setTextColor(Color.BLACK);
		}
	}
	
	/**
	 * Draws the button as a normal date. No weekend or anything. If you want to make the button to be drawn for
	 * any other  you can have this method to draw just a nomral date 
	 */
	public void isNormalDate()
	{
		super.setBackgroundResource(R.drawable.normal_date_selector);
		super.setTextColor(Color.BLACK);
		super.setTypeface(null, Typeface.NORMAL);
		super.setEnabled(true);
	}
	
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}