package com.itcr.clinica.activities;

import java.util.ArrayList;

import com.itcr.clinica.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class TakeAppointmentActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		final Dialog optionDialog = new Dialog(this);
		
		optionDialog.setContentView(R.layout.take_appointment);
		optionDialog.setTitle("Opciones");

		
		
		ListView listView = (ListView) optionDialog.findViewById(R.id.listview_option);
		//Saca la info de un contacto a un ListArray
		final String phone = "2550-9180";
		final String website = "http://www.tec.ac.cr/citas" ;
		
		
		
		ArrayList<String> optionList = new ArrayList<String>();
		optionList.add(phone);
		optionList.add(website);
		
		
		ListAdapter listAdapter = new ArrayAdapter<String>(optionDialog.getContext(), R.layout.simple_layout,optionList);
		
		listView.setAdapter(listAdapter);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id){
				
				Intent call = new Intent (Intent.ACTION_CALL);
				Intent browser = new Intent (Intent.ACTION_VIEW);
				
				switch(position){
				case 0:
					call.setData(Uri.parse("tel:"+phone));
					startActivity(call);
					break;
				case 1:
					browser.setData(Uri.parse(website));
					startActivity(browser);					
					break;
				}
			}
		});
		
		optionDialog.show();
		
	}

}
