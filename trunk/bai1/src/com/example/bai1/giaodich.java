package com.example.bai1;

import java.sql.Date;

import android.content.Context;

public class giaodich {
	private int _id;
	private int id_giaodich;
	private Date date;
	private String ghichu;
	private String theloai;
	private int moneygiaodich;
	private String khoangiaodich;
	
	
	giaodich(int id)
	{
		this._id=id;
	}
	giaodich(Date date, int moneygiaodich, int id_giaodich)
	{
		this.date=date;
		this.moneygiaodich=moneygiaodich;
		this.id_giaodich=id_giaodich;
	}
	giaodich( int id_giaodich,Date date, String khoangiaodich, String theloai, int moneygiaodich,String ghichu )
	{
		this.id_giaodich=id_giaodich;
		this.date=date;
		this.ghichu=ghichu;
		this.theloai=theloai;
		this.moneygiaodich=moneygiaodich;
		this.khoangiaodich=khoangiaodich;
	}
	giaodich(Date date, String khoangiaodich, String theloai, int moneygiaodich,String ghichu)
	{
		this.date=date;
		this.ghichu=ghichu;
		this.theloai=theloai;
		this.moneygiaodich=moneygiaodich;
		this.khoangiaodich=khoangiaodich;
	}
	
	public int getID()
	{
		return _id;
	}
	public void setID(int id)
	{
		this._id=id;
	}
	public String getGhichu()
	{
		return ghichu;
	}
	public void setGhichu(String ghichu)
	{
		this.ghichu=ghichu;
	}
	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date=date;
	}
	public int getMoney()
	{
		return moneygiaodich;
	}
	public void setMoney(int moneygiaodich)
	{
		this.moneygiaodich=moneygiaodich;
	}
	public String getTheloai()
	{
		return theloai;
	}
	public void setTheloai(String theloai)
	{
		this.theloai=theloai;
	}
	public String getKhoangiaodich()
	{
		return khoangiaodich;
	}
	public void setKhoanchi(String khoangiaodich)
	{
		this.khoangiaodich=khoangiaodich;
	}
	
	public void setid_giaodich(int id_giaodich)
	{
		this.id_giaodich=id_giaodich;
	}
	public String getNamegiaodich(Context con, int id_giaodich)
	{
		 return new giaodichdatabase(con).GetGiaodich(id_giaodich);
	}
	public int getid_giaodich()
	{
		return this.id_giaodich;
	}
}

