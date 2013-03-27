package com.itcr.clinica.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.itcr.clinica.R;

public class InformationListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);

		//Obtener de Base de datos 
		//cursor

		//ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.row_information, cursor, new String[] {}, new int[] {R.id.serviceName, R.id.place});

		//setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView lv, View v, int position, long id){
		//list[position]
		
	}

}
