package com.itcr.custom.sqlite;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.itcr.clinica.R;

public class DataBaseHelper extends SQLiteOpenHelper {

	private Resources res;

	public DataBaseHelper(Context context, String databaseName, int version) {
		super(context, databaseName, null, version);
		this.res = context.getResources();
	}

	@Override
	public void onCreate(SQLiteDatabase database){
		String[] sql = this.res.getStringArray(R.array.database_create);
		for (int i = 0; i < sql.length; i++){
			database.execSQL(sql[i]);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(DataBaseHelper.class.getName(),"UPGRADING DATABASE FROM VERSION " + oldVersion +
				" TO " + newVersion + ", WHICH WILL DESTROY ALL OLD DATA");
		db.execSQL("DROP TABLE IF EXISTS " + R.string.table_service);
		onCreate(db);
	}

}
