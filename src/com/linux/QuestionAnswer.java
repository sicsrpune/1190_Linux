package com.linux;

import java.util.ArrayList;
import java.util.Collections;
public class QuestionAnswer {

	  public static  ArrayList<Integer> numbers = new ArrayList<Integer>();
	  static int num;
	 // public static int range=10;
	  public QuestionAnswer() {
		// TODO Auto-generated constructor stub
		  for(int i = 0; i < 115; i++)
		     {
		       numbers.add(i);
		     }
		  Collections.shuffle(numbers);
	}
	  
	 public static int ranret(int pos)
	 {
		
		num=numbers.get(pos);
			 
		 
		 
		 return num;
		 
		 
	 }
	    
	 
	     
	
}
