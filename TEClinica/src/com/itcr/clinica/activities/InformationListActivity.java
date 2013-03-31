package com.itcr.clinica.activities;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.itcr.clinica.R;
import com.itcr.custom.sqlite.DataSourceService;


public class InformationListActivity extends ListActivity {

	private static final int DIALOG_INFORMATION = 1;
	private DataSourceService datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);

		datasource = new DataSourceService(this);
		datasource.open();

		Cursor serviceCursor = datasource.getServiceCursor();

		ListAdapter adapter = new SimpleCursorAdapter(this, 
				R.layout.row_information, serviceCursor, 
				new String[] {getResources().getString(R.string.column_name), 
				getResources().getString(R.string.column_website)},
				new int[] {R.id.serviceName, R.string.column_website}, 1);

		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView lv, View v, int position, long id){
		onCreateDialog(DIALOG_INFORMATION);
	}

	@Override
	protected Dialog onCreateDialog(int id){
		AlertDialog dialog = null;
		switch (id) {
		case DIALOG_INFORMATION:
			// Create out AlterDialog
			String info = null; //= information from DB;
			Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(info);
			builder.setCancelable(true);
			builder.setPositiveButton("Ir a sitio web", new OkOnClickListener());
			builder.setNegativeButton("Salir", new CancelOnClickListener());
			dialog = builder.create();
			dialog.show();
		}
		return dialog;
	}

	private final class CancelOnClickListener implements
	DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			//visit website
		}
	}

	private final class OkOnClickListener implements
	DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			//exit pop-up
		}
	}

}