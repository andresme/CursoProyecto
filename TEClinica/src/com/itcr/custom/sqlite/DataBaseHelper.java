package com.itcr.custom.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.itcr.clinica.R;

public class DataBaseHelper extends SQLiteOpenHelper {


	private Context context;

	public DataBaseHelper(Context context, String databaseName, int version) {
		super(context, databaseName, null, version);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase database){
		String[] sql = this.context.getResources().getStringArray(R.array.database_create);

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
