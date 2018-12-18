package com.example.mvp_gwc;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp_gwc.adapter.MyAdapter;
import com.example.mvp_gwc.base.BaseActivity;
import com.example.mvp_gwc.bean.MyBean;
import com.example.mvp_gwc.ivew.IVew;
import com.example.mvp_gwc.pressent.PressentImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends BaseActivity implements IVew {

    private CheckBox main_ck_qx;
    private TextView allprice;
    private TextView allnum;
    private ExpandableListView expend;
    private PressentImpl pressent;
    private List<MyBean.DataBean> list = new ArrayList<>();
    private String murl = "http://www.wanandroid.com/tools/mockapi/6523/restaurant-list";
    private MyAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void getfindbyid() {
        main_ck_qx = findViewById(R.id.main_ck_qx);
        allprice = findViewById(R.id.allprice);
        allnum = findViewById(R.id.allnum);
        expend = findViewById(R.id.expend);
        adapter = new MyAdapter(list, this);
        expend.setAdapter(adapter);
        expend.setGroupIndicator(null);
        adapter.setcallback(new MyAdapter.Adaptercallback() {
            @Override
            public void groupchecked(int groupposition) {
                boolean b = adapter.lookgroupchecked(groupposition);
                adapter.groupallchecked(groupposition, !b);
                boolean b1 = adapter.lookallchecked();
                if (b1) {
                    main_ck_qx.setChecked(b1);
                } else {
                    main_ck_qx.setChecked(b1);
                }
                getallprice();
                getallnumber();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void childchecked(int groupposition, int childposition) {
                boolean b = adapter.lookchildchecked(groupposition, childposition);
                adapter.childckecked(groupposition, childposition, !b);
                boolean b1 = adapter.lookallchecked();
                if (b1) {
                    main_ck_qx.setChecked(b1);
                } else {
                    main_ck_qx.setChecked(b1);
                }
                getallprice();
                getallnumber();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void jiajian() {
                getallprice();
                getallnumber();
            }
        });
    }

    @Override
    protected void setonclicked() {
        main_ck_qx.setOnClickListener(this);
    }

    @Override
    protected void prasce() {
        pressent = new PressentImpl(this);
        pressent.getdata(murl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_ck_qx:
                boolean b = adapter.lookallchecked();
                main_ck_qx.setChecked(!b);
                adapter.setallchecked(!b);
                getallprice();
                getallnumber();
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void success(Object success) {
        list.addAll((Collection<? extends MyBean.DataBean>) success);
        getallprice();
        getallnumber();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    public void getallprice() {
        double number = 0.0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getSpus().size(); j++) {
                if (list.get(i).getSpus().get(j).isChildischeck()) {
                    number += list.get(i).getSpus().get(j).getPraise_num() * list.get(i).getSpus().get(j).getSkus().get(0).getPrice();
                }
            }
        }
        allprice.setText("商品总价：" + number + "");
    }

    public void getallnumber() {
        int number = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getSpus().size(); j++) {
                if (list.get(i).getSpus().get(j).isChildischeck()) {
                    number += list.get(i).getSpus().get(j).getPraise_num();
                }
            }
        }
        allnum.setText("商品总数量：" + number + "");
    }

}
