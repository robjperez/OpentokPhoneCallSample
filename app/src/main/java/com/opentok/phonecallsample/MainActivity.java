package com.opentok.phonecallsample;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int PERMISSION_REQUEST_CODE = 1000;

    CallReceiverService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent serviceIntent = new Intent(this, CallReceiverService.class);
        startService(serviceIntent);
        bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);

        findViewById(R.id.simulate_call_button).setEnabled(false);
    }

    @Override
    protected void onStop() {
        unbindService(connection);

        super.onStop();
    }

    public void simulateCall(View view) {
        Log.d(TAG, "Scheduling incoming call");
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //if (service != null) {
                    service.notifyIncomingCall();
                //}
            }
        }, 5000);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "Service Connected");
            CallReceiverService.CallServiceBinder binder = (CallReceiverService.CallServiceBinder) iBinder;
            service = binder.getService();
            findViewById(R.id.simulate_call_button).setEnabled(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "Service Disconnected");
            service = null;
        }
    };
}
