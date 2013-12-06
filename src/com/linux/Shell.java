package com.linux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("CutPasteId")
public class Shell extends Activity {
	protected AutoCompleteTextView cmd;
	protected ImageButton run;
	protected TextView tv1,tv2;
	protected Button b1,b2;
	protected CountDownTimer tm1,tm2;
	protected static StringBuilder res=new StringBuilder();
    protected static int linecount=0;
    protected Command cm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shell);
		
		cmd = (AutoCompleteTextView)findViewById(R.id.m4autoCompleteTextView1);
		run = (ImageButton)findViewById(R.id.m4imageButton1);
		tv1 = (TextView)findViewById(R.id.m4notification1);
		tv2 = (TextView)findViewById(R.id.m4commandres);
		b1 = (Button)findViewById(R.id.m4button2);
		b2 = (Button)findViewById(R.id.m4button3);
		cm= new Command(getApplicationContext());
		cm.all();
		
		final Animation an = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
		run.setLongClickable(true);
		
		
		final View vi=(View)findViewById(R.id.m4autoCompleteTextView1);
		Runnable ac = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				tm1.start();
			}
		};
		vi.post(ac);
		
		
		cmd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vi.requestFocus();
				Toast t=Toast.makeText(getApplicationContext(), "Enter keyword and wait to view suggessions",Toast.LENGTH_LONG);
				t.setGravity(Gravity.CENTER, 0, 0);
				t.show();
			}
		});
		

     final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, Command.arr);
     cmd.setAdapter(adapter);
		
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				res.delete(0,res.length());
				tv2.setText("");
				Toast t = Toast.makeText(getApplicationContext(), "Console is Cleared", Toast.LENGTH_SHORT);
				t.setGravity(17,0,0);
				t.show();
			}
		});
		b1.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				res.delete(0,res.length());
				tv2.setText("");
				cmd.setText("");
				Toast t = Toast.makeText(getApplicationContext(), "Cleared", Toast.LENGTH_SHORT);
				t.setGravity(17,0,0);
				t.show();
				
				
				return true;
			}
		});
		
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int callingActivity = getIntent().getIntExtra("calling-activity", 0);
	
				switch (callingActivity) {
			        case 1:
			            
			        	Intent in=new Intent(getApplicationContext(),ListActivity.class);
						startActivity(in);
						finish();
			            break;
			        case 2:
			            
			        	Intent i = new Intent(getApplicationContext(),Search.class);
						startActivity(i);
						finish();
			            break;
			         
			        case 3:
			            
			        	Intent t = new Intent(getApplicationContext(),Search.class);
						startActivity(t);
						finish();
			            break;
			            
			            
			        case 4:
			            
			        	Intent de = new Intent(getApplicationContext(),Home.class);
						startActivity(de);
						finish();
			            break;
			            
			        }
			
			}
		});
		
		
		tm1 = new CountDownTimer(5000,1000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
					tv1.setText("Note! : may be all linux commands are not runnable due to device dependency.");
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				tv1.startAnimation(an);
				tm2.start();
				
			}
		};
		
		tm2 = new CountDownTimer(6000,1000) {
		
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				
				
					tv1.setText("Note! : to check command press execute button and hold for a while.");
			
				
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				tv1.startAnimation(an);
				Runnable ro = new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						tm1.start();
					}
				};
				tv1.post(ro);
				
			}
		};
		
		
		
		
		
		
		
		
		
		run.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String i = cmd.getText().toString();
				if(i!=null)
				{
					
					try {
						runcommand(i);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Toast t = Toast.makeText(getApplicationContext(),"Enter Command",Toast.LENGTH_SHORT);
						t.setGravity(17,0,0);
						t.show();
					}
					
					
				}
				else
				{
					Toast t = Toast.makeText(getApplicationContext(), "Enter Command", Toast.LENGTH_SHORT);
					t.setGravity(17,0,0);
					t.show();
				}
			}
		});
		
		
		
		run.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				cmd.append(" ");
				if(Command.arr.contains(cmd.getText().toString().substring(0,cmd.getText().toString().indexOf(" "))))
				{
					Toast t = Toast.makeText(getApplicationContext(), "correct", Toast.LENGTH_SHORT);
					t.setGravity(17,0,0);
					t.show();
				}
				else
				{

					Toast t = Toast.makeText(getApplicationContext(), "command not found", Toast.LENGTH_SHORT);
					t.setGravity(17,0,0);
					t.show();
					
				}
				
				return true;
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
		getMenuInflater().inflate(R.menu.shell, menu);
		return true;
	}
	
	public void runcommand(String command)
	{
		
		 try {
		        java.lang.Process p =  Runtime.getRuntime().exec(command);
		        
		        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()) );
		        String line="";
		        while ((line = in.readLine()) != null) {
		          
		        	
		        	res.append("  "+line+"<br/>");
		        	linecount++;
		        }
		        
		        in.close();
		 } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		}
		 finally
		 {
			 if(res!=null)
			 {
			 tv2.setText(Html.fromHtml(res+"<br/>"));
			 //log.delete(0,log.length());
			 tv2.setMovementMethod(new ScrollingMovementMethod());
			 tv2.scrollTo(0,linecount);
			 }
			 else
			 {
			 Toast t = Toast.makeText(getApplicationContext(), "Not Available", Toast.LENGTH_SHORT);
			 t.show();
				 
			 }
		 }
		
	}

}
