package com.example.bai1;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class giaodichdatabase extends SQLiteOpenHelper {
	 static final String DATABASE_NAME="quanlygiaodich";
	 static final int DATABASE_VERSION=33;
	 static final String GIAODICH_TABLE ="giaodich";
	 
	 static final String KEY_ID="_rowID";
	 static final String KEY_ID_GIAODICH="_idgiaodich";
	 static final String KEY_NGAY="_date";
	 static final String KEY_GHICHU="_ghichu";
	 static final String KEY_KHOANGIAODICH="_khoangiaodich";
	 static final String KEY_THELOAIGIAODICH="_theloai";
	 static final String KEY_MONEY="_moneygiaodich";
	
	 static final String TENGIAODICH_TABLE="_tengiaodich";
	 static final String KEY_GIAODICH_ID="_gdID";
	static final String KEY_GIAODICH_TEN="_gdTEN";
	
	static final String VIEWGIAODICH="Viewgiaodich";
	
	public giaodichdatabase(Context context){
		super (context, DATABASE_NAME,null,DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table "+TENGIAODICH_TABLE+" ("+KEY_GIAODICH_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
		KEY_GIAODICH_TEN+ " text"+ ")");
		
		db.execSQL("create table "+GIAODICH_TABLE+" ("+
				KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ 
				KEY_NGAY+" date null, "+ 
				KEY_KHOANGIAODICH+" text, "+
				KEY_THELOAIGIAODICH+" text, "+
				KEY_MONEY+" interger, "+
				KEY_GHICHU+" text, "+
				KEY_ID_GIAODICH+ " INTEGER NOT NULL, FOREIGN KEY ("+KEY_ID_GIAODICH+") REFERENCES "+TENGIAODICH_TABLE+" ("+KEY_GIAODICH_ID+"));"); 
				
				
		db.execSQL("CREATE TRIGGER fk_TENGIAODICH_TABLE_GIAODICH_TABLE " +
				" BEFORE INSERT "+
				" ON "+GIAODICH_TABLE+
				" FOR EACH ROW BEGIN"+
				" SELECT CASE WHEN ((SELECT "+KEY_GIAODICH_ID+" FROM "+TENGIAODICH_TABLE+" WHERE "+KEY_GIAODICH_ID+"=new."+KEY_ID_GIAODICH+" ) IS NULL)"+
				" THEN RAISE (ABORT,'Foreign Key Violation') END;"+
				"  END;");
		db.execSQL("CREATE VIEW "+VIEWGIAODICH+
				" AS SELECT "+GIAODICH_TABLE+"."+KEY_ID+" AS _id,"+
				" "+GIAODICH_TABLE+"."+KEY_NGAY+","+
				" "+GIAODICH_TABLE+"."+KEY_GHICHU+","+
				" "+GIAODICH_TABLE+"."+KEY_KHOANGIAODICH+","+
				" "+GIAODICH_TABLE+"."+KEY_THELOAIGIAODICH+","+
				" "+GIAODICH_TABLE+"."+KEY_MONEY+","+
				" "+TENGIAODICH_TABLE+"."+KEY_GIAODICH_TEN+""+
				" FROM "+GIAODICH_TABLE+" JOIN "+TENGIAODICH_TABLE+
				" ON "+GIAODICH_TABLE+"."+KEY_ID_GIAODICH+" ="+TENGIAODICH_TABLE+"."+KEY_GIAODICH_ID
				);
				InsertGIAODICH(db);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists "+GIAODICH_TABLE);
		db.execSQL("DROP TABLE IF EXISTS "+TENGIAODICH_TABLE);
		db.execSQL("DROP TRIGGER IF EXISTS GIAODICH_ID_trigger");
		
		db.execSQL("DROP TRIGGER IF EXISTS GIAODICH_ID_trigger22");
		db.execSQL("DROP TRIGGER IF EXISTS fk_TENGIAODICH_TABLE_GIAODICH_TABLE");
		
		db.execSQL("DROP VIEW IF EXISTS "+VIEWGIAODICH);
		onCreate(db);
	}
	public void addGiaodich(giaodich acc)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues noidung=new ContentValues();
		noidung.put(KEY_ID_GIAODICH, acc.getid_giaodich());
		noidung.put(KEY_NGAY, acc.getDate().toString());
		noidung.put(KEY_KHOANGIAODICH, acc.getKhoangiaodich());
		noidung.put(KEY_THELOAIGIAODICH, acc.getTheloai());
		noidung.put(KEY_MONEY, acc.getMoney());
		noidung.put(KEY_GHICHU, acc.getGhichu());
		db.insert(GIAODICH_TABLE, null, noidung);
		db.close();
		
	}
	int getgiaodichcount()
	{
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cur= db.rawQuery("Select * from "+GIAODICH_TABLE, null);
		int x= cur.getCount();
		cur.close();
		return x;
	}
	int count(String gd)
	{
		
			 SQLiteDatabase db=this.getReadableDatabase();
			 String [] columns=new String[]{"_id",KEY_NGAY,KEY_MONEY,KEY_KHOANGIAODICH,KEY_GIAODICH_TEN};
			 Cursor c=db.query(VIEWGIAODICH, columns, KEY_GIAODICH_TEN+"=?", new String[]{gd}, null, null, null,null);
			 int x= c.getCount();
			 c.close();
			 return x;
	}
	
	Cursor getAllEmployees()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 //Cursor cur= db.rawQuery("Select "+colID+" as _id , "+colName+", "+colAge+" from "+employeeTable, new String [] {});
		 Cursor cur= db.rawQuery("SELECT * FROM "+VIEWGIAODICH,null);
		 return cur;
		 
	 }
	
	Cursor getAllGDByRangeDate(String from, String to){
		SQLiteDatabase db=this.getReadableDatabase();
		String sql = "SELECT FROM " + GIAODICH_TABLE + " WHERE " ;
		Cursor cur = db.rawQuery(sql, null);
		return cur;
	}
	
	 Cursor getAllGIAODICH()
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor cur=db.rawQuery("SELECT "+KEY_GIAODICH_ID+" as _id,"+KEY_GIAODICH_TEN+" from "+TENGIAODICH_TABLE,new String [] {});
		 
		 return cur;
	 }
	 public Cursor getttheodate(String gd)
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 String [] columns=new String[]{"_id",KEY_NGAY,KEY_MONEY,KEY_KHOANGIAODICH,KEY_GIAODICH_TEN};
		 Cursor cur=db.query(VIEWGIAODICH, columns,KEY_GIAODICH_TEN+"=?", new String[]{gd},KEY_NGAY,null,null,null );
		 return cur;
	 }
	 void InsertGIAODICH(SQLiteDatabase db)
	 {
		 ContentValues cv=new ContentValues();
			cv.put(KEY_GIAODICH_ID, 1);
			cv.put(KEY_GIAODICH_TEN, "Thu");
			db.insert(TENGIAODICH_TABLE, KEY_GIAODICH_ID, cv);
			cv.put(KEY_GIAODICH_ID, 2);
			cv.put(KEY_GIAODICH_TEN, "Chi");
			db.insert(TENGIAODICH_TABLE, KEY_GIAODICH_ID, cv);
			cv.put(KEY_GIAODICH_ID, 3);
			cv.put(KEY_GIAODICH_TEN, "Cho vay");
			db.insert(TENGIAODICH_TABLE, KEY_GIAODICH_ID, cv);
			cv.put(KEY_GIAODICH_ID, 4);
			cv.put(KEY_GIAODICH_TEN, "Nợ");
			db.insert(TENGIAODICH_TABLE, KEY_GIAODICH_ID, cv);
			db.insert(TENGIAODICH_TABLE, KEY_GIAODICH_ID, cv);
			
			
	 }
	 public String GetGiaodich(int ID)
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 
		 String[] params=new String[]{String.valueOf(ID)};
		 Cursor c=db.rawQuery("SELECT "+KEY_GIAODICH_TEN+" FROM"+ TENGIAODICH_TABLE+" WHERE "+KEY_GIAODICH_ID+"=?",params);
		 c.moveToFirst();
		 int index= c.getColumnIndex(KEY_GIAODICH_TEN);
		 return c.getString(index);
	 }
	 public Cursor getEmpByDept(String gd)
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 String [] columns=new String[]{"_id",KEY_NGAY,KEY_MONEY,KEY_KHOANGIAODICH,KEY_GIAODICH_TEN};
		 Cursor c=db.query(VIEWGIAODICH, columns, KEY_GIAODICH_TEN+"=?", new String[]{gd}, null, null, null,null);
		 return c;
	 }
	 public int GetGiaodichID(String ten)
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 Cursor c=db.query(TENGIAODICH_TABLE, new String[]{KEY_GIAODICH_ID+" as _id",KEY_GIAODICH_TEN},KEY_GIAODICH_TEN+"=?", new String[]{ten}, null, null, null);
		 //Cursor c=db.rawQuery("SELECT "+colDeptID+" as _id FROM "+deptTable+" WHERE "+colDeptName+"=?", new String []{Dept});
		 c.moveToFirst();
		 return c.getInt(c.getColumnIndex("_id"));
		 
	 }
	 
	 public int updateGiaodich(giaodich acc){
	        SQLiteDatabase mdb = getWritableDatabase();

	        ContentValues mValue = new ContentValues();
	        mValue.put(KEY_ID, acc.getID());
	        mValue.put(KEY_ID_GIAODICH, acc.getid_giaodich());
	        mValue.put(KEY_NGAY,acc.getDate().toString());
	        mValue.put(KEY_KHOANGIAODICH, acc.getKhoangiaodich());
	        mValue.put(KEY_THELOAIGIAODICH, acc.getTheloai());
	        mValue.put(KEY_MONEY, acc.getMoney());
	        mValue.put(KEY_GHICHU,acc.getGhichu());

	        return mdb.update(GIAODICH_TABLE, mValue, KEY_ID+"=?",
	                new String[]{String.valueOf(acc.getID())});
	        
	    }
	 public void deleteGiaodich(giaodich acc){
	        SQLiteDatabase mdb = getWritableDatabase();

	        //Xóa
	        mdb.delete(GIAODICH_TABLE,KEY_ID+ "=?",
	                new String[]{String.valueOf(acc.getID())});
	        mdb.close();
	    }
	
}
