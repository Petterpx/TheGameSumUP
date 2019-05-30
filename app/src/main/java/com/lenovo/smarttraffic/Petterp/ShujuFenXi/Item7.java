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
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.lenovo.smarttraffic.Okhttp;
import com.lenovo.smarttraffic.Petterp.Bean.WeiZhang;
import com.lenovo.smarttraffic.Petterp.Bean.WeiZhangBean;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author Petterp on 2019/5/27
 * Summary:计算违章前十名占比
 * 需要计算违章bean代码，通过车牌ID计算相应的条目。用MAP储存相应的数据，最后转为List，再排序，然后设置相应的数据
 * 邮箱：1509492795@qq.com
 */
public class Item7 extends BaseFragment {
    private HorizontalBarChart chart;
    private XAxis xAxis;
    private List<String> listuser;
    private List<String> listBean;
    private List<Map.Entry<String, Integer>> lists;

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
        setWeizhangBean();
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
        xAxis = chart.getXAxis();
        xAxis.setAxisMaximum(10f);
        xAxis.setLabelCount(10);
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
                return decimalFormat.format(value * 100) + "%";
            }
        });
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(10, 10, 10, 10);
    }

    private void setWeizhangBean() {
        Okhttp.Builder().setOkHttpClient("action/GetPeccancyType.do", "{\"UserName\":\"user1\"}", new Okhttp.Post() {
            @Override
            public void on(String s) {
                Gson gson = new Gson();
                WeiZhangBean bean = gson.fromJson(s, WeiZhangBean.class);
                int size = bean.getROWS_DETAIL().size();
                listuser = new ArrayList<>();
                listBean = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    listBean.add(bean.getROWS_DETAIL().get(i).getPcode());
                    listuser.add(bean.getROWS_DETAIL().get(i).getPremarks());
                }
                setWeiZhang();
            }

            @Override
            public void no() {
                setWeizhangBean();
            }
        });
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
                    list.add(bean.getROWS_DETAIL().get(i).getPcode());
                }
                HashMap<String, Integer> map = new HashMap<>();
                for (int i = 0; i < listuser.size(); i++) {
                    map.put(listuser.get(i), 0);
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).equals(listBean.get(i))) {
                            int mode = map.get(listuser.get(i));
                            map.put(listuser.get(i), ++mode);
                        }
                    }
                }
                lists = new ArrayList<>(map.entrySet());
                Collections.sort(lists, (t2, t1) -> t1.getValue().compareTo(t2.getValue()));
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
        List<BarEntry> list = new ArrayList<>();
        int mode = 0;
        for (int i = 0; i < 10; i++) {
            mode += lists.get(i).getValue();
        }
        for (int i = 0; i < 10; i++) {
            list.add(new BarEntry(i, (float) (lists.get(i).getValue()* 1.0 / mode)));
        }
        List<Integer> colors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            colors.add(ColorTemplate.VORDIPLOM_COLORS[i]);
        }
        for (int i = 0; i < 5; i++) {
            colors.add(ColorTemplate.LIBERTY_COLORS[i]);
        }
        BarDataSet set = new BarDataSet(list, "");
        set.setColors(colors);
        set.setValueTextColor(Color.RED);
        xAxis.setValueFormatter((value, axis) -> value>9?"":lists.get((int) value).getKey());
        set.setValueTextSize(15f);
        BarData data = new BarData(set);
        data.setBarWidth(0.5f);
        chart.setData(data);
        chart.invalidate();
    }
}
