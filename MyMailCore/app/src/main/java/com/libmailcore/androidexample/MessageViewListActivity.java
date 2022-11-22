package com.libmailcore.androidexample;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import com.libmailcore.IMAPMessage;

public class MessageViewListActivity extends Activity
        implements MessageViewListFragment.Callbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messageview_list);
    }

    public void onItemSelected(IMAPMessage msg) {
        MessagesSyncManager.singleton().currentMessage = msg;
        Intent detailIntent = new Intent(this, MessageViewListActivity.class);
        startActivity(detailIntent);
    }
}
