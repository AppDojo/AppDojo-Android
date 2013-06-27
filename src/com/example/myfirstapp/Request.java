package com.example.myfirstapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.util.Log;

/*
 * So far, it only does get and post
 * ex.
 *      
 *      Request req = new Request("https://appdojo-api.herokuapp.com/users", null, "get");
 *		req.start();
 *		while(!req.isComplete);
 *      my_resposne = req.response();  // returns a json object
 */

public class Request extends Thread 
{
	private String url;
	private JSONObject json;
	private String request_type;
	public JSONObject response;
	public boolean complete;
	
	public Request(String url, JSONObject json, String request_type)
	{
		this.url = url;
		this.json = json;
		this.request_type = request_type;
		this.response = null;
		this.complete = false;
	}
	
	public void request()
	{
		HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
        HttpResponse response;
        
        if(this.request_type.equalsIgnoreCase("post"))
        {
	        try {
	            HttpPost post = new HttpPost(this.url);
	            StringEntity se = new StringEntity( json.toString());  
	            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	            post.setEntity(se);
	            response = client.execute(post);
	
	            /*Checking response */
	            if(response!=null){
	                InputStream in = response.getEntity().getContent(); //Get the data in the entity
	                
	                BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8")); 
	                StringBuilder responseStrBuilder = new StringBuilder();
	
	                String inputStr;
	                while ((inputStr = streamReader.readLine()) != null)
	                    responseStrBuilder.append(inputStr);
	                JSONObject jobj = new JSONObject(responseStrBuilder.toString());
	                this.response = jobj;
	                Log.w("return", "What a success");
	                this.complete = true;
	            }
	
	        } catch(Exception e) {
	            e.printStackTrace();
	            Log.w("return", "What a massive fail!");
	            this.complete = true;
	        }
        }
        else if(this.request_type.equalsIgnoreCase("get"))
        {
	        try {
	            HttpGet get = new HttpGet();
	            get.setURI(new URI(this.url));
	            response = client.execute(get);
	
	            /*Checking response */
	            if(response!=null)
	            {
	                InputStream in = response.getEntity().getContent(); //Get the data in the entity
	                
	                BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8")); 
	                StringBuilder responseStrBuilder = new StringBuilder();
	
	                String inputStr;
	                while ((inputStr = streamReader.readLine()) != null)
	                    responseStrBuilder.append(inputStr);
	                JSONObject jobj = new JSONObject(responseStrBuilder.toString());
	                this.response = jobj;
	                Log.w("return", "What a success");
	                this.complete = true;
	            }
	
	        } catch(Exception e) {
	            e.printStackTrace();
	            Log.w("return", "What a massive fail!");
	            this.complete = true;
	        }
        }
	}
	
	public JSONObject response()
	{
		if(this.complete)
			return this.response;
		else
			return null;
	}
	
	public boolean isComplete()
	{
		return this.complete;
	}
	
	public void run()
	{
		this.request();
	}
}
