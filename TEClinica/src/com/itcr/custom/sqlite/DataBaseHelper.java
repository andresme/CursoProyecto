package com.itcr.custom.sqlite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

	private Context context;

	public DataBaseHelper(Context context, String databaseName, int version) {
		super(context, databaseName, null, version);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase database){ 		
		InputStream script = null;
		try {
			script = context.getAssets().open("creation.sql");
			if (script != null){
				database.beginTransaction();
				BufferedReader reader = new BufferedReader(new InputStreamReader(script));
				String line = reader.readLine();
				while (!TextUtils.isEmpty(line)) {
					database.execSQL(line);
					line = reader.readLine();
				}
				database.setTransactionSuccessful();
				database.endTransaction();
			}

		} catch (IOException e) {
			Log.e("SQLite","Error filling DB");
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
