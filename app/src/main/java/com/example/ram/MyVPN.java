package com.example.ram;


import android.app.PendingIntent;
import android.content.Intent;
import android.net.VpnService;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class MyVPN extends VpnService {

    private Thread mThread;
    private ParcelFileDescriptor mInterface;
    WifiManager wifiMgr;
    Builder builder = new Builder();
    Handler error;
    //  String ip;
    ByteBuffer packet, packet2;
    int last, current;
    DatagramChannel tunnel;
    Process p;
    protected Intent intentpublic;

    //   byte [] n;
    // Services interface
    /*String getIpAddress() {
        wifiMgr = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        return Formatter.formatIpAddress(wifiMgr.getConnectionInfo().getIpAddress());

    }*/

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int out = super.onStartCommand(intent, flags, startId);
        intentpublic = intent;
        // Start a new session by creating a new thread.
        Toast.makeText(this, "VPN start", Toast.LENGTH_SHORT).show();
        error = new Handler();
//        ip = getIpAddress();
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //a. Configure the TUN and get the interface.
                    mInterface = builder.setSession("MyVPN")
                            .addAddress("192.168.1.2", 24)
                            //        .addAddress("fda8:f5ac:6deb:6400:1521:c626:d3db:aaf9",112)
                            .addSearchDomain("https://www.google.com")
                            .addSearchDomain("https://www.youtube.com")
                            .addSearchDomain("https://www.facebook.com")
                            .addDnsServer("8.8.8.8").addDnsServer("8.8.4.4")
                            .addDnsServer("1.1.1.1").addDnsServer("1.0.0.1")
                            .addDnsServer("9.9.9.9").addDnsServer("149.112.112.112")
                            .addDnsServer("206.71.50.230")
                            .addRoute("0.0.0.0", 0)
                            .addRoute("192.168.1.255", 32)
                            .setConfigureIntent(PendingIntent.getService(MyVPN.this, 0, new Intent(MyVPN.this, new_vpn.class), PendingIntent.FLAG_UPDATE_CURRENT))
                            .setMtu(5000).establish();
                    //b. Packets to be sent are queued in this input stream.
                    assert mInterface != null;
                    FileInputStream in = new FileInputStream(
                            mInterface.getFileDescriptor());
                    //b. Packets received need to be written to this output stream.
                    FileOutputStream out = new FileOutputStream(
                            mInterface.getFileDescriptor());
                    //c. The UDP channel can be used to pass/get ip package to/from server
                    tunnel = DatagramChannel.open();
                    // Connect to the server, localhost is used for demonstration only.
                    tunnel.connect(new InetSocketAddress("192.168.1.1", 80));

                    //d. Protect this socket, so package send by it will not be feedback to the vpn service.
                    protect(tunnel.socket());
                    p.getOutputStream().write("ip link set dev tun0 up mtu 1500\n".getBytes());
                    p.getOutputStream().write("ip link set dev tun0 up qlen 1000\n".getBytes());
                    p.getOutputStream().write("route add default gw 192.168.1.1 tun0\n".getBytes());
                    p.getOutputStream().write(" ifconfig tun0 192.168.1.2 netmask 255.255.255.0 pointopoint 192.168.1.255 up\n".getBytes());

                    //e. Use a loop to pass packets.
                    while (true) {
                        current += in.read(packet.array());
                        if (current > 0) {
                            packet.limit(current + 1);
                            packet.put(current++, (byte) '\n');
                            tunnel.write(packet);
                            packet.clear();
                        }
                        last += tunnel.read(packet2);
                        if (last > 0) {
                            packet2.limit(last + 1);
                            packet2.put(last++, (byte) '\n');
                            out.write(packet2.array());
                            packet2.clear();
                        }
                        Thread.sleep(100);
                    }

                } catch (Exception e) {
                    // Catch any exception
                    error.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MyVPN.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                    e.printStackTrace();
                } finally {
                    try {
                        if (mInterface != null) {
                            packet.clear();
                            packet2.clear();
                            tunnel.disconnect();
                            tunnel.close();
                            mInterface.close();
                            mInterface = null;
                            mThread.interrupt();
                        }
                    } catch (Exception e) {

                    }
                }
            }

        }, "MyVpnRunnable");

        //start the service
        mThread.start();

        return out;
    }

    @Override
    public void onCreate() {
        try {
            p = Runtime.getRuntime().exec("su");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //     n=new byte[100000];
        last = 0;
        current = 0;
        packet = ByteBuffer.allocate(1000);
        packet2 = ByteBuffer.allocate(1000);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        if (mThread != null) {
            mThread.interrupt();
        }
        super.onDestroy();
    }
}
