package com.linux;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Internet extends Activity {

		 protected static WebView wb;
		protected EditText edt;
		protected ImageButton img;
		protected static int count=0;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internet);
		this.edt =(EditText)this.findViewById(R.id.m3editText1);
		this.img = (ImageButton)this.findViewById(R.id.m3imageButton1);
	 Internet.wb =(WebView)this.findViewById(R.id.m3webView1);
	 
	 
	 			
			 wb.getSettings().setJavaScriptEnabled(true);
		     wb.getSettings().setSupportZoom(true);
		     wb.getSettings().setBuiltInZoomControls(true);
		     wb.canGoBackOrForward(1);
	         wb.setWebViewClient(new WebViewClient());      
	         wb.loadUrl("http://www.google.com");
	         
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(edt.getText().toString() !="")
				{
				String i =edt.getText().toString();
				i=i.replace(" ","+");
				wb.loadUrl("http://www.google.com/#q="+i);
				count++;
				 if(count<2)
				 {
					 Toast t = Toast.makeText(getApplicationContext(), "Hold search Key to Redirect to the Website ", Toast.LENGTH_LONG);
					t.setGravity(17, 0, 0);
					t.show();
					 
				 }
			 
				}
				else
				{
					Toast t = Toast.makeText(getApplicationContext(), "Enter Search Key", Toast.LENGTH_SHORT);
					t.setGravity(17, 0, 0);
					t.show();
				}
			}
		});
		
		img.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				
				if(edt.getText().toString() !="")
				{
				String i =edt.getText().toString();
				    i=i.trim();
					if(i.contains("www.") && i.contains(".com"))
					{
						count++;
						//wb.clearFormData();
						//swb.setWebViewClient(new WebViewClient());
						wb.loadUrl("http://"+i);
						
						
					}
					else if (i.contains("www.") && !i.contains(".com")) {
						count++;
						wb.loadUrl("http://"+i+".com");
						
						//wv.startAnimation(an2);
						
					}
					else if (!(i.contains("www.") && i.contains(".com"))) {
						
						wb.loadUrl("http://www."+i+".com");
						count++;
						
					}
					else
					{
						Toast t = Toast.makeText(getApplicationContext(), "Enter Web Address Correctly", Toast.LENGTH_SHORT);
						t.setGravity(17, 0, 0);
						t.show();
						
					}
			   
				}
				
				else
				{
					Toast t = Toast.makeText(getApplicationContext(), "Enter Search key", Toast.LENGTH_SHORT);
					t.setGravity(17, 0, 0);
					t.show();
				}
				
				return true;
			}
		});
		
		
		edt.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				v.setFocusableInTouchMode(true);
				edt.setText("");
				
			}
		});
		
		
		
	}

	@Override
	public void onBackPressed()
	{
		
		wb.goBack();
		count--;
		if(count<=0)
		{
		Intent h = new Intent(getApplicationContext(),Home.class);
		startActivity(h);
		finish();
		}
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add("Home");
		menu.add("Forword");
		menu.add("Back");
		menu.add("Quit");
		getMenuInflater().inflate(R.menu.internet, menu);
		menu.getItem(0).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				Intent l = new Intent(getApplicationContext(),Home.class);
				startActivity(l);
				finish();
				return true;
			}
		});
		menu.getItem(1).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				
				wb.goForward();
				
				return true;
			}
		});
		
		menu.getItem(2).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				
				wb.goBack();
				
				return true;
			}
		});
		
		menu.getItem(3).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				
				finish();
				
				return true;
			}
		});
		
		
		
		return true;
	}
	
	
	
}
