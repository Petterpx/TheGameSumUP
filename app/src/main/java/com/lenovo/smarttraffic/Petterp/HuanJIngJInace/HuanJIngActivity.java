package com.lenovo.smarttraffic.Petterp.HuanJIngJInace;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.lenovo.smarttraffic.Okhttp;
import com.lenovo.smarttraffic.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 环境监测
 */
public class HuanJIngActivity extends AppCompatActivity {

    private ImageView back;
    private TextView title;
    private TextView title_time;
    private TextView time_update;
    private PieChart chart;
    private TextView title_item;
    private TextView pm_max;
    private TextView pm_min;
    private TextView pm_scale;
    private TextView co_max;
    private TextView co_min;
    private TextView co_scale;
    private TextView gunag_max;
    private TextView guang_min;
    private TextView gunag_scale;
    private TextView shi_max;
    private TextView shi_min;
    private TextView shi_scale;
    private TextView wen_max;
    private TextView wen_min;
    private TextView wen_sacle;
    private CountDownTimer count;
    private float[] sum = new float[5];
    private List<Integer> colors = new ArrayList<>();
    private int a, b, c, d, e;
    private String[] res = {"北京", "上海", "深圳", "重庆", "雄安"};
    private static List<List<Frits>> lists = new ArrayList<>();
    private LinearLayout layout;
    private int mode = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huan_jing);
        initView();
        colors.add(Color.parseColor("#75A1A6"));
        colors.add(Color.parseColor("#6600FF"));
        colors.add(Color.parseColor("#EF5AA1"));
        colors.add(Color.parseColor("#33FF66"));
        colors.add(Color.parseColor("#009DD9"));
        for (int i = 0; i < 5; i++) {
            lists.add(new ArrayList<>());
        }
        count = new CountDownTimer(Integer.MAX_VALUE, 5000) {
            @Override
            public void onTick(long l) {
                for (int i = 0; i < 5; i++) {
                    setPost(i);
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (e.getY() == sum[0]) {
                    title_item.setText("北京");
                    setShuju(0);
                    mode = 0;
                } else if (e.getY() == sum[1]) {
                    title_item.setText("上海");
                    setShuju(1);
                    mode = 1;
                } else if (e.getY() == sum[2]) {
                    title_item.setText("深圳");
                    setShuju(2);
                    mode = 2;
                } else if (e.getY() == sum[3]) {
                    title_item.setText("重庆");
                    setShuju(3);
                    mode = 3;
                } else {
                    title_item.setText("雄安");
                    setShuju(4);
                    mode = 4;
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setTime();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.title);
        title_time = (TextView) findViewById(R.id.title_time);
        time_update = (TextView) findViewById(R.id.time_update);
        chart = (PieChart) findViewById(R.id.chart);
        title_item = (TextView) findViewById(R.id.title_item);
        pm_max = (TextView) findViewById(R.id.pm_max);
        pm_min = (TextView) findViewById(R.id.pm_min);
        pm_scale = (TextView) findViewById(R.id.pm_scale);
        co_max = (TextView) findViewById(R.id.co_max);
        co_min = (TextView) findViewById(R.id.co_min);
        co_scale = (TextView) findViewById(R.id.co_scale);
        gunag_max = (TextView) findViewById(R.id.gunag_max);
        guang_min = (TextView) findViewById(R.id.guang_min);
        gunag_scale = (TextView) findViewById(R.id.gunag_scale);
        shi_max = (TextView) findViewById(R.id.shi_max);
        shi_min = (TextView) findViewById(R.id.shi_min);
        shi_scale = (TextView) findViewById(R.id.shi_scale);
        wen_max = (TextView) findViewById(R.id.wen_max);
        layout = findViewById(R.id.layout);
        wen_min = (TextView) findViewById(R.id.wen_min);
        wen_sacle = (TextView) findViewById(R.id.wen_sacle);
        chart.setDrawCenterText(false);
        chart.setDrawEntryLabels(false);
        chart.setDrawHoleEnabled(false);
        chart.setExtraOffsets(15, 15, 15, 15);
    }

    private void setPost(int i) {
        Okhttp.Builder().setOkHttpClient("action/GetAllSense.do", "{\"UserName\":\"user1\"}", new Okhttp.Post() {
            @Override
            public void on(String s) {
                try {
                    Log.e("demo",s);
                    JSONObject jsonObject = new JSONObject(s);
                    a = jsonObject.getInt("pm2.5");
                    b = jsonObject.getInt("co2");
                    c = jsonObject.getInt("LightIntensity");
                    d = jsonObject.getInt("humidity");
                    e = jsonObject.getInt("temperature");
                    lists.get(i).add(new Frits(a, b, c, d, e));
                    sum[i] = a+i*0.001f;
                    if (i == 4) {
                        setData();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void no() {

            }
        });
    }

    private void setData() {
        Toast.makeText(this, "wo", Toast.LENGTH_SHORT).show();
        if (mode!=9){
            setShuju(mode);
        }
        List<PieEntry> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new PieEntry(sum[i]));
        }
        PieDataSet set = new PieDataSet(list, "");
        set.setValueLinePart1OffsetPercentage(100F);
        set.setValueLinePart1Length(0.8f);
        set.setValueLinePart2Length(0.2f);
        set.setValueTextSize(15f);
        set.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        set.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        set.setValueTextColors(colors);
        set.setColors(colors);
        set.setSliceSpace(10f);
        set.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                for (int i = 0; i < 5; i++) {
                    if (value == sum[i]) {
                        return res[i];
                    }
                }
                return "";
            }
        });
        PieData data = new PieData(set);
        chart.setData(data);
        chart.invalidate();
    }

    @SuppressLint("SetTextI18n")
    private void setShuju(int i) {
        int a = 0, b = 0, c = 0, d = 0, e = 0;
        int size = lists.get(i).size();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();
        List<Integer> list5 = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            a += lists.get(i).get(j).pm;
            b += lists.get(i).get(j).co;
            c += lists.get(i).get(j).guang;
            d += lists.get(i).get(j).shi;
            e += lists.get(i).get(j).wen;
            list1.add(lists.get(i).get(j).pm);
            list2.add(lists.get(i).get(j).co);
            list3.add(lists.get(i).get(j).guang);
            list4.add(lists.get(i).get(j).shi);
            list5.add(lists.get(i).get(j).wen);
        }
        Collections.sort(list1);
        Collections.sort(list2);
        Collections.sort(list3);
        Collections.sort(list4);
        Collections.sort(list5);
        pm_max.setText("" + list1.get(size - 1));
        co_max.setText("" + list2.get(size - 1));
        gunag_max.setText("" + list3.get(size - 1));
        shi_max.setText("" + list4.get(size - 1));
        wen_max.setText("" + list5.get(size - 1));

        pm_min.setText("" + list1.get(0));
        co_min.setText("" + list2.get(0));
        guang_min.setText("" + list3.get(0));
        shi_min.setText("" + list4.get(0));
        wen_min.setText("" + list5.get(0));

        pm_scale.setText("" + a / size);
        co_scale.setText("" + b / size);
        gunag_scale.setText("" + c / size);
        shi_scale.setText("" + d / size);
        wen_sacle.setText("" + e / size);
        layout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (count != null) {
            count.cancel();
            count=null;
        }
    }
    @SuppressLint("SetTextI18n")
    private void setTime(){
        SharedPreferences data=getSharedPreferences("time",MODE_PRIVATE);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time=System.currentTimeMillis();
        Long sptime=data.getLong("time",time);
        int mode= (int) ((time-sptime)/1000);
        if (mode>60){
            time_update.setText("最近更新:"+mode/60+"分钟前");
        }else{
            time_update.setText("最近更新：最新数据");
        }
        title_time.setText(simpleDateFormat.format(time));
        data.edit().putLong("time",time).apply();
    }
}
