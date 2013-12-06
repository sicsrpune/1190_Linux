package com.linux;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.linux.util.SystemUiHider;


public class Home extends Activity {
	
	protected View controlsView;
	
	protected View contentView;
	
	protected static final boolean AUTO_HIDE = true;

	protected static final int AUTO_HIDE_DELAY_MILLIS = 3000;

	protected static final boolean TOGGLE_ON_CLICK = true;
	
	 private static final int TEXT_ID = 95;
	 
	 private static EditText et;
	 
	 private static int du;
	 
	 public static int deftq=0;

	protected static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	protected SystemUiHider mSystemUiHider;
	protected Button m1b1,m2b1,m8b1,m3b1,m4b1,m5b1,m6b1,m7b1;

	protected static final int ACTIVITY_1= 4;
	
	private static Animation up;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		
		  final Animation left = AnimationUtils.loadAnimation(this, R.anim.translate);
		  final Animation right = AnimationUtils.loadAnimation(this, R.anim.right);
		  up = AnimationUtils.loadAnimation(this, R.anim.up);
		  
		 controlsView = findViewById(R.id.fullscreen_content_controls_home);
		contentView = findViewById(R.id.linearLayout_home);
////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
			m1b1=(Button)findViewById(R.id.m1b1);
			m2b1=(Button)findViewById(R.id.m2b1);
			m3b1=(Button)findViewById(R.id.m3b1);
			m4b1=(Button)findViewById(R.id.m4b1);
			m5b1=(Button)findViewById(R.id.m5b1);
			m6b1=(Button)findViewById(R.id.m6b1);
			m7b1=(Button)findViewById(R.id.m7b1);
			m8b1=(Button)findViewById(R.id.m8b1);
			

			
			
///////////////////////////////////////////////////////////////////////////////////////////
			
			
		
		


		mSystemUiHider = SystemUiHider.getInstance(this, contentView,
				HIDER_FLAGS);
		mSystemUiHider.setup();
		mSystemUiHider
				.setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
					// Cached values.
					int mControlsHeight;
					int mShortAnimTime;

					@Override
					@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
					public void onVisibilityChange(boolean visible) {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
							
							if (mControlsHeight == 0) {
								mControlsHeight = controlsView.getHeight();
							}
							if (mShortAnimTime == 0) {
								mShortAnimTime = getResources().getInteger(
										android.R.integer.config_shortAnimTime);
							}
							controlsView
									.animate()
									.translationY(visible ? 0 : mControlsHeight)
									.setDuration(mShortAnimTime);
						} else {
						
							controlsView.setVisibility(visible ? View.VISIBLE
									: View.GONE);
						}

						if (visible && AUTO_HIDE) {
						
							delayedHide(AUTO_HIDE_DELAY_MILLIS);
						}
					}
				});

	
	contentView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (TOGGLE_ON_CLICK) {
					mSystemUiHider.toggle();
				} else {
					mSystemUiHider.show();
				}
			}
		});
	
		
	m1b1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			v.startAnimation(left);
			Runnable run = new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Intent m1 = new Intent(getApplicationContext(),ListActivity.class);
					startActivity(m1);
					finish();
					
				}
			};
		v.postDelayed(run, 250);
			
			
		}
	});		
		
	m2b1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			v.startAnimation(right);
			Runnable run = new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Intent m2 = new Intent(getApplicationContext(),Search.class);
					startActivity(m2);
					finish();
				}
			};
		v.postDelayed(run, 250);
		}
	});
		
///////////////////////////////////////////////////////////////////////////////////////////

		m8b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				v.startAnimation(left);
				finish();
			}
		});
		
		m3b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				v.startAnimation(right);
				Intent in =new Intent(getApplicationContext(),Internet.class);
				startActivity(in);
				finish();
			}
		});
		
		m4b1.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						v.startAnimation(left);
						Intent in = new Intent(getApplicationContext(),Shell.class);
						in.putExtra("calling-activity", ACTIVITY_1);
						startActivity(in);
						finish();
					}
				});
				
		m5b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				v.startAnimation(right);
				Runnable run = new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent m1 = new Intent(getApplicationContext(),Quize.class);
						startActivity(m1);
						finish();
					}
				};
			v.postDelayed(run, 250);
			}
		});	
				
		m6b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				v.startAnimation(left);
				
				dialough();
				
				
			}
		});	
				
				
		m7b1.setOnClickListener(new OnClickListener() {
			//dialog.getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//v.startAnimation(right);
				m1b1.startAnimation(up);
				m2b1.startAnimation(up);
				m3b1.startAnimation(up);
				m4b1.startAnimation(up);
				m5b1.startAnimation(up);
				m6b1.startAnimation(up);
				m7b1.startAnimation(up);
				m8b1.startAnimation(up);
				Runnable at =new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						about();
					}
				};
				v.postDelayed(at, 2000);
				
				
			}
		});	
		
	
}	
		
