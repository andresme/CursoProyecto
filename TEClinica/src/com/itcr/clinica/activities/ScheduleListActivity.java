package com.itcr.clinica.activities;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.itcr.clinica.R;
import com.itcr.custom.sqlite.DataSourceService;
import com.itcr.custom.sqlite.SqlConstants;
import com.itcr.datastructures.Appointment;

public class ScheduleListActivity extends ListActivity {

	private static final int DELETE_DIALOG = 1;
	private static Appointment selected;

	private String messageDialog;
	private String positiveDialog;
	private String negativeDialog;
	private String titleDialogAdd;
	private String titleDialogDelete;

	private DataSourceService datasource;
	private List<Appointment> appointments;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		datasource = new DataSourceService(this);
		datasource.open();

		messageDialog = this.getResources().getString(R.string.dialog_message_delete);
		positiveDialog = this.getResources().getString(R.string.positive);
		negativeDialog = this.getResources().getString(R.string.negative);
		titleDialogAdd =  this.getResources().getString(R.string.menu_add_appointment);
		titleDialogDelete = this.getResources().getString(R.string.delete_appointment);

		loadAppointments();
	}

	//Loads appointments from database and put them on the listview
	private void loadAppointments(){

		Cursor appointmentCursor;
		ListAdapter serviceAdapter;

		appointments = datasource.getAllAppointmentes();
		if(!appointments.isEmpty()){
			appointmentCursor = datasource.getAppointmentesCursor();

			serviceAdapter = new SimpleCursorAdapter(this,
					R.layout.row_appointment, appointmentCursor, 
					new String[] {SqlConstants.COLUMN_NAME, SqlConstants.COLUMN_DATE},
					new int[] {R.id.name_appointment, R.id.date_appointment}, 1);

			setListAdapter(serviceAdapter);
		}
		else{
			setListAdapter(null);
			String message = getResources().getString(R.string.appointment_toast_message);
			Toast addToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
			addToast.show();
		}
	}

	//Menu buttons actions
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.Appointment:
			clickAddAppointment();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	//Menu button addAppointment action
	private void clickAddAppointment(){
		final Dialog addAppointment = new Dialog(this);

		addAppointment.setContentView(R.layout.appointment_form);
		addAppointment.setTitle(titleDialogAdd);

		Button addButton = (Button) addAppointment.findViewById(R.id.add_button);
		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText nameET = (EditText) addAppointment.findViewById(R.id.appointment_name);
				EditText descriptionET = (EditText) addAppointment.findViewById(R.id.appointment_description);
				EditText dateET = (EditText) addAppointment.findViewById(R.id.appointment_date);
				EditText timeET = (EditText) addAppointment.findViewById(R.id.appointment_time);

				String name = nameET.getText().toString();
				String description = descriptionET.getText().toString();
				String date = dateET.getText().toString();
				String time = timeET.getText().toString();

				if(name.equals("") || description.equals("") || date.equals("") || time.equals("")){
					String message = getResources().getString(R.string.appointment_toast_form_error_fields);
					Toast addToast = Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT);
					addToast.show();
				}
				else{
					datasource.createAppointment(name, description, date+":-:"+time);
					String message = getResources().getString(R.string.appointment_toast_form_accept);
					Toast addToast = Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT);
					addToast.show();
				}
				loadAppointments();
				addAppointment.dismiss();
			}
		});

		addAppointment.show();
	}


	//Creates the menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.schedule, menu);
		return true;
	}
	
	//When an item on the list is clicked
	@Override
	protected void onListItemClick(ListView lv, View v, int position, long id){
		if(!appointments.isEmpty()){
			selected = appointments.get(position);
			onCreateDialog(DELETE_DIALOG);
		}
	}
	
	//Creates a dialog
	@Override
	protected Dialog onCreateDialog(int id){
		AlertDialog dialog = null;
		Builder builder = new AlertDialog.Builder(this);

		switch (id) {
		//Delete Dialog.
		case DELETE_DIALOG:
			builder.setTitle(titleDialogDelete);
			builder.setMessage(messageDialog);
			builder.setCancelable(true);
			builder.setPositiveButton(positiveDialog, new OkOnClickListener());
			builder.setNegativeButton(negativeDialog, new CancelOnClickListener());
			builder.setIcon(android.R.drawable.ic_menu_delete);
		}
		dialog = builder.create();
		dialog.show();
		return dialog;
	}

	//The cancel action
	private final class CancelOnClickListener implements
	DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
		}
	}

	//The Accept action
	private final class OkOnClickListener implements
	DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			datasource.deleteAppointment(ScheduleListActivity.selected);
			loadAppointments();
		}
	}
}
