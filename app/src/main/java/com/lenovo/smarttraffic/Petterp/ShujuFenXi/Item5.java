package com.lenovo.smarttraffic.Petterp.ShujuFenXi;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;
import com.lenovo.smarttraffic.Okhttp;
import com.lenovo.smarttraffic.Petterp.Bean.UserBean;
import com.lenovo.smarttraffic.Petterp.Bean.WeiZhang;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Petterp on 2019/5/27
 * Summary:计算不同性别是否违章占比。
 * -> 首先判断否是平台用户，是的话，添加平台身份证，
 * 通过身份证信息做数据清洗。
 * 邮箱：1509492795@qq.com
 */
public class Item5 extends BaseFragment {
    private BarChart chart;
    private List<String> listuser;
    private List<String> listbean;
    private int[] sum1=new int[2];
    private int[] sum2=new int[2];

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
        setUser();
        while (true) {
            if (thread.isInterrupted()) {
                return 1;
            }
        }
    }

    @Override
    public void onClick(View view) {

    }

    private void setUser() {
        Okhttp.Builder().setOkHttpClient("action/GetCarInfo.do", "{\"UserName\":\"user1\"}", new Okhttp.Post() {
            @Override
            public void on(String s) {
                Gson gson = new Gson();
                UserBean bean = gson.fromJson(s, UserBean.class);
                int size = bean.getROWS_DETAIL().size();
                listuser = new ArrayList<>();
                listbean = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    listuser.add(bean.getROWS_DETAIL().get(i).getPcardid());
                    listbean.add(bean.getROWS_DETAIL().get(i).getCarnumber());
                }
                setWeiZhang();
            }

            @Override
            public void no() {
                setUser();
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
                HashSet<String> set = new HashSet<>();
                for (int i=0;i<size;i++){
                    set.add(bean.getROWS_DETAIL().get(i).getCarnumber());
                }
                List<String> list=new ArrayList<>();
                for (String data:set){
                    for (int i=0;i<listbean.size();i++){
                        if (data.equals(listbean.get(i))){
                            list.add(listuser.get(i));
                        }
                    }
                }
                size=list.size();
                for (int i=0;i<size;i++){
                    if (list.get(i).charAt(16)%2==0){
                        ++sum1[0];
                    }else {
                        ++sum1[1];
                    }
                }

                size=listuser.size();
                for (int i=0;i<size;i++){
                    if (listuser.get(i).charAt(16)%2==0){
                        ++sum2[0];
                    }else {
                        ++sum2[1];
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
        for (int i=0;i<2;i++){
            list.add(new BarEntry(i,new float[]{sum2[i]-sum1[i],sum1[i]}));
        }
        BarDataSet set=new BarDataSet(list,"");
        set.setColors(Color.parseColor("#33FF66"),Color.parseColor("#CA8622"));
        set.setValueTextColor(Color.RED);
        set.setValueTextSize(15f);
        set.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                for (int i=0;i<2;i++){
                    if (value==sum1[i]){
                        return decimalFormat.format((sum1[i]*1.0/sum2[i])*100)+"%";
                    }
                }
                return "";
            }
        });
        BarData data=new BarData(set);
        data.setBarWidth(0.5f);
        chart.setData(data);
        chart.invalidate();
    }
    private void init(){
        XAxis xAxis=chart.getXAxis();
        xAxis.setLabelCount(2);
        xAxis.setAxisMaximum(2f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        String[] setx={"女","男"};
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value>1?"":setx[(int) value];
            }
        });
        xAxis.setTextSize(15f);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setAxisMinimum(0f);
        chart.getAxisLeft().setTextSize(15f);
        chart.getAxisLeft().setLabelCount(8);
        Legend legend=chart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setXOffset(45);
        legend.setDrawInside(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(10,10,10,10);
    }
}
