package com.itcr.clinica.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.itcr.clinica.R;
import com.itcr.custom.dialogs.ScheduleDialog;
import com.itcr.custom.sqlite.DataSourceService;
import com.itcr.custom.sqlite.SqlConstants;
import com.itcr.datastructures.Appointment;

import java.util.List;

public class ScheduleListActivity extends ListFragment {

	public static Appointment selected;

	private String messageDialog;
	private String titleDialogDelete;

	private DataSourceService datasource;
	private List<Appointment> appointments;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		datasource = new DataSourceService(this.getActivity().getBaseContext());
		datasource.open();

		messageDialog = this.getResources().getString(R.string.dialog_message_delete);
		titleDialogDelete = this.getResources().getString(R.string.delete_appointment);

		loadAppointments();
	}




    @Override
    public void onResume(){
        super.onResume();
        loadAppointments();
    }

	public void loadAppointments(){

		Cursor appointmentCursor;
		ListAdapter serviceAdapter;

		appointments = datasource.getAllAppointmentes();
		if(!appointments.isEmpty()){
			appointmentCursor = datasource.getAppointmentesCursor();

			serviceAdapter = new SimpleCursorAdapter(this.getActivity().getBaseContext(),
					R.layout.row_appointment, appointmentCursor, 
					new String[] {SqlConstants.COLUMN_NAME, SqlConstants.COLUMN_DATE},
					new int[] {R.id.name_appointment, R.id.date_appointment}, 1);

			setListAdapter(serviceAdapter);
		}
		else{
			setListAdapter(null);
			String message = getResources().getString(R.string.appointment_toast_message);
			Toast addToast = Toast.makeText(this.getActivity().getBaseContext(), message, Toast.LENGTH_SHORT);
			addToast.show();
		}
	}


	//When an item on the list is clicked
	@Override
	public void onListItemClick(ListView lv, View v, int position, long id){
		if(!appointments.isEmpty()){
			selected = appointments.get(position);
			new ScheduleDialog(this, this.getActivity(), titleDialogDelete, messageDialog, datasource).show(getFragmentManager(), titleDialogDelete);
            loadAppointments();
		}
	}


}