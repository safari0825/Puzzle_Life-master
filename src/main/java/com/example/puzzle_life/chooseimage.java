package com.example.puzzle_life;

import java.io.FileNotFoundException;



import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class chooseimage extends Activity{
	Button xuanzetupian;
	Button qiehuankaishi;
	ImageButton showib;
	Puzzle_Life puzzle;
	Bitmap bitmap;
	Uri uri;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.chooseimage);
	        LinearLayout container =(LinearLayout)findViewById(R.id.AdLinearLayout);


	        xuanzetupian=(Button)findViewById(R.id.xuanze);
	        qiehuankaishi=(Button)findViewById(R.id.qiehuan);
	       qiehuankaishi.setEnabled(false);

	       puzzle = ((Puzzle_Life)getApplicationContext());
	        showib=(ImageButton)findViewById(R.id.showib);
	        bitmap =((BitmapDrawable)getResources().getDrawable(R.drawable.a)).getBitmap();
	        showib.setImageBitmap(bitmap);
	        xuanzetupian.setOnClickListener(new OnClickListener(){
	    			@Override
	    			public void onClick(View v) {
	    			 	Intent intent = new Intent();		       
	    		        intent.setType("image/*");	       
	    		        intent.setAction(Intent.ACTION_GET_CONTENT); 		        
	    		        startActivityForResult(intent, 1);
	    		        qiehuankaishi.setText(R.string.selectStart);
	    		       }  	
	            });
	           final Intent intent1=new Intent(this,xuanzekaishi.class);
	           final Intent intent2=new Intent(this,xuanzekaishi_four.class);
	            qiehuankaishi.setOnClickListener(new OnClickListener()
	            {
	    			@Override
	    			public void onClick(View v) {
	    				if(puzzle.getgrade()==3)
	    				{
	    				intent1.putExtra("uri", uri.toString()); 
	                    startActivity(intent1);
	    				}
	    				else
	    				{
	    					intent2.putExtra("uri", uri.toString()); 
		                    startActivity(intent2);
	    				}
	    			}
	            });
	        }
	        @Override
	    	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    		if (resultCode == RESULT_OK) {
	    		    uri = data.getData();
	    		    qiehuankaishi.setEnabled(true);
	    			Log.e("uri", uri.toString());
	    			ContentResolver cr = this.getContentResolver();
	    			try {
	    				 
	    				bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));							
	    				showib.setImageBitmap(bitmap);
	    			} catch (FileNotFoundException e) {
	    				Log.e("Exception", e.getMessage(),e);
	    			}
	    		}
	    		super.onActivityResult(requestCode, resultCode, data);
	    	}
	        public boolean onKeyDown(int keyCode, KeyEvent event) {
	    		if(keyCode == KeyEvent.KEYCODE_BACK){
	    			finish();			
	    		}
	    		return true;
	    		}
	        protected void onDestroy() {
	    		// TODO Auto-generated method stub
	        	if(bitmap.isRecycled()==false) {bitmap.recycle();}
	    		super.onDestroy();
	    	}
}

