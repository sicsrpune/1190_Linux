package com.linux;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class Splash extends Activity {
	
	
	protected View controlsView1;
	protected View contentView1;
	protected Button dummy;
	private static Toast ty;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		controlsView1 = findViewById(R.id.fullscreen_content_controls_splash);
		 contentView1 = findViewById(R.id.fullscreen_content_image_splash);
		 dummy = (Button)findViewById(R.id.dummy_button_splash);
		 ty=Toast.makeText(getApplicationContext(),"Wait", Toast.LENGTH_LONG);
		 ty.setGravity(Gravity.CENTER,0,0);
		 ty.show();
		
		 final Runnable delay = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ty.cancel();
				Intent hm= new Intent(getApplicationContext(),Home.class);
				startActivity(hm);
				finish();
			}
		};
		 contentView1.postDelayed(delay,4000);
		 dummy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
			contentView1.removeCallbacks(delay);
			finish();
			
			}
		});
		contentView1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				contentView1.removeCallbacks(delay);
				ty.cancel();
				Intent hm= new Intent(getApplicationContext(),Home.class);
				startActivity(hm);
				finish();
			}
		});

}
}
