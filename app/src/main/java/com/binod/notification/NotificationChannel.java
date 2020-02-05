package com.binod.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class NotificationChannel {

    Context context;
    public final static  String CHANNEL_1 = "Channel1";
    public final static  String CHANNEL_2 = "Channel2";

    public NotificationChannel(Context context){
        this.context = context;
    }

    public void createChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            android.app.NotificationChannel Channel = new android.app.NotificationChannel(CHANNEL_1, "Channel 1", NotificationManager.IMPORTANCE_HIGH);
            Channel.setDescription("This is channel 1");


            android.app.NotificationChannel Channel1 = new android.app.NotificationChannel(CHANNEL_2, "Channel 2", NotificationManager.IMPORTANCE_LOW);
            Channel1.setDescription("This is channel 2");


            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(Channel);
            manager.createNotificationChannel(Channel1);
        }
    }
}
