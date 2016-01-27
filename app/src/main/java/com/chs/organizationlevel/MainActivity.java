package com.chs.organizationlevel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chs.organizationlevel.adapter.MyAdapter;
import com.chs.organizationlevel.bean.MyEntity;
import com.chs.organizationlevel.wedgit.MyLinearLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private MyLinearLayout ll_department;
    private List<MyEntity.DataEntity> mData;
    private List<List<MyEntity.DataEntity>> mAllDatas = new ArrayList<>();
    private LinearLayout ll_organization;
    private TextView tv_organization;

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (mData.get(groupPosition).getList().size() != 0) {   //如果有下级组
                    ll_organization.setVisibility(View.VISIBLE);
                    adapter.setData(mData.get(groupPosition).getList());
                    adapter.notifyDataSetChanged();
                    ll_department.addView(getTextView(mData.get(groupPosition).getName()));

                    mData = mData.get(groupPosition).getList();
                    mAllDatas.add(mData);
                    //关闭所有展开的
                    for (int i = 0; i < mData.size(); i++) {
                        expandableListView.collapseGroup(i);
                    }
                    return true;
                } else {
                    //如果没有下级组 控制箭头的上 先变动
                    mData.get(groupPosition).setIsExpand(!mData.get(groupPosition).isExpand());
                    adapter.notifyDataSetChanged();
                    return false;
                }
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
               Toast.makeText(MainActivity.this,"groupPosition: "+groupPosition+"   childPosition: "+childPosition,Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        ll_department.setOnItemClickListener(new MyLinearLayout.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if ((position + 2) < mAllDatas.size()) {
                    ll_department.removeView(position);
                    adapter.setData(mAllDatas.get(position + 1));
                    adapter.notifyDataSetChanged();
                    mData = mAllDatas.get(position + 1);

                    //删除点击的地方以后的数据
                    int count = mAllDatas.size();
                    int x = 0;
                    for (int i = 0; i < count; i++) {
                        if (i > position + 1) {
                            mAllDatas.remove(i - x);
                            x++;
                        }
                    }
//                将所有的展开的关闭
                    for (int i = 0; i < mData.size(); i++) {
                        expandableListView.collapseGroup(i);
                    }
                }
            }
        });

        tv_organization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_department.removeAllViews();
                ll_organization.setVisibility(View.GONE);
                adapter.setData(mAllDatas.get(0));
                adapter.notifyDataSetChanged();
                mData = mAllDatas.get(0);

                int count = mAllDatas.size();
                int x = 0;
                //清除掉从第0个开始的以后的所有数据
                for (int i = 0; i < count; i++) {
                    if (i > 0) {
                        mAllDatas.remove(i - x);
                        x++;
                    }
                }
                for (int i = 0; i < mData.size(); i++) {
                    expandableListView.collapseGroup(i);
                }
            }
        });
    }

    private void initData() {
//        String json = getString(R.string.json);
        String json = "{\"returncode\":0,\"data\":[{\"id\":\"2\",\"sort\":\"0\",\"pid\":\"0\",\"ppath\":\"-2-\",\"name\":\"总部组\",\"is_show\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\",\"del_state\":\"1\",\"user_list\":[{\"id\":\"1\",\"shops_id\":\"4\",\"user_id\":\"2\",\"out_userid\":\"2\",\"directory_id\":\"2\",\"name\":\"张老大\",\"name_short\":\"yjt\",\"role_name\":\"管理员\",\"role_id\":\"1\",\"head\":\"http:\\/\\/api.51bxt.com\\/file\\/4\\/head\\/2.jpg\",\"email\":\"\",\"subgroup\":\"3\",\"subgroup_name\":\"\",\"gender\":\"1\",\"mobile\":\"1234\",\"del_state\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\"}],\"list\":[]},{\"id\":\"3\",\"sort\":\"0\",\"pid\":\"0\",\"ppath\":\"-3-\",\"name\":\"第一级\",\"is_show\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\",\"del_state\":\"1\",\"user_list\":[{\"id\":\"7\",\"shops_id\":\"4\",\"user_id\":\"15\",\"out_userid\":\"62\",\"directory_id\":\"3\",\"name\":\"小海\",\"name_short\":\"xh\",\"role_name\":\"维修员\",\"role_id\":\"2\",\"head\":\"http:\\/\\/api.51bxt.com\\/file\\/4\\/head\\/62.jpg\",\"email\":\"\",\"subgroup\":\"22172\",\"subgroup_name\":\"暖通组\",\"gender\":\"1\",\"mobile\":\"1234\",\"del_state\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\"},{\"id\":\"18\",\"shops_id\":\"4\",\"user_id\":\"35\",\"out_userid\":\"101\",\"directory_id\":\"3\",\"name\":\"追梦\",\"name_short\":\"zm\",\"role_name\":\"运营部报修\",\"role_id\":\"5\",\"head\":\"http:\\/\\/api.51bxt.com\\/file\\/4\\/head\\/101.jpg\",\"email\":\"\",\"subgroup\":\"0\",\"subgroup_name\":\"\",\"gender\":\"1\",\"mobile\":\"1234\",\"del_state\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\"}],\"list\":[{\"id\":\"9\",\"sort\":\"0\",\"pid\":\"3\",\"ppath\":\"-3-9-\",\"name\":\"第二级\",\"is_show\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\",\"del_state\":\"1\",\"user_list\":[{\"id\":\"5\",\"shops_id\":\"4\",\"user_id\":\"7\",\"out_userid\":\"48\",\"directory_id\":\"9\",\"name\":\"哈来\",\"name_short\":\"yl\",\"role_name\":\"维修员\",\"role_id\":\"2\",\"head\":\"\",\"email\":\"\",\"subgroup\":\"22173\",\"subgroup_name\":\"保洁组\",\"gender\":\"1\",\"mobile\":\"18210829859\",\"del_state\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\"},{\"id\":\"4\",\"shops_id\":\"4\",\"user_id\":\"6\",\"out_userid\":\"66\",\"directory_id\":\"9\",\"name\":\"王老扬\",\"name_short\":\"gly\",\"role_name\":\"维修员\",\"role_id\":\"2\",\"head\":\"http:\\/\\/api.51bxt.com\\/file\\/4\\/head\\/66.jpg\",\"email\":\"\",\"subgroup\":\"22170\",\"subgroup_name\":\"特种设备组\",\"gender\":\"1\",\"mobile\":\"1234\",\"del_state\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\"}],\"list\":[{\"id\":\"13\",\"sort\":\"0\",\"pid\":\"9\",\"ppath\":\"-3-9-13-\",\"name\":\"第三级1\",\"is_show\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\",\"del_state\":\"1\",\"user_list\":[{\"id\":\"15\",\"shops_id\":\"4\",\"user_id\":\"34\",\"out_userid\":\"99\",\"directory_id\":\"13\",\"name\":\"老高\",\"name_short\":\"lg\",\"role_name\":\"维修员\",\"role_id\":\"2\",\"head\":\"\",\"email\":\"\",\"subgroup\":\"22172\",\"subgroup_name\":\"暖通组\",\"gender\":\"1\",\"mobile\":\"43562\",\"del_state\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\"}],\"list\":[{\"id\":\"14\",\"sort\":\"0\",\"pid\":\"13\",\"ppath\":\"-3-9-13-14-\",\"name\":\"第四层1\",\"is_show\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\",\"del_state\":\"1\",\"user_list\":[{\"id\":\"14\",\"shops_id\":\"4\",\"user_id\":\"33\",\"out_userid\":\"96\",\"directory_id\":\"14\",\"name\":\"巴恩斯\",\"name_short\":\"bes\",\"role_name\":\"维修员\",\"role_id\":\"2\",\"head\":\"http:\\/\\/api.51bxt.com\\/file\\/4\\/head\\/96.jpg\",\"email\":\"\",\"subgroup\":\"22173\",\"subgroup_name\":\"保洁组\",\"gender\":\"1\",\"mobile\":\"34567\",\"del_state\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\"}],\"list\":[]}]}]},{\"id\":\"10\",\"sort\":\"0\",\"pid\":\"3\",\"ppath\":\"-3-10-\",\"name\":\"第二级\",\"is_show\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\",\"del_state\":\"1\",\"user_list\":[{\"id\":\"8\",\"shops_id\":\"4\",\"user_id\":\"20\",\"out_userid\":\"87\",\"directory_id\":\"10\",\"name\":\"伊戈达拉\",\"name_short\":\"ygdl\",\"role_name\":\"维修员\",\"role_id\":\"2\",\"head\":\"http:\\/\\/api.51bxt.com\\/file\\/4\\/head\\/87.jpg\",\"email\":\"\",\"subgroup\":\"22171\",\"subgroup_name\":\"综修组\",\"gender\":\"1\",\"mobile\":\"5477436\",\"del_state\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\"},{\"id\":\"9\",\"shops_id\":\"4\",\"user_id\":\"16\",\"out_userid\":\"80\",\"directory_id\":\"10\",\"name\":\"枫叶\",\"name_short\":\"fy\",\"role_name\":\"维修员\",\"role_id\":\"2\",\"head\":\"\",\"email\":\"\",\"subgroup\":\"22172\",\"subgroup_name\":\"暖通组\",\"gender\":\"1\",\"mobile\":\"3542256\",\"del_state\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\"}],\"list\":[]}]},{\"id\":\"8\",\"sort\":\"0\",\"pid\":\"0\",\"ppath\":\"-8-\",\"name\":\"第一级\",\"is_show\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\",\"del_state\":\"1\",\"user_list\":[{\"id\":\"6\",\"shops_id\":\"4\",\"user_id\":\"13\",\"out_userid\":\"83\",\"directory_id\":\"8\",\"name\":\"送老二员\",\"name_short\":\"mwxy\",\"role_name\":\"维修员\",\"role_id\":\"2\",\"head\":\"http:\\/\\/api.51bxt.com\\/file\\/4\\/head\\/83.jpg\",\"email\":\"\",\"subgroup\":\"22170\",\"subgroup_name\":\"特种设备组\",\"gender\":\"1\",\"mobile\":\"18583875010\",\"del_state\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\"}],\"list\":[{\"id\":\"11\",\"sort\":\"0\",\"pid\":\"8\",\"ppath\":\"-8-11-\",\"name\":\"第二级\",\"is_show\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\",\"del_state\":\"1\",\"user_list\":[{\"id\":\"10\",\"shops_id\":\"4\",\"user_id\":\"27\",\"out_userid\":\"92\",\"directory_id\":\"11\",\"name\":\"李老三\",\"name_short\":\"yxg\",\"role_name\":\"运营部报修\",\"role_id\":\"5\",\"head\":\"http:\\/\\/api.51bxt.com\\/file\\/4\\/head\\/92.jpg\",\"email\":\"\",\"subgroup\":\"0\",\"subgroup_name\":\"\",\"gender\":\"1\",\"mobile\":\"214534\",\"del_state\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\"},{\"id\":\"11\",\"shops_id\":\"4\",\"user_id\":\"25\",\"out_userid\":\"91\",\"directory_id\":\"11\",\"name\":\"大S\",\"name_short\":\"dS\",\"role_name\":\"维修员\",\"role_id\":\"2\",\"head\":\"\",\"email\":\"\",\"subgroup\":\"22170\",\"subgroup_name\":\"特种设备组\",\"gender\":\"1\",\"mobile\":\"134214\",\"del_state\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\"}],\"list\":[]},{\"id\":\"12\",\"sort\":\"0\",\"pid\":\"8\",\"ppath\":\"-8-12-\",\"name\":\"第二级\",\"is_show\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\",\"del_state\":\"1\",\"user_list\":[{\"id\":\"12\",\"shops_id\":\"4\",\"user_id\":\"26\",\"out_userid\":\"53\",\"directory_id\":\"12\",\"name\":\"小丸子\",\"name_short\":\"c\",\"role_name\":\"运营部报修\",\"role_id\":\"5\",\"head\":\"\",\"email\":\"\",\"subgroup\":\"0\",\"subgroup_name\":\"\",\"gender\":\"1\",\"mobile\":\"2345334\",\"del_state\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\"},{\"id\":\"13\",\"shops_id\":\"4\",\"user_id\":\"28\",\"out_userid\":\"27\",\"directory_id\":\"12\",\"name\":\"测试\",\"name_short\":\"cs\",\"role_name\":\"运营部报修\",\"role_id\":\"5\",\"head\":\"http:\\/\\/api.51bxt.com\\/file\\/4\\/head\\/27.jpg\",\"email\":\"\",\"subgroup\":\"0\",\"subgroup_name\":\"\",\"gender\":\"1\",\"mobile\":\"23454523\",\"del_state\":\"1\",\"del_user\":\"0\",\"del_time\":\"0\"}],\"list\":[]}]}]}";
        Gson gson = new Gson();
        MyEntity entity = gson.fromJson(json,MyEntity.class);
        mData = entity.getData();//最外层的数据
        mAllDatas.add(mData);//将数据放入到总的存贮数据的list中
        adapter = new MyAdapter(this, entity.getData());
        expandableListView.setAdapter(adapter);
        expandableListView.setGroupIndicator(null);
    }

    private void initView() {
        expandableListView = (ExpandableListView) findViewById(R.id.expendable_list);
        ll_department = (MyLinearLayout) findViewById(R.id.ll_department);
        ll_organization = (LinearLayout) findViewById(R.id.ll_organization);
        tv_organization = (TextView) findViewById(R.id.tv_organization);
    }
    private TextView getTextView(String title) {
        TextView textView = new TextView(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setPadding(40, 20, 20, 20);
        textView.setText(title);
        textView.setTextSize(17);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
