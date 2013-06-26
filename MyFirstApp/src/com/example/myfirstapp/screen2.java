package com.example.myfirstapp;

import java.util.Calendar;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;
public class screen2 extends Activity {

	private TextView tvDisplayDate;
	private int year;
	private int month;
	private int day;
	
	
   	 
		
		protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.checkin);
		
	        setCurrentDateOnView();	      
	        
		}
		
		
		
		public void setCurrentDateOnView() {
		tvDisplayDate = (TextView) findViewById(R.id.tvDate);		
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
 
		// set current date into textview
		tvDisplayDate.setText(new StringBuilder()
			// Month is 0 based, just add 1
			.append(month + 1).append("-").append(day).append("-")
			.append(year).append(" "));
 
	
 
	}

}
