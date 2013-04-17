package com.itcr.clinica.activities;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
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
	private DataSourceService datasource;
	private static int id;
	private List<Appointment> appointments;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		Cursor appointmentCursor;
		ListAdapter serviceAdapter;

		datasource = new DataSourceService(this);
		datasource.open();
		appointments = datasource.getAllAppointmentes();

		if(!appointments.isEmpty()){
			appointmentCursor = datasource.getAppointmentesCursor();

			serviceAdapter = new SimpleCursorAdapter(this,
					R.layout.row_information, appointmentCursor, 
					new String[] {SqlConstants.COLUMN_NAME, SqlConstants.COLUMN_DATE},
					new int[] {R.id.name_appointment, R.id.date_appointment}, 1);

			setListAdapter(serviceAdapter);
		}
		else{
			Toast addToast = new Toast(this);
			addToast.setText("Debe agregar citas primero");
			addToast.show();
		}
	}
	/*
	@Override
	protected void onListItemClick(ListView lv, View v, int position, long id){
		InformationListActivity.id = position;		
		id = position;
		onCreateDialog(DIALOG_INFORMATION);
	}

	@Override
	protected Dialog onCreateDialog(int id){
		AlertDialog dialog = null;
		Builder builder = new AlertDialog.Builder(this);
		String info = "¿Desea Borrar esta cita?";

		switch (id) {
		case DIALOG_INFORMATION:
			builder.setMessage(info);
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
			String website = services.get(InformationListActivity.id).getWeb_Site();
			Intent browser = new Intent(Intent.ACTION_VIEW, 
					Uri.parse(website));
			startActivity(browser);
		}
	}
	*/

}