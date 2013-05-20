package com.itcr.custom.dialogs;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.itcr.clinica.R;

public class ConfigurationDialog extends DialogFragment {

    private Context mContext;

    public ConfigurationDialog(Context context){
        this.mContext = context;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Dialog dialog =  new Dialog(mContext);
        dialog.setContentView(R.layout.dialog_config);
        dialog.setTitle(mContext.getResources().getString(R.string.about));

        return dialog;

    }

}
