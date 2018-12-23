package ru.olegshulika.asmeet4;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

public class StateIntentService extends IntentService {
    private static final String TAG = "_StateIntSrv";
    private static final String STATEINTENT_ACTION = "ru.olegshulika.asmeet4.action.stateintent";
    private static final String EXTRA_PARAM_SYSTIME = "ru.olegshulika.asmeet4.extra.systime";
    private AStateManager stateMgr;

    public StateIntentService() {
        super(StateIntentService.class.getName());
        stateMgr = AStateManager.getInstance();
        Log.d(TAG, "constructor");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent "+intent);
        if (intent != null) {
            final String action = intent.getAction();
            if (STATEINTENT_ACTION.equals(action)) {
                final long sysTime =  intent.getLongExtra(EXTRA_PARAM_SYSTIME, 0);
                runActionChangeState(sysTime);
            }
        }
    }

    private void runActionChangeState(long systemTime) {
        Log.d(TAG, "runActionChangeState ms="+(System.currentTimeMillis()-systemTime));
        stateMgr.changeState();
        Intent broadcastIntent = StateReceiver.newIntent (stateMgr.getState());
        sendBroadcast(broadcastIntent);
        Log.d(TAG, "send broadcast="+broadcastIntent);
    }

    public static final Intent getIntentForChangeState(@NonNull Context context, long sysTime) {
        Intent intent = newIntent(context);
        intent.setAction(STATEINTENT_ACTION);
        intent.putExtra(EXTRA_PARAM_SYSTIME, sysTime);
        return intent;
    }

    private static final Intent newIntent(Context context){
        Intent intent = new Intent (context, StateIntentService.class);
        return intent;
    }
}
