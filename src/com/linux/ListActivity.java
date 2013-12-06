package com.linux;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends Activity {

	protected ListView m1lv1;
	protected Button m1b9;
	protected EditText edt;
	private Adapter ad;
	private Command cmd;
	private ArrayList<String> names;
	protected TextView tvalc;
	private Animation n,al;
	protected static final int ACTIVITY_1 = 1;
		
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		edt = (EditText)findViewById(R.id.m1etlist);
		m1b9 = (Button)findViewById(R.id.m1b9);
		m1lv1 = (ListView)findViewById(R.id.m1lv1list);
		tvalc = (TextView)findViewById(R.id.m1tv1list);
		n=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shoot);
		al=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
		cmd = new Command(getApplicationContext());
		names = new ArrayList<String>();
		names = cmd.all();
			
		ad = new Adapter(getApplicationContext(),names);
		m1lv1.setAdapter(ad);
		m1lv1.setFastScrollEnabled(true);
		
		
		
		m1lv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				Description.set(pos);
				arg1.startAnimation(n);
				Runnable run = new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent in = new Intent(getApplicationContext(),Description.class);
						in.putExtra("calling-activity",ACTIVITY_1);
						startActivity(in);
						finish();
						
					}
				};
			arg1.postDelayed(run, 500);
				
			}
			
			
		});
	m1b9.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			Intent in = new Intent(getApplicationContext(),Home.class);
			startActivity(in);
			finish();
				
			}
		});
	
	
	edt.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast t=Toast.makeText(getApplicationContext(), "Click Again for Input",Toast.LENGTH_LONG);
			t.setGravity(Gravity.CENTER, 0, 0);
			t.show();
		v.setFocusableInTouchMode(true);
	
		}
	});
	
	
	
	edt.addTextChangedListener(new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
	
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			String tex = edt.getText().toString();
			Integer po = cmd.srcpos(tex);
			m1lv1.smoothScrollToPosition(po);
			m1lv1.setSelection(po);
			if(Command.arr.contains(tex))
			{
				
				Toast n1 = Toast.makeText(getApplicationContext(), "Found",Toast.LENGTH_SHORT);
				n1.setGravity(17,0 ,0);
				n1.show();
			}
			else
			{
				//Toast.makeText(getApplicationContext(), "Not Found",Toast.LENGTH_SHORT).show();
				tvalc.startAnimation(al);
				tvalc.setText("Not Found");
				Runnable defaultalc = new Runnable()
				{
				    @Override
				    public void run()
				    {
				    	tvalc.setText("All Linux Command");
				    	tvalc.startAnimation(al);
				    }
				 };
				tvalc.postDelayed(defaultalc, 4000);
				m1lv1.setSelection(0);
			}
				
			
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		
		return true;
	}

}
