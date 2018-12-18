package com.example.mvp_gwc.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp_gwc.R;


public class jiajianView extends LinearLayout implements View.OnClickListener {
    private TextView jian;
    private TextView ed;
    private TextView jia;
    private int mnum;

    public jiajianView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LinearLayout.inflate(context, R.layout.add_jian, this);
        init();
    }

    public void setNum(int num){
       this.mnum =num;
       ed.setText(num+"");
    }

    private void init() {
        jian =findViewById(R.id.jian);
        jia =findViewById(R.id.jia);
        jia.setOnClickListener(this);
        jian.setOnClickListener(this);
        ed =findViewById(R.id.ed);
    }
    private jiajiancallback jiajiancallback;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jia:
                mnum++;
                ed.setText(mnum+"");
                if(jiajiancallback!=null){
                    jiajiancallback.setjiajian(mnum);
                }
                break;
            case R.id.jian:
                if(mnum>0){
                    mnum--;
                    ed.setText(mnum+"");
                    if(jiajiancallback!=null){
                        jiajiancallback.setjiajian(mnum);
                    }
                }else{
                    Toast.makeText(getContext(), "最小为0", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public interface jiajiancallback{
        void setjiajian(int mnum);
    }

    public void setjiajian(jiajiancallback jiajiancallback){
        this.jiajiancallback =jiajiancallback;
    }
}
