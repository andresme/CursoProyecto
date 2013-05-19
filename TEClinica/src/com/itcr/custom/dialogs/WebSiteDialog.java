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


public class WebSiteDialog extends DialogFragment {

    private Context mContext;
    String info;
    String website;

    public WebSiteDialog(Context context, String info, String website){
        this.info = info;
        this.website = website;
        this.mContext = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getResources().getString(R.string.offers));
        this.info = this.info.replace("Ofrece: -", "*");
        this.info = this.info.replaceAll(" -","\n*");
        builder.setMessage(info);
        builder.setCancelable(true);
        builder.setPositiveButton(mContext.getResources().getString(R.string.go_web_site),  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
                    startActivity(browser);
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
