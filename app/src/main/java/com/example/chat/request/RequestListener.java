package com.example.chat.request;

public interface RequestListener {
    void onSuccess(Object o);
    void onError();
}
