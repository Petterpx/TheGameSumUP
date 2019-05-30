package com.lenovo.smarttraffic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Amoly
 * @date 2019/4/11.
 * description：
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences data=getSharedPreferences("data",MODE_PRIVATE);
        if (data.getBoolean("login",false)){
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            data.edit().putBoolean("login",true).apply();
            InitApp.getHandler().postDelayed(() -> {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            },1000);
        }
    }

}
