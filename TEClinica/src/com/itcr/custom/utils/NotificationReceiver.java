package com.itcr.custom.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.itcr.clinica.R;
import com.itcr.clinica.activities.MainActivity;


public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent paramIntent){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification(R.drawable.ic_launcher, "Cita Pendiente", System.currentTimeMillis());
        notification.setLatestEventInfo(context, "Clinica", "Cita Pendiente", pendingIntent);
        notificationManager.notify(1, notification);
    }

}
