package com.example.mvp_gwc.model;

import android.os.Handler;
import android.os.Message;

import com.example.mvp_gwc.bean.MyBean;
import com.example.mvp_gwc.callback.MyCallBack;
import com.example.mvp_gwc.utils.Okutil;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ModelImpl implements Model {
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Gson gson =new Gson();
                    MyBean bean = gson.fromJson((String) msg.obj, MyBean.class);
                    myCallBack.success(bean.getData());
                    break;
            }
        }
    };
    private MyCallBack myCallBack;
    @Override
    public void getdata(String murl, final MyCallBack myCallBack) {
        this.myCallBack =myCallBack;
        Okutil.getIntent().get(murl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                myCallBack.error(e.getMessage()+"");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handler.sendMessage(handler.obtainMessage(0,response.body().string()));
            }
        });
    }
}
