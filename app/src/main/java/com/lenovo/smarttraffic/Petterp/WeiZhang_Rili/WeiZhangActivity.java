package com.lenovo.smarttraffic.Petterp.WeiZhang_Rili;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.gson.Gson;
import com.lenovo.smarttraffic.Okhttp;
import com.lenovo.smarttraffic.Petterp.Bean.WeiZhang;
import com.lenovo.smarttraffic.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeiZhangActivity extends AppCompatActivity {

    private ImageView back;
    private TextView title;
    private ImageView rili;
    private TextView time1;
    private TextView time2;
    private LineChart chart;
    private String s1 = "2016-5-1", s2 = "2017-1-1";
    private Long d1, d2;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String[] datas = {"学院路", "联想路", "医院路", "幸福路", "环城快速路", "环城高速"};
    private HashMap<String, Integer> map;
    private int size;
    private List<String> listuser;
    private List<Long> listtime;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_zhang);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("正在请求内容");
        progressDialog.show();
        initView();
        initChart();
        setWeizhang();
    }

    private void initView() {
        try {
            d1=simpleDateFormat.parse(s1).getTime();
            d2=simpleDateFormat.parse(s2).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        back = (ImageView) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.title);
        rili = (ImageView) findViewById(R.id.rili);
        time1 = (TextView) findViewById(R.id.time1);
        time2 = (TextView) findViewById(R.id.time2);
        chart = (LineChart) findViewById(R.id.chart);
        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Dia1(WeiZhangActivity.this, 0).show();
            }
        });
        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Dia1(WeiZhangActivity.this, 1).show();
            }
        });
        rili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Dia2(WeiZhangActivity.this).show();
            }
        });
    }

    private class Dia1 extends Dialog {

        private TextView dia_back;
        private TextView dia_ok;
        private DatePicker dia1;
        private int postion;

        public Dia1(@NonNull Context context, int postion) {
            super(context);
            this.postion = postion;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.weizhang_dia1);
            dia_back = findViewById(R.id.dia_back);
            dia_ok = findViewById(R.id.dia_ok);
            dia1 = findViewById(R.id.dia1);
            String[] res;
            if (postion == 0) {
                res = s1.split("-");
            } else {
                res = s2.split("-");
            }
            dia1.init(Integer.parseInt(res[0]), Integer.parseInt(res[1]) - 1, Integer.parseInt(res[2]), new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {

                }
            });
            dia_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
            dia_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (postion == 0) {
                        s1 = dia1.getYear() + "-" + (dia1.getMonth() + 1) + "-"+dia1.getDayOfMonth();
                        try {
                            d1 = simpleDateFormat.parse(s1).getTime();
                            time1.setText(s1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        s2 = dia1.getYear() + "-" + (dia1.getMonth() + 1) + "-"+dia1.getDayOfMonth();
                        try {
                            d2 = simpleDateFormat.parse(s2).getTime();
                            time2.setText(s2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    dismiss();
                    rinse();
                }
            });
        }
    }

    private class Dia2 extends Dialog {

        private DatePicker dia2;
        private TextView back;
        private TextView ok;

        public Dia2(@NonNull Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.weizhang_dia2);
            dia2=findViewById(R.id.dia2);
            String[] res= s1.split("-");
            dia2.init(Integer.parseInt(res[0]), Integer.parseInt(res[1]) - 1, Integer.parseInt(res[2]), new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {

                }
            });
            findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
            findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    s1 = dia2.getYear() + "-" + (dia2.getMonth() + 1) + "-"+dia2.getDayOfMonth();
                    try {
                        d1 = simpleDateFormat.parse(s1).getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    time1.setText(s1);
                    dismiss();
                    rinse();
                }
            });
        }
    }

    private void initChart() {
        XAxis xAxis = chart.getXAxis();
        xAxis.setLabelCount(6);
        xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(6f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return datas[(int) Math.abs(value % 6)];
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getAxisLeft().setAxisMinimum(0f);
        chart.getAxisLeft().enableGridDashedLine(10, 10, 3);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
    }

    private void setWeizhang() {
        Okhttp.Builder().setOkHttpClient("action/GetAllCarPeccancy.do", "{\"UserName\":\"user1\"}", new Okhttp.Post() {
            @Override
            public void on(String s) {
                Log.e("Demo",s);
                Gson gson = new Gson();
                WeiZhang weiZhang = gson.fromJson(s, WeiZhang.class);
                size = weiZhang.getROWS_DETAIL().size();
                listuser = new ArrayList<>();
                listtime = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    try {
                        listtime.add(simpleDateFormat.parse(weiZhang.getROWS_DETAIL().get(i).getPdatetime()).getTime());
                        listuser.add(weiZhang.getROWS_DETAIL().get(i).getPaddr());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                rinse();
                progressDialog.dismiss();
            }

            @Override
            public void no() {
                setWeizhang();
            }
        });
    }

    private void rinse() {
        map = new HashMap<>(6);
        for (int i = 0; i < 6; i++) {
            map.put(datas[i], 0);
            for (int j = 0; j < size; j++) {
                if (listtime.get(j) >= d1 && listtime.get(j) <= d2 && listuser.get(j).equals(datas[i])) {
                    int mode = map.get(datas[i]);
                    map.put(datas[i], ++mode);
                }
            }
        }
        setData();
    }

    private void setData() {
        List<Entry> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(new Entry(i + 0.5f, map.get(datas[i])));
        }
        LineDataSet set = new LineDataSet(list, "");
        set.setCircleColor(Color.RED);
        set.setColor(Color.RED);
        set.setCircleRadius(10f);
        set.setDrawCircleHole(false);
        set.setLineWidth(3f);
        set.setValueTextColor(Color.RED);
        set.setValueTextSize(15f);
        LineData data = new LineData(set);
        chart.setData(data);
        chart.invalidate();
    }
}
