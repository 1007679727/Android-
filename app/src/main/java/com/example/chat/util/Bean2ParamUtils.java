package com.example.chat.util;

import java.lang.reflect.Field;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by FHZ on 2018/7/19.
 */

public class Bean2ParamUtils {
    private static String TAG = Bean2ParamUtils.class.getSimpleName();

//    public static <T> String generateGetReqUrl(T t) {
//        //路径首字母小写
//        StringBuilder stringBuilderReqUrl = new StringBuilder();
//        stringBuilderReqUrl.append(Constant.reqAddrWithPre).append(t.getClass().getSimpleName().charAt(0) + 32).append(t.getClass().getSimpleName().substring(1));
//        Field[] fields = t.getClass().getDeclaredFields();
//        try {
//            for (Field field : fields) {
//                field.setAccessible(true);
//                if (field.get(t) != null) {
//                    if (!field.getName().equals("serialVersionUID")) {
//                        stringBuilderReqUrl.append("&").append(field.getName()).append("=").append(URLEncoder.encode(("" + field.get(t)), "UTF-8"));
//                    }
//                }
//            }
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return stringBuilderReqUrl.toString().trim();
//    }
//
//    public static <T> String generateGetReqUrl(T t, String requestMethod) {
//        //路径首字母小写
//        StringBuilder stringBuilderReqUrl = new StringBuilder();
//        stringBuilderReqUrl.append(Constant.reqAddrWithPre).append(requestMethod);
////                .append(t.getClass().getSimpleName().charAt(0) + 32).append(t.getClass().getSimpleName().substring(1));
//        Field[] fields = t.getClass().getDeclaredFields();
//        try {
//            for (Field field : fields) {
//                field.setAccessible(true);
//                if (field.get(t) != null) {
//                    if (!field.getName().equals("serialVersionUID")) {
//                        stringBuilderReqUrl.append("&").append(field.getName()).append("=").append(URLEncoder.encode(("" + field.get(t)), "UTF-8"));
//                    }
//                }
//            }
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return stringBuilderReqUrl.toString().trim();
//    }

    public static <T> RequestBody generatePostReqUrl(T t) {
        Field[] fields = t.getClass().getDeclaredFields();
//        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        FormBody.Builder builder = new FormBody.Builder();
        String fileName = null;
        RequestBody requestBodyFile = null;
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(t) != null) {
//                    if (field.getName().equals("file")) {
//                        requestBodyFile = RequestBody.create(MediaType.parse("application/octet-stream"), (File) field.get(t));
//                        continue;
//                    } else if (field.getName().equals("fileName")) {
//                        fileName = URLEncoder.encode(("" + field.get(t)), "UTF-8");
//                    } else
                        if (field.getName().equals("serialVersionUID")) {
                    } else {
//                        builder.addFormDataPart(field.getName(), ("" + field.get(t)));
                        builder.add(field.getName(), ("" + field.get(t)));
                    }
                }
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
//        if (fileName != null) {
//            builder.addFormDataPart("file", fileName, requestBodyFile);
//        }
        return builder.build();
    }
}
