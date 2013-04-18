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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.itcr.clinica.R;
import com.itcr.custom.sqlite.DataSourceService;
import com.itcr.custom.sqlite.SqlConstants;
import com.itcr.datastructures.Appointment;

public class ScheduleListActivity extends ListActivity {

	private static final int DIALOG_INFORMATION = 1;
	private static int id;
	private String messageDialog;
	private DataSourceService datasource;
	private List<Appointment> appointments;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		Cursor appointmentCursor;
		ListAdapter serviceAdapter;

		datasource = new DataSourceService(this);
		datasource.open();
		appointments = datasource.getAllAppointmentes();

		messageDialog = this.getResources().getString(R.string.dialog_message_delete);

		if(!appointments.isEmpty()){
			appointmentCursor = datasource.getAppointmentesCursor();

			serviceAdapter = new SimpleCursorAdapter(this,
					R.layout.row_information, appointmentCursor, 
					new String[] {SqlConstants.COLUMN_NAME, SqlConstants.COLUMN_DATE},
					new int[] {R.id.name_appointment, R.id.date_appointment}, 1);

			setListAdapter(serviceAdapter);
		}
		else{
			Toast addToast = Toast.makeText(this, "Debe agregar citas primero", Toast.LENGTH_SHORT);
			addToast.show();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.Appointment:
			clickAddAppointment();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void clickAddAppointment(){
		final Dialog addAppointment = new Dialog(this);
		addAppointment.setContentView(R.layout.appointment_form);
		addAppointment.setTitle("Agregar Cita");

		addAppointment.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.schedule, menu);
		return true;
	}

	@Override
	protected void onListItemClick(ListView lv, View v, int position, long id){		
		id = appointments.get(position).getId();
		onCreateDialog(DIALOG_INFORMATION);
	}

	@Override
	protected Dialog onCreateDialog(int id){
		AlertDialog dialog = null;
		Builder builder = new AlertDialog.Builder(this);

		switch (id) {
		case DIALOG_INFORMATION:
			builder.setMessage(messageDialog);
			builder.setCancelable(true);
			builder.setPositiveButton("Borrar", new OkOnClickListener());
			builder.setNegativeButton("Cancelar", new CancelOnClickListener());
			builder.setIcon(android.R.drawable.ic_menu_delete);
		}
		dialog = builder.create();
		dialog.show();
		return dialog;
	}

	private final class CancelOnClickListener implements
	DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {

		}
	}

	private final class OkOnClickListener implements
	DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			datasource.deleteAppointment(ScheduleListActivity.id);
		}
	}
}
