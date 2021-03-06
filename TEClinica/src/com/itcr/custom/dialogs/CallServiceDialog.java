package com.itcr.custom.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.itcr.clinica.R;

import java.util.ArrayList;

public class CallServiceDialog extends DialogFragment{

    private final static String PHONE = "2550-9180";
    private final static String WEBSITE = "http://www.tec.ac.cr/citas" ;

    private Context mContext;

    public CallServiceDialog(Context context){
        this.mContext = context;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Dialog optionDialog = new Dialog(mContext);

        optionDialog.setContentView(R.layout.take_appointment);
        optionDialog.setTitle(getResources().getString(R.string.options));

        ListView listView = (ListView) optionDialog.findViewById(R.id.listview_option);

        ArrayList<String> optionList = new ArrayList<String>();
        optionList.add(PHONE);
        optionList.add(WEBSITE);


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
                        call.setData(Uri.parse("tel:"+ PHONE));
                        startActivity(call);
                        break;
                    case 1:
                        browser.setData(Uri.parse(WEBSITE));
                        startActivity(browser);
                        break;
                }
            }
        });

        return optionDialog;

    }
}
