package ru.olegshulika.asmeet4;

import android.util.Log;

// Change State action A->B->C->D->E->A...
public class AStateManager {
    private static final String TAG = "AStateManager";
    private static final Character START_STATE='A';
    private static final Character END_STATE='E';

    private Character state;

    private static final AStateManager instance = new AStateManager();

    private AStateManager() {
        state=START_STATE;
    }

    public static synchronized AStateManager getInstance() {
        return instance;
    }

    public synchronized void changeState() {
        if(++state>END_STATE)
            state=START_STATE;
        Log.d(TAG,"changeState-->"+state);
    }

    public synchronized Character getState() {
        return state;
    }
}
