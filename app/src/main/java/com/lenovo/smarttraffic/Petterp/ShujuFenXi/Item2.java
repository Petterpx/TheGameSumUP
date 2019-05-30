package com.lenovo.smarttraffic.Petterp.ShujuFenXi;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
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
 * Summary:只请求违章的，计算重复违章有多少，然后set洗数据，相减就是相应的数据
 * 邮箱：1509492795@qq.com
 */
public class Item2 extends BaseFragment {
    private PieChart chart;
    private int size,size2;

    @Override
    protected View getSuccessView() {
        View view=View.inflate(getContext(), R.layout.shuju_item2,null);
        TextView title=view.findViewById(R.id.text);
        title.setText("");
        chart=view.findViewById(R.id.chart);
        init();
        return view;
    }

    @Override
    protected Object requestData() {
        setWeiZhang();
        while (true){
            if (thread.isInterrupted()){
                return 1;
            }
        }
    }

    @Override
    public void onClick(View view) {

    }
    private void init(){
        chart.setDrawHoleEnabled(false);
        chart.setDrawEntryLabels(false);
        chart.setDrawCenterText(false);
        chart.setTouchEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(20,20,20,20);
    }
    private void setWeiZhang() {
        Okhttp.Builder().setOkHttpClient("action/GetAllCarPeccancy.do", "{\"UserName\":\"user1\"}", new Okhttp.Post() {
            @Override
            public void on(String s) {
                Gson gson=new Gson();
                WeiZhang bean=gson.fromJson(s,WeiZhang.class);
                size=bean.getROWS_DETAIL().size();
                List<String> list=new ArrayList<>();
                for (int i=0;i<size;i++){
                    list.add(bean.getROWS_DETAIL().get(i).getCarnumber());
                }
                for (int i=0;i<size;i++){
                    int n=0;
                    for (int j=0;j<size;j++){
                        if (i!=j){
                            if (list.get(i).equals(list.get(j))){
                                n=1;
                                break;
                            }
                        }
                    }
                    if (n==0){
                        ++size2;
                    }
                }
                HashSet set=new HashSet(list);
                size=set.size();
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
        List<PieEntry> list=new ArrayList<>();
        list.add(new PieEntry(size-size2));
        list.add(new PieEntry(size2));
        PieDataSet set=new PieDataSet(list,"");
        set.setColors(Color.parseColor("#C23531"),Color.parseColor("#009DD9"));
        set.setValueLinePart1OffsetPercentage(100f);
        set.setValueLinePart1Length(0.8f);
        set.setValueLinePart2Length(0.2f);
        set.setValueTextSize(15f);
        set.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                if(value==size2){
                    return "无重复违章"+decimalFormat.format((size2*1.0/size)*100)+"%";
                }else{
                    return "有重复违章"+decimalFormat.format(((size-size2)*1.0/size)*100)+"%";
                }
            }
        });
        PieData data=new PieData(set);
        chart.setData(data);
        chart.invalidate();
    }
}
