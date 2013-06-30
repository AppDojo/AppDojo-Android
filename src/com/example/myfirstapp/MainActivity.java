package com.example.myfirstapp;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import com.loopj.android.http.*;

/*
 * There is already a popular open source library for dealing with
 * asynchronous requests that can be found here : http://loopj.com/android-async-http/
 * Below is an example of how to use it. Go to the site for more examples
 */

/*AsyncHttpClient client = new AsyncHttpClient();
client.get("https://appdojo-api.herokuapp.com/users", new AsyncHttpResponseHandler() {
    @Override
    public void onSuccess(String response) {
        TextView tv1 = (TextView)findViewById(R.id.textView1);
        tv1.setText(response);
    }
});*/

public class MainActivity extends Activity implements OnClickListener 
{

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        Button btnLogin = (Button) findViewById(R.id.buttonLogin);
       
        btnLogin.setOnClickListener(this);
    }
    
    
    public void onClick(View w) 
    {
    	Intent act2 = new Intent(this, screen2.class);
    	startActivity(act2);
    }
    
}
