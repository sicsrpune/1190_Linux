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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
public class Search extends Activity {

	protected Command cmd;
	protected EditText edt;
	protected ListView m2lv;
	protected RadioGroup rdg;
	protected RadioButton r1,r2;
	protected Button b2,b3;
	protected ImageView src;
	protected Adapter ad;
	private static ArrayList<String> m2data;
	private static int p;
	protected static Animation al;
	protected static final int ACTIVITY_1 = 2;
	protected static final int ACTIVITY_2 = 3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		
		edt=(EditText)findViewById(R.id.m2search);
		m2lv= (ListView)findViewById(R.id.m2listView1);
		rdg =(RadioGroup)findViewById(R.id.m2rg1);
		r1=(RadioButton)findViewById(R.id.m2r0);
		r2=(RadioButton)findViewById(R.id.m2r1);
		b2= (Button)findViewById(R.id.m2button2);
		b3= (Button)findViewById(R.id.m2button3);
		src = (ImageView)findViewById(R.id.m2imageView1);
		al=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shoot);
		m2data=new ArrayList<String>();
		cmd = new Command(getApplicationContext());
		m2data=cmd.all();
		ad = new Adapter(getApplicationContext(),m2data);
		m2lv.setAdapter(ad);
		m2lv.setFastScrollEnabled(true);
		//final View sr =(EditText)findViewById(R.id.m2search); 
	
		edt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stubs
				Toast t=Toast.makeText(getApplicationContext(), "Click Again for Input",Toast.LENGTH_LONG);
				t.setGravity(Gravity.CENTER, 0, 0);
				t.show();
				v.setFocusableInTouchMode(true);
				m2lv.setSelection(0);
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
				m2lv.setSelection(0);
			}
		});		
		
		rdg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				m2lv.setSelection(0);
			}
		});
		
		b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),Home.class));
				finish();
			}
		});
		
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(getApplicationContext(),Shell.class);
				in.putExtra("calling-activity", ACTIVITY_2);
				startActivity(in);
				finish();
			}
		});
		
		
		src.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!edt.getText().toString().equals("") && (r1.isChecked()||r2.isChecked()))
				{
					if(r1.isChecked())
					{
						String tex = edt.getText().toString();
						Integer po = cmd.srcpos(tex);
						
						if(Command.arr.contains(tex))
						{
							
							Toast n1 = Toast.makeText(getApplicationContext(), "Found",Toast.LENGTH_SHORT);
							n1.setGravity(17,0 ,0);
							n1.show();
						}
						else
						{
							Toast.makeText(getApplicationContext(), "Not Found",Toast.LENGTH_SHORT).show();
							
						}
						m2lv.smoothScrollToPosition(po);
						m2lv.setSelection(po);
					
					}
					if(r2.isChecked())
					{
						
						String i=edt.getText().toString().substring(0, 1);
						String w=""; 
						Toast n1 = Toast.makeText(getApplicationContext(), "Showing",Toast.LENGTH_SHORT);
						n1.setGravity(17,0 ,0);
						n1.show();
						for(int c=0;c<Command.arr.size();c++)
						{
							w=Command.arr.get(c);
							if(w.startsWith(i))
							{
								p=c;
								break;
							}
						}
						m2lv.smoothScrollToPosition(p);
						m2lv.setSelection(p);
						
					}
					
					
				}
				else
				{
					if(edt.getText().toString().equals(""))
					{
						
						Toast.makeText(getApplicationContext(), "Enter Search Value",Toast.LENGTH_SHORT).show();
						
					}
					if(!(r1.isChecked() || r1.isChecked()))
					{
						Toast t=Toast.makeText(getApplicationContext(), "Select Option",Toast.LENGTH_SHORT);
						t.setGravity(17, 0, 0);
						t.show();
					}
				}
			}
		});
		
		
		
		
		m2lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> v1, final View v, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				Description.set(pos);
				v.startAnimation(al);
				Runnable ru = new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						Intent d = new Intent(getApplicationContext(),Description.class);
						d.putExtra("calling-activity", ACTIVITY_1);
						startActivity(d);
						finish();
					}
				};
				v.postDelayed(ru,800);
				
				
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
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

}
