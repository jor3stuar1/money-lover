package com.example.bai1;

import java.sql.Date;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class activity extends Activity {
	EditText khoangiaodich,sotien,ghichu,ngaygiaodich;
	giaodichdatabase dbgiaodich;
	Spinner theloai, loaigiaodich;
	String arr[]={"Công việc","Mua sắm","Vấn đề khác"};
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	setContentView(R.layout.act1);
	String arr[]={"Công việc","Mua sắm","Vấn đề khác"};
	khoangiaodich=(EditText)findViewById(R.id.editText1);
	sotien=(EditText)findViewById(R.id.editText2);
	ghichu=(EditText)findViewById(R.id.editText3);
	ngaygiaodich=(EditText)findViewById(R.id.editText4);
	theloai=(Spinner)findViewById(R.id.spinner1);
	loaigiaodich=(Spinner)findViewById(R.id.spinner2);
	 ArrayAdapter<String> adapter=new ArrayAdapter<String>
	    (this,android.R.layout.simple_spinner_item,arr);
	    adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
	    theloai.setAdapter(adapter);
	    theloai.setOnItemSelectedListener(new  MyProcessEvent());
	    }
	    private class MyProcessEvent implements OnItemSelectedListener
		{
		    @Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					// TODO Auto-generated method stub
					
				}
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
			
			}
	
	
	public void onStart()
	{
		try
		{
		super.onStart();
		dbgiaodich=new giaodichdatabase(this);
		Cursor c = dbgiaodich.getAllGIAODICH();
		startManagingCursor(c);
		
		
		SimpleCursorAdapter ca=new SimpleCursorAdapter(this,R.layout.giaodichspinnerrow, c, new String [] {giaodichdatabase.KEY_GIAODICH_TEN,"_id"}, new int []{R.id.txtDeptName});
		loaigiaodich.setAdapter(ca);
		loaigiaodich.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});

}
		catch(Exception ex)
		{
			CatchError(ex.toString());
		}
		
	}
	public void btnAddEmp_Click(View view)
	{
		boolean ok=true;
		try
		{
			String kt=khoangiaodich.getText().toString();
			int money=Integer.parseInt(sotien.getText().toString());
			String gc=ghichu.getText().toString();
			Date nt=Date.valueOf(ngaygiaodich.getText().toString());
			int gdID=Integer.valueOf((int)loaigiaodich.getSelectedItemId());
			String tl=theloai.getSelectedItem().toString();
			giaodich gd=new giaodich(gdID,nt,kt,tl,money,gc);
			dbgiaodich.addGiaodich(gd);
		}
		catch(Exception ex)
		{
			ok=false;
			CatchError(ex.toString());
		}
		finally
		{
			if(ok)
			{
				//NotifyEmpAdded();
				Alerts.ShowGiaodichAddedAlert(this);
			}
		}
	}
		void CatchError(String Exception)
		{
			Dialog diag=new Dialog(this);
			diag.setTitle("Add new Employee");
			TextView txt=new TextView(this);
			txt.setText(Exception);
			diag.setContentView(txt);
			diag.show();
		}
		void NotifyEmpAdded()
		{
			Dialog diag=new Dialog(this);
			diag.setTitle("Add new Employee");
			TextView txt=new TextView(this);
			txt.setText("Employee Added Successfully");
			diag.setContentView(txt);
			diag.show();
			try {
				diag.wait(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				CatchError(e.toString());
			}
			diag.notify();
			diag.dismiss();
		}
			
	}
	











