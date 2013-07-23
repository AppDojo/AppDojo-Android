package com.example.myfirstapp;

import com.example.myfirstapp.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

public class Register extends Activity 
{
	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
    }
	
	public void cancel(View can)
    {
    	Intent cancel = new Intent(this, MainActivity.class);
    	startActivity(cancel);
    }
	
	public void finish(View fin)
	{
		// Register here
	}
}
