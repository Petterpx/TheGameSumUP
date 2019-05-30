package com.lenovo.smarttraffic.Petterp.Shenghuo;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.lenovo.smarttraffic.Okhttp;
import com.lenovo.smarttraffic.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 生活助手
 */
public class ShenghuoActivity extends AppCompatActivity {

    private ImageView back;
    private TextView title;
    private TextView wendu;
    private TextView jintian;
    private ImageView refresh;
    private LineChart chart;
    private TextView zi;
    private TextView zi1;
    private TextView zi2;
    private TextView gan;
    private TextView gan1;
    private TextView gan2;
    private TextView chuan;
    private TextView chuan1;
    private TextView chuan2;
    private TextView yong;
    private TextView yong1;
    private TextView yong2;
    private TextView kong;
    private TextView kong1;
    private TextView kong2;
    private ViewPager viewpager;
    private TextView view1;
    private TextView view2;
    private TextView view3;
    private TextView view4;
    private Item1 item1;
    private Item2 item2;
    private Item3 item3;
    private Item4 item4;
    private int a,b,c,d,e;
    private boolean mode=true;
    private CountDownTimer count;
    private List<Entry> max;
    private List<Entry> min;
    int[] colors={Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN,Color.GREEN};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shenghuo);
        initView();
        init();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.title);
        wendu = (TextView) findViewById(R.id.wendu);
        jintian = (TextView) findViewById(R.id.jintian);
        refresh = (ImageView) findViewById(R.id.refresh);
        chart = (LineChart) findViewById(R.id.chart);
        zi = (TextView) findViewById(R.id.zi);
        zi1 = (TextView) findViewById(R.id.zi1);
        zi2 = (TextView) findViewById(R.id.zi2);
        gan = (TextView) findViewById(R.id.gan);
        gan1 = (TextView) findViewById(R.id.gan1);
        gan2 = (TextView) findViewById(R.id.gan2);
        chuan = (TextView) findViewById(R.id.chuan);
        chuan1 = (TextView) findViewById(R.id.chuan1);
        chuan2 = (TextView) findViewById(R.id.chuan2);
        yong = (TextView) findViewById(R.id.yong);
        yong1 = (TextView) findViewById(R.id.yong1);
        yong2 = (TextView) findViewById(R.id.yong2);
        kong = (TextView) findViewById(R.id.kong);
        kong1 = (TextView) findViewById(R.id.kong1);
        kong2 = (TextView) findViewById(R.id.kong2);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        view1 = (TextView) findViewById(R.id.view1);
        view2 = (TextView) findViewById(R.id.view2);
        view3 = (TextView) findViewById(R.id.view3);
        view4 = (TextView) findViewById(R.id.view4);
    }
    public void setView(View view){
        view1.setBackgroundColor(Color.WHITE);
        view2.setBackgroundColor(Color.WHITE);
        view3.setBackgroundColor(Color.WHITE);
        view4.setBackgroundColor(Color.WHITE);
        view.setBackgroundResource(R.drawable.shenghuo_item1);
    }
    private void init(){
        List<Fragment> list=new ArrayList<>();
        list.add(item1=new Item1());
        list.add(item2=new Item2());
        list.add(item3=new Item3());
        list.add(item4=new Item4());
        viewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:setView(view1);break;
                    case 1:setView(view2);break;
                    case 2:setView(view3);break;
                    case 3:setView(view4);break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpager.setOffscreenPageLimit(3);
        setWendu();
        count=new CountDownTimer(Integer.MAX_VALUE,3000) {
            @Override
            public void onTick(long l) {
                setShenghuo();
            }

            @Override
            public void onFinish() {

            }
        };
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWendu();
            }
        });
    }

    private void setWendu(){
        max = new ArrayList<>();
        min = new ArrayList<>();
        Okhttp.Builder().setOkHttpClient("action/GetWeather.do", "{\"UserName\":\"user1\"}", new Okhttp.Post() {
            @Override
            public void on(String s) {
                try {
                    Log.e("demo",s);
                    JSONObject jsonObject=new JSONObject(s);
                    wendu.setText(jsonObject.getString("WCurrent"));
                    JSONArray jsonArray=new JSONArray(jsonObject.getString("ROWS_DETAIL"));
                    for (int i=0;i<jsonArray.length();i++){
                        String[] res=jsonArray.getJSONObject(i).getString("temperature").split("~");
                        max.add(new Entry(i,Integer.parseInt(res[1])));
                        min.add(new Entry(i,Integer.parseInt(res[0])));
                        if (Integer.parseInt(res[1])>25){
                            colors[i]=Color.RED;
                        }else{
                            colors[i]=Color.GREEN;
                        }
                        if (i==1){
                            jintian.setText("今天："+res[0]+"-"+res[1]+"°C");
                        }
                    }
                    if (mode){
                        count.start();
                        mode=false;
                    }
                    initChart();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void no() {

            }
        });
    }
    private void setShenghuo(){
        Okhttp.Builder().setOkHttpClient("action/GetAllSense.do", "{\"UserName\":\"user1\"}", new Okhttp.Post() {
            @SuppressLint("SetTextI18n")
            @Override
            public void on(String s) {
                try {
                    JSONObject jsonObject=new JSONObject(s);
                    a=jsonObject.getInt("pm2.5");
                    b=jsonObject.getInt("co2");
                    c=jsonObject.getInt("LightIntensity");
                    d=jsonObject.getInt("humidity");
                    e=jsonObject.getInt("temperature");
                    item1.setData(a);
                    item2.setData(b);
                    item3.setData(c);
                    item4.setData(d);
                    if (a>1000){
                        zi1.setText("弱("+a+")");
                        zi2.setText("辐射较弱，涂擦SPF12~15、PA+护肤品");
                    }else{
                        zi1.setText("弱("+a+")");
                        zi2.setText("辐射较弱，涂擦SPF12~15、PA+护肤品");
                    }
                    if (b>1000){
                        gan1.setText("较易发("+b+")");
                        gan2.setText("温度低，风较大，较易发生感冒，注意防护");
                    }else{
                        gan1.setText("较易发("+b+")");
                        gan2.setText("温度低，风较大，较易发生感冒，注意防护");
                    }
                    if (c>1000){
                        chuan1.setText("冷("+c+")");
                        chuan2.setText("建议穿长袖衬衫、单裤等服装");
                    }else{
                        chuan1.setText("冷("+c+")");
                        chuan2.setText("建议穿长袖衬衫、单裤等服装");
                    }
                    if (d>1000){
                        yong1.setText("适宜("+d+")");
                        yong2.setText("辐射较弱，涂擦SPF12~15、PA+护肤品");
                    }else{
                        yong1.setText("适宜("+d+")");
                        yong2.setText("辐射较弱，涂擦SPF12~15、PA+护肤品");
                    }
                    if (e>1000){
                        kong1.setText("优("+e+")");
                        kong2.setText("空气质量非常好，非常适合户外活动，趁机出去多呼吸新鲜空气");
                    }else{
                        kong1.setText("优("+e+")");
                        kong2.setText("空气质量非常好，非常适合户外活动，趁机出去多呼吸新鲜空气");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void no() {
                setShenghuo();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (count != null) {
            count.cancel();
            count=null;
        }
    }
    private void initChart(){
        XAxis xAxis=chart.getXAxis();
        xAxis.setLabelCount(6,true);
        String[] setx={"昨天","今天","明天","周五","周六","周日"};
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value>5?"":setx[(int) value];
            }
        });
        xAxis.setDrawGridLines(false);
        chart.setDrawBorders(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextColor(Color.BLUE);
        xAxis.setTextSize(15f);
        chart.setExtraOffsets(20,10,20,0);
        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getAxisLeft().setDrawLabels(false);
        chart.getAxisRight().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);
        LineDataSet set1=new LineDataSet(max,"");
        LineDataSet set2=new LineDataSet(min,"");
        set1.setColor(Color.RED);
        set2.setColor(Color.BLUE);
        set1.setLineWidth(2f);
        set2.setLineWidth(2f);
        set1.setDrawCircleHole(false);
        set2.setDrawCircleHole(false);
        set1.setCircleRadius(3f);
        set2.setCircleRadius(3f);
        set1.setCircleColors(colors);
        set2.setCircleColor(Color.BLUE);
        LineData data=new LineData(set1,set2);
        chart.setData(data);
        chart.invalidate();
    }
}
