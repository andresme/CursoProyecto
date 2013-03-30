package com.itcr.custom.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ServicioDataSource {

	  // Database fields
	  private SQLiteDatabase database;
	  private DataBaseHelper dbHelper;
	  private String[] allColumns = { DataBaseHelper.COLUMN_ID,
			  DataBaseHelper.COLUMN_NAME, DataBaseHelper.COLUMN_INFORMATION,
			  DataBaseHelper.COLUMN_WEBSITE};

	  public ServicioDataSource(Context context) {
	    dbHelper = new DataBaseHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public Servicio createServicio(String name, String information, String website) {
	    ContentValues values = new ContentValues();
	    values.put(DataBaseHelper.COLUMN_NAME, name);
	    values.put(DataBaseHelper.COLUMN_INFORMATION, information);
	    values.put(DataBaseHelper.COLUMN_WEBSITE, website);
	    long insertId = database.insert(DataBaseHelper.TABLE,null,values);
	    Cursor cursor = database.query(DataBaseHelper.TABLE,
	        allColumns, DataBaseHelper.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Servicio newServicio = cursorToServicio(cursor);
	    cursor.close();
	    return newServicio;
	  }

	  public void deleteServicio(Servicio servicio) {
	    long id = servicio.getID();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(DataBaseHelper.TABLE,DataBaseHelper.COLUMN_ID + " = " + id, null);
	  }

	  public List<Servicio> getAllServicios() {
	    List<Servicio> comments = new ArrayList<Servicio>();

	    Cursor cursor = database.query(DataBaseHelper.TABLE,allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Servicio comment = cursorToServicio(cursor);
	      comments.add(comment);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return comments;
	  }

	  private Servicio cursorToServicio(Cursor cursor) {
	    Servicio servicio = new Servicio();
	    servicio.setID(cursor.getLong(0));
	    servicio.setName(cursor.getString(1));
	    servicio.setInformation(cursor.getString(2));
	    servicio.setWeb_Site(cursor.getString(3));
	    return servicio;
	  }
} 

