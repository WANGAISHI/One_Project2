package com.example.one_project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bean.Bean2;

/**
 * Created by 王爱诗 on 2017/9/26.
 */

public class MyAdapter extends BaseAdapter {

    private int type_num=2;
    private final int atype=1;
    private final int btype=0;
    private Context context;
    private List<Bean2> list;

    public MyAdapter(Context context, List<Bean2> list) {
        this.context=context;
        this.list=list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==0)
        {
            return atype;
        }else
        {
            return btype;
        }

    }

    @Override
    public int getViewTypeCount() {
        return type_num;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder1 holder1=null;
        ViewHolder2 holder2=null;

        int type = getItemViewType(i);
        if(view==null)
        {
            switch (type)
            {
                case atype:
                    holder1=new ViewHolder1();
                    view = LayoutInflater.from(context).inflate(R.layout.item1, null);
                    holder1.tv_title1=view.findViewById(R.id.tv_title1);
                    holder1.tv_image1=view.findViewById(R.id.tv_image1);
                    view.setTag(holder1);
                    break;
                case btype:
                    holder2=new ViewHolder2();
                    view = LayoutInflater.from(context).inflate(R.layout.item2, null);
                    holder2.tv_title2=view.findViewById(R.id.tv_title2);
                    holder2.tv_image2=view.findViewById(R.id.tv_image2);
                    view.setTag(holder2);
                    break;
            }
        }else
        {
            switch (type)
            {
                case atype:
                    holder1 = (ViewHolder1) view.getTag();
                    break;
                case btype:
                    holder2 = (ViewHolder2) view.getTag();
                    break;
            }
        }
        switch (type)
        {
            case atype:
                holder1.tv_title1.setText(list.get(i).getTitle());
                ImageLoader.getInstance().displayImage(list.get(i).getThumbnail_pic_s(),holder1.tv_image1);
                break;
            case btype:
                holder2.tv_title2.setText(list.get(i).getTitle());
                ImageLoader.getInstance().displayImage(list.get(i).getThumbnail_pic_s(),holder2.tv_image2);
                break;
        }
        return view;
    }

    public class ViewHolder1
    {
        public TextView tv_title1;
        public ImageView tv_image1;
    }

    public class ViewHolder2
    {
        public TextView tv_title2;
        public ImageView tv_image2;
    }
}
