// IResponse.aidl
package com.example.chat;
import com.example.chat.IMMessage;
// Declare any non-default types here with import statements

interface IClient{
    void response(in IMMessage imMessage);
    void notifyGps(in String gps);
    void debug(in String debug);
}
