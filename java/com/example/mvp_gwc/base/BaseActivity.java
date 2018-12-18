package com.example.mvp_gwc.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected abstract int getLayout();

    protected abstract void getfindbyid();

    protected abstract void setonclicked();

    protected abstract void prasce();

   void init(){
        if(getLayout()!=0){
            setContentView(getLayout());
            getfindbyid();
            setonclicked();
            prasce();
        }
    }

}
