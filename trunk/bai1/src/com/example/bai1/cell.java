package com.example.bai1;

import java.sql.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class cell extends Activity {
	giaodichdatabase dbgiaodich;
	static public GridView grid;
	Spinner spingiaodich;
	TextView text, text1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridrow);
		grid=(GridView)findViewById(R.id.gridView1);
		spingiaodich=(Spinner)findViewById(R.id.spinDept1);
		text=(TextView)findViewById(R.id.textView1);
		text1=(TextView)findViewById(R.id.textView2);
		ultility.ManageDeptSpinner(this,spingiaodich);
		try
        {
		spingiaodich.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				LoadGrid();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
         });
        }
		 catch(Exception ex)
	        {
	        	text.setText(ex.toString());
	        }
		try
	     {
	     grid.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				try
				{
					SQLiteCursor cr=(SQLiteCursor)parent.getItemAtPosition(position);
					Date date=Date.valueOf(cr.getString(cr.getColumnIndex(giaodichdatabase.KEY_NGAY)));
					int money=cr.getInt(cr.getColumnIndex(giaodichdatabase.KEY_MONEY));
					String gd=cr.getString(cr.getColumnIndex(giaodichdatabase.KEY_GIAODICH_TEN));
					giaodich acc=new giaodich(date, money,dbgiaodich.GetGiaodichID(gd));
					acc.setID((int)id);
					AlertDialog diag= Alerts.ShowEditDialog(cell.this,acc);
					diag.setOnDismissListener(new OnDismissListener() {

						@Override
						public void onDismiss(DialogInterface dialog) {
							// TODO Auto-generated method stub
							
							LoadGrid();
						}
					
				});
					diag.show();
				}
				catch(Exception ex)
				{
					Alerts.CatchError(cell.this, ex.toString());
				}
			}
				
	    	 
	     });
	     }
		catch(Exception ex)
	     {
	     	
	     }
		}
		@Override
		public void onStart()
		{
	    super.onStart();
	    //LoadGrid();
		}
		public void LoadGrid()
		   {
		    dbgiaodich=new giaodichdatabase(this);
		    try
		    {
		    	View v=spingiaodich.getSelectedView();
		    	TextView txt=(TextView)v.findViewById(R.id.txtDeptName);
		    	String Dept=String.valueOf(txt.getText());
		    	Cursor c=dbgiaodich.getEmpByDept(Dept);
		    	startManagingCursor(c);
		    	
		    	String [] from=new String []{giaodichdatabase.KEY_NGAY,giaodichdatabase.KEY_MONEY,giaodichdatabase.KEY_GIAODICH_TEN};
		    	int [] to=new int [] {R.id.textView1,R.id.textView2,R.id.textView3};
		    	SimpleCursorAdapter sca=new SimpleCursorAdapter(this,R.layout.cell,c,from,to);
		    	grid.setAdapter(sca);
		    	
		    	
		   }
		    catch(Exception ex)
	    	{
	    		AlertDialog.Builder b=new AlertDialog.Builder(this);
	    		b.setMessage(ex.toString());
	    		b.show();
	    	}
		   }
}

