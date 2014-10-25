package com.jimmy510s.simplecalendar;

import java.util.Calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * This class is the starting class of the calendar and it contains the hole view. The grid with the dates,
 * the next and previous month buttons and the display date of the calendar. Also has an interface to get the
 * clicks for the adapter and change the calendar.
 * 
 * @author jimmy510s
 *
 */
public class SimpleCalendarView extends FrameLayout implements OnClickListener
{
	private Context m_context;
	private GridView m_gridView;
	private ImageButton m_btnPrevMonth, m_btnNextMonth;
	private TextView m_title;
	private SimpleCallendarAdapter m_adapter;
	private Calendar m_calendar,m_initialMonth;
	
	private SimpleCalendarClickListener m_clickListener;
	
	//constructors for all the cases
	public SimpleCalendarView(Context a_context)
    {
	    super(a_context);
	    m_context = a_context;
	    initialize();
    }

	public SimpleCalendarView(Context a_context, AttributeSet a_attrs)
	{
		super(a_context, a_attrs);
	    m_context = a_context;
	    initialize();
	}
	
	public SimpleCalendarView(Context a_context, AttributeSet a_attrs, int a_defStyle)
	{
		super(a_context, a_attrs, a_defStyle);
	    m_context = a_context;
	    initialize();
	}
	
	/*
	 * you can implement this interface to you calling activity to be notified when moved to previous or
	 * next month
	 */
	public interface SimpleCalendarClickListener 
    {
        public void onNextMonthClicked();
        public void onPreviousMonthClicked();
    }

	private void initialize()
	{
		if(isInEditMode())
		{
			return;
		}
		else
		{
			//just the basic stuff
			LayoutInflater l_inflater = (LayoutInflater) m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View l_view = l_inflater.inflate(R.layout.simple_calendar_view,(ViewGroup)SimpleCalendarView.this,false);
			this.addView(l_view);
			
			//declaration of the base widgets of this control
			m_gridView = (GridView)findViewById(R.id.calendar_view_gridView);
			m_btnPrevMonth = (ImageButton)findViewById(R.id.calendar_view_prev_month);
			m_btnPrevMonth.setOnClickListener(this);
			m_btnNextMonth  = (ImageButton)findViewById(R.id.calendar_view_next_month);
			m_btnNextMonth.setOnClickListener(this);
			m_title = (TextView)findViewById(R.id.calendar_view_title);
		}		
	}
	
	/*
	 * This method should be called from your activity after you have a valid caledarView object in order to
	 * set the calendar to the initial state
	 */
	public void setData(Calendar a_startingMonth)
	{
		m_calendar = Calendar.getInstance();
		m_initialMonth = Calendar.getInstance();
		
		if(a_startingMonth != null)
			m_calendar.setTimeInMillis(a_startingMonth.getTimeInMillis());
		else
			m_calendar.set(Calendar.DAY_OF_MONTH, 1);
				
		writeTitle();
		
		//save the initial month
		m_initialMonth.setTimeInMillis(m_calendar.getTimeInMillis());
		
		m_adapter = new SimpleCallendarAdapter(m_context,m_calendar);
		m_gridView.setAdapter(m_adapter);
	}
	

	@Override
	public void onClick(View l_view) 
	{
		if(l_view.getId() == R.id.calendar_view_next_month)
		{
			incrementMonth();
		}
		else if(l_view.getId() == R.id.calendar_view_prev_month)
		{
			decrementMonth();
		}
	}
	
	private void incrementMonth()
	{
		SimpleCalendarClickListener l_listener = getClickListener();
		if(l_listener != null)
		{
			l_listener.onNextMonthClicked();
		}
		m_calendar.add(Calendar.MONTH, +1);		
		
		writeTitle();
		
		m_adapter = new SimpleCallendarAdapter(m_context, m_calendar);
		m_gridView.setAdapter(m_adapter);
	}
	
	private void decrementMonth()
	{
		SimpleCalendarClickListener l_listener = getClickListener();
		if(l_listener != null)
		{
			l_listener.onPreviousMonthClicked();
		}
		m_calendar.add(Calendar.MONTH, -1);
		
		writeTitle();
		
		m_adapter = new SimpleCallendarAdapter(m_context, m_calendar);
		m_gridView.setAdapter(m_adapter);
	}
	
	private void writeTitle()
	{
		m_title.setText(CalendarUtils.getCalendarControlsTitle(m_calendar));
	}
	
	public SimpleCalendarClickListener getClickListener() 
	{
		return m_clickListener;
	}

	public void setClickListener(SimpleCalendarClickListener m_clickListener) 
	{
		this.m_clickListener = m_clickListener;
	}
	
	public void moveToMonth(Calendar a_calendar)
	{
		if(a_calendar != null)
		{
			m_calendar.setTimeInMillis(a_calendar.getTimeInMillis());
			
			writeTitle();
			
			m_adapter = new SimpleCallendarAdapter(m_context, m_calendar);
			m_gridView.setAdapter(m_adapter);
		}
	}
	
	public void resetCalendar()
	{
		m_calendar.setTimeInMillis(m_initialMonth.getTimeInMillis());
		
		writeTitle();
		
		m_adapter = new SimpleCallendarAdapter(m_context, m_initialMonth);
		m_gridView.setAdapter(m_adapter);
	}
}