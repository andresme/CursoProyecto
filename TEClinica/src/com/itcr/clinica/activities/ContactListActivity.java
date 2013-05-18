package com.itcr.clinica.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.itcr.clinica.R;
import com.itcr.custom.sqlite.DataSourceService;
import com.itcr.custom.sqlite.SqlConstants;
import com.itcr.datastructures.Contact;


public class ContactListActivity extends ListFragment {

	

	private static int id;
	private List<Contact> contacts;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        DataSourceService datasource;

        Cursor contactCursor;
		ListAdapter contactAdapter;

		datasource = new DataSourceService(this.getActivity().getBaseContext());
		datasource.open();
		contacts = datasource.getAllContacts();
		for(Contact i : contacts){
			Log.d("test", i.getNameContact()+ i.getDescription());

		}
		contactCursor = datasource.getContactsCursor();

		contactAdapter = new SimpleCursorAdapter(this.getActivity().getBaseContext(),
				R.layout.row_contact, contactCursor,
				new String[] {SqlConstants.COLUMN_NAMECONTACT, SqlConstants.COLUMN_POSITION},
				new int[] {R.id.contactName, R.id.position }, 1);




		setListAdapter(contactAdapter);
	}

	@Override
    public void onListItemClick(ListView lv, View v, int position, long id){
		final Dialog contactDialog = new Dialog(this.getActivity().getBaseContext());
		ContactListActivity.id = position;


		contactDialog.setContentView(R.layout.contact_listview);
		contactDialog.setTitle("Contactar");

		ListView listView = (ListView) contactDialog.findViewById(R.id.listview_contacts);
		//Saca la info de un contacto a un ListArray
		final String phone = contacts.get(ContactListActivity.id).getPhoneOffice();
		final String cell = contacts.get(ContactListActivity.id).getCell();
		final String mail = contacts.get(ContactListActivity.id).getMail();
		
		
		ArrayList<String> infoList = new ArrayList<String>();
		infoList.add(phone);
		infoList.add(cell);
		infoList.add(mail);
		
		ListAdapter listAdapter = new ArrayAdapter<String>(contactDialog.getContext(), R.layout.simple_layout, infoList);
		
		listView.setAdapter(listAdapter);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id){
				
				Intent call = new Intent (Intent.ACTION_CALL);
				Intent email = new Intent(Intent.ACTION_SEND);
				
				switch(position){
				case 0:
					call.setData(Uri.parse("tel:"+phone));
					startActivity(call);
					break;
				case 1:
					call.setData(Uri.parse("tel:"+cell));
					startActivity(call);
					break;
				case 2:
					email.putExtra(Intent.EXTRA_EMAIL, new String[]{mail});
					email.setType("message/rfc822");
					startActivity(Intent.createChooser(email, "Choose an Email client :"));
					break;
				}
				
				
			}
		});
		
		contactDialog.show();
	}

}