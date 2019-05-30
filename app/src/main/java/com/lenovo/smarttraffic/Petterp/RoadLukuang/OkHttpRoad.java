package com.lenovo.smarttraffic.Petterp.RoadLukuang;

import android.os.Handler;
import android.os.Looper;

import com.lenovo.smarttraffic.Okhttp;

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

/**
 * @author Petterp on 2019/5/28
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class OkHttpRoad {
    Handler handler = new Handler(Looper.getMainLooper());
    OkHttpClient okHttpClient;
    MediaType JSON = MediaType.parse("application/json");

    public interface Post {
        void on(String s);

        void no();
    }

    public static OkHttpRoad Builder() {
        return OkHttpRoad.Client.okhttp;
    }

    private static class Client {
        private static OkHttpRoad okhttp = new OkHttpRoad();
    }

    ExecutorService service = Executors.newSingleThreadExecutor();

    public void setOkHttpClient(String url, String info, OkHttpRoad.Post post) {
        okHttpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, info);
        Request request = new Request
                .Builder()
                .url("http://192.168.1.102:8088/transportservice/" + url)
                .post(body)
                .build();
        try {
            Response execute = okHttpClient.newCall(request).execute();
            String string = execute.body().string();
            try {
                JSONObject jsonObject = new JSONObject(string);
                String result = jsonObject.getString("RESULT");
                if (result.equals("S")) {

                    post.on(string);

                }
            } catch (JSONException e) {
                post.no();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
