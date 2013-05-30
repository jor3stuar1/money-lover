package com.example.bai1;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class chaythu extends Activity {
		TextView tv;
		Button bt;
		giaodichdatabase dbgiaodich=new giaodichdatabase(this);
		public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		setContentView(R.layout.act3);
		
		
		
		tv=(TextView)findViewById(R.id.itemMoney);
		bt=(Button)findViewById(R.id.button1);
		bt.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Cursor c=dbgiaodich.getAllEmployees();
				String chuoi="";
				c.moveToFirst();
				do{
					chuoi+=c.getString(0)+" ";
					chuoi+=c.getString(1)+" ";
					chuoi+=c.getString(2)+" ";
					chuoi+=c.getString(3)+" ";
					chuoi+=c.getString(4)+" ";
					chuoi+=c.getString(5)+" ";
					chuoi+=c.getString(6)+"\n";
				}while(c.moveToNext());
				
				tv.setText(chuoi);
			}
			
		});
		
	}
}

