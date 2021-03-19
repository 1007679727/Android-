package com.example.chat.request;

import java.io.File;

public interface DownloadListener {
    void inProgress(double progress);

    void onError();

    void onSuccess(File file);
}
