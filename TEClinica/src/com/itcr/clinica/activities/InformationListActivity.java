package com.itcr.clinica.activities;

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

import com.itcr.clinica.R;
import com.itcr.custom.sqlite.DataSourceService;


public class InformationListActivity extends ListActivity {

	private static final int DIALOG_INFORMATION = 1;
	private DataSourceService datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);

		Cursor serviceCursor;
		ListAdapter serviceAdapter;

		datasource = new DataSourceService(this);
		datasource.open();

		serviceCursor = datasource.getServiceCursor();

		serviceAdapter = new SimpleCursorAdapter(this, 
				R.layout.row_information, serviceCursor, 
				new String[] {getResources().getString(R.string.column_name), 
				getResources().getString(R.string.column_website)},
				new int[] {R.id.serviceName, R.string.column_website}, 1);

		setListAdapter(serviceAdapter);
	}

	@Override
	protected void onListItemClick(ListView lv, View v, int position, long id){
		onCreateDialog(DIALOG_INFORMATION);
	}

	@Override
	protected Dialog onCreateDialog(int id){
		AlertDialog dialog = null;
		Builder builder = new AlertDialog.Builder(this);
		String info = null; //= information from DB of service;
		
		switch (id) {
		case DIALOG_INFORMATION:
			builder.setMessage(info);
			builder.setCancelable(true);
			builder.setPositiveButton("Ir a sitio web", new OkOnClickListener());
			builder.setNegativeButton("Cancelar", new CancelOnClickListener());
			builder.setIcon(android.R.drawable.ic_menu_info_details);
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
			String website = null; //= website from DB;
			Intent browser = new Intent(Intent.ACTION_VIEW, 
					Uri.parse(website));
			startActivity(browser);
		}
	}

}