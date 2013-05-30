package com.example.bai1;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class ultility {
static public void ManageDeptSpinner(Context context,Spinner view)
{
	giaodichdatabase dbHelper=new giaodichdatabase(context);
	Cursor c=dbHelper.getAllGIAODICH();
	//context.startManagingCursor(c);
	
	//SimpleCursorAdapter ca=new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item, c, new String [] {DatabaseHelper.colDeptName}, new int []{android.R.id.text1});
	SimpleCursorAdapter ca=new SimpleCursorAdapter(context,R.layout.giaodichspinnerrow, c, new String [] {giaodichdatabase.KEY_GIAODICH_TEN,"_id"}, new int []{R.id.txtDeptName});
	view.setAdapter(ca);
	
}
}