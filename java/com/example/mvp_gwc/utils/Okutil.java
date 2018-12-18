package com.example.mvp_gwc.utils;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Okutil {
    private OkHttpClient okHttpClient;

    public Okutil() {
        okHttpClient =new OkHttpClient();
    }

    public static  Okutil getIntent(){
        return okHolder.utils;
    }

    static class okHolder{
        private static final Okutil utils = new Okutil();
    }

    public void get(String murl, Callback callback){
        Request request =new Request.Builder().url(murl).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public void post(String murl,String name,String key,Callback callback){
        RequestBody body =new FormBody.Builder().add(name,key).build();
        Request request =new Request.Builder().url(murl).post(body).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

}
