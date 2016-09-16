/*
 * @since  2013??2??27?? 21:09:20
 * @
 * function  
 */
package com.example.puzzle_life;

import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class option  extends Activity{

	protected static Context java;
	Button startButton;
	Button chooseimagestartButton;
	Button settingButton;
	Button helpButton;
	Button aboutButton;
	Button exitButton;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option);

        startButton=(Button)findViewById(R.id.start);
        settingButton=(Button)findViewById(R.id.setting);
        helpButton=(Button)findViewById(R.id.help);
        aboutButton=(Button)findViewById(R.id.about);
        exitButton=(Button)findViewById(R.id.exit);
        chooseimagestartButton=(Button)findViewById(R.id.choose_start);       
        startButton.setOnClickListener(new OnClickListener() 
        		{  			
					@Override
					public void onClick(View v) {
						final Puzzle_Life puzzle = ((Puzzle_Life)getApplicationContext());
						final Intent intent = new Intent(option.this, start.class); 
						final Intent intent1 = new Intent(option.this, start_four.class); 
						if(puzzle.getgrade()==3)
						{
							startActivity(intent);
						}
						else
						{
							startActivity(intent1);
						}
					}       	
        		});
        chooseimagestartButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				final Intent intent=new Intent(option.this,chooseimage.class);
				startActivity(intent);
			}       	
		});
        settingButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(option.this,setting.class);
				startActivity(intent);
			}       	
        });
        helpButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				Intent intent=new Intent(option.this,help.class);
				startActivity(intent);
			}        	
        });
        /*
         *
         *
         */
        aboutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(option.this,about.class);
				startActivity(intent);
			}       	
        });
        exitButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(option.this)   
				.setTitle("拼图游戏")
				.setMessage("真的不玩了？")
				.setPositiveButton("是的", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						System.exit(0);
					}
					
				})  
				.setNegativeButton("还要玩", null)
				.show();  
				}       	
		});
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			new AlertDialog.Builder(this)
			.setTitle("拼图游戏")

			.setMessage("真的不玩了？")

			.setNegativeButton("还要玩", null)

			.setPositiveButton("是的", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int whichButton) {
				
				System.exit(0);
			
			}
			}).show();
			
		}
		return true;
		}
	@Override
	protected void onDestroy()
	{
	super.onDestroy();
	}
}
