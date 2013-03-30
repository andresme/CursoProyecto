package com.itcr.custom.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.itcr.clinica.R;

public class DataBaseHelper extends SQLiteOpenHelper {
	
	/*public static final String TABLE = "Servicios";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME= "name";
	public static final String COLUMN_INFORMATION = "information";
	public static final String COLUMN_WEBSITE = "website";
	
	private static final String DATABASE_NAME = "clinicaTEC.db";
	
	
	private static final String DATABASE_CREATE = "CREATE TABLE" + TABLE + "(" + COLUMN_ID
		      + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME+ " TEXT NOT NULL," 
			+ COLUMN_INFORMATION + " TEXT NOT NULL," +  COLUMN_WEBSITE + " TEXT NOT NULL);";
			*/

	private static final int DATABASE_VERSION = 1;
	public DataBaseHelper(Context context) {
		super(context, R.string.database_name, null, DATABASE_VERSION);		
	}
	
	@Override
	public void onCreate(SQLiteDatabase database){
		database.execSQL(DATABASE_CREATE);
	}
	
	@Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(DataBaseHelper.class.getName(),"UPGRADING DATABASE FROM VERSION " + oldVersion +
	    		" TO " + newVersion + ", WHICH WILL DESTROY ALL OLD DATA");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE);
	    onCreate(db);
	  }
	
	

}
