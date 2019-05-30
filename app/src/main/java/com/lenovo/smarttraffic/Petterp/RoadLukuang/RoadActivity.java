package com.lenovo.smarttraffic.Petterp.RoadLukuang;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.lenovo.smarttraffic.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoadActivity extends AppCompatActivity {

    private ImageView back;
    private TextView title;
    private BarChart chart;
    private ExecutorService executorService=Executors.newScheduledThreadPool(7);
    private List<float[]> sums=new ArrayList<>();
    private List<List<BarEntry>> lists=new ArrayList<>();
    private List<BarDataSet> sets=new ArrayList<>();
    private CountDownTimer count;
    private boolean[] mode=new boolean[7];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road);
        initView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        count=new CountDownTimer(Integer.MAX_VALUE,5000) {
            @Override
            public void onTick(long l) {
                for (int i=0;i<7;i++){
                    int finalI = i;
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            for (int j=0;j<7;j++){
                                setPost(j,sums.get(finalI));
                                if (finalI ==6&&j==6){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            setData();
                                        }
                                    });
                                }
                            }
                        }
                    });
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }


    private void init(){
        XAxis xAxis=chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(7);
        String[] data={"周一","周二","周三","周四","周五","周六","周日"};
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return null;
            }
        });
        xAxis.setTextSize(15f);
        xAxis.setAxisMaximum(7f);
        xAxis.setAxisMinimum(0f);
        xAxis.setCenterAxisLabels(true);

        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getAxisLeft().setAxisMinimum(0f);
        chart.getAxisLeft().setAxisMaximum(5.2f);
        chart.getAxisRight().setAxisMinimum(0f);
        chart.getAxisRight().setLabelCount(6,true);
        chart.getAxisRight().setAxisMaximum(5f);
        chart.getAxisLeft().setYOffset(-45);
        String[] setx={"畅通","缓行","一般拥堵","中度拥堵","严重拥堵"};
        chart.getAxisLeft().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return setx[(int) Math.abs(value%5)];
            }
        });
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.title);
        chart = (BarChart) findViewById(R.id.chart);
    }
    private void setPost(int i,float[] data){
            OkHttpRoad.Builder().setOkHttpClient("action/GetRoadStatus.do", "{\"RoadId\":"+(i+1)+",\"UserName\":\"user1\"}", new OkHttpRoad.Post() {
                @Override
                public void on(String s) {
                    try {
                        JSONObject jsonObject=new JSONObject(s);
                        data[i]=jsonObject.getInt("Status");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void no() {

                }
            });
    }


    private void rinse(){
        for (int i=0;i<7;i++){
            if (mode[i]){
                for (int j=0;j<7;j++){
                    sums.get(i)[j]=0;
                }
            }else{
                for (int j=0;j<7;j++){
                    if (sums.get(i)[j]==0){
                        sums.get(i)[j]=0;
                    }
                }
            }
        }
    }

    private void setData() {
        lists.clear();
        sets.clear();
        for (int i=0;i<7;i++){
            lists.add(new ArrayList<>());
            for(int j=0;j<7;j++){
                lists.get(i).add(new BarEntry(j,sums.get(i)[j]));
            }
            sets.add(new BarDataSet(lists.get(i),""));
        }
        BarData data=new BarData(sets.get(0),sets.get(1),sets.get(2),sets.get(3),sets.get(4),sets.get(5),sets.get(6));
        data.setBarWidth((1-0.3f-0.14f)/7);
        data.groupBars(0,0.3f,0.02f);
        chart.setData(data);
        chart.invalidate();
    }

}
