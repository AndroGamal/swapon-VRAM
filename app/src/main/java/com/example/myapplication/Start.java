package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.io.IOException;

public class Start extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            //all
            Process p=Runtime.getRuntime().exec("su");
            p.getOutputStream().write("sysctl vm.swappiness=100\n".getBytes());
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/swapfile bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/swapfile\n".getBytes());
            p.getOutputStream().write("swapon -p -1 storage/extSdCard/swapfile\n".getBytes());
            //0
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/swapfile0 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup  storage/extSdCard/swapfile0 /dev/block/loop0\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/swapfile0\n".getBytes());
            p.getOutputStream().write("swapon -p 0 storage/extSdCard/swapfile0\n".getBytes());
            //1
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/swapfile1 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup  storage/extSdCard/swapfile1 /dev/block/loop1\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/swapfile1\n".getBytes());
            p.getOutputStream().write("swapon -p 1 storage/extSdCard/swapfile1\n".getBytes());
            //2
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/swapfile2 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup  storage/extSdCard/swapfile2 /dev/block/loop2\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/swapfile2\n".getBytes());
            p.getOutputStream().write("swapon -p 2 storage/extSdCard/swapfile2\n".getBytes());
            //3
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/swapfile3 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup  storage/extSdCard/swapfile3 /dev/block/loop3\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/swapfile3\n".getBytes());
            p.getOutputStream().write("swapon -p 3 storage/extSdCard/swapfile3\n".getBytes());
            //4
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/swapfile4 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup  storage/extSdCard/swapfile4 /dev/block/loop3\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/swapfile4\n".getBytes());
            p.getOutputStream().write("swapon -p 4 storage/extSdCard/swapfile4\n".getBytes());
            //5
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/swapfile5 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup  storage/extSdCard/swapfile5 /dev/block/loop5\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/swapfile5\n".getBytes());
            p.getOutputStream().write("swapon -p 5 storage/extSdCard/swapfile5\n".getBytes());
            //6
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/swapfile6 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup  storage/extSdCard/swapfile6 /dev/block/loop6\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/swapfile6\n".getBytes());
            p.getOutputStream().write("swapon -p 6 storage/extSdCard/swapfile6\n".getBytes());
            //7
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/swapfile7 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup  storage/extSdCard/swapfile7 /dev/block/loop7\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/swapfile7\n".getBytes());
            p.getOutputStream().write("swapon -p 7 storage/extSdCard/swapfile7\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
