package com.example.ram;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class myservice extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    PackageManager pm;
    ActivityManager mActivityManager;
    File folder;
    boolean delete;
    Timer timer;
    Handler p;
    List<ApplicationInfo> packages;

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    public void onCreate() {
        timer = new Timer();
        pm = getPackageManager();
        packages = pm.getInstalledApplications(0);
        mActivityManager = (ActivityManager) getApplication().getSystemService(Context.ACTIVITY_SERVICE);
        folder = new File("storage/extSdCard/RAM");
        p = new Handler();
        super.onCreate();
    }

    public void freeMemory() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                delete=folder.mkdir();
                delete=folder.mkdirs();
                delete = folder.delete();
                delete = Objects.requireNonNull(folder.getParentFile()).delete();
                p.post(new Runnable() {
                    @Override
                    public void run() {
                        for (ApplicationInfo packageInfo : packages) {
                            if(packageInfo.packageName.equals("com.example.ram")) continue;
                            mActivityManager.killBackgroundProcesses(packageInfo.packageName);
                        }

                        freeMemory();
                       // Toast.makeText(myservice.this, "delete RAM", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }, 1, 50000);
        return super.onStartCommand(intent, flags, startId);
    }
}
