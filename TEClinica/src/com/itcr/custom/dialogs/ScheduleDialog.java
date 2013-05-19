package com.itcr.custom.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.itcr.clinica.R;
import com.itcr.clinica.activities.ScheduleListActivity;
import com.itcr.custom.sqlite.DataSourceService;


public class ScheduleDialog extends DialogFragment {

    private ScheduleListActivity mActivity;
    private Context mContext;
    private String mTitle;
    private String mMessage;
    private DataSourceService mDatasource;

    public ScheduleDialog(ScheduleListActivity activity, Context context, String title, String message, DataSourceService datasource){
        this.mActivity = activity;
        this.mContext = context;
        this.mTitle = title;
        this.mMessage = message;
        this.mDatasource = datasource;

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setTitle(mTitle);
        builder.setMessage(mMessage);
        builder.setCancelable(true);
        builder.setPositiveButton(mContext.getResources().getString(R.string.positive),  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mDatasource.deleteAppointment(ScheduleListActivity.selected);
                mActivity.loadAppointments();
            }
        });


        builder.setNegativeButton(mContext.getResources().getString(R.string.negative), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });
        builder.setIcon(android.R.drawable.ic_menu_delete);

        return builder.create();

    }
}
