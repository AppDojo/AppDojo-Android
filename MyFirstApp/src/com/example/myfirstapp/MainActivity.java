package com.example.myfirstapp;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;


public class MainActivity extends Activity implements OnClickListener {

	
	
 
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
   
        Button btnLogin = (Button) findViewById(R.id.buttonLogin);
       
        btnLogin.setOnClickListener(this);
		
    }
    
    
    public void onClick(View w) {
    	
    	
    	Intent act2 = new Intent(this, screen2.class);
    	startActivity(act2);
    	
    }
    
}
