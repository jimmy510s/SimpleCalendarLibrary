
SimpleCalendarView External Library

!!!!Just two lines of code to get it working!!!!

This external library contains a simple calendarView that you can use in your projects to:
	- display a calendar
	- have functions on any calendar button
	- move to month (like any calendar has)


How to use it:

- import the library project to eclipse
- add the library as external library to your project
- add it to your xml file
	<com.jimmy510s.simplecalendar.SimpleCalendarView
        	android:id="@+id/calendarView"
        	android:layout_width="match_parent"
        	android:layout_height="wrap_content" >
    	</com.jimmy510s.simplecalendar.SimpleCalendarView>

- then added to your activity
	    m_calendarView = (SimpleCalendarView)findViewById(R.id.calendarView);
	    m_calendarView.setData(Calendar.getInstance());

for any problems / bugs / suggestions you can send me an email -> jim510s@gmail.com 