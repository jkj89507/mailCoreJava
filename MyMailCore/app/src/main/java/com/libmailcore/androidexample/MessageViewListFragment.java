package com.libmailcore.androidexample;

import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;

import com.libmailcore.ConnectionType;
import com.libmailcore.IMAPFetchMessagesOperation;
import com.libmailcore.IMAPMessage;
import com.libmailcore.IMAPSession;
import com.libmailcore.MailException;
import com.libmailcore.OperationCallback;
import com.libmailcore.IndexSet;
import com.libmailcore.IMAPMessagesRequestKind;
import com.libmailcore.Range;

public class MessageViewListFragment extends ListFragment implements OperationCallback {
    public IMAPSession session;
    public interface Callbacks {}

    public MessageViewListFragment() {
        session = new IMAPSession();
        session.setUsername("jkj89507@yandex.ru");
        session.setPassword("qrledrkjmsdwkzov");
        session.setHostname("imap.yandex.ru");
        session.setPort(993);
        session.setConnectionType(ConnectionType.ConnectionTypeTLS);
    }

    private IMAPFetchMessagesOperation fetchMessagesOp;
    private java.util.List<IMAPMessage> messages;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fetchMessagesOp = session.fetchMessagesByNumberOperation(
                                                    "INBOX",
                                                    IMAPMessagesRequestKind.IMAPMessagesRequestKindHeaders |
                                                    IMAPMessagesRequestKind.IMAPMessagesRequestKindStructure,
                                                    IndexSet.indexSetWithRange(new Range(1, Range.RangeMax))
                                            );
        fetchMessagesOp.start(this);
    }

    public void succeeded() {
        messages = fetchMessagesOp.messages();
        updateResult();
    }

    public void failed(MailException exception) {}

    private void updateResult() {
        for(IMAPMessage msg : messages) {
            Log.d("item", msg.toString());
        }
    }
}
