package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.IOException;

public class Start extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {  //all
            Process p=Runtime.getRuntime().exec("su");
            p.getOutputStream().write("sysctl vm.swappiness=100\n".getBytes());
            p.getOutputStream().write("mkdir -p /storage/extSdCard/RAM\n".getBytes());
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/RAM/swapfile bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/RAM/swapfile\n".getBytes());
            p.getOutputStream().write("swapon -p -1 storage/extSdCard/RAM/swapfile\n".getBytes());
            //0
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/RAM/swapfile0 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup -d storage/extSdCard/RAM/swapfile0 /dev/block/loop0\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/RAM/swapfile0\n".getBytes());
            p.getOutputStream().write("swapon -p 0 storage/extSdCard/RAM/swapfile0\n".getBytes());
            //1
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/RAM/swapfile1 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup -d storage/extSdCard/RAM/swapfile1 /dev/block/loop1\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/RAM/swapfile1\n".getBytes());
            p.getOutputStream().write("swapon -p 1 storage/extSdCard/RAM/swapfile1\n".getBytes());
            //2
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/RAM/swapfile2 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup -d storage/extSdCard/RAM/swapfile2 /dev/block/loop2\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/RAM/swapfile2\n".getBytes());
            p.getOutputStream().write("swapon -p 2 storage/extSdCard/RAM/swapfile2\n".getBytes());
            //3
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/RAM/swapfile3 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup -d storage/extSdCard/RAM/swapfile3 /dev/block/loop3\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/RAM/swapfile3\n".getBytes());
            p.getOutputStream().write("swapon -p 3 storage/extSdCard/RAM/swapfile3\n".getBytes());
            //4
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/RAM/swapfile4 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup -d storage/extSdCard/RAM/swapfile4 /dev/block/loop3\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/RAM/swapfile4\n".getBytes());
            p.getOutputStream().write("swapon -p 4 storage/extSdCard/RAM/swapfile4\n".getBytes());
            //5
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/RAM/swapfile5 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup -d storage/extSdCard/RAM/swapfile5 /dev/block/loop5\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/RAM/swapfile5\n".getBytes());
            p.getOutputStream().write("swapon -p 5 storage/extSdCard/RAM/swapfile5\n".getBytes());
            //6
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/RAM/swapfile6 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup -d storage/extSdCard/RAM/swapfile6 /dev/block/loop6\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/RAM/swapfile6\n".getBytes());
            p.getOutputStream().write("swapon -p 6 storage/extSdCard/RAM/swapfile6\n".getBytes());
            //7
            p.getOutputStream().write("dd if=/dev/zero of=storage/extSdCard/RAM/swapfile7 bs=1024 count=75000\n".getBytes());
            p.getOutputStream().write("losetup -d storage/extSdCard/RAM/swapfile7 /dev/block/loop7\n".getBytes());
            p.getOutputStream().write("mkswap storage/extSdCard/RAM/swapfile7\n".getBytes());
            p.getOutputStream().write("swapon -p 7 storage/extSdCard/RAM/swapfile7\n".getBytes());
            //dev
            p.getOutputStream().write("mount -o bind /dev storage/extSdCard/RAM\n".getBytes());
            p.getOutputStream().write("losetup storage/extSdCard/RAM /dev\n".getBytes());
        } catch (IOException e) {
            Toast.makeText(context, "faild", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
