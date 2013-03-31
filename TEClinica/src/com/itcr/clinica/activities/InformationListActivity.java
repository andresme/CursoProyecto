package com.itcr.clinica.activities;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.itcr.clinica.R;
import com.itcr.custom.sqlite.DataSourceService;
import com.itcr.datastructures.Service;


public class InformationListActivity extends ListActivity {

	private DataSourceService datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);

		datasource = new DataSourceService(this);

		datasource.open();

		List<Service> values = datasource.getAllService();

		ArrayAdapter<Service> adapter = new ArrayAdapter<Service>(this,R.layout.row_information, values);
		setListAdapter(adapter);

		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView lv, View v, int position, long id){
		//list[position]

	}

}