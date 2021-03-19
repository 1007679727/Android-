// IServer.aidl
package com.example.chat.msg;
import com.example.chat.msg.IClient;
import com.example.chat.msg.IMMessage;

// Declare any non-default types here with import statements

interface IServer {
    void onConnectSuccess(IClient client);
    void onDisConnectSuccess(IClient client);
    void send(in IMMessage imMessage);
}
