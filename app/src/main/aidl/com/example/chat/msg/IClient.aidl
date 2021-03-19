// IResponse.aidl
package com.example.chat.msg;
import com.example.chat.msg.IMMessage;
// Declare any non-default types here with import statements

interface IClient{
    void response(in IMMessage imMessage);
    void notifyGps(in String gps);
    void debug(in String debug);
}
