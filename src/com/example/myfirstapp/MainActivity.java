package com.example.myfirstapp;


import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;
import com.loopj.android.http.*;

public class MainActivity extends Activity 
{

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }
    
    
    /*public void onClick(View w) 
    {
    	Intent act2 = new Intent(this, screen2.class);
    	startActivity(act2);
    }*/
    
    public void login(View log)
    {
    	EditText email_field = (EditText) findViewById(R.id.editEmail);
    	String email = email_field.getText().toString();
    	EditText pass_field = (EditText) findViewById(R.id.editPassword);
    	String password = pass_field.getText().toString();
    	
    	RequestParams params = new RequestParams();
    	params.put("email", email);
    	params.put("password", password);
    	
    	Requests.post("api/v1/users/sign_in", params, new AsyncHttpResponseHandler() {
    	    @Override
    	    public void onSuccess(String response) 
    	    {
    	    	JSONObject json;
    	    	JSONObject user = null;
    	    	
    	    	try 
    	    	{
    	    		Log.w("test", response);
					json = new JSONObject(response);
					user = json.getJSONObject("user");
					Log.w("test", user.getString("last_name"));
					Session.set_session(user);
					Intent login = new Intent();
					login.setClass(MainActivity.this, Home.class);
			    	startActivity(login);
				} 
    	    	catch (JSONException jsone) 
    	    	{
					Log.w("test", "unable to convert string to json");
					return;
				}
    	    }
    	    
    	    @Override
    	    public void onFailure(Throwable e) 
    	    {
    	        Log.w("test", "OnFailure!", e);
    	    }
    	});
    }
    
    public void register(View reg)
    {
    	Intent register = new Intent(this, Register.class);
    	startActivity(register);
    }
    
}
