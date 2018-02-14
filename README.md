### DISCLAIMER

This sample is in its very early stages and still Work in Progress. Use it at your own risk ;)

Opentok Phone Call sample
=========================

In this sample we will show an example of how a phonecall like experience can be done using OpenTok Android SDK.
Since OpenTok sdk does not provide a way to receive notifications without being connected to a session, this sample shows how to implement the UI and the OpenTok session management around a phone call like interface.

Is up to the developer to set up a communication channel where you can receive the phone call notification and the OpenTok Session details

What's inside
-------------

- *MainActivity*: This activity contains a button to simulate the incoming phone call. In a real world application you will receive the incoming call notification using any notification service. Please note that this activity is just used for demo purposes.

- *CallReceiverService*: Once the notification has been received, this service will be in charge of showing the IncomingCall activity. We have used a _Sticky_ service since this will be running in the background.

- *IncomingCallActivity*: When you want to notify the user that he has an incoming call, you can use this activity. This activity is also setup to be showed over the lock screen of the phone. This behaviour is similar to what a phone call experencie will be. From this activity you can accept the call and go to the call activity or decline the call. If you want to play a ringtone, this is where you should do it.

- *CallActivity*: Here is where the video call happens. You will find a couple of views for the Publisher and the Subscriber. For the sake of simplicity this sample just covers 1 on 1 video calls.

- *OpenTokShared*: Since the session, publisher and subscriber objects are shared between several activities and services, this is where we store all the OpenTok related objects.

Incoming Phone Call Scenario
----------------------------

This is an example of an architecture to receive a phone call

```
          Backend                              Android
          -------                              -------
                                 |
Application    > Sends        >  |  Incoming           >  Launch Incoming   >   Accept Call
Service        > Notification >  |  Call notification  >  IncomingCall      >   and connect
Backend        >              >  |  Service            >  Activity          >   to the OpenTok Session
                                 |
```

The backend will receive the notification from a given user A to call user B. At that point the service backend will generate a notification to user B and will send that notification using some service like GCM, Firebase or any other. That notification will reach the phone and the service which is listening to it will show the Incoming call activity (*IncomingCallActivity* in our sample) to the user. At that point we can decide to start connecting to the opentok session and show the video of the subscriber (similar to what Duo does) or just present the call to the user. That activity will popup even if the phone is locked. If the user decides to answer the call, the *CallActivity* will be shown. *CallActivity* is where the opentok session is set up (if it hasn't been done in the *IncomingCallActivity*) and where the publisher will be built and his stream published.
