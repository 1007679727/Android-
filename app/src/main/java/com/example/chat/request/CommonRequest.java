package com.example.chat.request;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.chat.BaseApplication;
import com.example.chat.Bean2ParamUtils;
import com.example.chat.Constant;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zh on 2017/6/14.
 */

public class CommonRequest {

    private String TAG = CommonRequest.class.getSimpleName();
    private final static long DEFAULT_TIMEOUT = 20000L;
    private OkHttpClient okHttpClient;

    private CommonRequest() {
        init();
    }

    private static class SingleHolder {
        private final static CommonRequest INSTANCE = new CommonRequest();
    }

    public static CommonRequest getInstance() {
        return SingleHolder.INSTANCE;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    private void init() {
        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                        
                        Constant.cookieStore.put(httpUrl.host(), list);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                        List<Cookie> cookies = Constant.cookieStore.get(httpUrl.host());
                        if (cookies != null) {

                            
                        }
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .sslSocketFactory(createSSLSocketFactory())
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }


    private static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public <T> void get(@NonNull String url, @NonNull final Object tag, final Class<T> resCls, @Nullable final RequestListener listener) {
        Request.Builder builder = new Request.Builder();
        if(MacAddressUtils.getLocalInetAddress()!=null) {
            builder.addHeader("terminalId", MacAddressUtils.getLocalInetAddress().getHostAddress());
            builder.addHeader("terminalSerialNumber", MacAddressUtils.getSERIAL(BaseApplication.getAppInstance()));
        }

        Request request =
//                new Request.Builder()
                builder
                .url(url)
                .tag(tag)
                .build();
        
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                
                if (call.isCanceled()) {
                    return;
                }
                if (listener != null) {
                    listener.onError();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (listener != null && response.isSuccessful()) {
                    String string = response.body().string();
                    
                    if (TextUtils.isEmpty(string)) {
                        listener.onError();
                    } else {
                        Gson gson = new Gson();
                        if (resCls != null) {
                            T resBean = gson.fromJson(string, resCls);
                            listener.onSuccess(resBean);
                        } else {
                            listener.onSuccess(string);
                        }
                    }
                }
            }
        });
    }

    public <T> void get(@NonNull String url, final Map<String, String> headers, @NonNull final Object tag, final Class<T> resCls, @Nullable final RequestListener listener) {
        Request.Builder builder = new Request.Builder();
        builder.addHeader("terminalId", MacAddressUtils.getLocalInetAddress().getHostAddress());
        builder.addHeader("terminalSerialNumber",MacAddressUtils.getSERIAL(BaseApplication.getAppInstance()));

//        headers.forEach(builder::addHeader);
        if (headers != null) {
            Iterator it = headers.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry en = (Map.Entry) it.next();
                String key = (String) en.getKey();
                String val = (String) en.getValue();
                builder.addHeader(key, val);
            }
        }

        Request request = builder
                .url(url)
                .tag(tag)
                .build();
        
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                
                if (call.isCanceled()) {
                    return;
                }
                if (listener != null) {
                    listener.onError();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (listener != null && response.isSuccessful()) {
                    String string = response.body().string();
                    
                    if (TextUtils.isEmpty(string)) {
                        listener.onError();
                    } else {
                        Gson gson = new Gson();
                        if (resCls != null) {
                            T resBean = gson.fromJson(string, resCls);
                            listener.onSuccess(resBean);
                        } else {
                            listener.onSuccess(string);
                        }
                    }
                }
            }
        });
    }

    public <T> void get(@NonNull String url, @NonNull final Object tag, long timeOut, @Nullable final RequestListener listener) {
        OkHttpClient client = generateNewClient(timeOut);
        Request request = new Request.Builder()
                .url(url)
                .tag(tag)
                .build();
        
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                
                if (call.isCanceled()) {
                    return;
                }
                if (listener != null) {
                    listener.onError();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (listener != null && response.isSuccessful()) {
                    String string = response.body().string();
                    
                    if (TextUtils.isEmpty(string)) {
                        listener.onError();
                    } else {
                        listener.onSuccess(string);
                    }
                }
            }
        });
    }

    public <T> void post(@NonNull String url, @NonNull Map<String, String> params, final Map<String, String> headers, @NonNull Object tag, final Class<T> resCls, @Nullable final RequestListener listener) {
        
        FormBody.Builder builder = new FormBody.Builder();

        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }
        RequestBody requestBody = builder.build();
        handlePost(url, tag, headers, resCls, listener, requestBody);
    }

    public <T> void post(@NonNull String url, @NonNull Map<String, String> params, @NonNull Object tag, final Class<T> resCls, @Nullable final RequestListener listener) {
        
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }
        RequestBody requestBody = builder.build();
        handlePost(url, tag, resCls, listener, requestBody);
    }

    public <T> void post(@NonNull String url, @NonNull String param, @NonNull Object tag, final Class<T> resCls, @Nullable final RequestListener listener) {
        
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), param);
        handlePost(url, tag, resCls, listener, requestBody);
    }

    public <T> void post(@NonNull String url, @NonNull String param, final Map<String, String> headers, @NonNull Object tag, final Class<T> resCls, @Nullable final RequestListener listener) {
        
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), param);
        handlePost(url, tag, headers, resCls, listener, requestBody);
    }

    public <M, N> void post(@NonNull String url, @NonNull M m, @NonNull Object tag, final Class<N> resCls, @Nullable final RequestListener listener) {
        
        RequestBody requestBody = Bean2ParamUtils.generatePostReqUrl(m);
        handlePost(url, tag, resCls, listener, requestBody);
    }

    public <T> void upload(@NonNull String url, @NonNull Map<String, String> params, @NonNull File file, @NonNull String name, @NonNull Object tag, Class<T> resCls, @Nullable final RequestListener listener) {
        
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        if (params != null) {
            for (String key : params.keySet()) {
                if (!TextUtils.isEmpty(params.get(key))) {
                    builder.addFormDataPart(key, params.get(key));
                }
            }
        }
        builder.addFormDataPart(name, file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
        RequestBody requestBody = builder.build();
        handlePost(url, tag, resCls, listener, requestBody);
    }

    public <T> void uploadFiles(@NonNull String url, @NonNull Map<String, String> params, @NonNull List<File> files, @NonNull String name, @NonNull Object tag, Class<T> resCls, @Nullable final RequestListener listener) {
        
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        if (params != null) {
            for (String key : params.keySet()) {
                if (!TextUtils.isEmpty(params.get(key))) {
                    builder.addFormDataPart(key, params.get(key));
                }
            }
        }
        for(File file:files){
            builder.addFormDataPart(name, file.getName(), RequestBody.create(MediaType.parse(guessMimeType(file.getName())), file));
        }
        RequestBody requestBody = builder.build();
        handlePost(url, tag, resCls, listener, requestBody);
    }

    private <T> void handlePost(@NonNull String url, @NonNull final Object tag, final Class<T> resCls, @Nullable final RequestListener listener, RequestBody requestBody) {
        Request.Builder builder = new Request.Builder();
        builder.addHeader("terminalId", MacAddressUtils.getLocalInetAddress().getHostAddress());
//        builder.addHeader("terminalSerialNumber",MacAddressUtils.getSERIAL(BaseApplication.getAppInstance()));

        Request request =
//                new Request.Builder()
                builder
                        .url(url).tag(tag).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                
                
                if (call.isCanceled()) {
                    return;
                }
                if (listener != null) {
                    listener.onError();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (listener != null && response.isSuccessful()) {
                    String string = response.body().string();
//                    
                    
                    if (TextUtils.isEmpty(string)) {
                        listener.onError();
                    } else {
                        Gson gson = new Gson();
                        if (resCls != null) {
                            T resBean = gson.fromJson(string, resCls);
                            listener.onSuccess(resBean);
                        } else {
                            listener.onSuccess(string);
                        }

                    }
                }
            }
        });
    }

    private <T> void handlePost(@NonNull String url, @NonNull final Object tag, final Map<String, String> headers, final Class<T> resCls, @Nullable final RequestListener listener, RequestBody requestBody) {
        Request.Builder builder = new Request.Builder();
        builder.addHeader("terminalId", MacAddressUtils.getLocalInetAddress().getHostAddress());
        builder.addHeader("terminalSerialNumber",MacAddressUtils.getSERIAL(BaseApplication.getAppInstance()));
//        headers.forEach(builder::addHeader);
        if (headers != null) {
            Iterator it = headers.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry en = (Map.Entry) it.next();
                String key = (String) en.getKey();
                String val = (String) en.getValue();
                builder.addHeader(key, val);
            }
        }

        Request request = builder
                .url(url)
                .tag(tag)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                
                
                if (call.isCanceled()) {
                    return;
                }
                if (listener != null) {
                    listener.onError();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (listener != null && response.isSuccessful()) {
                    String string = response.body().string();
//                    
                    
                    if (TextUtils.isEmpty(string)) {
                        listener.onError();
                    } else {
                        Gson gson = new Gson();
                        if (resCls != null) {
                            T resBean = gson.fromJson(string, resCls);
                            listener.onSuccess(resBean);
                        } else {
                            listener.onSuccess(string);
                        }
                    }
                }
            }
        });
    }


    private OkHttpClient generateNewClient(long timeOut) {
        OkHttpClient client = okHttpClient.newBuilder()
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                        
                        Constant.cookieStore.put(httpUrl.host(), list);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                        List<Cookie> cookies = Constant.cookieStore.get(httpUrl.host());
                        if (cookies != null) {

                            
                        }
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .connectTimeout(timeOut, TimeUnit.MILLISECONDS)
                .readTimeout(timeOut, TimeUnit.MILLISECONDS)
                .build();
        return client;

    }

    public void cancel(@NonNull Object tag) {
        for (Call call : okHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : okHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    public void download(@NonNull String url, @NonNull Object tag, File file, @Nullable final DownloadListener listener) {
        Request request = new Request.Builder().url(url).tag(tag).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (listener != null) {
                    listener.onError();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                
                if (listener != null) {
                    if (!response.isSuccessful()) {
                        listener.onError();
                        return;
                    }
                }
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    long total = response.body().contentLength();
                    long current = 0;
                    is = response.body().byteStream();
                    File filePath = file.getParentFile();
                    if (!filePath.exists()) {
                        filePath.mkdirs();
                    }
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        current += len;
                        fos.write(buf, 0, len);
                        if (listener != null) {
                            double perscent = current / total;
                            
                            listener.inProgress(current*100 / total);
                        }
                    }
                    fos.flush();
                    if (listener != null) {
                        
                        listener.onSuccess(file);
                    }

                } catch (IOException e) {
                    if (listener != null) {
                        listener.onError();
                    }
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        });


    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = null;

        try {
            contentTypeFor = fileNameMap.getContentTypeFor(URLEncoder.encode(path, "UTF-8"));
        } catch (UnsupportedEncodingException var5) {
            var5.printStackTrace();
        }

        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }

        return contentTypeFor;
    }

}
