package com.example.demowebapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WebView webview=(WebView) findViewById(R.id.web_items);
		webview.loadUrl("http://m.yebhi.com/index.aspx?101");
		webview.getSettings().setJavaScriptEnabled(true);
	    webview.getSettings().setDomStorageEnabled(true); // I think you will need this one
	    webview.getSettings().setPluginState(PluginState.ON);
	    webview.getSettings().setAllowFileAccess(true);
	    webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);// no need I think

	    webview.getSettings().setLoadWithOverviewMode(true);
	    webview.getSettings().setUseWideViewPort(true);
	    webview.getSettings().setBuiltInZoomControls(true);
	    webview.setInitialScale(1);
	    int SDK_INT = android.os.Build.VERSION.SDK_INT;
	    if (SDK_INT > 16) {
	    	webview.getSettings().setMediaPlaybackRequiresUserGesture(false);
	    }
	   webview.setWebChromeClient (new WebChromeClient (){
			@Override
			public View getVideoLoadingProgressView() {
				// TODO Auto-generated method stub
				return super.getVideoLoadingProgressView();
			}
			@Override
			public void onReceivedTouchIconUrl(WebView view, String url,
					boolean precomposed) {
				if(!url.contains("yebhi.com")){
					Toast.makeText(MainActivity.this, url, Toast.LENGTH_LONG).show();
				}
				super.onReceivedTouchIconUrl(view, url, precomposed);
			}
		});
	   webview.setWebViewClient(new WebViewClient (){
		   @Override
		    public boolean shouldOverrideUrlLoading(WebView wv, String url) {
		        if (url.contains("http://im6.ybndc.com/")) { 
		        	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=OxsKcx1IwI8"));
		            startActivity(intent); 
		            return true; // we handled the url loading
		        }

		        return false; // let WebView handle this event
		    }
	   });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
