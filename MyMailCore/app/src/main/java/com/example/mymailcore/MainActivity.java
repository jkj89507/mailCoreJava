package com.example.mymailcore;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.libmailcore.ConnectionType;
import com.libmailcore.IMAPOperation;
import com.libmailcore.IMAPSession;
import com.libmailcore.MailException;
import com.libmailcore.OperationCallback;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getMessages(View view) {
        IMAPSession session = new IMAPSession();

        session.setHostname("imap.yandex.ru");
        session.setPort(993);
        session.setConnectionType(ConnectionType.ConnectionTypeTLS);

        session.setUsername("JKJ89507@yandex.ru");
        session.setPassword("ygtccdpgvhuebryc");

//        int requestKind = IMAPMessagesRequestKind.IMAPMessagesRequestKindHeaders;
//        String folder = "INBOX";
//        IndexSet uids = IndexSet.indexSetWithRange(new Range(1, Range.RangeMax));

//        IMAPFetchMessagesOperation fetchOperation = session.fetchMessagesByUIDOperation(folder, requestKind, uids);
        IMAPOperation checkQuest = session.checkAccountOperation();

        checkQuest.start(new OperationCallback() {
            @Override
            public void succeeded() {
                Log.i("MyActivity", "Hello World!");
            }

            @Override
            public void failed(MailException e) {
                Log.i("MyActivity", "Fuck World! " + e.toString());
            }
        });


    }
}