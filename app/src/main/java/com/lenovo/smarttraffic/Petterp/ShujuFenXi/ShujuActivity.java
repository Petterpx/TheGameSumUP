package com.lenovo.smarttraffic.Petterp.ShujuFenXi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Petterp on 2019/5/27
 * Summary:数据分析
 * -> 用的框架，对框架做了简单更爱，注意线程中断，及乐观锁的处理->while(true)
 *  数据请求完成,中断线程。
 * 邮箱：1509492795@qq.com
 */
public class ShujuActivity extends BaseActivity {


    private ImageView back;
    private TextView title;
    private ViewPager viewPager;
    private View view1;
    private View view2;
    private View view3;
    private View view4;
    private View view5;
    private View view6;
    private View view7;

    @Override
    protected int getLayout() {
        return R.layout.activity_shuju;
    }

    @Override
    protected void onResume() {
        super.onResume();

        initView();
    }


    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.title);
        viewPager =findViewById(R.id.view_pager);
        view1 = (View) findViewById(R.id.view1);
        view2 = (View) findViewById(R.id.view2);
        view3 = (View) findViewById(R.id.view3);
        view4 = (View) findViewById(R.id.view4);
        view5 = (View) findViewById(R.id.view5);
        view6 = (View) findViewById(R.id.view6);
        view7 = (View) findViewById(R.id.view7);
        title.setText("数据分析");
        List<Fragment> list=new ArrayList<>();
        list.add(new Item1());
        list.add(new Item2());
        list.add(new Item3());
        list.add(new Item4());
        list.add(new Item5());
        list.add(new Item6());
        list.add(new Item7());
        setView(view1);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:setView(view1);break;
                    case 1:setView(view2);break;
                    case 2:setView(view3);break;
                    case 3:setView(view4);break;
                    case 4:setView(view5);break;
                    case 5:setView(view6);break;
                    case 6:setView(view7);break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void setView(View view){
        view1.setBackgroundResource(R.drawable.shuju_item_view1);
        view2.setBackgroundResource(R.drawable.shuju_item_view1);
        view3.setBackgroundResource(R.drawable.shuju_item_view1);
        view4.setBackgroundResource(R.drawable.shuju_item_view1);
        view5.setBackgroundResource(R.drawable.shuju_item_view1);
        view6.setBackgroundResource(R.drawable.shuju_item_view1);
        view7.setBackgroundResource(R.drawable.shuju_item_view1);
        view.setBackgroundResource(R.drawable.shuju_item_view2);
    }
}
