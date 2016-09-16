package com.example.puzzle_life;

import android.app.Application;

public class Puzzle_Life extends Application{
	
	private int grade=3;
	private int image_no=0;
	public void setgrade(int a){grade=a;}
	public void setimage_no(int b){image_no=b;}	
	public int getgrade(){return grade;}
	public int getimage_no(){return image_no;}
}
