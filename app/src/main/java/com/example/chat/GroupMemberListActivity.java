package com.example.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.chat.adapter.GroupMemberListAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Unbinder;

import static com.example.chat.adapter.GroupMemberListAdapter.SELECT_TYPE_MULTIPLE;


public class GroupMemberListActivity extends BaseActivity {
    public static final int SELECT_TYPE_SINGLE = 0x0001;

    private final int RESULT_TYPE = 0x0001;
    public static final String REQUEST_TYPE_AT = "at";
    public static final String REQUEST_TYPE_ADD_GROUP_MEMBER = "add_group_member";
    public static final String REQUEST_TYPE_SET_COMMANDER = "set_commander";
    public static final String REQUEST_TYPE_ADD_TASK_MEMBER = "add_task_member";

    private static final String TAG = GroupMemberListActivity.class.getSimpleName();
    @BindView(R.id.listViewGroupMember)
    ListView listViewGroupMember;
    @BindView(R.id.ivTitleBack)
    ImageView ivTitleBack;
    @BindView(R.id.tvTitleBar)
    TextView tvTitleBar;
    @BindView(R.id.tvTitleEdit)
    TextView tvTitleEdit;
    private Unbinder unbinder;
    private GroupMemberListAdapter groupMemberListAdapter;
    private String requestType;
    private Set<Integer> memberPositionSet = new HashSet<>();
    ArrayList<String> realNameList;
    private List<Boolean> isSelectedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_member_list);
        unbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        requestType = getIntent().getType();
        ivTitleBack.setVisibility(View.VISIBLE);
        tvTitleBar.setText("请选择");
        tvTitleEdit.setVisibility((requestType.equals(REQUEST_TYPE_ADD_TASK_MEMBER)|requestType.equals(REQUEST_TYPE_ADD_GROUP_MEMBER)) ? View.VISIBLE : View.INVISIBLE);
        tvTitleEdit.setText("确定");

        realNameList = (ArrayList<String>) getIntent().getSerializableExtra("realNames");
        for (String realName : realNameList) {
            isSelectedList.add(false);
        }
        groupMemberListAdapter = new GroupMemberListAdapter(this);
        listViewGroupMember.setAdapter(groupMemberListAdapter);
        groupMemberListAdapter.setGroupMemberList(realNameList, isSelectedList, (requestType.equals(REQUEST_TYPE_ADD_TASK_MEMBER)|requestType.equals(REQUEST_TYPE_ADD_GROUP_MEMBER)) ? SELECT_TYPE_MULTIPLE : SELECT_TYPE_SINGLE);
    }

    @OnItemClick({R.id.listViewGroupMember})
    public void onItemClick(int position) {
        switch (requestType) {
            case REQUEST_TYPE_AT:
            case REQUEST_TYPE_SET_COMMANDER:
                
                Intent intent = new Intent();
                intent.putExtra("position", position);
                setResult(RESULT_TYPE, intent);
                finish();
                break;
            case REQUEST_TYPE_ADD_GROUP_MEMBER:
            case REQUEST_TYPE_ADD_TASK_MEMBER:
                isSelectedList.set(position, !isSelectedList.get(position));
                if (isSelectedList.get(position)) {
                    memberPositionSet.add(position);
                } else {
                    memberPositionSet.remove(position);
                }
                groupMemberListAdapter.notifyDataSetChanged();
                break;
        }
    }

    @OnClick({R.id.ivTitleBack, R.id.tvTitleEdit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivTitleBack:
                finish();
                break;
            case R.id.tvTitleEdit:
                Intent intent = new Intent();
                intent.putExtra("positions", new ArrayList<>(memberPositionSet));
                setResult(RESULT_TYPE, intent);
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.ivTitleBack, R.id.tvTitleEdit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivTitleBack:
                break;
            case R.id.tvTitleEdit:
                break;
        }
    }
}
