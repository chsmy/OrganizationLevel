package com.chs.organizationlevel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chs.organizationlevel.R;
import com.chs.organizationlevel.bean.MyEntity;

import java.util.List;

/**
 * 作者：chs on 2015/12/31 11:32
 * 邮箱：657083984@qq.com
 * 通讯录的适配器
 */
public class MyAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<MyEntity.DataEntity> mGroupData;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<MyEntity.DataEntity> groupData) {
        this.mContext = context;
        this.mGroupData = groupData;
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(List<MyEntity.DataEntity> data) {
        if (data != null && data.size() > 0) {
            this.mGroupData = data;
        }
    }

    @Override
    public int getGroupCount() {
        return mGroupData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroupData.get(groupPosition).getUser_list().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroupData.get(groupPosition).getUser_list().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            groupHolder = new GroupHolder();
            convertView = inflater.inflate(R.layout.item_expandable_parent, null);
            groupHolder.iv_arrow = (ImageView) convertView.findViewById(R.id.iv_arrow);
            groupHolder.tv_department = (TextView) convertView.findViewById(R.id.tv_department);
            groupHolder.tv_department_num = (TextView) convertView.findViewById(R.id.tv_department_num);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        groupHolder.tv_department.setText(mGroupData.get(groupPosition).getName());
        int userCount = mGroupData.get(groupPosition).getUser_list().size();
//        int groupCount = mGroupData.get(groupPosition).getList().size();
        groupHolder.tv_department_num.setText("(" + userCount + ")");
        if (mGroupData.get(groupPosition).isExpand()) {
            groupHolder.iv_arrow.setImageResource(R.mipmap.arrow_up);
        } else {
            groupHolder.iv_arrow.setImageResource(R.mipmap.arrow_down);
        }
        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null) {
            childHolder = new ChildHolder();
            convertView = inflater.inflate(R.layout.item_expandable_child, null);
            childHolder.iv_dead_img = (ImageView) convertView.findViewById(R.id.iv_head_img);
            childHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            childHolder.tv_position = (TextView) convertView.findViewById(R.id.tv_position);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        final MyEntity.DataEntity.UserListEntity entity = mGroupData.get(groupPosition).getUser_list().get(childPosition);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        public TextView tv_department;
        public TextView tv_department_num;
        private ImageView iv_arrow;
    }

    class ChildHolder {
        public ImageView iv_dead_img;
        public TextView tv_name;
        public TextView tv_position;
    }
}
