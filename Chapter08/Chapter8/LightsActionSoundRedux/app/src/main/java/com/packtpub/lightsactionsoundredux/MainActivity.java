package com.packtpub.lightsactionsoundredux;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    final String CHANNEL_ID="notifications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickLightsActionSound(View view) {
        Uri notificationSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                    .build();
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Notifications", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("All app notifications");
            channel.setSound(notificationSoundUri, audioAttributes);
            channel.setLightColor(Color.BLUE);
            channel.enableLights(true);
            channel.enableVibration(true);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Lights, Action & Sound")
                .setSound(notificationSoundUri)
                .setLights(Color.BLUE, 500, 500)
                .setVibrate(new long[]{250,500,250,500,250,500})
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

        //Action notification
//        NotificationCompat.Builder notificationBuilder = new
//                NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("LightsActionSoundRedux")
//                .setContentText("Lights, Action & Sound");
//        Intent activityIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(
//                this,0,activityIntent,0);
//        notificationBuilder.addAction(android.R.drawable.ic_dialog_email, "Email",
//                pendingIntent);

        //Inbox style
//        NotificationCompat.Builder notificationBuilder =
//                new NotificationCompat.Builder(this, CHANNEL_ID)
//                        .setSmallIcon(R.mipmap.ic_launcher);
//        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
//        inboxStyle.setBigContentTitle("InboxStyle - Big Content Title")
//                .addLine("Line 1")
//                .addLine("Line 2");
//        notificationBuilder.setStyle(inboxStyle);

        //Big picture
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("LightsActionSoundRedux")
//                .setContentText("BigPictureStyle");
//        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
//        bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
//        notificationBuilder.setStyle(bigPictureStyle);

        //Big Text Style
//        NotificationCompat.Builder notificationBuilder =
//                new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("LightsActionSoundRedux");
//        NotificationCompat.BigTextStyle BigTextStyle = new NotificationCompat.BigTextStyle();
//        BigTextStyle.bigText("This is an example of the BigTextStyle expanded notification.");
//        notificationBuilder.setStyle(BigTextStyle);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, notificationBuilder.build());
    }

}
