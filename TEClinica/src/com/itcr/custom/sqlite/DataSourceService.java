package com.itcr.custom.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.itcr.clinica.R;
import com.itcr.datastructures.Service;

public class DataSourceService {

	private SQLiteDatabase database;
	private DataBaseHelper dbHelper;
	private Resources res;

	public DataSourceService(Context context) {
		String dbName;
		int version;

		this.res = context.getResources();
		dbName = res.getString(R.string.database_name);
		version = res.getInteger(R.integer.database_version);

		dbHelper = new DataBaseHelper(context, dbName, version);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Service createService(String name, String information, String website) {
		String[] serviceColumns = {res.getString(R.string.column_id), res.getString(R.string.column_information),
				res.getString(R.string.column_name), res.getString(R.string.column_website)};

		ContentValues values = new ContentValues();

		values.put(res.getString(R.string.column_name), name);
		values.put(res.getString(R.string.column_information), information);
		values.put(res.getString(R.string.column_website), website);

		long insertId = database.insert(res.getString(R.string.table_service), null, values);

		Cursor cursor = database.query(res.getString(R.string.table_service),
				serviceColumns, res.getString(R.string.column_id) + " = " + insertId, null,
				null, null, null);

		cursor.moveToFirst();
		Service newService = cursorToService(cursor);
		cursor.close();
		return newService;
	}

	public void deleteService(Service servicio) {
		long id = servicio.getID();

		Log.d("DeleteService", "deleted id:"+ id);

		database.delete(res.getString(R.string.table_service), res.getString(R.string.column_id) + " = " + id, null);
	}

	public List<Service> getAllService() {
		String[] serviceColumns = {res.getString(R.string.column_id), res.getString(R.string.column_information),
				res.getString(R.string.column_name), res.getString(R.string.column_website)};
		List<Service> services = new ArrayList<Service>();

		Cursor cursor = database.query(res.getString(R.string.table_service) , serviceColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Service service = cursorToService(cursor);
			services.add(service);
			cursor.moveToNext();
		}

		cursor.close();
		return services;
	}

	public Cursor getServiceCursor(){
		String[] serviceColumns = {res.getString(R.string.column_id), res.getString(R.string.column_information),
				res.getString(R.string.column_name), res.getString(R.string.column_website)};

		Cursor cursor = database.query(res.getString(R.string.table_service) , serviceColumns, null, null, null, null, null);
		return cursor;
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
