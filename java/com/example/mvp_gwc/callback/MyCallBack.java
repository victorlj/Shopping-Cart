package com.example.mvp_gwc.callback;

public interface MyCallBack<T> {
    void success(T success);
    void error(String error);
}
