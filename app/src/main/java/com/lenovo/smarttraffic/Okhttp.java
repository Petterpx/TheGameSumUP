package com.lenovo.smarttraffic;

import android.os.Handler;
import android.os.Looper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Okhttp {
    Handler handler=new Handler(Looper.getMainLooper());
    OkHttpClient okHttpClient;
    MediaType JSON=MediaType.parse("application/json");
    public  interface  Post{
        void  on(String s);
        void  no();
    }
    public  static Okhttp Builder(){
        return  Client.okhttp;
    }
    private static class  Client{
        private static Okhttp okhttp=new Okhttp();
    }
    ExecutorService service=Executors.newSingleThreadExecutor();
    public   void   setOkHttpClient(String url,String info,Post post){
        service.execute(new Runnable() {
            @Override
            public void run() {
                okHttpClient=new OkHttpClient();
                RequestBody body=RequestBody.create(JSON,info);
                Request request=new Request
                        .Builder()
                        .url("http://192.168.1.101:8088/transportservice/"+url)
                        .post(body)
                        .build();
                try {
                    Response execute = okHttpClient.newCall(request).execute();
                    String string = execute.body().string();
                    try {
                        JSONObject jsonObject=new JSONObject(string);
                        String result = jsonObject.getString("RESULT");
                        if (result.equals("S")){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    post.on(string);
                                }
                            });
                        }
                    } catch (JSONException e) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                post.no();
                            }
                        });
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