///////////////////////////////////////////////////////////////////////////////////////////		
		

	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		delayedHide(100);
	}


	View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (AUTO_HIDE) {
				delayedHide(AUTO_HIDE_DELAY_MILLIS);
			}
			return false;
		}
	};

	Handler mHideHandler = new Handler();
	Runnable mHideRunnable = new Runnable() {
		@Override
		public void run() {
			mSystemUiHider.hide();
		}
	};

	@Override
	public void onBackPressed()
	{
		
		
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        switch (which){
		        case DialogInterface.BUTTON_POSITIVE:
		            //Yes button clicked
		        	finish();
		            break;

		        case DialogInterface.BUTTON_NEGATIVE:
		            //No button clicked
		        	
		            break;
		        }
		    }
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Want to Quit ?").setPositiveButton("Yes", dialogClickListener)
		    .setNegativeButton("No", dialogClickListener).show();
		
		
	}
	private void delayedHide(int delayMillis) {
		mHideHandler.removeCallbacks(mHideRunnable);
		mHideHandler.postDelayed(mHideRunnable, delayMillis);
	}
	
	
	
	public void dialough()
	{
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        switch (which){
		        case DialogInterface.BUTTON_POSITIVE:
		            //Yes button clicked
		         
		        	
					try {
						du= Integer.parseInt(et.getText().toString());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						Toast t = Toast.makeText(getApplicationContext(), "Enter Valid Value", Toast.LENGTH_SHORT);
						t.setGravity(Gravity.TOP, 0,0);
						t.show();
						dialough();
						break;
					}
					
		        	
		        	if(du>=10 && du<=115)
		        	{
		        		deftq=du;
		        		Toast t = Toast.makeText(getApplicationContext(), "Question no set to "+deftq, Toast.LENGTH_SHORT);
						t.setGravity(17, 0,0);
						t.show();
		        	}
		        
		        	else
		        	{
		        		Toast t = Toast.makeText(getApplicationContext(), "Enter Valid Range Value", Toast.LENGTH_SHORT);
						t.setGravity(Gravity.TOP, 0,0);
						t.show();
						dialough();
						
		        	}
		            break;

		        case DialogInterface.BUTTON_NEGATIVE:
		            //No button clicked
		        	dialog.dismiss();
		            break;
		        }
		    }
		};

	final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 et=new EditText(this);
		builder.setMessage("Number of Question");
		et.setId(TEXT_ID);
		et.setHint("Enter Value 10 to 115");
		et.requestFocus();
		builder.setView(et);
		builder.setPositiveButton("Yes", dialogClickListener)
		    .setNegativeButton("No", dialogClickListener).show();
		
		
	}
	
	public void about()
	{

		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        switch (which){
		        case DialogInterface.BUTTON_NEUTRAL:
		            //Yes button clicked
		            	break;

		       
		        }
		    }
		};
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("About");
		//final View vi=(View)findViewById(R.id.m7abouttextView1);
		String s= new String("Version Linux 1.0 <br /><br/><b>Developed by Nirupam and Manish,Msc(ca).</b><br/><br/>You can find all Linux based commands and it's details with Efficient faster searching techniques and not only that bind you,can also try out most of the Linux Command much like a Linux machine Env. It gives you all.</br>Your Boundaries doesn't end at all.You can also check your command skill by taking random quize option.<br/>Everythings are coming randomly infront of you so that you can assure your performance in a much better way.You can also set total number of quize questions to provide you flexibility by clicking settings option in Home menu.<br/>To provide you a large knowledge world,how we can stop you to use Internet, so here is the option search online where you can able to connect yourself to the large Internet world.<br/>It covers 125 different questions and Linux command with description and example<br/><br/>Need help!. Email me at <br/><br/>niru2006@gmail.com ");
		builder.setMessage(Html.fromHtml(s));
		builder.setNeutralButton("OK",  dialogClickListener).show();
	}
	
}

