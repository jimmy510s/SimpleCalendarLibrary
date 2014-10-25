package com.example.calendarexample;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jimmy510s.simplecalendar.SimpleCalendarView;

public class MainActivity extends Activity
{
	SimpleCalendarView m_calendarView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    
	    m_calendarView = (SimpleCalendarView)findViewById(R.id.calendarView);
	    m_calendarView.setData(Calendar.getInstance());
	    
	    /*
	     * these two buttons are just some examples of the methods reset calendar and moveToMonth
	     * use these methods wherever you want (actionbar etc) ;)
	     */
	    Button reset = (Button)findViewById(R.id.button1);
	    reset.setOnClickListener(new OnClickListener()
	    {
			
			@Override
			public void onClick(View v)
			{
				m_calendarView.resetCalendar();
				
			}
		});
	    
	    Button move = (Button)findViewById(R.id.button2);
	    move.setOnClickListener(new OnClickListener()
	    {
			
			@Override
			public void onClick(View v)
			{
				Calendar l_cal = Calendar.getInstance();
				l_cal.set(2016, Calendar.NOVEMBER, 23);
				m_calendarView.moveToMonth(l_cal);
				
			}
		});
	}
}
