package com.example.myfirstapp;

import org.json.JSONObject;

public class Session 
{
	public static String first_name = null;
	public static String last_name = null;
	public static String email = null;
	public static String session_key = null;
	
	public static void set_session(JSONObject user)
	{
		first_name = user.optString("first_name");
		last_name = user.optString("last_name");
		email = user.optString("email");
		session_key = user.optString("authentication_token");
	}
	
}
