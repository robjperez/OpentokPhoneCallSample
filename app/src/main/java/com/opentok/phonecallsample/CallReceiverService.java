package com.opentok.phonecallsample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class CallReceiverService extends Service {
    private static final String TAG = CallReceiverService.class.getSimpleName();
    CallServiceBinder binder = new CallServiceBinder();

    public CallReceiverService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service onStartCommand");
        return  Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void notifyIncomingCall() {
        Intent incomingCallActivity = new Intent(this, IncomingCallActivity.class);
        startActivity(incomingCallActivity);
    }

    class CallServiceBinder extends Binder {
        CallReceiverService getService() {
            return CallReceiverService.this;
        }
    }
}
