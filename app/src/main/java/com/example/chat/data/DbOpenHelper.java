package com.example.chat.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.example.chat.data.dao.ChatGroupMemberDao;
import com.example.chat.data.dao.ChatMsgSendingBeanDao;
import com.example.chat.data.dao.ContactBeanDao;
import com.example.chat.data.dao.DaoMaster;
import com.example.chat.data.dao.HistoryMsgBeanDao;
import com.example.chat.data.dao.LatestMsgBeanDao;
import com.example.chat.data.dao.LogisticsBeanDao;
import com.example.chat.data.dao.MessageDao;
import com.example.chat.data.dao.PatrolGroupBeanDao;
import com.example.chat.data.dao.PatrolMemberBeanDao;
import com.example.chat.data.dao.PatrolReqParamSaveBeanDao;
import com.example.chat.data.dao.UnitDao;
import com.example.chat.data.dao.UserInfoBeanDao;

import org.greenrobot.greendao.database.Database;


public class DbOpenHelper extends DaoMaster.OpenHelper {

    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            MigrationHelper.migrate(db,
                    UserInfoBeanDao.class,
                    MessageDao.class,
                    LatestMsgBeanDao.class,
                    ContactBeanDao.class,
                    UnitDao.class,
                    LogisticsBeanDao.class,
                    PatrolMemberBeanDao.class,
                    PatrolGroupBeanDao.class,
                    HistoryMsgBeanDao.class,
                    ChatMsgSendingBeanDao.class,
                    ChatGroupMemberDao.class,
                    PatrolReqParamSaveBeanDao.class);
        }
    }
}
