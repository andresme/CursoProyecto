package com.itcr.clinica.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.itcr.clinica.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void mapActivity(View v){
		Intent mapIntent = new Intent(this, MapActivity.class);
		startActivity(mapIntent);
	}

	public void informationActivity(View v){
		Intent informationIntent = new Intent(this, InformationListActivity.class);
		startActivity(informationIntent);
	}
	
	public void scheduleActivity(View v){
		Intent scheduleActivity = new Intent(this, ScheduleListActivity.class);
		startActivity(scheduleActivity);
	}
	
	public void contactActivity(View v){
		Intent contactActivity = new Intent(this, ContactListActivity.class);
		startActivity(contactActivity);
	}
	
	public void emergencyCall(View v){
		Intent call = new Intent (Intent.ACTION_CALL);
		call.setData(Uri.parse("tel:"+25509111));
		startActivity(call);
	}

}
