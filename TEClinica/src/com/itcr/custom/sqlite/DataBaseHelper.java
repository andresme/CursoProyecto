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
	private static final int DATABASE_VERSION = 1;
	
	
	private static final String DATABASE_CREATE = "CREATE TABLE" + TABLE + "(" + COLUMN_ID
		      + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME+ " TEXT NOT NULL," 
			+ COLUMN_INFORMATION + " TEXT NOT NULL," +  COLUMN_WEBSITE + " TEXT NOT NULL);";
			*/

	
	public DataBaseHelper(Context context) {
		super(context, R.string.database_name, null, R.integer.database_version);		
	}
	
	@Override
	public void onCreate(SQLiteDatabase database){
		for (int i; i<R.array.database_create.length; i++){
			database.execSQL(R.array.database_create[i]);
		}
	}
	
	
	@Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(DataBaseHelper.class.getName(),"UPGRADING DATABASE FROM VERSION " + oldVersion +
	    		" TO " + newVersion + ", WHICH WILL DESTROY ALL OLD DATA");
	    db.execSQL("DROP TABLE IF EXISTS " + R.string.table_service);
	    onCreate(db);
	  }
	
	

}
