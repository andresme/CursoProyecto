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

    private final static String HEADER = "Ofrece: -";
    private final static String NEW_HEADER = "*";
    private final static String ITEM_START = " -";
    private final static String NEW_ITEM_START = "\n*";

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
        this.info = this.info.replace(HEADER, NEW_HEADER);
        this.info = this.info.replaceAll(ITEM_START, NEW_ITEM_START);
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
