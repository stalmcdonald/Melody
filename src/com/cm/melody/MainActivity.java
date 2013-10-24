/*
-Title: Melody
-By: Crystal McDonald
-For: MDF3
-Assignment: Project 4
-Term: 1310
*/

package com.cm.melody;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {
		Button browseButton;//global variables

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//makes activity full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
		
		//finding my custom view
		WebView webView = (WebView)findViewById(R.id.webView);
		//enables view to be posted to webview
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebChromeClient(new WebChromeClient());//responsible for everything browser UI specific
		
		//referencing the www files (Html, CSS, and JS)
		webView.loadUrl("file:///android_asset/www/index.html");//responsible for everything that is related to the rendering of the web content
		
		browseButton = (Button) findViewById(R.id.browseButton);
		
		browseButton.setOnClickListener(new OnClickListener() {//setup onclick listener
		
		/*
		 * Implicit Intent: Launches Web Browsers to open website
		 */
			public void onClick(View webview) {
				// sends user to site that shows more information on sailing in San Francisco
	Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.teoria.com/tutorials/reading/12-notes.php"));
	startActivity(viewIntent); 
	playsound();
};	
		});
	}
	
	
	
	
	@JavascriptInterface
	private void playsound() {

		        try {
		        	//set up MediaPlayer    
			        MediaPlayer mp = new MediaPlayer();
		        	//grabbing soundfile from assets
		            AssetFileDescriptor descriptor = getAssets().openFd("piano_c.mp3");
		          
		            mp.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
		            descriptor.close();
		            mp.prepare();
		            mp.start();
		        } catch (Exception e) {
		            e.printStackTrace();
		            Log.e("AssetFileDescriptor", e.toString());
		            playsound();
		        }
		}
	



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
