package com.opentok.phonecallsample;

import android.os.Bundle;
import android.app.Activity;
import android.widget.FrameLayout;

public class CallActivity extends Activity implements OpenTokShared.OpenTokListener {
    private FrameLayout publisherViewContainer;
    private FrameLayout subscriberViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        publisherViewContainer = findViewById(R.id.publisher_container);
        subscriberViewContainer = findViewById(R.id.subscriber_container);

        OpenTokShared.getInstance().connectToSession(this);
        OpenTokShared.getInstance().createPublisher(this, true);
        OpenTokShared.getInstance().addOpenTokListener(this);

        publisherViewContainer.addView(OpenTokShared.getInstance().getPublisherView());
    }

    @Override
    public void subscriberConnected() {
        subscriberViewContainer.addView(OpenTokShared.getInstance().getSubscriberView());
    }
}
