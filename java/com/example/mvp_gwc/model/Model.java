package com.example.mvp_gwc.model;


import com.example.mvp_gwc.callback.MyCallBack;

public interface Model {
    void getdata(String murl,MyCallBack myCallBack);
}
