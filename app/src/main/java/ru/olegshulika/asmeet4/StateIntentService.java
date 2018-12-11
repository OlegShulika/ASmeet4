package ru.olegshulika.asmeet4;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class StateIntentService extends IntentService {
    private static final String TAG = "_StateIntSrv";
                                                    // Change State action A->B->C->D->E->A...
    private static final String ACTION_CHANGESTATE = "ru.olegshulika.asmeet4.action.changestate";
    private static final String EXTRA_PARAM_SYSTIME = "ru.olegshulika.asmeet4.extra.systime";

    public StateIntentService() {
        super("StateIntentService");
    }

    /**
     * Starts this service to perform ChangeState action with the SysTime parameter. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionChangeState(Context context, long sysTime) {
        Intent intent = new Intent(context, StateIntentService.class);
        intent.setAction(ACTION_CHANGESTATE);
        intent.putExtra(EXTRA_PARAM_SYSTIME, sysTime);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_CHANGESTATE.equals(action)) {
                final long sysTime =  intent.getLongExtra(EXTRA_PARAM_SYSTIME, 0);
                handleActionChangeState(sysTime);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionChangeState(long sysemTime) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
