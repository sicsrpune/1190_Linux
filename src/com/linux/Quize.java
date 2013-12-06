package com.linux;

import java.util.ArrayList;
import java.util.Collections;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Quize extends Activity {

		protected TextView m5timer,m5ques,m5first;

		protected Button b1,b2,start;
		protected RadioGroup rdg;
		private RadioButton r1;

		private RadioButton r2;

		private RadioButton r3;

		private RadioButton r4;
		private static int score=0; 
		protected static int count;
		protected static int po=0;
		/*private static ArrayList<String> qus;
		private static ArrayList<String> ans;
	    private static ArrayList<Integer> ran;*/
	    private static ArrayList<String> fake;
	    private CountDownTimer timer;
	    protected Command cm;
	    protected QuestionAnswer rangen;
		static int correctans=0;
		public static int total_ques=10;
		public static int tim=150000;
		private static View rd;
		static Animation quesshoot,anshoot;
	@SuppressLint("CutPasteId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quize);
		m5timer = (TextView)findViewById(R.id.m5timer);
		m5ques = (TextView)findViewById(R.id.m5ques);
		cm=new Command(getApplicationContext());
		cm.all();
		rangen =new QuestionAnswer();
		b1=(Button)findViewById(R.id.m5back);
		b2=(Button)findViewById(R.id.m5next);
		start=(Button)findViewById(R.id.m5start);
		rdg=(RadioGroup)findViewById(R.id.m5radioGroup1);
		r1=(RadioButton)findViewById(R.id.m5ans1);
		r2=(RadioButton)findViewById(R.id.m5ans2);
		r3=(RadioButton)findViewById(R.id.m5ans3);
		r4=(RadioButton)findViewById(R.id.m5ans4);
		rd=(View)findViewById(R.id.m5radioGroup1);
		m5first=(TextView)findViewById(R.id.m5fst); 
		
		fake=new ArrayList<String>();
		b2.setEnabled(false);
		final Animation zoom = AnimationUtils.loadAnimation(this, R.anim.zoom);
		quesshoot = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.quesshootleft);
		anshoot = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.quesshootlate);
		
		if(Home.deftq>=10 && Home.deftq<=115)
		{
			total_ques=Home.deftq;
			tim =total_ques*15*1000;
			
		}
	/*	Runnable run= new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
				//Random randomGenerator = new Random();
			    for (int idx = 1; idx <= 10; ++idx){
			      int randomInt = randomGenerator.nextInt(120);
			      ran.add(randomInt);
			      qus.add(Command.des.get(idx));
			      System.out.println(""+qus.get(0));
			      ans.add(Command.arr.get(idx));
			    }}
			    catch (Exception e) {
					// TODO: handle exception
			    	e.printStackTrace();
				}
			    
			}
		};
		m5first.post(run);*/
		
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				timer.cancel();
				Intent in=new Intent(getApplicationContext(),Home.class);
				startActivity(in);
				finish();
			}
		});
		
		
		 timer = new CountDownTimer(tim+1000, 1000) {
			
			
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
			
				int s = (int) ((millisUntilFinished / 1000) % 60);
				int m = (int) ((millisUntilFinished / (1000 * 60)) % 60);
				m5timer.setText(m+" : "+s);
				m5timer.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha));
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				/*if(r1.isChecked() || r2.isChecked() || r3.isChecked() || r4.isChecked())
				{
				View rb =(View)findViewById(rdg.getCheckedRadioButtonId());
				int index = rdg.indexOfChild(rb);
				
				if(correctans==index)
				{
					score+=1;
				}
				}*/
				check();
				b2.setEnabled(false);
				r1.setEnabled(false);
				r2.setEnabled(false);
				r3.setEnabled(false);
				r4.setEnabled(false);
				rd.setVisibility(View.GONE);
				m5timer.setText("Times Up !!");
				m5timer.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate));
				Toast t = Toast.makeText(getApplicationContext(), "Completed", Toast.LENGTH_SHORT);
				t.setGravity(17, 0,0);
				t.show();
				
				
				
				//start.setEnabled(true);
				m5first.setText(Html.fromHtml("<b>Report Card :</b>"));
				b2.setText("Score");
				rdg.setVisibility(2);
				r1.setVisibility(2);
				r2.setVisibility(2);
				r3.setVisibility(2);
				r4.setVisibility(2);
				b2.setVisibility(0);
				start.setEnabled(true);
				m5ques.setText(Html.fromHtml("<b>Total Points : "+total_ques+"<br/>Your Final Score is  :"+score+"<br/>Question Attempted :"+count+"</b>"));
				
				
			}
		};
		
		
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					v.startAnimation(zoom);
					count=0;
			
					timer.start();
					m5ques.setVisibility(0);
					rd.setVisibility(View.VISIBLE);
					r1.setVisibility(0);
					r2.setVisibility(0);
					r3.setVisibility(0);
					r4.setVisibility(0);
					r1.setEnabled(true);
					r2.setEnabled(true);
					r3.setEnabled(true);
					r4.setEnabled(true);
					start.setEnabled(false);
					setq(QuestionAnswer.ranret(po));
					b2.setEnabled(true);
					m5first.setText(Html.fromHtml("<b>Test Yourself :</b>"));
					score=0;
					
			}
		});
		
		
		
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				check();
				if(count<total_ques)
				{	
					m5ques.startAnimation(quesshoot);
					rdg.startAnimation(anshoot);
					setq(QuestionAnswer.ranret(po));
					
					
				}
				else
				{	
					timer.cancel();
					//timer.onFinish();
					Toast t = Toast.makeText(getApplicationContext(), "Completed", Toast.LENGTH_SHORT);
					t.setGravity(17, 0,0);
					t.show();
					m5first.setText(Html.fromHtml("<b>Report Card :</b>"));
					b2.setEnabled(false);
					r1.setEnabled(false);
					r2.setEnabled(false);
					r3.setEnabled(false);
					r4.setEnabled(false);
					rdg.setEnabled(false);
					rd.setVisibility(View.GONE);
					start.setEnabled(true);
					rdg.setVisibility(1);
					r1.setVisibility(1);
					r2.setVisibility(1);
					r3.setVisibility(1);
					r4.setVisibility(1);
					m5ques.setText(Html.fromHtml("<b>Total Points : "+total_ques+"<br/>Your Final Score is  :"+score+"<br/>Question Attempted :"+count+"</b>"));
					
					
				}
				//count++;
		
				
			}
		});
		
	}
	
	@Override
	public void onBackPressed()
	{
		timer.cancel();
		Intent l = new Intent(getApplicationContext(),Home.class);
		startActivity(l);
		finish();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quize, menu);
		return true;
	}
	
	public void setq(int pos)
	{
		
		rdg.clearCheck();
		fake.clear();
		String i =Command.des.get(pos).toString();
		String cans=Command.arr.get(pos).toString();
		m5ques.setText(Html.fromHtml("what will you do to"+i+" ?"));
		i=Command.arr.get(pos);
		fake.add(i);
		i=Command.arr.get(pos+1);
		fake.add(i);
		i=Command.arr.get(pos+2);
		fake.add(i);
		i=Command.arr.get(pos+3);
		fake.add(i);
		Collections.shuffle(fake);
		correctans=fake.indexOf(cans);
		r1.setText(Html.fromHtml("<br/>"+fake.get(0)));
		r2.setText(Html.fromHtml("<br/>"+fake.get(1)));
		r3.setText(Html.fromHtml("<br/>"+fake.get(2)));
		r4.setText(Html.fromHtml("<br/>"+fake.get(3)));
		count++;
		po++;
		
	}
	public void check()
	{
		if(r1.isChecked() || r2.isChecked() || r3.isChecked() || r4.isChecked())
		{
		View rb =(View)findViewById(rdg.getCheckedRadioButtonId());
		int index = rdg.indexOfChild(rb);
		
		if(correctans==index)
		{
			score+=1;
		}
		}
		
	}
	

}
