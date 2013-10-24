/*
 * Crystal McDonald
 * MDF 3
 * 1310
 * Web Activity
 * 
 * Set Sail Browser is a custom based Nautical Theme browser.
 */

package com.cm.melody;

import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;


public class WebActivity extends Activity implements OnClickListener{
	WebView myWebView;
	EditText urlText;
	Button goBack, goForward, goButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//makes activity full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
		                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_web);
		//getting webView
		myWebView = (WebView)findViewById(R.id.musicalWebView);
		
		/*
		 * Screen Control: Optimal Usability: loads views zoomed out
		 */
		myWebView.getSettings().setUseWideViewPort(true);
		myWebView.getSettings().setLoadWithOverviewMode(true);
		
		/*
		 * Helps application perform action intended when clicking links
		 * allows links to load in my custom web browser
		 */
		myWebView.setWebViewClient(new CustomBrowserView());
		
		
		//set up onclick listeners for buttons
		findViewById(R.id.goBack).setOnClickListener(this);
		findViewById(R.id.goForward).setOnClickListener(this);
		
		//gets reference to the intent 
		Intent intent = getIntent();
		//extracts the uri data from the intent object and converts Uri data to a URL Object
		Uri data = intent.getData();
		URL url = null;
		try {
			url = new URL(data.getScheme(), data.getHost(), data.getPath());
		}catch(Exception e){
			e.printStackTrace();	
		}
		//Loads the URL into myWebView, converting it into a string
		WebView myWebView = (WebView) findViewById(R.id.musicalWebView);
		myWebView.loadUrl(url.toString());
	}
	
	//set up switch for buttons in case I add more functionality later
	@Override
	public void onClick(View v) {
		// set up switch case for buttons
		switch (v.getId()){
			
		/*
		 * Custom back button functionality
		 */
		case R.id.goBack:
			if(myWebView.canGoBack()){
				myWebView.goBack();
			}
			break;
			/*
			 * Custom forward button functionality
			 */	
		case R.id.goForward:
			if(myWebView.canGoForward()){
				myWebView.goForward();
				break;
			}
		
		}
	}

}//end