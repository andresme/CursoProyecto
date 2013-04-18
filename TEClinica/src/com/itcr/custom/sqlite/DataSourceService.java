package com.itcr.custom.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.itcr.datastructures.Appointment;
import com.itcr.datastructures.Contact;
import com.itcr.datastructures.Service;

public class DataSourceService {

	private SQLiteDatabase database;
	private DataBaseHelper dbHelper;

	public DataSourceService(Context context) {
		String dbName;
		int version;

		dbName = SqlConstants.NAME;
		version = SqlConstants.VERSION;

		dbHelper = new DataBaseHelper(context, dbName, version);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Service createService(String name, String information, String website) {
		String[] serviceColumns = {SqlConstants.COLUMN_ID, SqlConstants.COLUMN_INFORMATION, 
				SqlConstants.COLUMN_NAME, SqlConstants.COLUMN_WEBSITE};

		ContentValues values = new ContentValues();

		values.put(SqlConstants.COLUMN_NAME, name);
		values.put(SqlConstants.COLUMN_INFORMATION, information);
		values.put(SqlConstants.COLUMN_WEBSITE, website);

		long insertId = database.insert(SqlConstants.TABLE_SERVICE, null, values);

		Cursor cursor = database.query(SqlConstants.TABLE_SERVICE,
				serviceColumns, SqlConstants.COLUMN_ID + " = " + insertId, null,
				null, null, null);

		cursor.moveToFirst();
		Service newService = cursorToService(cursor);
		cursor.close();
		return newService;
	}

	public void deleteService(Service servicio) {
		long id = servicio.getID();

		Log.d("DeleteService", "deleted id:"+ id);

		database.delete(SqlConstants.TABLE_SERVICE, SqlConstants.COLUMN_ID + " = " + id, null);
	}

	public List<Service> getAllService() {
		String[] serviceColumns = {SqlConstants.COLUMN_ID, SqlConstants.COLUMN_INFORMATION, 
				SqlConstants.COLUMN_NAME, SqlConstants.COLUMN_WEBSITE};
		List<Service> services = new ArrayList<Service>();

		Cursor cursor = database.query(SqlConstants.TABLE_SERVICE , serviceColumns, null, null, 
				null, null, SqlConstants.COLUMN_ID);

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
		String[] serviceColumns = {SqlConstants.COLUMN_ID, SqlConstants.COLUMN_INFORMATION, 
				SqlConstants.COLUMN_NAME, SqlConstants.COLUMN_WEBSITE};

		Cursor cursor = database.query(SqlConstants.TABLE_SERVICE , serviceColumns, null, null, null, null, null);
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

	public Appointment createAppointment(String description, String date){

		String[] appointmentColumns = {SqlConstants.COLUMN_ID, SqlConstants.COLUMN_NAME,
				SqlConstants.COLUMN_DESCRIPTION, SqlConstants.COLUMN_DATE};

		ContentValues values = new ContentValues();

		values.put(SqlConstants.COLUMN_DESCRIPTION, description);
		values.put(SqlConstants.COLUMN_DATE, date);

		long insertId = database.insert(SqlConstants.TABLE_SCHEDULE, null, values);

		Cursor cursor = database.query(SqlConstants.TABLE_SCHEDULE,
				appointmentColumns, SqlConstants.COLUMN_ID + " = " + insertId, null,
				null, null, null);

		cursor.moveToFirst();
		Appointment newAppointment = cursorToAppointment(cursor);
		cursor.close();
		return newAppointment;
	}

	public List<Appointment> getAllAppointmentes(){
		String[] appointmentColumns = {SqlConstants.COLUMN_ID, SqlConstants.COLUMN_NAME,
				SqlConstants.COLUMN_DESCRIPTION, SqlConstants.COLUMN_DATE};
		List<Appointment> appointments = new ArrayList<Appointment>();

		Cursor cursor = database.query(SqlConstants.TABLE_SCHEDULE, appointmentColumns, null, null, null, null, SqlConstants.COLUMN_ID);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Appointment appointment = cursorToAppointment(cursor);
			appointments.add(appointment);
			cursor.moveToNext();
		}

		cursor.close();
		return appointments;

	}

	public Cursor getAppointmentesCursor(){
		String[] appointmentColumns = {SqlConstants.COLUMN_ID, SqlConstants.COLUMN_NAME, 
				SqlConstants.COLUMN_DESCRIPTION, SqlConstants.COLUMN_DATE};

		Cursor cursor = database.query(SqlConstants.TABLE_SCHEDULE, appointmentColumns, null, null, null, null, SqlConstants.COLUMN_ID);

		return cursor;

	}
	
	public void deleteAppointment(long id){
		Log.d("DeleteService", "deleted id:"+ id);
		database.delete(SqlConstants.TABLE_SCHEDULE, SqlConstants.COLUMN_ID + " = " + id, null);
	}



	private Appointment cursorToAppointment(Cursor cursor){
		Appointment appointment = new Appointment();
		appointment.setId(cursor.getLong(0));
		appointment.setName(cursor.getString(1));
		appointment.setEvent(cursor.getString(2));
		appointment.setDescription(cursor.getString(3));

		return appointment;
	}
	
/* Contact*/
	 
	public Contact createContact(String nameC, String phoneO, String cell, String mail){
	
		String[] contactColumns = {SqlConstants.COLUMN_ID, SqlConstants.COLUMN_NAMECONTACT,
				SqlConstants.COLUMN_PHONEOFFICE, SqlConstants.COLUMN_CELL, 
				SqlConstants.COLUMN_MAIL};
		
		ContentValues values = new ContentValues();
		
		values.put(SqlConstants.COLUMN_NAMECONTACT, nameC);
		values.put(SqlConstants.COLUMN_PHONEOFFICE, phoneO);
		values.put(SqlConstants.COLUMN_CELL, cell);
		values.put(SqlConstants.COLUMN_MAIL, mail);
		
		long insertId = database.insert(SqlConstants.TABLE_CONTACT, null, values);
		
		Cursor cursor = database.query(SqlConstants.TABLE_CONTACT,
				contactColumns, SqlConstants.COLUMN_ID = "=" + insertId, null,
				null, null, null);
		
		cursor.moveToFirst();
		Contact newContact = cursorToContact(cursor);
		cursor.close();
		return newContact;
		
	}
	
	public List<Contact> getAllContacts(){
		String[] contactColumns = {SqlConstants.COLUMN_ID, SqlConstants.COLUMN_NAMECONTACT,
				SqlConstants.COLUMN_PHONEOFFICE, SqlConstants.COLUMN_CELL, 
				SqlConstants.COLUMN_MAIL};
		List<Contact> contacts = new ArrayList<Contact>();

		Cursor cursor = database.query(SqlConstants.TABLE_CONTACT, contactColumns, null, null, null, null, SqlConstants.COLUMN_ID);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Contact contact = cursorToContact(cursor);
			contacts.add(contact);
			cursor.moveToNext();
		}

		cursor.close();
		return contacts;

	}

	public Cursor getContactsCursor(){
		String[] contactColumns = {SqlConstants.COLUMN_ID, SqlConstants.COLUMN_NAMECONTACT,
				SqlConstants.COLUMN_PHONEOFFICE, SqlConstants.COLUMN_CELL, 
				SqlConstants.COLUMN_MAIL};

		Cursor cursor = database.query(SqlConstants.TABLE_CONTACT, contactColumns, null, null, null, null, SqlConstants.COLUMN_ID);

		return cursor;

	}
	
	public void deleteContact(long id){
		Log.d("DeleteService", "deleted id:"+ id);
		database.delete(SqlConstants.TABLE_CONTACT, SqlConstants.COLUMN_ID + " = " + id, null);
	}



	private Contact cursorToContact(Cursor cursor){
		Contact contact = new Contact();
		
		contact.setID(cursor.getLong(0));
		contact.setNameContact(cursor.getString(1));
		contact.setPhoneOffice(cursor.getString(2));
		contact.setCell(cursor.getString(3));
		contact.setMail(cursor.getString(4));
		
		return contact;

	}
	
	

}
