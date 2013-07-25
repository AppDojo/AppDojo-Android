package com.example.myfirstapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class Register extends Activity 
{
	JSONObject response;
	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy); 
        
        setContentView(R.layout.activity_register);
        Log.w("test", "inside");
    }
	
	public void cancel(View can)
    {
    	Intent cancel = new Intent(this, MainActivity.class);
    	startActivity(cancel);
    }
	
	public void finish(View fin) throws JSONException
	{
		JSONObject json = new JSONObject();
		JSONObject json2 = new JSONObject();
				
		EditText f_name_field = (EditText) findViewById(R.id.editFirstReg);
		String f_name = f_name_field.getText().toString();
		EditText l_name_field = (EditText) findViewById(R.id.editLastReg);
		String l_name = l_name_field.getText().toString();
		EditText email_field = (EditText) findViewById(R.id.editEmailReg);
    	String email = email_field.getText().toString();
    	EditText pass_field = (EditText) findViewById(R.id.editPasswordReg);
    	String password = pass_field.getText().toString();
    	
    	json.put("first_name", f_name);
    	json.put("last_name", l_name);
    	json.put("email", email);
    	json.put("password", password);
    	json.put("password_confirmation", password);
    	json2.put("user", json);
    	String sigh = json2.toString();
    	
    	Log.w("test", sigh);
    	
    	String[] args = new String[2];
    	args[0] = "https://appdojo-api.herokuapp.com/api/v1/users.json";
    	args[1] = sigh;
    	
    	new FallBackRequest().execute(args);
        
	}
	
	private class FallBackRequest extends AsyncTask<String, Void, JSONObject>
	{
		JSONObject response = null;

		@Override
		protected JSONObject doInBackground(String... arg0) 
		{
			Log.w("test", arg0[0]);
			Log.w("test", arg0[1]);
			JSONObject json = null;
			
			try 
			{
			    json = new JSONObject(arg0[1]);
			} 
			catch (JSONException e) 
			{
				e.printStackTrace();
				return null;
			}
			
			HttpClient client = new DefaultHttpClient();
	        HttpConnectionParams.setConnectionTimeout(client.getParams(), 20000); //Timeout Limit
	        HttpResponse response;
			
			try {
	            HttpPost post = new HttpPost(arg0[0]);
	            StringEntity se = new StringEntity(json.toString());  
	            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	            post.setEntity(se);
	            response = client.execute(post);

	            /*Checking response */
	            if(response!=null)
	            {
	                InputStream in = response.getEntity().getContent(); //Get the data in the entity
	                
	                BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8")); 
	                StringBuilder responseStrBuilder = new StringBuilder();

	                String inputStr;
	                while ((inputStr = streamReader.readLine()) != null)
	                {
	                	Log.w("test", inputStr);
	                    responseStrBuilder.append(inputStr);
	                }
	                JSONObject jobj = new JSONObject(responseStrBuilder.toString());
	                this.response = jobj;
	                Log.w("test", this.response.toString());
	                return this.response;
	            }

	        } catch(Exception e) {
	            e.printStackTrace();
	            Log.w("test", "What a massive fail!", e);
	        }
			
			return null;
		}

		protected void onPostExecute(JSONObject json) 
		{
			JSONObject user = null;
			
	        if(json != null)
	        {
	        	Log.w("test", "success");
	        	
	        	try 
	        	{
					user = json.getJSONObject("user");
					Log.w("test", user.getString("last_name"));
					Session.set_session(user);
					Intent login = new Intent();
					login.setClass(Register.this, Home.class);
			    	startActivity(login);
				} 
	        	catch (JSONException e) 
	        	{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
		
	}
}
