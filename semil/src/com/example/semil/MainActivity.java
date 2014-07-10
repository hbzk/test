package com.example.semil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;


/*window.semi.newBrower(o.url);*/


public class MainActivity extends ActionBarActivity {
	public class WebAppInterface {
	    Context mContext;

	    /** Instantiate the interface and set the context */
	    WebAppInterface(Context c) {
	        mContext = c;
	    }

	    /** Show a toast from the web page */
	    /*@JavascriptInterface
	    public void showToast(String toast) {
	        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
	    }*/
	    
	    @JavascriptInterface
	    public void showToast(String toast) {
	        //Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
	        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(toast));
	        startActivity(intent);
	    }
	    
	}

	private static final String TAG = "LocalBrowser";
	private static final String LOG_TAG = "WebViewDemo";
	private BackPressCloseHandler backPressCloseHandler;
	private long backKeyPressedTime = 0;
	private Toast toast;
	private Activity activity;
	private final Handler handler = new Handler();
	private WebView webView;
	private TextView textView;
	private NotificationManager notimng;

	private final long	FINSH_INTERVAL_TIME    = 2000;
	private long		backPressedTime        = 0;

	@SuppressLint("JavascriptInterface")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		startActivity(new Intent(this,Splash.class));


		webView = new WebView(this);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient());
		webView.setWebChromeClient(new WebChromeClient());

		WebSettings settings = webView.getSettings();  
		settings.setJavaScriptEnabled(true);
		settings.setDomStorageEnabled(true);
		settings.setDatabaseEnabled(true);
		settings.setAppCacheEnabled(false);

		//webView.addJavascriptInterface(new AndroidBridge(), "HybridApp");
		webView.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				return true;
			}
		});
		webView.setLongClickable(false);


		webView.setWebChromeClient(new WebChromeClient(){

			@Override
			public boolean onJsAlert(final WebView view, final String url, final String message, JsResult result) {
				Log.d(TAG, "onJsAlert(" + view + ", " + url + ", " + message + ", " + result + ")");



				//startService(new Intent("com.example.tt"));

				notimng = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				PendingIntent notiP = PendingIntent.getActivity(	getApplicationContext(), 0, 
						new Intent(getApplicationContext(), MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

				Notification notice = new NotificationCompat.Builder(getApplicationContext())
				.setContentTitle("SemiLog")
				.setContentText("지정한 시간이 되었다록")
				.setSmallIcon(R.drawable.ic_notif)
				.setTicker("알림").setAutoCancel(true)
				.setContentIntent(notiP).build();

				notimng.notify(0, notice);

				/*
		    	if ( notimng != null ) {
		  			notimng.cancel(0);
		  		}
				 */
				Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				vibe.vibrate(1000);



				AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
				.setIcon(R.drawable.ic_notification)
				.setTitle("알림")
				.setMessage("기록을 계속하시겠습니까?")
				.setCancelable(false)
				.setPositiveButton("계속", new DialogInterface.OnClickListener() {	
					@Override
					public void onClick(DialogInterface dialog, int which) {

						notimng.cancel(0);
						webView.loadUrl("javascript:yes()");

					}
				}).setNegativeButton("중단", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						notimng.cancel(0);
						webView.loadUrl("javascript:no()");
					}
				}).create();
				alert.show();


				result.confirm(); 
				return true;

			}
		});
		webView.addJavascriptInterface(new WebAppInterface(this), "Android");
		
		
		webView.loadUrl("file:///android_asset/www/index.html");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(webView);


	}




	@Override 
	public void onBackPressed() {
		long tempTime        = System.currentTimeMillis();
		long intervalTime    = tempTime - backPressedTime;

		if ( 0 <= intervalTime && FINSH_INTERVAL_TIME >= intervalTime ) {
			super.onBackPressed(); 
			System.exit(0);
		} else { 
			backPressedTime = tempTime; 
			Toast.makeText(getApplicationContext(),"'뒤로'버튼을한번더누르시면종료됩니다.",Toast.LENGTH_SHORT).show(); 
		} 
	} 

}