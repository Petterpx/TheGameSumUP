package com.lenovo.smarttraffic.Petterp.shenghuochuxing;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.lenovo.smarttraffic.Okhttp;
import com.lenovo.smarttraffic.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ShenghuoActivity extends AppCompatActivity {

    private ImageView back;
    private TextView title;
    private TextView wendu;
    private ImageView refresh;
    private TextView time1;
    private TextView time2;
    private TextView time3;
    private TextView time4;
    private TextView time5;
    private TextView time6;
    private LineChart chart;
    private List<Entry> max = new ArrayList<>();
    private List<Entry> min = new ArrayList<>();
    private TextView wendu_1;
    private List<TextView> listtime = new ArrayList<>();
    private ProgressDialog progressDialog;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
    private TextView update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setTitle("正在请求");
        setContentView(R.layout.activity_shenghuo2);
        initView();
        init();
        progressDialog.show();
        setPost();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.title);
        wendu = (TextView) findViewById(R.id.wendu);
        refresh = (ImageView) findViewById(R.id.refresh);
        time1 = (TextView) findViewById(R.id.time1);
        time2 = (TextView) findViewById(R.id.time2);
        time3 = (TextView) findViewById(R.id.time3);
        time4 = (TextView) findViewById(R.id.time4);
        time5 = (TextView) findViewById(R.id.time5);
        time6 = (TextView) findViewById(R.id.time6);
        chart = (LineChart) findViewById(R.id.chart);
        wendu_1 = (TextView) findViewById(R.id.wendu_1);
        listtime.add(time1);
        listtime.add(time2);
        listtime.add(time3);
        listtime.add(time4);
        listtime.add(time5);
        listtime.add(time6);
        title.setText("天气信息");
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                setPost();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        update = (TextView) findViewById(R.id.update);
    }

    private void setPost() {
        max.clear();
        min.clear();
        Okhttp.Builder().setOkHttpClient("action/GetWeather.do", "{\"UserName\":\"user1\"}", new Okhttp.Post() {
            @SuppressLint("SetTextI18n")
            @Override
            public void on(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    wendu.setText(jsonObject.getString("WCurrent"));
                    JSONArray jsonArray = new JSONArray(jsonObject.getString("ROWS_DETAIL"));
                    wendu_1.setText(jsonArray.getJSONObject(1).getString("type"));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String[] data = jsonArray.getJSONObject(i).getString("temperature").split("~");
                        min.add(new Entry(i, Integer.parseInt(data[0])));
                        max.add(new Entry(i, Integer.parseInt(data[1])));
                        String[] res = jsonArray.getJSONObject(i).getString("WData").split("-");
                        listtime.get(i).setText(res[1] + "/" + res[2]);
                    }
                    progressDialog.dismiss();
                    setData();
                    update.setText(simpleDateFormat.format(System.currentTimeMillis()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void no() {
                setPost();
            }
        });
    }

    private void setData() {
        LineDataSet set1 = new LineDataSet(max, "");
        LineDataSet set2 = new LineDataSet(min, "");
        set1.setDrawCircleHole(false);
        set1.setCircleRadius(10f);
        set1.setColor(Color.BLUE);
        set1.setCircleColor(Color.BLUE);
        set1.setLineWidth(3f);

        set2.setDrawCircleHole(false);
        set2.setCircleRadius(10f);
        set2.setColor(Color.GREEN);
        set2.setCircleColor(Color.BLUE);
        set2.setLineWidth(3f);
        LineData data = new LineData(set1, set2);
        chart.setData(data);
        chart.invalidate();
    }
    private void init(){
        chart.getXAxis().setEnabled(false);
        chart.getXAxis().setLabelCount(6,true);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
    }
}
