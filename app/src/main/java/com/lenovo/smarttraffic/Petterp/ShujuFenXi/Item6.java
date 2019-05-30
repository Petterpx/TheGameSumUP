package com.lenovo.smarttraffic.Petterp.ShujuFenXi;

import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;
import com.lenovo.smarttraffic.Okhttp;
import com.lenovo.smarttraffic.Petterp.Bean.WeiZhang;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author Petterp on 2019/5/27
 * Summary:计算不同时段用户违章比
 * 土办法，截取违章时间10-12位,符合相应条件的++
 * 邮箱：1509492795@qq.com
 */
public class Item6 extends BaseFragment {
    private BarChart chart;
    private int[] sum = new int[12];

    @Override
    protected View getSuccessView() {
        View view = View.inflate(getContext(), R.layout.shuju_item4, null);
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

    private void setWeiZhang() {
        Okhttp.Builder().setOkHttpClient("action/GetAllCarPeccancy.do", "{\"UserName\":\"user1\"}", new Okhttp.Post() {
            @Override
            public void on(String s) {
                Gson gson = new Gson();
                WeiZhang bean = gson.fromJson(s, WeiZhang.class);
                int size = bean.getROWS_DETAIL().size();
                List<String> list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    list.add(bean.getROWS_DETAIL().get(i).getPdatetime().substring(10, 12));
                }
                for (int i = 0; i < size; i++) {
                    String res = list.get(i);
                    if (res.equals("00") || res.equals("01")) {
                        ++sum[0];
                    } else if (res.equals("02") || res.equals("03")) {
                        ++sum[1];
                    } else if (res.equals("04") || res.equals("05")) {
                        ++sum[2];
                    } else if (res.equals("06") || res.equals("07")) {
                        ++sum[3];
                    } else if (res.equals("08") || res.equals("09")) {
                        ++sum[4];
                    } else if (res.equals("10") || res.equals("11")) {
                        ++sum[5];
                    } else if (res.equals("12") || res.equals("13")) {
                        ++sum[6];
                    } else if (res.equals("14") || res.equals("15")) {
                        ++sum[7];
                    } else if (res.equals("16") || res.equals("17")) {
                        ++sum[8];
                    } else if (res.equals("18") || res.equals("19")) {
                        ++sum[9];
                    } else if (res.equals("20") || res.equals("21")) {
                        ++sum[10];
                    } else {
                        ++sum[11];
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

    private void init() {
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(12);
        xAxis.setLabelRotationAngle(-45);
        xAxis.setTextSize(15f);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(12F);
        String[] setx = {
                "0:00:01-2:00:00", "2:00:01-4:00:00",
                "4:00:01-6:00:00", "6:00:01-8:00:00",
                "8:00:01-10:00:00", "10:00:01-12:00:00",
                "12:00:01-14:00:00", "14:00:01-16:00:00",
                "16:00:01-18:00:00", "18:00:01-20:00:00",
                "20:00:01-22:00:00", "22:00:01-24:00:00"};
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value > 11 ? "" : setx[(int) value];
            }
        });
        chart.getAxisLeft().setAxisMinimum(0f);
        chart.getAxisLeft().setLabelCount(8);
        chart.getAxisLeft().setTextSize(15f);
        chart.getAxisRight().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
    }


    private void setData() {
        List<BarEntry> list = new ArrayList<>();
        int mode=0;
        for (int i = 0; i < 12; i++) {
            mode+=sum[i];
        }
        for (int i=0;i<12;i++){
            list.add(new BarEntry(i, (float) (sum[i]*1.0/mode)));
        }
        List<Integer> colors=new ArrayList<>();
        for (int i=0;i<5;i++){
            colors.add(ColorTemplate.VORDIPLOM_COLORS[i]);
        }
        for (int i=0;i<5;i++){
            colors.add(ColorTemplate.LIBERTY_COLORS[i]);
        }
        for (int i=0;i<2;i++){
            colors.add(ColorTemplate.PASTEL_COLORS[i]);
        }
        BarDataSet set=new BarDataSet(list,"");
        set.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return decimalFormat.format(value*100)+"%";
            }
        });
        set.setColors(colors);
        BarData data=new BarData(set);
        data.setBarWidth(0.5f);
        chart.setData(data);
        chart.invalidate();
    }
}
