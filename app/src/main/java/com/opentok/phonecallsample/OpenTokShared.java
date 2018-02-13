package com.opentok.phonecallsample;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.View;

import com.opentok.android.BaseVideoRenderer;
import com.opentok.android.Publisher;
import com.opentok.android.Session;

/**
 * Created by rpc on 13/02/2018.
 */

public class OpenTokShared {
    
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

    public void connectToSession(Context context) {
        session = new Session.Builder(context, APIKEY, SESSION_ID).build();
        session.connect(TOKEN);
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
    }

    public View getPublisherView() {
        if (publisher != null) {
            return publisher.getView();
        }
        return null;
    }


}
