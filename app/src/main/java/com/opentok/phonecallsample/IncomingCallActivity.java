package com.opentok.phonecallsample;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class IncomingCallActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_call);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        OpenTokShared.getInstance().connectToSession(this);
        OpenTokShared.getInstance().createPublisher(this, true);

        ((FrameLayout)findViewById(R.id.publisher_container)).addView(OpenTokShared.getInstance().getPublisherView());
    }

    @Override
    protected void onPause() {
        ((FrameLayout)findViewById(R.id.publisher_container)).removeAllViews();
        super.onPause();
    }

    public void answerCall(View view) {
        Intent callActivity = new Intent(this, CallActivity.class);
        startActivity(callActivity);
    }
}
