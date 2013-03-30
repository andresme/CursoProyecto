package com.itcr.clinica.activities;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.itcr.clinica.R;
import com.itcr.custom.sqlite.DataSourceService;
import com.itcr.custom.sqlite.Service;



public class InformationListActivity extends ListActivity {
	
	private DataSourceService datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);

		//Obtener de Base de datos 
		//cursor
		datasource = new DataSourceService(this);
		
		datasource.open();
		
		List<Service> values = datasource.getAllService();
		
		// Use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    ArrayAdapter<Service> adapter = new ArrayAdapter<Service>(this,R.layout.row_information, values);
	    setListAdapter(adapter);
		

		//ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.row_information, cursor, new String[] {}, new int[] {R.id.serviceName, R.id.place});

		//setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView lv, View v, int position, long id){
		//list[position]
		
	}

}
