package ru.olegshulika.asmeet4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class StateReceiver extends BroadcastReceiver {
    private static final String TAG = "-StateReceiver";
    public static final String STATE_MESSAGE_ACTION = "ru.olegshulika.asmeet4.STATE_MESSAGE";
    public static final String CURRENT_STATE = "ru.olegshulika.asmeet4.CURRENT_STATE";
    public static final String STATE_MESSAGE_PERMISSION = "ru.olegshulika.asmeet4.STATE_MESSAGE_PERMISSION";

    @Override
    public void onReceive(Context context, Intent intent) {
        Character state = intent.getCharExtra(CURRENT_STATE,'?');
        Log.d(TAG, "onReceive="+state);
        Toast.makeText(context, "current state="+state, Toast.LENGTH_LONG).show();
    }

    public static Intent newIntent (Character state) {
        Intent broadcastIntent = new Intent ();
        broadcastIntent.setAction(STATE_MESSAGE_ACTION);
        broadcastIntent.putExtra(CURRENT_STATE, state);
        broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        Log.d(TAG, "newIntent="+state);

        return broadcastIntent;
    }
}
