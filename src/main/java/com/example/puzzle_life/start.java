package com.example.puzzle_life;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class start extends Activity{

	Bitmap []bitmap=new Bitmap[3];
	Bitmap []small_bitmap=new Bitmap[9];
	ImageButton []ib=new ImageButton[9];
	 Bitmap blank;
	int []index=new int[9];
	int blank_index;
	int height;
    int width;
    int used_time;
    int used_step;
    String timeviewtext;
    String stepviewtext;
    TextView time_view;
    TextView step_view;
    int image_no;
    boolean run;
    Puzzle_Life puzzle;
    int i,j; 
    final Handler handler = new Handler(); 
    Runnable task;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three);
        puzzle = ((Puzzle_Life)getApplicationContext());
        bitmap[0] =((BitmapDrawable)getResources().getDrawable(R.drawable.w2 )).getBitmap();
        //bitmap[1] =((BitmapDrawable)getResources().getDrawable(R.drawable.w3 )).getBitmap();
        //bitmap[2] =((BitmapDrawable)getResources().getDrawable(R.drawable.w1 )).getBitmap();
        time_view=(TextView)findViewById(R.id.time);
        step_view=(TextView)findViewById(R.id.step);
         used_time=-1;
         used_step=0;
        task = new Runnable() {     
            public void run() {   
                // TODO Auto-generated method stub   
                if (run) {   
                    handler.postDelayed(this, 1000);   
                    used_time++;   
                }   
                time_view.setText("  Time: " +used_time);   
            }   
        };
        ini();
        ib[0].setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {jundge_and_swap(0);} 
        } );
        ib[1].setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {jundge_and_swap(1);} 
        } );
        ib[2].setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {jundge_and_swap(2);} 
        } );
        ib[3].setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {jundge_and_swap(3);} 
        } );
        ib[4].setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {jundge_and_swap(4);} 
        } );
        ib[5].setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {jundge_and_swap(5);} 
        } );
        ib[6].setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {jundge_and_swap(6);} 
        } );
        ib[7].setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {jundge_and_swap(7);} 
        } );
        ib[8].setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {jundge_and_swap(8);} 
        } );
   } 
	public void ini()
	{
		used_time=0;
        used_step=0;
        run = true;
        stepviewtext="   Step数："+used_step;
		image_no=puzzle.getimage_no()%3;
        height=bitmap[image_no].getHeight();
        width=bitmap[image_no].getWidth();      
        boolean okflag=false;
        while(okflag==false)
        {
        	 List<Integer> list = new ArrayList<Integer>();
             for (i=0;i<8;i++)
             {
                 list.add(i);
             }
             int count=8;
        	for(i=0;i<8;i++)
            {
            	int randomInt = new Random().nextInt(count);
                index[i] = list.get(randomInt);
                list.remove(randomInt);
                count--;
            }
        	int times=0;
        	for(i=0;i<7;i++)
        		for(j=i;j<8;j++)
        		{
        			if(index[i]>index[j])
        			{times+=1;}
        		}
        	if(times%2==0)
        	{okflag=true;break;}
        }       
        index[8]=8;        
        for(i=0;i<9;i++)
        {
       	 ib[i]=(ImageButton)findViewById(R.id.ImageButton01+i);       	 
        }
        for(i=0;i<3;i++)
        {
        	for(j=0;j<3;j++)
        	{
        		small_bitmap[i*3+j]=Bitmap.createBitmap(bitmap[image_no],j*width/3 ,i*height/3 ,width/3,height/3);
        	}
        }
        Drawable d = getResources().getDrawable(R.drawable.blank);
        BitmapDrawable bd = (BitmapDrawable) d;
        blank = bd.getBitmap();
        small_bitmap[8]=blank;
        for(i=0;i<9;i++)
        {
        	ib[i].setImageBitmap(small_bitmap[index[i]]);
        }
        blank_index=8;
        handler.post(task); 
	}
   public void jundge_and_swap(int in)
   {
   	if((in+3==blank_index)||(in-3==blank_index)||((in+1==blank_index)&&(in/3==blank_index/3))||((in-1==blank_index)&&(in/3==blank_index/3)))
   	{
   		int temp;
   		ib[blank_index].setImageBitmap(small_bitmap[index[in]]);
   		ib[in].setImageBitmap(small_bitmap[index[blank_index]]);
   		temp=index[in];
   		index[in]=index[blank_index];
   		index[blank_index]=temp; 
   		blank_index=in;	
   		used_step+=1;
        stepviewtext="   Step数："+used_step;
        step_view.setText(stepviewtext);
   		for(int k=0;k<9;k++)
   		{
   			if(index[k]!=k)
   				break;
   			if(index[k]==k&&k==8)
   			{
   				for(int num=0;num<9;num++)
   				{ib[num].setClickable(false);}
   				String text="完成用时："+used_time+"完成用时："+used_step+" 恭喜过关！";
   				run=false;
   				new AlertDialog.Builder(this)
   				.setTitle("拼图游戏")
   				.setMessage(text)
   				.setNegativeButton("不玩了", null)
   				.setPositiveButton("继续下一关", new DialogInterface.OnClickListener() {
   				public void onClick(DialogInterface dialog, int whichButton) {
   					for(int num=0;num<9;num++)
   	   				{ib[num].setClickable(true);}
   					puzzle.setimage_no(image_no+1);
   					ini(); 
   				}
   				}).show();
   			}
   		}
   	}
   }
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			finish();		
		}
		return true;
}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		for(int a=2;a>=0;a--)
		{
		if(bitmap[a].isRecycled()==false) {bitmap[a].recycle();}
		}
		for(int a=8;a>=0;a--)
		{
		if(small_bitmap[a].isRecycled()==false) {small_bitmap[a].recycle();}
		}
		
		super.onDestroy();
	}
}