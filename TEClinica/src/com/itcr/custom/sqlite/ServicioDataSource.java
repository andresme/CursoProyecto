package com.itcr.custom.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.itcr.clinica.R;

public class ServicioDataSource {

	  // Database fields
	  private SQLiteDatabase database;
	  private DataBaseHelper dbHelper;
	  private String[] allColumns = { R.string.column_id,R.string.column_name,
			  R.string.column_information,R.string.column_website};

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
	    values.put(R.string.column_name, name);
	    values.put(R.string.column_information, information);
	    values.put(R.string.column_website, website);
	    long insertId = database.insert(R.string.table_service,null,values);
	    Cursor cursor = database.query(R.string.table_service,
	        allColumns, R.string.column_id + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Servicio newServicio = cursorToServicio(cursor);
	    cursor.close();
	    return newServicio;
	  }

	  public void deleteServicio(Servicio servicio) {
	    long id = servicio.getID();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(R.string.table_service,R.string.column_id+ " = " + id, null);
	  }

	  public List<Servicio> getAllServicios() {
	    List<Servicio> comments = new ArrayList<Servicio>();

	    Cursor cursor = database.query(R.string.table_service,allColumns, null, null, null, null, null);

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

