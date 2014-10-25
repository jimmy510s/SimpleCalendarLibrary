package com.jimmy510s.simplecalendar;

import java.util.Calendar;
import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

/**
 * This class is responsible for drawing each button of the calendar. On the constructor initializes some vars
 * and then in the getView method decides what the button should look like 
 * 
 * @author jimmy510s
 *
 */
public class SimpleCallendarAdapter extends BaseAdapter implements OnClickListener
{
	private LayoutInflater m_inflater;
	private Context m_context;
	private Calendar m_calendar, m_startingCalendar;
	private int m_calOffset;
	private int m_daysInPrevMonth;
	private String  m_dateStringToDraw, m_currentDate;

	public SimpleCallendarAdapter(Context a_context, Calendar a_calendar)
	{
		// Initializations
		super();
		
		m_context = a_context;
		
		m_currentDate = CalendarUtils.dateForCalendarControl(Calendar.getInstance());

		m_calendar = Calendar.getInstance();
		m_calendar.setTimeInMillis(a_calendar.getTimeInMillis());

		this.m_inflater = (LayoutInflater) m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// find the offset of this month
		m_calOffset = CalendarUtils.getDaysAfterStartOfWeek(m_calendar);
		
		// find how many days the previous month had
		m_daysInPrevMonth = CalendarUtils.getDaysOfPreviousMonth(m_calendar);

		initStartingCalendar(m_calendar);
	}

	
	@Override
	public int getCount()
	{
		// we set 42 because this is the number of days that will be shown. 5 weeks.
		return 42;
	}

	@Override
	public Object getItem(int arg0)
	{
		return null;
	}

	@Override
	public long getItemId(int arg0)
	{
		return 0;
	}

	private static class ViewHolder
	{
		SimpleCalendarButton button;
	}

	@Override
	public View getView(int a_position, View a_convertView, ViewGroup a_parent)
	{
		ViewHolder l_holder;
		if (a_convertView == null)
		{
			a_convertView = m_inflater.inflate(R.layout.simple_calendar_cell, null);
			l_holder = new ViewHolder();

			l_holder.button = (SimpleCalendarButton) a_convertView.findViewById(R.id.calendar_cell_button);
			a_convertView.setTag(l_holder);
		}
		else
		{
			l_holder = (ViewHolder) a_convertView.getTag();
		}
		l_holder.button.isNormalDate();
		
		Calendar l_currentDate = Calendar.getInstance();
		l_currentDate.setTimeInMillis(m_startingCalendar.getTimeInMillis());
		l_currentDate.add(Calendar.DAY_OF_MONTH, a_position);
		
		// convert the date we are about to draw in string in order to do some
		// functionalities.		
		m_dateStringToDraw = CalendarUtils.dateForCalendarControl(l_currentDate);

		// draw the date (the number)
		String l_date = String.format(Locale.US,"%02d", l_currentDate.get(Calendar.DATE));
		l_holder.button.setText(l_date);

		//draw previous and next month
		if (l_currentDate.get(Calendar.MONTH) != m_calendar.get(Calendar.MONTH))
		{
			l_holder.button.isOutOfMonth();
			drawWeekends(l_holder.button, l_currentDate, true);
			
			l_holder.button.setTextColor(Color.LTGRAY);
			
			l_holder.button.setOnClickListener(null);
		}
		//draw current month
		else 
		{
			l_holder.button.setTag(m_dateStringToDraw);
			drawWeekends(l_holder.button, l_currentDate, false);

			if (m_dateStringToDraw.equalsIgnoreCase(m_currentDate))
				l_holder.button.isToday();
			
			l_holder.button.setOnClickListener(this);
			l_holder.button.setTag(m_dateStringToDraw);

		}
		return a_convertView;
	}

	/*
	 * This method is responsible for drawing the weekend buttons.
	 */
	private void drawWeekends(SimpleCalendarButton a_btn, Calendar a_calendar,boolean a_isOutOfMonth)
	{
		Calendar l_tempCal = Calendar.getInstance();
		l_tempCal.setTimeInMillis(a_calendar.getTimeInMillis());

		if (l_tempCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || l_tempCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
		{
			a_btn.isWeekend(a_isOutOfMonth);
		}
	}

	/*
	 * The starting calendar is set to the date that we have to start drawing in the grid view. Every time that the getView is called with a position
	 * we add that position number to the starting calendar date so we know exactly what date we are drawing.
	 */
	private void initStartingCalendar(Calendar a_calendar)
	{
		m_startingCalendar = Calendar.getInstance();
		m_startingCalendar.setTimeInMillis(a_calendar.getTimeInMillis());
		m_startingCalendar.add(Calendar.MONTH, -1);
		m_startingCalendar.set(Calendar.DAY_OF_MONTH, m_daysInPrevMonth - m_calOffset + 1);
	}


	@Override
	public void onClick(View v)
	{
		//do what ever you want with your clicks here!!!
		Toast.makeText(m_context, v.getTag().toString(), Toast.LENGTH_SHORT).show();
	}
}