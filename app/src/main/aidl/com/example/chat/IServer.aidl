// IServer.aidl
package com.example.chat;
import com.example.chat.IClient;
import com.example.chat.IMMessage;

// Declare any non-default types here with import statements

interface IServer {
    void onConnectSuccess(IClient client);
    void onDisConnectSuccess(IClient client);
    void send(in IMMessage imMessage);
}
