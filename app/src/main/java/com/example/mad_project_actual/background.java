package com.example.mad_project_actual;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class background extends Service {
    private static final String TAG = "MyForegroundService";
    Location location;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        dashboard d = new dashboard();
        Log.d(TAG, "onStartCommand: ");
        String receiver_mail = intent.getStringExtra("receiver_mail");
        int time = Integer.parseInt(intent.getStringExtra("time"));
        String send_mail = intent.getStringExtra("send_mail") + "@gmail.com";

        startForeground(1, getNotification());

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "latandlong" + location.getLongitude() + " " + location.getLatitude());

//                    d.sendmail(receiver_mail, location.getLatitude(), location.getLongitude(), send_mail);
            }
        }, 0, 5*1000);
//    }, 0, time * 60 * 1000);

        return START_STICKY;
    }

    private Notification getNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My Foreground Service")
                .setContentText("Running in the background");
        return builder.build();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}