package com.libmailcore.androidexample;

import com.libmailcore.ConnectionType;
import com.libmailcore.IMAPSession;
import com.libmailcore.IMAPMessage;

public class MessagesSyncManager {
    public IMAPSession session;
    public IMAPMessage currentMessage;

    static private MessagesSyncManager theSingleton;

    public static MessagesSyncManager singleton() {
        if (theSingleton == null) {
            theSingleton = new MessagesSyncManager();
        }
        return theSingleton;
    }

    private MessagesSyncManager() {
        session = new IMAPSession();
        session.setUsername("jkj89507@yandex.ru");
        session.setPassword("qrledrkjmsdwkzov");
        session.setHostname("imap.yandex.ru");
        session.setPort(993);
        session.setConnectionType(ConnectionType.ConnectionTypeTLS);
    }
}
