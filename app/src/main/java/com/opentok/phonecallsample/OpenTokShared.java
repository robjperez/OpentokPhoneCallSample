package com.opentok.phonecallsample;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.View;

import com.opentok.android.BaseVideoRenderer;
import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;

/**
 * Created by rpc on 13/02/2018.
 */

public class OpenTokShared implements Session.SessionListener {

    public static final String APIKEY = "";
    public static final String TOKEN = "";
    public static final String SESSION_ID = "";


    private static OpenTokShared instance;
    public static synchronized OpenTokShared getInstance() {
        if (instance == null)
            instance = new OpenTokShared();

        return instance;
    }

    private Session session;
    private Publisher publisher;
    private Subscriber subscriber;
    private boolean sessionIsConnected = false;
    private Context sessionContext;

    public void connectToSession(Context context) {
        sessionContext = context;
        session = new Session.Builder(context, APIKEY, SESSION_ID).build();
        session.connect(TOKEN);
        session.setSessionListener(this);
    }

    public void createPublisher(Context context, boolean startPreview) {
        publisher = new Publisher.Builder(context).build();
        publisher.getRenderer().setStyle(BaseVideoRenderer.STYLE_VIDEO_SCALE,
                BaseVideoRenderer.STYLE_VIDEO_FILL);
        if (publisher.getView() instanceof GLSurfaceView) {
            ((GLSurfaceView) publisher.getView()).setZOrderOnTop(true);
        }
        if (startPreview) {
            publisher.startPreview();
        }

        if (session != null && sessionIsConnected) {
            session.publish(publisher);
        }
    }

    public View getPublisherView() {
        if (publisher != null) {
            return publisher.getView();
        }
        return null;
    }

    public View getSubscriberView() {
        if (subscriber != null) {
            return subscriber.getView();
        }
        return null;
    }


    @Override
    public void onConnected(Session session) {
        sessionIsConnected = true;
        if (publisher != null) {
            session.publish(publisher);
        }
    }

    @Override
    public void onDisconnected(Session session) {
        sessionIsConnected = false;
    }

    @Override
    public void onStreamReceived(Session session, Stream stream) {
        subscriber = new Subscriber.Builder(sessionContext, stream).build();
        subscriber.getRenderer().setStyle(BaseVideoRenderer.STYLE_VIDEO_SCALE,
                BaseVideoRenderer.STYLE_VIDEO_FILL);
        session.subscribe(subscriber);
    }

    @Override
    public void onStreamDropped(Session session, Stream stream) {

    }

    @Override
    public void onError(Session session, OpentokError opentokError) {

    }
}
