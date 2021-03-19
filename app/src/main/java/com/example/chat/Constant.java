package com.example.chat;

import android.util.SparseArray;

import androidx.appcompat.app.AppCompatActivity;


import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;

/**
 * Created by FHZ on 2018/7/12.
 */
public class Constant {

//    public static String IP_COMMAND = "https://gap.szxt.tj.yd.nkg.js:8443/";
        public static String IP_COMMAND="http://47.102.36.228:12311/";
    public static String reqAddr = IP_COMMAND + "share-upms/share-upms/";
//        public static String IP_WEB_SOCKET ="ws://139.196.59.188:8081/";
    public static String IP_WEB_SOCKET = "wss://szxt.tj.yd.nkg.js:10052/";
    public static final String IP_IM = IP_COMMAND + "chat/";

    //首页地图
//    public static final String MAP_URL = "http://139.196.59.188:8090/mobilemap/#/?mapType=";
    public static final String MAP_URL = IP_COMMAND + "mobilemap/#/?mapType=";

    //获取所有辖区分局信息
    public static final String GET_ALL_SUBSTATION = IP_COMMAND + "police-run/organization/getAllOrgDataByType?type=2";
    // 获取辖区分局对应派出所 后拼接 辖区编号
    public static final String GET_ALL_OFFICE = IP_COMMAND + "police-run/PSPC/DPS/getAllPoliceStationBySectionTeam/";

    //WEB_RTC
//    public static final String HTTP_RTC_SERVER_PREFIX = "https://139.196.59.188:8443/";
    public static final String HTTP_RTC_SERVER_PREFIX = "https://gap.szxt.tj.yd.nkg.js:8443/";
    public static final String HTTP_RTC_SERVER_TAG = "rtc";
    public static final String HTTP_RTC_CREATE_TAG = "create";
    public static final String HTTP_RTC_DESTROY_TAG = "destroy";
    public static final String HTTP_RTC_ATTACH_TAG = "attach";
    public static final String HTTP_RTC_DETACH_TAG = "detach";
    public static final String HTTP_RTC_POLLING_TAG = "polling";
    public static final String HTTP_RTC_REGISTER_TAG = "register";
    public static final String HTTP_RTC_CALL_TAG = "call";
    public static final String HTTP_RTC_HUNGUP_TAG = "hungup";
    public static final String HTTP_RTC_ACCEPT_TAG = "accept";
    public static final String HTTP_RTC_ICECANDIDATE_TAG = "candidate";
    public static final String HTTP_RTC_ICECANDIDATE_COMPLETE_TAG = "candidate_complete";

    //conference
    public static final String HTTP_RTC_CONFERENCE_CREATE_TAG = "conference_create";
    public static final String HTTP_RTC_CONFERENCE_PUBLISH_TAG = "conference_publish";
    public static final String HTTP_RTC_CONFERENCE_SUBSCRIBE_TAG = "conference_subscribe";
    public static final String HTTP_RTC_CONFERENCE_OFFER_TAG = "conference_offer";
    public static final String HTTP_RTC_CONFERENCE_ANSWER_TAG = "conference_answer";
    public static final String HTTP_RTC_CONFERENCE_CANDIDATE_TAG = "conference_candidate";
    public static final String HTTP_RTC_CONFERENCE_CANDIDATE_COMPLETE_TAG = "conference_candidate_complete";
    public static final String HTTP_RTC_CONFERENCE_QUIT_TAG = "conference_quit";
    public static final String HTTP_RTC_CONFERENCE_DESTROY_TAG = "conference_destroy";
    public static final String HTTP_RTC_CONFERENCE_POSITION_TAG = "sju/mixer";

    public static final String HTTP_RTC_FACE_DETECTION = "sju/facedetect";
    public static final String HTTP_RTC_CROWD_DETECTION = "sju/crowddetect";
    public static final String HTTP_RTC_POINT_DETECTION = "sju/pointerdetect";

    //获取混流的记录
//    public static final String HTTP_GET_FUSION_RECORD = "http://39.97.112.101:3000/vasu/getFusionRecordInfo";
//    public static final String HTTP_GET_CONFERENCE_PART = "http://39.97.112.101:3000/vasu/getParticipants";
//    public static final String HTTP_GET_SINGLE_RECORD = "http://39.97.112.101:3000/vasu/getRecordInfo";

    public static final String HTTP_GET_FUSION_RECORD = IP_COMMAND + "vasu/getFusionRecordInfo";
    public static final String HTTP_GET_CONFERENCE_PART = IP_COMMAND + "vasu/getParticipants";
    public static final String HTTP_GET_SINGLE_RECORD = IP_COMMAND + "vasu/getRecordInfo";

    public static ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();

    //login
    public static final String HTTP_LOGIN_TAG = "ajaxLogin";

    public static final String HTTP_UPDATE_PSD = "share-upms/manage/user/updatePassword?newPassword=";

