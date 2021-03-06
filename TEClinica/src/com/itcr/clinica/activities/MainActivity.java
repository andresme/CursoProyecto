package com.itcr.clinica.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;
import com.itcr.clinica.R;
import com.itcr.custom.dialogs.*;
import com.itcr.custom.sqlite.DataSourceService;

public class MainActivity extends FragmentActivity {


    private FragmentTabHost mTabHost;
    private DataSourceService mDatasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_holder);

        mDatasource = new DataSourceService(this.getBaseContext());
        mDatasource.open();

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec(getResources().getString(R.string.services)).setIndicator(getResources().getString(R.string.services)),
                InformationListActivity.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(getResources().getString(R.string.map)).setIndicator(getResources().getString(R.string.map)),
                MapActivity.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(getResources().getString(R.string.schedule)).setIndicator(getResources().getString(R.string.schedule)),
                ScheduleListActivity.class, null);

	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.appointment:
                try{
                ScheduleListActivity activity = (ScheduleListActivity) getSupportFragmentManager().findFragmentByTag(mTabHost.getCurrentTabTag());
                    new AddAppointmentDialog(activity, this, getResources().getString(R.string.menu_add_appointment), mDatasource)
                            .show(getSupportFragmentManager(), getResources().getString(R.string.menu_add_appointment));
                } catch(ClassCastException e){
                    new AddAppointmentDialog(this, getResources().getString(R.string.menu_add_appointment), mDatasource)
                            .show(getSupportFragmentManager(), getResources().getString(R.string.menu_add_appointment));
                }
                return true;
            case R.id.menu_contacts:
                Intent contactIntent = new Intent(this, ContactListActivity.class);
                startActivity(contactIntent);
                return true;
            case R.id.menu_emergency:
                new ConfirmDialog(this).show(getSupportFragmentManager(), getResources().getString(R.string.confirm));
                return true;
            case R.id.menu_call:
                new CallServiceDialog(this).show(getSupportFragmentManager(), getResources().getString(R.string.options));
                return true;
            case R.id.about:
                new AboutDialog(this).show(getSupportFragmentManager(), getResources().getString(R.string.about));
                return true;
            case R.id.notification:
                new ConfigurationDialog(this).show(getSupportFragmentManager(), getResources().getString(R.string.notification));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}