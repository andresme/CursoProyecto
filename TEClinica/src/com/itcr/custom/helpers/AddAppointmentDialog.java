package com.itcr.custom.helpers;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.*;
import com.itcr.clinica.R;
import com.itcr.clinica.activities.ScheduleListActivity;
import com.itcr.custom.sqlite.DataSourceService;


public class AddAppointmentDialog extends DialogFragment {

    private ScheduleListActivity mActivity;
    private Context mContext;
    private String mTitle;
    private DataSourceService mDatasource;

    public AddAppointmentDialog(ScheduleListActivity activity, Context context, String title, DataSourceService datasource){
        this.mActivity = activity;
        this.mContext = context;
        this.mTitle = title;
        this.mDatasource = datasource;

    }

    public AddAppointmentDialog(Context context, String title, DataSourceService datasource){
        this.mActivity = null;
        this.mContext = context;
        this.mTitle = title;
        this.mDatasource = datasource;

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Dialog addAppointment =  new Dialog(mContext);
        addAppointment.setContentView(R.layout.appointment_form);
        addAppointment.setTitle(mTitle+"                                           ");

        Button addButton = (Button) addAppointment.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText nameET = (EditText) addAppointment.findViewById(R.id.appointment_name);
                EditText descriptionET = (EditText) addAppointment.findViewById(R.id.appointment_description);
                DatePicker dateP = (DatePicker) addAppointment.findViewById(R.id.datePicker1);
                TimePicker timeP = (TimePicker) addAppointment.findViewById(R.id.timePicker1);

                String name = nameET.getText().toString();
                String description = descriptionET.getText().toString();
                String date = dateP.getDayOfMonth() + "/" + dateP.getMonth() + "/" + dateP.getYear();
                String time = timeP.getCurrentHour() + ":" + timeP.getCurrentMinute();

                if (name.equals("") || description.equals("") || date.equals("") || time.equals("")) {
                    String message = getResources().getString(R.string.appointment_toast_form_error_fields);
                    Toast addToast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
                    addToast.show();
                } else {
                    mDatasource.createAppointment(name, description, date + " - " + time);
                    String message = getResources().getString(R.string.appointment_toast_form_accept);
                    Toast addToast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
                    addToast.show();
                    if(mActivity != null)
                        mActivity.loadAppointments();
                }
                addAppointment.dismiss();
            }
        });
        return addAppointment;

    }
}
