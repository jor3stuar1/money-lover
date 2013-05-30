package com.example.bai1;


import java.sql.Date;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class Alerts {
	public static void ShowGiaodichAddedAlert(Context con)
	{
		AlertDialog.Builder builder=new AlertDialog.Builder(con);
		builder.setTitle("Nhập giao dịch");
		builder.setIcon(android.R.drawable.ic_dialog_info);
		DialogListener listner=new DialogListener();
		builder.setMessage("Thành công");
		builder.setPositiveButton("ok", listner);
		
		AlertDialog diag=builder.create();
		diag.show();
	}
	public static AlertDialog ShowEditDialog(final Context con,final giaodich acc)
	{
		AlertDialog.Builder b=new AlertDialog.Builder(con);
		b.setTitle("Chi tiết giao dịch");
		LayoutInflater li=LayoutInflater.from(con);
		View v=li.inflate(R.layout.editdialog, null);
		b.setIcon(android.R.drawable.ic_input_get);
		b.setView(v);
		final TextView txtName=(TextView)v.findViewById(R.id.txtDelName);
		final TextView txtAge=(TextView)v.findViewById(R.id.txtDelAge);
		final Spinner spin=(Spinner)v.findViewById(R.id.spinDiagDept);
		ultility.ManageDeptSpinner(con, spin);
		for(int i=0;i<spin.getCount();i++)
		{
			long id=spin.getItemIdAtPosition(i);
			if(id==acc.getid_giaodich())
			{
				spin.setSelection(i, true);
				break;
			}
		}
		txtName.setText(acc.getDate().toString());
		txtAge.setText(String.valueOf(acc.getMoney()));
		
		b.setPositiveButton("Sửa đồi", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				acc.setDate(Date.valueOf(txtName.getText().toString()));
				acc.setMoney(Integer.valueOf(txtAge.getText().toString()));
				acc.setid_giaodich((int)spin.getItemIdAtPosition(spin.getSelectedItemPosition()));
				
				try
				{
				giaodichdatabase db=new giaodichdatabase(con);
				db.updateGiaodich(acc);
				
				}
				catch(Exception ex)
				{
					CatchError(con, ex.toString());
				}
			}
		});
		b.setNeutralButton("Xóa giao dịch", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				giaodichdatabase db=new giaodichdatabase(con);
				db.deleteGiaodich(acc);
			}
		});
		b.setNegativeButton("Đóng", null);
		
		return b.create();
		//diag.show();
	}
	static public void CatchError(Context con, String Exception)
	{
		Dialog diag=new Dialog(con);
		diag.setTitle("Error");
		TextView txt=new TextView(con);
		txt.setText(Exception);
		diag.setContentView(txt);
		diag.show();
	}
}
