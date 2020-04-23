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
            p.getOutputStream().write("mkdir storage/extSdCard/ramdisk\n".getBytes());
            p.getOutputStream().write("mount -t ramfs -o size=512m ramfs storage/extSdCard/ramdisk\n".getBytes());
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/ramdisk/swapfile bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/ramdisk/swapfile\n".getBytes());
            p.getOutputStream().write("swapon -p -1 storage/extSdCard/ramdisk/swapfile\n".getBytes());
            //0
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/ramdisk/swapfile0 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup  storage/extSdCard/ramdisk/swapfile0 /dev/block/loop0\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/ramdisk/swapfile0\n".getBytes());
            p.getOutputStream().write("swapon -p 0 storage/extSdCard/ramdisk/swapfile0\n".getBytes());
            //1
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/ramdisk/swapfile1 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup  storage/extSdCard/ramdisk/swapfile1 /dev/block/loop1\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/ramdisk/swapfile1\n".getBytes());
            p.getOutputStream().write("swapon -p 1 storage/extSdCard/ramdisk/swapfile1\n".getBytes());
            //2
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/ramdisk/swapfile2 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup  storage/extSdCard/ramdisk/swapfile2 /dev/block/loop2\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/ramdisk/swapfile2\n".getBytes());
            p.getOutputStream().write("swapon -p 2 storage/extSdCard/ramdisk/swapfile2\n".getBytes());
            //3
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/ramdisk/swapfile3 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup  storage/extSdCard/ramdisk/swapfile3 /dev/block/loop3\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/ramdisk/swapfile3\n".getBytes());
            p.getOutputStream().write("swapon -p 3 storage/extSdCard/ramdisk/swapfile3\n".getBytes());
            //4
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/ramdisk/swapfile4 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup  storage/extSdCard/ramdisk/swapfile4 /dev/block/loop3\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/ramdisk/swapfile4\n".getBytes());
            p.getOutputStream().write("swapon -p 4 storage/extSdCard/ramdisk/swapfile4\n".getBytes());
            //5
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/ramdisk/swapfile5 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup  storage/extSdCard/ramdisk/swapfile5 /dev/block/loop5\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/ramdisk/swapfile5\n".getBytes());
            p.getOutputStream().write("swapon -p 5 storage/extSdCard/ramdisk/swapfile5\n".getBytes());
            //6
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/ramdisk/swapfile6 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup  storage/extSdCard/ramdisk/swapfile6 /dev/block/loop6\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/ramdisk/swapfile6\n".getBytes());
            p.getOutputStream().write("swapon -p 6 storage/extSdCard/ramdisk/swapfile6\n".getBytes());
            //7
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/ramdisk/swapfile7 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup  storage/extSdCard/ramdisk/swapfile7 /dev/block/loop7\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/ramdisk/swapfile7\n".getBytes());
            p.getOutputStream().write("swapon -p 7 storage/extSdCard/ramdisk/swapfile7\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
