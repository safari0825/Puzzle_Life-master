package com.example.puzzle_life;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

public class setting extends Activity{  
	RadioButton rb_nomal; 
	RadioButton rb_hard;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        final Puzzle_Life puzzle = ((Puzzle_Life)getApplicationContext());          	
        rb_nomal=(RadioButton)findViewById(R.id.nomal);
        rb_hard=(RadioButton)findViewById(R.id.hard);
        
        if(puzzle.getgrade()==3)
        {rb_nomal.setChecked(true);rb_hard.setChecked(false);}
        else
        {rb_nomal.setChecked(false);rb_hard.setChecked(true);}
        
        rb_nomal.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				puzzle.setgrade(3);
			}       	
        });
        
        rb_hard.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				puzzle.setgrade(4);
			}        	
        });
        
       
    }
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){			
			finish();			
			}
		return true;
		}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
