package com.example.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "example_channel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        Button btnBigText = findViewById(R.id.btnBigText);
        Button btnBigPicture = findViewById(R.id.btnBigPicture);
        Button btnInboxStyle = findViewById(R.id.btnInboxStyle);
        //------------Listenery dla buttonów wywaołujące funkcje------------//
        btnBigText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBigTextNotification();
            }
        });
        btnBigPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBigPictureNotification();
            }
        });
        btnInboxStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInboxStyleNotification();
            }
        });
    }

    //------------Kanał powiadomień------------//
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Kanał powiadomień";
            String description = "Opis kanału powiadomień";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            channel.enableLights(true);
            channel.setLightColor(Color.BLUE);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    //------------Powiadomienie z długim tekstem------------//
    private void showBigTextNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Powiadomienie z długim tekstem")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("To jest bardzo długi tekst, który pojawia się, gdy użytkownik kliknie przycisk. Dzięki BigTextStyle można pokazać dużo więcej treści w jednym powiadomieniu."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        NotificationManagerCompat.from(this).notify(1, builder.build());
    }
    //------------Powiadomienie z obrazem------------//
    private void showBigPictureNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_menu_camera)
                .setContentTitle("Powiadomienie z obrazem")
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.dicepust))
                        .bigLargeIcon((Bitmap) null))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        NotificationManagerCompat.from(this).notify(2, builder.build());
    }
    //------------Powiadomienie z wieloma liniami tekstu------------//
    private void showInboxStyleNotification() {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
                .addLine("Linia 1: Powiadomienie")
                .addLine("Linia 2: z wieloma")
                .addLine("Linia 3: liniami tekstu")
                .addLine("Linia 4: dzięki stylowi InboxStyle.");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setContentTitle("Powiadomienie InboxStyle")
                .setStyle(inboxStyle)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        NotificationManagerCompat.from(this).notify(3, builder.build());
    }
}