    //存储的用户名
    public static final String USER_NAME = "userName";
    //存储的密码
    public static final String USER_PSD = "user_psd";
    //存储状态
    public static final String SAVE_STATE = "save_state";
    //授权序列号
    public static final String AUTHORIZATION = "authorization";

    //移动巡防
    public static final String PATROL_PREFIX_APP = "command-control-nj/app/";
    public static final String PATROL_PREFIX_USER = "command-control-nj/user/";
    public static final String PATROL_PREFIX_REPORT = "command-control-nj/report/";
    public static final String PATROL_GET_REPORT_DETAIL = "getDetailById?userId=";
    public static final String PATROL_GET_CAR_LIST = "getCarListOrderByNum";
    public static final String PATROL_GET_GROUP_LIST = "getHhList";
    public static final String PATROL_GET_USER = "getGroupUsers";
    public static final String PATROL_REPORT = "save?";
    public static final String PATROL_REPORT_FINISH = "end?";

    public static final String PATROL_EQUIP_RECEIVE = "equipment/consuming/add";
    public static final String PATROL_EQUIP_RETURN = "equipment/consuming/rtn";

    //标注
//    public static final String MARK_IP = "https://39.97.112.101:8443/realtimeLabel/";
    public static final String MARK_IP = IP_COMMAND + "realtimeLabel/";
    public static final String MARK_ADD = "add";
    public static final String MARK_DELETE = "delete";
    public static final String MARK_QUERY = "query";

    //情报研判
    public static final String HTTP_INTELLIGENCE_PREFIX = "patrol-nj/patrol/intelligence/";
    public static final String HTTP_INTELLIGENCE_BY_STATUS = "getIntelligencePageDataByUserIdAndStatus";
    public static final String HTTP_INTELLIGENCE_DETAIL_BY_ID = "getIntelligenceDetailByDetailId";
    public static final String HTTP_INTELLIGENCE_FEEDBACK = "feedback";
    public static final String HTTP_INTELLIGENCE_DETAIL = "detail";

    //巡防督查
    public static final String HTTP_INSPECT_PREFIX = "patrol-nj/patrol/inspector/";
    public static final String HTTP_INSPECT_HISTORY = "list";
    public static final String HTTP_INSPECT_SEARCH = "command-control-nj/app/getUserOrGroup";

    //通知提示
    public static final String HTTP_NOTICE_LIST = "police-run/popt/notify/getPersonalNotify?orgCode=";

    //公务流转
    public static final String HTTP_OFFICIAL_PREFIX = IP_COMMAND + "police-run/popt/office/";
    public static final String HTTP_OFFICIAL_LIST = "newlist?page=1&pageSize=5000";
    public static final String HTTP_OFFICIAL_RECEIVE = "receive";

    //特警手册
    public static final String HTTP_HANDBOOK_BASE_CATEGORY = "patrol-nj/patrol/sort/handbook/queryDataByLevel?level=1";
    public static final String HTTP_HANDBOOK_PREFIX = "patrol-nj/patrol/handbook/";
    public static final String HTTP_HANDBOOK_LIST = "list";
    public static final String HTTP_HANDBOOK_SECONDARY = "queryClassifys?id=";

    //装备
    public static final String HTTP_EQUIP_LIST_EQUIP = "equipment?page=1&rows=0";
    public static final String HTTP_EQUIP_LIST_CATEGORY = "equipment/category/listAllCategory";
    public static final String HTTP_EQUIP_DETAIL = "equipment/equipment/qry/";
    public static final String HTTP_EQUIP_CONSUMING = "equipment/consuming?rows=10000";

    //数据导巡
    public static final String HTTP_WARNING_PREFIX = "command-control-nj/alarm/";
    public static final String HTTP_WARNING_LIST = "listAlarmByUserId";
    public static final String HTTP_WARNING_FEEDBACK = "feedbackAlarm";
    public static final String HTTP_WARNING_READ = "setAlarmRead";

    public static final String HTTP_MODEL_PREFIX = "patrol-nj/patrol/model/";
    public static final String HTTP_MODEL_STATUS = "/status";
    public static final String HTTP_MODEL_ADD = "/add";
    public static final String HTTP_MODEL_DETAIL = "/detail";
    public static final String HTTP_MODEL_UPDATE = "/update";

    public static final String HTTP_QUICK_PREFIX = "patrol-nj/patrol/quick/check/";
    public static final String HTTP_QUICK_LIST = "list";
    public static final String HTTP_QUICK_UPDATE = "update";
    public static final String HTTP_QUICK_ADD = "add";
    public static final String HTTP_QUICK_UPLOAD_IMAGE = "patrol-nj/patrol/library/person/savePic";
    public static AppCompatActivity activity = null;
    public static Double longitude = -1d;
    public static Double latitude = -1d;
    public static int loginPosition = -1;
//    public static SparseArray<List<GroupLeaderRes.DataBean.GroupLeader>> sparseArray = null;
//    public static SparseArray<List<GroupLeaderRes.DataBean.LogisticsPoint>> pointArray = null;
}
