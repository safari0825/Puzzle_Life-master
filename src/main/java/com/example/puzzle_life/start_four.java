package com.example.puzzle_life;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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

public class start_four extends Activity{

	Bitmap []bitmap=new Bitmap[3];
	Bitmap []small_bitmap=new Bitmap[16];
	ImageButton []ib=new ImageButton[16];
	int []index=new int[16];
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
        setContentView(R.layout.four);
        puzzle = ((Puzzle_Life)getApplicationContext());
        bitmap[0] =((BitmapDrawable)getResources().getDrawable(R.drawable.w2 )).getBitmap();
        bitmap[1] =((BitmapDrawable)getResources().getDrawable(R.drawable.w3 )).getBitmap();
        bitmap[2] =((BitmapDrawable)getResources().getDrawable(R.drawable.w1 )).getBitmap();
        time_view=(TextView)findViewById(R.id.time1);
        step_view=(TextView)findViewById(R.id.step1);
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
        ib[9].setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {jundge_and_swap(9);} 
        } );
        ib[10].setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {jundge_and_swap(10);} 
        } );
        ib[11].setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {jundge_and_swap(11);} 
        } );
        ib[12].setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {jundge_and_swap(12);} 
        } );
        ib[13].setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {jundge_and_swap(13);} 
        } );
        ib[14].setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {jundge_and_swap(14);} 
        } );
        ib[15].setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {jundge_and_swap(15);} 
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
             for (i=0;i<15;i++)
             {
                 list.add(i);
             }
             int count=15;
        	for(i=0;i<15;i++)
            {
            	int randomInt = new Random().nextInt(count);
                index[i] = list.get(randomInt);
                list.remove(randomInt);
                count--;
            }
        	int times=0;
        	for(i=0;i<14;i++)
        		for(j=i;j<15;j++)
        		{
        			if(index[i]>index[j])
        			{times+=1;}
        		}
        	if(times%2==0)
        	{okflag=true;break;}
        }       
        index[15]=15;        
        for(i=0;i<16;i++)
        {
       	 ib[i]=(ImageButton)findViewById(R.id.ImageButton0001+i);       	 
        }
        for(i=0;i<4;i++)
        {
        	for(j=0;j<4;j++)
        	{
        		small_bitmap[i*4+j]=Bitmap.createBitmap(bitmap[image_no],j*width/4,i*height/4,width/4,height/4);
        	}
        } 
        Drawable d = getResources().getDrawable(R.drawable.blank);
        BitmapDrawable bd = (BitmapDrawable) d;
        Bitmap blank = bd.getBitmap();
        small_bitmap[15]=blank;
        for(i=0;i<16;i++)
        {
        	ib[i].setImageBitmap(small_bitmap[index[i]]);
        }
        blank_index=15;
        handler.post(task); 
	}
   public void jundge_and_swap(int in)
   {
   	if((in+4==blank_index)||(in-4==blank_index)||((in+1==blank_index)&&(in/4==blank_index/4))||((in-1==blank_index)&&(in/4==blank_index/4)))
   	{
   		int temp;
   		ib[blank_index].setImageBitmap(small_bitmap[index[in]]);
   		ib[in].setImageBitmap(small_bitmap[index[blank_index]]);
   		temp=index[in];
   		index[in]=index[blank_index];
   		index[blank_index]=temp; 
   		blank_index=in;	
   		used_step+=1;
        stepviewtext="   Step��"+used_step;
        step_view.setText(stepviewtext);
   		for(int k=0;k<16;k++)
   		{
   			if(index[k]!=k)
   				break;
   			if(index[k]==k&&k==15)
   			{
   				for(int num=0;num<16;num++)
   				{ib[num].setClickable(false);}
   				String text="����ƴͼ��ʱ��"+used_time+"�룬�ܹ�"+used_step+"��. ��������Ϸ��";
   				run=false;
   				new AlertDialog.Builder(this)
   				.setTitle("��ϲ�㣬��Ϸ�ɹ���")
   				.setMessage(text)
   				.setNegativeButton("ȡ��", null)
   				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
   				public void onClick(DialogInterface dialog, int whichButton) {
   					for(int num=0;num<16;num++)
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
		for(int a=15;a>=0;a--)
		{
		if(small_bitmap[a].isRecycled()==false) {small_bitmap[a].recycle();}
		}
		
		super.onDestroy();
	}
}