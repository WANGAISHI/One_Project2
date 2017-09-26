package com.example.one_project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Bean;
import bean.Bean2;
import view.xlistview.XListView;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private String url="http://v.juhe.cn/toutiao/index?type=&key=8ce3cb8698e2e1410eb8c37ae226d4a0";
    private XListView xlv;
    private List<Bean2> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initDate();

    }

    private void initDate() {
        RequestParams params=new RequestParams(url);
        x.http().get(params,new Callback.CommonCallback<String>()
        {

            private MyAdapter ma;

            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                Bean bean = gson.fromJson(result, Bean.class);
                List<Bean.ResultBean.DataBean> data = bean.result.data;
                for (int i = 0; i < data.size(); i++) {
                    Bean.ResultBean.DataBean dataBean = data.get(i);
                    String title = dataBean.title;
                    String thumbnail_pic_s = dataBean.thumbnail_pic_s;
                    Bean2 bean2=new Bean2(title,thumbnail_pic_s);
                    list.add(bean2);
                   // Toast.makeText(MainActivity.this,"list.++++++++list.++++++++"+list,Toast.LENGTH_SHORT).show();
                }

                ma = new MyAdapter(MainActivity.this,list);
                xlv.setAdapter(ma);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initView() {
        xlv = (XListView) findViewById(R.id.xlv);
        xlv.setPullLoadEnable(true);
        xlv.setXListViewListener(this);
        list = new ArrayList<>();
    }

    @Override
    public void onRefresh() {
        list.clear();
        xlv.setPullLoadEnable(true);
        initDate();
        //adapter.notifyDataSetChanged();
        onLoad();
    }

    @Override
    public void onLoadMore() {
        onLoad();
        initDate();
    }

    public void onLoad()
    {
        xlv.stopLoadMore();
        xlv.stopRefresh();
        SimpleDateFormat formatter = new  SimpleDateFormat  ("HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());
        String   str = formatter.format(curDate);
        xlv.setRefreshTime(str);
    }
}
