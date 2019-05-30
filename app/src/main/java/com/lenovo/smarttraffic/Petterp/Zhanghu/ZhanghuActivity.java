package com.lenovo.smarttraffic.Petterp.Zhanghu;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.Okhttp;
import com.lenovo.smarttraffic.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ZhanghuActivity extends AppCompatActivity {
    private List<Frits> list = new ArrayList<>();
    private ImageView back;
    private TextView title;
    private ListView listview;
    private ProgressDialog progressDialog;
    private ListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhanghu);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("正在请求");
        progressDialog.setTitle("Loading...");
        initView();
        list.add(new Frits("1",R.mipmap.baoma,"辽A10001","张三",400,false));
        list.add(new Frits("2",R.mipmap.baoma,"辽A10001","张三s",400,false));
        list.add(new Frits("3",R.mipmap.baoma,"辽A10001","张三2",400,false));
        list.add(new Frits("4",R.mipmap.baoma,"辽A10001","张三2",400,false));
        adapter=new ListViewAdapter(this);
        listview.setAdapter(adapter);
        findViewById(R.id.pl_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 0;
                for (int i=0;i<list.size();i++){
                    if (list.get(i).mode){
                        ++mode;
                    }
                }
                if (mode!=0){
                    new Dia1(ZhanghuActivity.this).show();
                }else{
                    Toast.makeText(ZhanghuActivity.this, "未选择任何小车！！！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.title);
        listview = (ListView) findViewById(R.id.listview);
    }

    private class ListViewAdapter extends BaseAdapter {
        private Context context;

        public ListViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            @SuppressLint("ViewHolder") View view1 = View.inflate(context, R.layout.zhanghu_list, null);
            ViewHolder viewHolder = new ViewHolder(view1);
            viewHolder.id.setText(list.get(i).id);
            viewHolder.image.setBackgroundResource(list.get(i).image);
            viewHolder.chepai.setText(list.get(i).chepai);
            viewHolder.name.setText("车主：" + list.get(i).id);
            viewHolder.money.setText("账户余额：" + list.get(i).money + "元");
            viewHolder.checkbox.setChecked(list.get(i).mode);
            viewHolder.checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (viewHolder.checkbox.isChecked()) {
                        list.get(i).mode = true;
                    } else {
                        list.get(i).mode = false;
                    }
                }
            });
            viewHolder.chongzhi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i=0;i<list.size();i++){
                        list.get(i).mode=false;
                    }
                    list.get(i).mode=true;
                    new Dia1(context).show();
                }
            });
            return view1;
        }

        public class ViewHolder {
            public View rootView;
            public TextView id;
            public ImageView image;
            public TextView chepai;
            public TextView name;
            public TextView money;
            public Button chongzhi;
            public CheckBox checkbox;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.id = (TextView) rootView.findViewById(R.id.id);
                this.image = (ImageView) rootView.findViewById(R.id.image);
                this.chepai = (TextView) rootView.findViewById(R.id.chepai);
                this.name = (TextView) rootView.findViewById(R.id.name);
                this.money = (TextView) rootView.findViewById(R.id.money);
                this.chongzhi = (Button) rootView.findViewById(R.id.chongzhi);
                this.checkbox = (CheckBox) rootView.findViewById(R.id.checkbox);
            }

        }
    }

    private class Dia1 extends Dialog {

        private TextView chepai;
        private EditText money;
        private Button chongzhi;
        private Button back;
        int mode=0;
        int ok=0;
        public Dia1(@NonNull Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.zhanghu_dia);
            chepai = findViewById(R.id.chepai);
            money = findViewById(R.id.money);
            chongzhi = findViewById(R.id.chongzhi);
            back = findViewById(R.id.back);
            StringBuilder res= new StringBuilder();
            res.append("车牌号：");
            List<Integer> ids=new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).mode){
                    res.append(list.get(i).chepai).append(" ");
                    ids.add(i+1);
                    ++mode;
                }
            }
            chepai.setText(res.toString());
            chongzhi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   String mon=money.getText().toString().trim();
                   if (mon.equals("")||mon.equals("0")){
                       money.setError("输入金额为1-999");
                   }else {
                       progressDialog.show();
                       for (int i=0;i<ids.size();i++){
                           setChongzhi(ids.get(i),Integer.parseInt(mon));
                       }
                   }
                }
            });
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
        }
        private void setChongzhi(int id,int money) {
            Okhttp.Builder().setOkHttpClient("action/SetCarAccountRecharge.do", "{\"CarId\":"+id+",\"Money\":"+money+", \"UserName\":\"user1\"}", new Okhttp.Post() {
                @Override
                public void on(String s) {
                    ++ok;
                    if (ok==mode){
                        progressDialog.dismiss();
                        Toast.makeText(ZhanghuActivity.this, "充值成功", Toast.LENGTH_SHORT).show();
                        for (int i=0;i<list.size();i++){
                            if (list.get(i).mode){
                                list.get(i).money+=money;
                                list.get(i).mode=false;
                            }
                            dismiss();
                            adapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void no() {
                    setChongzhi(id, money);
                }
            });
        }
    }


}
