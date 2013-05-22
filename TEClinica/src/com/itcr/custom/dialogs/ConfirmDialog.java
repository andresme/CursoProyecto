package com.itcr.custom.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.itcr.clinica.R;

/**
 * Created by andres on 5/22/13.
 */
public class ConfirmDialog extends DialogFragment {


    private Context mContext;
    String info;

    public ConfirmDialog(Context context){
        this.mContext = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getResources().getString(R.string.confirm));
        builder.setMessage(mContext.getResources().getString(R.string.confirm_msg));
        builder.setCancelable(true);
        builder.setPositiveButton(mContext.getResources().getString(R.string.positive),  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent call = new Intent (Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:" + 25509111));
                startActivity(call);
            }
        });


        builder.setNegativeButton(mContext.getResources().getString(R.string.negative), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        builder.setIcon(android.R.drawable.ic_dialog_info);

        return builder.create();
    }
}
