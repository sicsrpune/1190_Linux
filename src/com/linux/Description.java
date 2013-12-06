package com.linux;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Description extends Activity {

	
		protected TextView tv1,tv2,tv3;
		protected Button b1,b2;
		public static int pos; 
		protected static int callingActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_description);


		tv1 = (TextView)findViewById(R.id.m1tv3);
		tv2 = (TextView)findViewById(R.id.m1tv4);
		tv3= (TextView)findViewById(R.id.m1tv5);
		b1 = (Button)findViewById(R.id.m1b11);
		b2 = (Button)findViewById(R.id.m1b12);
		tv1.setText(Command.arr.get(pos));
		tv2.setText(Command.des.get(pos));
		tv3.setText(Command.example.get(pos));
		callingActivity = getIntent().getIntExtra("calling-activity", 0);
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
		
				switch (callingActivity) {
			        
				case 1:
				{  
			        	Intent l = new Intent(getApplicationContext(),ListActivity.class);
						startActivity(l);
						finish();
			            break;
			            
				}
			        case 2:
			        {     
			        	Intent in = new Intent(getApplicationContext(),Search.class);
						startActivity(in);
						finish();
			            break;
			        }
			       /*  default:
			        	 Intent n = new Intent(getApplicationContext(),Search.class);
							startActivity(n);
							finish();
				            break;*/
			            
			            
			        }
				
				
				}
			
		});
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(getApplicationContext(),Shell.class);
				in.putExtra("calling-activity", callingActivity);
				startActivity(in);
				callingActivity=0;
				finish();
				
				
			}
		});
		
		
		
	}
	
	@Override
	public void onBackPressed()
	{
		Intent l = new Intent(getApplicationContext(),Home.class);
		startActivity(l);
		finish();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.description, menu);
		return true;
	}
	public static void set(int p)
	{
		Description.pos=p;
		
	}

}
