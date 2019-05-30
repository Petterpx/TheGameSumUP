package com.lenovo.smarttraffic.Petterp.TongZhiLan;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lenovo.smarttraffic.R;

public class TongZhiActivity extends AppCompatActivity implements View.OnClickListener {

    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong_zhi);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        manager = (NotificationManager) getApplication().getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:tongzhi();break;
            case R.id.button2:stopNo();break;
        }
    }
    private void startNo(){
        NotificationCompat.Builder  builder=new NotificationCompat
                .Builder(getApplicationContext(),"")
                .setSmallIcon(R.drawable.shadow_left)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentText("弹出通知栏")
                .setContentTitle("标题");
        if (manager != null) {
            manager.notify(1,builder.build());
        }
    }

    private void tongzhi(){
        Notification notification=new NotificationCompat.Builder(getApplicationContext(),"")
                .setContentText("涉及到卡来到")
                .setContentTitle("title")
                .setSmallIcon(R.drawable.shadow_left)
                .build();
        if (manager != null) {
            manager.notify(1,notification);
        }
    }


    private void stopNo(){
        if (manager != null) {
            manager.cancel(1);
        }
    }

}
