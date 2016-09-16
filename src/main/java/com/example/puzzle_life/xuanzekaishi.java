package com.example.puzzle_life;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.DragEvent; 
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class xuanzekaishi extends Activity{
	String stringuri;
	Uri uri;
	ImageButton []ib=new ImageButton[9];
	Bitmap []small_bitmap=new Bitmap[9];
	int []index=new int[9];
	int blank_index;
	int height;
    int width;
    int newheight;
    int newwidth;
    Bitmap bitmap;
    int used_time;
    int used_step;
    String timeviewtext;
    String stepviewtext;
    TextView time_view;
    TextView step_view;
    boolean run;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three);
        time_view=(TextView)findViewById(R.id.time);
        step_view=(TextView)findViewById(R.id.step);
        used_time=-1;
        used_step=0;
        run = true;
        final Handler handler = new Handler();   
        Runnable task = new Runnable() {   
      
            public void run() {   
                // TODO Auto-generated method stub   
                if (run) {   
                    handler.postDelayed(this, 1000);   
                    used_time++;
                }   
                time_view.setText("  时间: " +used_time);
            }   
        };
        handler.post(task); 
        Intent intent=getIntent();
        stringuri=intent.getStringExtra("uri"); 
        uri=Uri.parse(stringuri);
        ContentResolver cr = this.getContentResolver();
		try {
			bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));							
		} catch (FileNotFoundException e) {
			if (bitmap != null) {
				bitmap.recycle();
			}
			Log.e("Exception", e.getMessage(),e);
		}
        int i,j;
        height=bitmap.getHeight();
        width=bitmap.getWidth(); 
       
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
        		small_bitmap[i*3+j]=Bitmap.createBitmap(bitmap,j*width/3,i*height/3,width/3,height/3);
        	}
        }
        for(i=0;i<9;i++)
        {
        	ib[i].setImageBitmap(small_bitmap[index[i]]);
        }

        ib[0].setOnDragListener(new OnDragListener()
        {
			@Override
			public boolean onDrag(View v, DragEvent evt) {return judge_and_swap(v,evt,0);} 
        } );
        
        ib[0].setOnLongClickListener(new OnLongClickListener() {  
            public boolean onLongClick(View view) {
            	ClipData data = ClipData.newPlainText("No", "0");
            	ib[0].startDrag(data, new DragShadowBuilder(view), (Object)view, 0);  
                return true;
            }  
        });  
        ib[1].setOnDragListener(new OnDragListener()
        {
			@Override
			public boolean onDrag(View v, DragEvent evt) {return judge_and_swap(v,evt,1);} 
        } );
        ib[1].setOnLongClickListener(new OnLongClickListener() {  
            public boolean onLongClick(View view) {
            	ClipData data = ClipData.newPlainText("No", "1");
            	ib[1].startDrag(data, new DragShadowBuilder(view), (Object)view, 0);  
                return true;
            }  
        }); 
        
        ib[2].setOnDragListener(new OnDragListener()
        {
			@Override
			public boolean onDrag(View v, DragEvent evt) {return judge_and_swap(v,evt,2);} 
        } );
        
        ib[2].setOnLongClickListener(new OnLongClickListener() {  
            public boolean onLongClick(View view) {
            	ClipData data = ClipData.newPlainText("No", "2");
            	ib[2].startDrag(data, new DragShadowBuilder(view), (Object)view, 0);  
                return true;
            }  
        }); 
        ib[3].setOnDragListener(new OnDragListener()
        {
			@Override
			public boolean onDrag(View v, DragEvent evt) {return judge_and_swap(v,evt,3);} 
        } );
        
        ib[3].setOnLongClickListener(new OnLongClickListener() {  
            public boolean onLongClick(View view) {
            	ClipData data = ClipData.newPlainText("No", "3");
            	ib[3].startDrag(data, new DragShadowBuilder(view), (Object)view, 0);  
                return true;
            }  
        }); 
        ib[4].setOnDragListener(new OnDragListener()
        {
			@Override
			public boolean onDrag(View v, DragEvent evt) {return judge_and_swap(v,evt,4);} 
        } );
        
        ib[4].setOnLongClickListener(new OnLongClickListener() {  
            public boolean onLongClick(View view) {
            	ClipData data = ClipData.newPlainText("No", "4");
            	ib[4].startDrag(data, new DragShadowBuilder(view), (Object)view, 0);  
                return true;
            }
        }); 
        ib[5].setOnDragListener(new OnDragListener()
        {
			@Override
			public boolean onDrag(View v, DragEvent evt) {return judge_and_swap(v,evt,5);} 
        } );
        
        ib[5].setOnLongClickListener(new OnLongClickListener() {  
            public boolean onLongClick(View view) {
            	ClipData data = ClipData.newPlainText("No", "5");
            	ib[5].startDrag(data, new DragShadowBuilder(view), (Object)view, 0);  
                return true;
            }  
        }); 
        ib[6].setOnDragListener(new OnDragListener()
        {
			@Override
			public boolean onDrag(View v, DragEvent evt) {return judge_and_swap(v,evt,6);} 
        } );
        
        ib[6].setOnLongClickListener(new OnLongClickListener() {  
            public boolean onLongClick(View view) {
            	ClipData data = ClipData.newPlainText("No", "6");
            	ib[6].startDrag(data, new DragShadowBuilder(view), (Object)view, 0);  
                return true;
            }  
        }); 
        ib[7].setOnDragListener(new OnDragListener()
        {
			@Override
			public boolean onDrag(View v, DragEvent evt) {return judge_and_swap(v,evt,7);} 
        } );
        ib[7].setOnLongClickListener(new OnLongClickListener() {  
            public boolean onLongClick(View view) {
            	ClipData data = ClipData.newPlainText("No", "7");
            	ib[7].startDrag(data, new DragShadowBuilder(view), (Object)view, 0);  
                return true;
            }  
        }); 
        ib[8].setOnDragListener(new OnDragListener()
        {
			@Override
			public boolean onDrag(View v, DragEvent evt) {return judge_and_swap(v,evt,8);} 
        } );
        ib[8].setOnLongClickListener(new OnLongClickListener() {  
            public boolean onLongClick(View view) {
            	ClipData data = ClipData.newPlainText("No", "8");
            	ib[8].startDrag(data, new DragShadowBuilder(view), (Object)view, 0);  
                return true;
            }  
        }); 
   } 
	
	
   public boolean judge_and_swap(View mDropView,DragEvent event ,int in) {

	   boolean result = true;

       used_step+=1;
       stepviewtext="   Step数"+used_step;
       step_view.setText(stepviewtext);

       switch (event.getAction()) {  
           case DragEvent.ACTION_DRAG_STARTED: {
               break;  
           }  
     
           case DragEvent.ACTION_DRAG_ENTERED: {
               break;  
           }
     
           case DragEvent.ACTION_DRAG_LOCATION: {
               break;
           }
     
           case DragEvent.ACTION_DROP: {
               ClipData data = event.getClipData();
               String oldNo = "";
               if(data.getItemCount()>0) {
            	   oldNo = data.getItemAt(0).getText().toString();
               }
               Bitmap tmpImage = ((BitmapDrawable)ib[in].getDrawable()).getBitmap();
               ib[in].setImageBitmap(((BitmapDrawable)ib[Integer.parseInt(oldNo)].getDrawable()).getBitmap()) ;
               ib[Integer.parseInt(oldNo)].setImageBitmap(tmpImage);

               break;
           }

           case DragEvent.ACTION_DRAG_ENDED: {

               //判断是否完成
               int count = 0;

               //TODO:判断方法不正
               for(int i = 0 ; i < 9;i++) {
                   if(((BitmapDrawable)ib[i].getDrawable()).getBitmap().sameAs(small_bitmap[i])) {
                       count++;
                   }
               }

               if(count == 9) {
                   for(int num=0;num<9;num++)
                   {ib[num].setClickable(false);}
                   final Intent intent2=new Intent(this,chooseimage.class);
                   String text="完成时间："+used_time+"秒 使用步数："+used_step+"步. 挑战下一关吗？";
                   run=false;
                   new AlertDialog.Builder(this)
                           .setTitle("拼图游戏")
                           .setMessage(text)
                           .setNegativeButton("不玩了", null)
                           .setPositiveButton("继续玩", new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog, int whichButton) {
                                   for(int a=8;a>=0;a--)
                                   {
                                       if(small_bitmap[a].isRecycled()==false) {small_bitmap[a].recycle();}
                                   }
                                   startActivity(intent2);
                               }
                           }).show();
               }
               break;  
           }
           case DragEvent.ACTION_DRAG_EXITED: {

               int tmp = 0;
               break;  
           }
           default: {
               break;  
           }  
       }  

       return result;  
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
        stepviewtext="   Step数"+used_step;
        step_view.setText(stepviewtext);
   		for(int k=0;k<9;k++)
   		{
   			if(index[k]!=k)
   				break;
   			if(index[k]==k&&k==8)
   			{
   				for(int num=0;num<9;num++)
   				{ib[num].setClickable(false);}
   				final Intent intent2=new Intent(this,chooseimage.class);
   				String text="完成时间："+used_time+" 使用步数："+used_step+"步. 挑战下一关吗？";
   				run=false;
   				new AlertDialog.Builder(this)
   				.setTitle("拼图游戏")
   				.setMessage(text)
   				.setNegativeButton("不玩了", null)
   				.setPositiveButton("继续玩", new DialogInterface.OnClickListener() {
   				public void onClick(DialogInterface dialog, int whichButton) {
   					for(int a=8;a>=0;a--)
   					{
   					if(small_bitmap[a].isRecycled()==false) {small_bitmap[a].recycle();}
   					}
   					startActivity(intent2);
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
		for(int a=8;a>=0;a--)
		{
		if(small_bitmap[a].isRecycled()==false) {small_bitmap[a].recycle();}
		}
		
		super.onDestroy();
	}
}
