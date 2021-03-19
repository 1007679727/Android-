package com.example.chat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.chat.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupMemberListAdapter extends BaseAdapter {

    public static final int SELECT_TYPE_SINGLE = 0x0001;
    public static final int SELECT_TYPE_MULTIPLE = 0x0002;
    private List<String> groupMemberList;
    List<Boolean> isSelectedList;
    private Context context;
    private int selectType;

    public GroupMemberListAdapter(Context context) {
        this.context = context;
    }

    public void setGroupMemberList(List<String> groupMemberList, List<Boolean> isSelectedList, int selectType) {
        this.groupMemberList = groupMemberList;
        this.isSelectedList = isSelectedList;
        this.selectType = selectType;
    }

    @Override
    public int getCount() {
        return groupMemberList == null ? 0 : groupMemberList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contacts_person, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textViewPerson.setText(groupMemberList.get(position));
        viewHolder.imageViewSelect.setVisibility(selectType == SELECT_TYPE_MULTIPLE ? View.VISIBLE : View.INVISIBLE);
        viewHolder.imageViewSelect.setSelected(isSelectedList.get(position));

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.imageViewPerson)
        ImageView imageViewPerson;
        @BindView(R.id.textViewPerson)
        TextView textViewPerson;
        @BindView(R.id.imageViewSelect)
        ImageView imageViewSelect;
        @BindView(R.id.relativeLayoutPerson)
        RelativeLayout relativeLayoutPerson;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
