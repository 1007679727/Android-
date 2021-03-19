package com.example.chat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmojiGridViewAdapter extends BaseAdapter {

    private static List<Integer> emojiUnicodeList;

    static {
        emojiUnicodeList = new ArrayList<>();
        emojiUnicodeList.add(0x1F601);
        emojiUnicodeList.add(0x1F602);
        emojiUnicodeList.add(0x1F603);
        emojiUnicodeList.add(0x1F604);
        emojiUnicodeList.add(0x1F605);
        emojiUnicodeList.add(0x1F606);
        emojiUnicodeList.add(0x1F614);
        emojiUnicodeList.add(0x1F608);
        emojiUnicodeList.add(0x1F60A);
        emojiUnicodeList.add(0x1F60B);
        emojiUnicodeList.add(0x1F60C);
        emojiUnicodeList.add(0x1F60D);
        emojiUnicodeList.add(0x1F60E);
        emojiUnicodeList.add(0x1F60F);
        emojiUnicodeList.add(0x1F618);
        emojiUnicodeList.add(0x1F61E);
        emojiUnicodeList.add(0x1F637);
        emojiUnicodeList.add(0x1F62A);
        emojiUnicodeList.add(0x1F630);
        emojiUnicodeList.add(0x1F628);
        emojiUnicodeList.add(0x1F621);
        emojiUnicodeList.add(0x1F620);
        emojiUnicodeList.add(0x1F632);
        emojiUnicodeList.add(0x1F631);
        emojiUnicodeList.add(0x1F633);
        emojiUnicodeList.add(0x1F622);
        emojiUnicodeList.add(0x1F47D);
        emojiUnicodeList.add(0);
    }

    private Context context;

    public EmojiGridViewAdapter(Context context) {
        this.context = context;
    }

    public static List<Integer> getEmojiUnicodeList() {
        return emojiUnicodeList;
    }

    @Override
    public int getCount() {
        return emojiUnicodeList.size();
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.emoji_gridview_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position < emojiUnicodeList.size() - 1) {
            viewHolder.emojiTxt.setText(new String(Character.toChars(emojiUnicodeList.get(position))));
            viewHolder.emojiTxt.setVisibility(View.VISIBLE);
            viewHolder.emojiDeleteIv.setVisibility(View.GONE);
        } else {
            viewHolder.emojiTxt.setVisibility(View.GONE);
            viewHolder.emojiDeleteIv.setVisibility(View.VISIBLE);
        }
        AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) convertView.getLayoutParams();
        layoutParams.height = parent.getHeight() / 4;
        convertView.setLayoutParams(layoutParams);
        return convertView;
    }

    static public class ViewHolder {
        @BindView(R.id.emoji_txt)
        TextView emojiTxt;
        @BindView(R.id.emoji_delete_iv)
        ImageView emojiDeleteIv;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
