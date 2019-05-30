package com.lenovo.smarttraffic.Petterp.ShujuFenXi;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.gson.Gson;
import com.lenovo.smarttraffic.Okhttp;
import com.lenovo.smarttraffic.Petterp.Bean.UserBean;
import com.lenovo.smarttraffic.Petterp.Bean.WeiZhang;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * @author Petterp on 2019/5/27
 * Summary:计算违章个数的长度
 * 计算违章个数，然后遍历判断相应的重复数符合相应条件++
 * 邮箱：1509492795@qq.com
 */
public class Item3 extends BaseFragment {
    private HorizontalBarChart chart;
    private int[] sum=new int[3];
    @Override
    protected View getSuccessView() {
        View view = View.inflate(getContext(), R.layout.shuju_item3, null);
        TextView title = view.findViewById(R.id.text);
        title.setText("");
        chart = view.findViewById(R.id.chart);
        init();
        return view;
    }

    @Override
    protected Object requestData() {
        setWeiZhang();
        while (true) {
            if (thread.isInterrupted()) {
                return 1;
            }
        }
    }

    @Override
    public void onClick(View view) {

    }

    private void init() {
        XAxis xAxis = chart.getXAxis();
        xAxis.setAxisMaximum(3f);
        xAxis.setLabelCount(3);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(15f);
        String[] setX = {"1-2条违章", "3-5条违章", "5条以上违章"};
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value > 2 ? "" : setX[(int) value];
            }
        });
        chart.getAxisLeft().setAxisMinimum(0);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setAxisMinimum(0f);
        chart.getAxisRight().setLabelCount(8);
        chart.getAxisRight().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return decimalFormat.format(value) + "%";
            }
        });
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(10, 10, 10, 10);
    }


    private void setWeiZhang() {
        Okhttp.Builder().setOkHttpClient("action/GetAllCarPeccancy.do", "{\"UserName\":\"user1\"}", new Okhttp.Post() {
            @Override
            public void on(String s) {
                Gson gson = new Gson();
                WeiZhang bean = gson.fromJson(s, WeiZhang.class);
                int size = bean.getROWS_DETAIL().size();
                List<String> list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    list.add(bean.getROWS_DETAIL().get(i).getCarnumber());
                }
                HashSet<String> set = new HashSet<>(list);
                for (String data:set){
                    if (Collections.frequency(list,data)>5){
                        ++sum[2];
                    }else if (Collections.frequency(list,data)>2){
                        ++sum[1];
                    }else{
                        ++sum[0];
                    }
                }
                setData();
                thread.interrupt();
            }

            @Override
            public void no() {
                setWeiZhang();
            }
        });
    }

    private void setData() {
        List<BarEntry> list=new ArrayList<>();
        int mode=0;
        for (int i=0;i<3;i++){
            mode+=sum[i];
        }
        for (int i=0;i<3;i++){
            list.add(new BarEntry(i, (float) (sum[i]*1.0/mode)));
        }
        BarDataSet set=new BarDataSet(list,"");
        set.setColors(Color.parseColor("#33FF66"),Color.parseColor("#6984A7"),Color.parseColor("#C23531"));
        set.setValueTextColor(Color.RED);
        set.setValueTextSize(15f);
        BarData data=new BarData(set);
        data.setBarWidth(0.5f);
        chart.setData(data);
        chart.invalidate();
    }
}
