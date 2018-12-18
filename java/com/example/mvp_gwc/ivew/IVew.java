package com.example.mvp_gwc.ivew;

public interface IVew<T> {
    void success(T success);
    void error(String error);
}
