package com.linux;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Adapter extends ArrayAdapter<String> {
	public static Context ctx;
	public static ArrayList<String> comlist;

	public Adapter(Context applicationContext, ArrayList<String> name) {
		// TODO Auto-generated constructor stub

		super(applicationContext,R.layout.rowdata, name);
		Adapter.ctx=applicationContext;
		Adapter.comlist=new ArrayList<String>();
		Adapter.comlist.addAll(name);
	}
	public View getView(int position,View view,ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.rowdata,parent,false);
		TextView namesText = (TextView)rowView.findViewById(R.id.m1tv2);
		namesText.setText(comlist.get(position));
		return rowView;
	}
	

}
