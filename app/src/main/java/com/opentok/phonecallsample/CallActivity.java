package com.opentok.phonecallsample;

import android.os.Bundle;
import android.app.Activity;
import android.widget.FrameLayout;

public class CallActivity extends Activity {
    private FrameLayout publisherViewContainer;
    private FrameLayout subscriberViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        publisherViewContainer = findViewById(R.id.publisher_container);
        subscriberViewContainer = findViewById(R.id.subscriber_container);

        publisherViewContainer.addView(OpenTokShared.getInstance().getPublisherView());
        subscriberViewContainer.addView(OpenTokShared.getInstance().getSubscriberView());

    }

}
