package com.example.bible;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent_bible = new Intent(context, MainActivity.class);
        intent_bible.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, intent_bible, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.app_icon)
                .setContentTitle(context.getResources().getString(R.string.title_notif_bible))
                .setContentText(context.getResources().getString(R.string.msg_notif_bible))
                .setSound(sound)
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelID = "b-sharikov";
            NotificationChannel channel = new NotificationChannel(channelID, "Sharikov Bible", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
            builder.setChannelId(channelID);
        }

        manager.notify(100, builder.build());

        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_WEEK);

        if (SettingPreferences.getNotifyPrayer(context)) {
            if (today == Calendar.TUESDAY || today == Calendar.FRIDAY) {
                builder.setContentTitle(context.getResources().getString(R.string.title_notif_pray))
                        .setContentText(context.getResources().getString(R.string.msg_notif_pray));
                manager.notify(101, builder.build());
            }
        }
    }
}
