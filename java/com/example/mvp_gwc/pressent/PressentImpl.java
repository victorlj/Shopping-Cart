package com.example.mvp_gwc.pressent;


import com.example.mvp_gwc.callback.MyCallBack;
import com.example.mvp_gwc.ivew.IVew;
import com.example.mvp_gwc.model.ModelImpl;

public class PressentImpl implements Pressent, MyCallBack {
    private IVew iVew;
    private ModelImpl model;

    public PressentImpl(IVew iVew) {
        this.iVew = iVew;
        model = new ModelImpl();
    }

    @Override
    public void getdata(String murl) {
        model.getdata(murl,this);
    }

    @Override
    public void success(Object success) {
        iVew.success(success);
    }

    @Override
    public void error(String error) {
        iVew.error(error);
    }

    public void onDatech(){
        if(iVew!=null){
            iVew=null;
        }
        if(model!=null){
            model=null;
        }
    }

}
