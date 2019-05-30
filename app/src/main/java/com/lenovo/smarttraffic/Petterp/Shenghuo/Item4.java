package com.lenovo.smarttraffic.Petterp.Shenghuo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.lenovo.smarttraffic.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Petterp on 2019/5/27
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class Item4 extends Fragment {

    private TextView text;
    private BarChart chart;
    private List<Integer> sum=new ArrayList<>();
    private List<BarEntry> list=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shenghuo_item1, container, false);
        initView(view);
        init();
        return view;
    }

    private void initView(View view) {
        text = (TextView) view.findViewById(R.id.text);
        chart=view.findViewById(R.id.chart);
    }

    private void init(){
        XAxis xAxis=chart.getXAxis();
        xAxis.setAxisMaximum(25f);
        xAxis.setLabelCount(20);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        List<String> list=new ArrayList<>();
        list.add("03");
        list.add("06");
        list.add("09");
        for (int i=12;i<61;i+=3){
            list.add(i+"");
        }
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value>19?"":list.get((int) value);
            }
        });
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setAxisMinimum(0f);
        chart.getAxisLeft().setTextSize(15f);
        xAxis.setTextSize(15f);
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);
    }
    public void setData(int k){
        list.add(0,new BarEntry(0,k));
        sum.add(0,k);
        for (int i=0;i<list.size();i++){
            list.get(i).setX(i);
        }
        if (list.size()>20){
            list.remove(20);
            List<Integer> sumbf=new ArrayList<>();
            sumbf.addAll(sum);
            sum.remove(20);
            Collections.sort(sumbf);
            text.setText("过去1分钟空气质量最差值："+sumbf.get(20));
        }
        BarDataSet set=new BarDataSet(list,"");
        set.setColor(Color.parseColor("#747474"));
        BarData data=new BarData(set);
        data.setBarWidth(0.5f);
        chart.setData(data);
        chart.invalidate();
    }

}
