package com.itcr.custom.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.itcr.clinica.R;

public class DataSourceService {
	// Database fields
		  private SQLiteDatabase database;
		  private DataBaseHelper dbHelper;
		  private String[] allColumns = { R.string.column_id,R.string.column_name,
				  R.string.column_information,R.string.column_website};

		  public DataSourceService(Context context) {
		    dbHelper = new DataBaseHelper(context);
		  }

		  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }

		  public void close() {
		    dbHelper.close();
		  }

		  public Service createService(String name, String information, String website) {
		    ContentValues values = new ContentValues();
		    values.put(R.string.column_name, name);
		    values.put(R.string.column_information, information);
		    values.put(R.string.column_website, website);
		    long insertId = database.insert(R.string.table_service,null,values);
		    Cursor cursor = database.query(R.string.table_service,
		        allColumns, R.string.column_id + " = " + insertId, null,
		        null, null, null);
		    cursor.moveToFirst();
		    Service newService = cursorToService(cursor);
		    cursor.close();
		    return newService;
		  }

		  public void deleteService(Service servicio) {
		    long id = servicio.getID();
		    System.out.println("Comment deleted with id: " + id);
		    database.delete(R.string.table_service,R.string.column_id+ " = " + id, null);
		  }

		  public List<Service> getAllService() {
		    List<Service> services = new ArrayList<Service>();

		    Cursor cursor = database.query(R.string.table_service,allColumns, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Service service = cursorToService(cursor);
		      services.add(service);
		      cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return services;
		  }

		  private Service cursorToService(Cursor cursor) {
		    Service service = new Service();
		    service.setID(cursor.getLong(0));
		    service.setName(cursor.getString(1));
		    service.setInformation(cursor.getString(2));
		    service.setWeb_Site(cursor.getString(3));
		    return service;
		  }

}
