package com.example.ram;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


import java.io.IOException;

public class Start extends BroadcastReceiver  {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "RAM", Toast.LENGTH_LONG).show();
        context.startService(new Intent(context, myservice.class));

      //  context.startService(new Intent(context, MyVPN.class));
        try {
            //all
            Process p = Runtime.getRuntime().exec("su");
            p.getOutputStream().write("sysctl vm.swappiness=100\n".getBytes());
            p.getOutputStream().write("sync ; sysctl vm.drop_caches=3\n".getBytes());
            p.getOutputStream().write("mount -o move storage/extSdCard /data/local/kali-armhf/sdcard\n".getBytes());
            p.getOutputStream().write("mount -o bind /data/local/kali-armhf/sdcard storage/extSdCard\n".getBytes());
            p.getOutputStream().write("mkdir -p /data/local/kali-armhf/sdcard/RAM\n".getBytes());
            p.getOutputStream().write("dd if=/dev/zero of=/data/local/kali-armhf/sdcard/RAM/swapfile bs=1024 count=75000 conv=swab\n".getBytes());
            p.getOutputStream().write("mkswap /data/local/kali-armhf/sdcard/RAM/swapfile 75000\n".getBytes());
            p.getOutputStream().write("swapon -p -1 /data/local/kali-armhf/sdcard/RAM/swapfile\n".getBytes());
            for (int i = 0; i < 8; i++) {
                p.getOutputStream().write(("dd if=/dev/zero of=/data/local/kali-armhf/sdcard/RAM/swapfile" + i + " bs=1024 count=75000 conv=swab\n").getBytes());
                p.getOutputStream().write(("losetup -d /data/local/kali-armhf/sdcard/RAM/swapfile" + i + " /dev/block/loop" + i + "\n").getBytes());
                p.getOutputStream().write(("mkswap /data/local/kali-armhf/sdcard/RAM/swapfile" + i + " 75000\n").getBytes());
                p.getOutputStream().write(("swapon -p " + i + " /data/local/kali-armhf/sdcard/RAM/swapfile" + i + "\n").getBytes());
            }
            //dev
            p.getOutputStream().write("losetup /data/local/kali-armhf/sdcard/RAM /dev\n".getBytes());
            p.getOutputStream().write("killall -9 com.google.android.music:main\n".getBytes());
            p.getOutputStream().write("pm disable com.google.android.music\n".getBytes());

        } catch (IOException e) {
            Toast.makeText(context, "faild", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}