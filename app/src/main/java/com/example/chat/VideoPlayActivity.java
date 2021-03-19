package com.example.chat;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.annotation.Nullable;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by FHZ on 2018/9/30.
 */

public class VideoPlayActivity extends BaseActivity {

    @BindView(R.id.videoViewMsg)
    VideoView videoViewMsg;
    private String playUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        playUrl = getIntent().getStringExtra("playUrl");
        videoViewMsg.setVideoURI(Uri.parse(playUrl));
        try {
            HttpsURLConnection.setDefaultSSLSocketFactory(SSlUtiles.createSSLSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new SSlUtiles.TrustAllHostnameVerifier());
        } catch (Exception e) {
            e.printStackTrace();
        }
        videoViewMsg.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoViewMsg.stopPlayback();
    }

    public static class SSlUtiles {
        /**
         * 默认信任所有的证书
         * <p>
         * xts
         */
        public static SSLSocketFactory createSSLSocketFactory() {

            SSLSocketFactory sSLSocketFactory = null;

            try {
                SSLContext sc = SSLContext.getInstance("TLS");
                sc.init(null, new TrustManager[]{(TrustManager) new TrustAllManager()}, new SecureRandom());
                sSLSocketFactory = sc.getSocketFactory();
            } catch (Exception e) {
            }

            return sSLSocketFactory;
        }

        public static class TrustAllManager implements X509TrustManager {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType)

                    throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }

        public static class TrustAllHostnameVerifier implements HostnameVerifier {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        }

    }
}
