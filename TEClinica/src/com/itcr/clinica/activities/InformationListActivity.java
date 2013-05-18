package com.itcr.clinica.activities;

import java.util.List;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.itcr.clinica.R;
import com.itcr.custom.helpers.WebSiteDialog;
import com.itcr.custom.sqlite.DataSourceService;
import com.itcr.custom.sqlite.SqlConstants;
import com.itcr.datastructures.Service;


public class InformationListActivity extends ListFragment {


	private List<Service> services;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        DataSourceService datasource;

		Cursor serviceCursor;
		ListAdapter serviceAdapter;

		datasource = new DataSourceService(this.getActivity().getBaseContext());
		datasource.open();
		services = datasource.getAllService();
		serviceCursor = datasource.getServiceCursor();

		serviceAdapter = new SimpleCursorAdapter(this.getActivity().getBaseContext(),
				R.layout.row_information, serviceCursor, 
				new String[] {SqlConstants.COLUMN_NAME},
				new int[] {R.id.serviceName}, 1);

		setListAdapter(serviceAdapter);
	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id){
        String website = services.get(position).getWeb_Site();
        String info = services.get(position).getInformation();
		new WebSiteDialog(this.getActivity(), info, website).show(getFragmentManager(), "Information");
	}
}