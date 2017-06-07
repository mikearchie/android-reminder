package com.murach.reminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import static android.app.Notification.PRIORITY_HIGH;
import static android.app.Notification.PRIORITY_MAX;

public class ReminderService extends Service {
    private Timer timer;

    public ReminderService() {}

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Reminder app", "Service bound - not used!");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Reminder app", "ReminderService started.");
        startTimer();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("Reminder app", "ReminderService stopped.");
        stopTimer();
    }

    private void startTimer() {
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
            Log.d("Reminder app", "Look into the distance. It's good for your eyes!");
            // display notification
            sendNotification("Look into the distance. It's good for your eyes!");
            }
        };

        timer = new Timer(true);
        int delay = 1000 * 10;      // 10 seconds
        int interval = 1000 * 10;   // 10 seconds
        timer.schedule(task, delay, interval);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private void sendNotification(String text) {

        // create the variables for the notification
        int icon = R.drawable.ic_launcher;
        CharSequence tickerText = text;
        CharSequence contentTitle = getText(R.string.app_name);
        CharSequence contentText = text;

        // create the notification and set its data
        Notification notification =
                new Notification.Builder(this)
                        .setSmallIcon(icon)
                        .setTicker(tickerText)
                        .setContentTitle(contentTitle)
                        .setContentText(contentText)
                        .build();

        // display the notification
        NotificationManager manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        final int NOTIFICATION_ID = 1;
        manager.notify(NOTIFICATION_ID, notification);
    }
}
