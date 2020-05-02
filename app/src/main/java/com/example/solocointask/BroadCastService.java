package com.example.solocointask;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BroadCastService extends Service {

    private final static String TAG = "BroadCastService";

    GeofenceTransitionService geofenceTransitionService;

    public static final String COUNTDOWN_BR = "com.example.solocointask.countdown_br";
    Intent bi = new Intent(COUNTDOWN_BR);
    int count = 10;

    CountDownTimer cdt = null;



    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "Starting timer...");




        cdt = new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished / 1000);
                String text = String.format(Locale.getDefault(), "%02d : %02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);

                bi.putExtra("countdown", text);
                sendBroadcast(bi);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "Timer finished");
                cdt.start();

            }
        };

        cdt.start();
    }


    @Override
    public void onDestroy() {

        Log.i(TAG, "Timer cancelled");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}