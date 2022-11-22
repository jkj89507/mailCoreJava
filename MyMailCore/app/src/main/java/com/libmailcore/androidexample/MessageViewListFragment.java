package com.libmailcore.androidexample;

import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;

import com.libmailcore.IMAPFetchMessagesOperation;
import com.libmailcore.IMAPMessage;
import com.libmailcore.MailException;
import com.libmailcore.OperationCallback;
import com.libmailcore.IndexSet;
import com.libmailcore.IMAPMessagesRequestKind;
import com.libmailcore.Range;

public class MessageViewListFragment extends ListFragment implements OperationCallback {
    public interface Callbacks {}

    public MessageViewListFragment() {}

    private IMAPFetchMessagesOperation fetchMessagesOp;
    private java.util.List<IMAPMessage> messages;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fetchMessagesOp = MessagesSyncManager.singleton()
                                            .session
                                            .fetchMessagesByNumberOperation(
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
