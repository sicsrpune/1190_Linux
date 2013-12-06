package com.linux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.content.Context;

public class Command{
	
	public static ArrayList<String> arr = new ArrayList<String>();
	public static ArrayList<String> src = new ArrayList<String>();
	public static ArrayList<String> des = new ArrayList<String>();
	public static ArrayList<String> example = new ArrayList<String>();
	public static int TotalEntries=0;
	public static int count=0;
	private static BufferedReader br;
	private static InputStream is;
	
	public Command(Context c)
	{
		try {
			
			Command.is = c.getAssets().open("x.txt");
			}
		catch (IOException e) {
				
				e.printStackTrace();
			}
    }
	
	//for all command listing 
	public ArrayList<String> all()
	{
			
		try {
			
			arr.clear();
			des.clear();
			example.clear();
		    br = new BufferedReader(new InputStreamReader( Command.is));
		    String line;
		   
		    while ((line = br.readLine()) != null) {
		    	
		    	TotalEntries++;
		    	arr.add(line.substring(line.indexOf("<")+1,line.indexOf(">")));
		    	des.add(line.substring(line.indexOf(">")+1,line.indexOf("#")));
		    	example.add(line.substring(line.indexOf("#")+1,line.indexOf("##")));
		    
		    	
		    }
		   
		}
		catch (IOException e) {
			e.printStackTrace();

		}
		finally
		{
			try {
				br.close();
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		return (arr);

		
	}
	
	
	public int srcpos(String t)      //this module used by listAct and SearchAct
	{
		int pos = Command.arr.indexOf(t);
		
		
		
		return(pos);
	}

	
}
