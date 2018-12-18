package com.example.mvp_gwc.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvp_gwc.R;
import com.example.mvp_gwc.bean.MyBean;
import com.example.mvp_gwc.weight.jiajianView;

import java.util.List;

public class MyAdapter extends BaseExpandableListAdapter {

    private List<MyBean.DataBean> list;
    private Context context;

    public MyAdapter(List<MyBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getSpus().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }


    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }



    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        groupHolder groupHolder;
        if(convertView ==null){
            convertView =View.inflate(context, R.layout.expenr_group,null);
            groupHolder = new groupHolder();
            groupHolder.group_ck=convertView.findViewById(R.id.group_ck);
            groupHolder.group_title=convertView.findViewById(R.id.group_title);
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (MyAdapter.groupHolder) convertView.getTag();
        }
        groupHolder.group_ck.setChecked(list.get(groupPosition).isGroupischeck());
        groupHolder.group_title.setText(list.get(groupPosition).getName());
        groupHolder.group_ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adaptercallback!=null){
                    adaptercallback.groupchecked(groupPosition);
                }
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        childHolder childHolder;
        if(convertView ==null){
            convertView =View.inflate(context, R.layout.expenr_child,null);
            childHolder = new childHolder();
            childHolder.child_ck=convertView.findViewById(R.id.child_ck);
            childHolder.child_title=convertView.findViewById(R.id.child_title);
            childHolder.child_img=convertView.findViewById(R.id.child_img);
            childHolder.child_price=convertView.findViewById(R.id.child_price);
            childHolder.jiajianView =convertView.findViewById(R.id.jiajian);
            convertView.setTag(childHolder);
        }else{
            childHolder = (MyAdapter.childHolder) convertView.getTag();
        }
        childHolder.child_ck.setChecked(list.get(groupPosition).getSpus().get(childPosition).isChildischeck());
        childHolder.child_title.setText(list.get(groupPosition).getSpus().get(childPosition).getName());
        childHolder.child_price.setText(list.get(groupPosition).getSpus().get(childPosition).getSkus().get(0).getPrice()+"");
        Glide.with(convertView).load(list.get(groupPosition).getSpus().get(childPosition).getPic_url()).into(childHolder.child_img);
        childHolder.jiajianView.setNum(list.get(groupPosition).getSpus().get(childPosition).getPraise_num());
        childHolder.child_ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adaptercallback!=null){
                    adaptercallback.childchecked(groupPosition,childPosition);
                }
            }
        });
        childHolder.jiajianView.setjiajian(new jiajianView.jiajiancallback() {
            @Override
            public void setjiajian(int mnum) {
                list.get(groupPosition).getSpus().get(childPosition).setPraise_num(mnum);
                if(adaptercallback!=null){
                    adaptercallback.jiajian();
                }
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class groupHolder{
        CheckBox group_ck;
        TextView group_title;
    }

    class childHolder{
        CheckBox child_ck;
        TextView child_title;
        TextView child_price;
        ImageView child_img;
        jiajianView jiajianView;
    }

    public boolean lookgroupchecked(int groupposition){
        for (int i = 0; i <list.get(groupposition).getSpus().size(); i++) {
            if(!list.get(groupposition).getSpus().get(i).isChildischeck()){
                return false;
            }
        }
        return true;
    }

    public void groupallchecked(int groupposition,boolean b){
        list.get(groupposition).setGroupischeck(b);
        for (int i = 0; i <list.get(groupposition).getSpus().size(); i++) {
            list.get(groupposition).getSpus().get(i).setChildischeck(b);
        }
    }

    public boolean lookchildchecked(int groupposition,int childposition){
        boolean b = list.get(groupposition).getSpus().get(childposition).isChildischeck();
        return b;
    }

    public void childckecked(int groupposition,int childposition,boolean b){
        list.get(groupposition).getSpus().get(childposition).setChildischeck(b);
        boolean b1 = lookgroupchecked(groupposition);
        if(b1){
            list.get(groupposition).setGroupischeck(b1);
        }else{
            list.get(groupposition).setGroupischeck(b1);
        }
    }

    public boolean lookallchecked(){
        for (int i = 0; i <list.size(); i++) {
            if(!lookgroupchecked(i)){
                return false;
            }
        }
        return true;
    }

    public void setallchecked(boolean b){
        for (int i = 0; i <list.size(); i++) {
            groupallchecked(i,b);
        }
    }

    @Override
    public boolean areAllItemsEnabled() {
        return super.areAllItemsEnabled();
    }

    public interface Adaptercallback{
        void groupchecked(int groupposition);

        void childchecked(int groupposition,int childposition);

        void jiajian();
    }

    private Adaptercallback adaptercallback;

    public void setcallback(Adaptercallback adaptercallback){
        this.adaptercallback =adaptercallback;
    }

}
