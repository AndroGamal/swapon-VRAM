package com.example.ram;

import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.QuickContactBadge;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Timer timer = new Timer();
    Animation animation;
    ImageView imageView2, imageView;
    Handler handler = new Handler();

    public void vpnstart() {
        Intent intent = MyVPN.prepare(getApplicationContext());
        if (intent != null) {
            startActivityForResult(intent, 0);
        } else {
            onActivityResult(0, RESULT_OK, null);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Intent intent = new Intent(this, MyVPN.class);
            startService(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        imageView2 = findViewById(R.id.imageView2);
        imageView = findViewById(R.id.imageView);
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView2.startAnimation(animation);
        new Start().onReceive(this, new Intent());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (new File("storage/extSdCard/RAM/swapfile7").exists()) {
                            animation.cancel();
                            imageView2.clearAnimation();
                            imageView2.setVisibility(View.INVISIBLE);
                            imageView.setImageResource(R.drawable.done);
                            timer.cancel();
                        }
                    }

                });
            }
        }, 0, 60);
        startService(new Intent(this, myservice.class));
        imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        vpnstart();
    }
});
    }
}
