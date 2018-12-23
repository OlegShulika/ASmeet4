package ru.olegshulika.asmeet4;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int TEXT_SIZE = 32;
    private Button mChangeStateButton;
    private TextView mCurrentState;
    private StateReceiver mStateReceiver;
    private IntentFilter mIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
        initBroadcastReceiver();
        Log.d(TAG, " onCreate");
    }

    void initViews(){
        mChangeStateButton = findViewById(R.id.button_change_state);
        mCurrentState = findViewById(R.id.current_state_value);
        mCurrentState.setTextSize(TEXT_SIZE);
        this.setTitle(getString(R.string.app_name));
    }

    void initListeners() {
        mChangeStateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = StateIntentService.getIntentForChangeState(MainActivity.this,
                                      System.currentTimeMillis());
                startService(intent);
            }
        });
    }

    void initBroadcastReceiver() {
        mStateReceiver = new StateReceiver(new StateActionCallBack(){
            @Override
            public void onStateMessageReceived(Character state){
                mCurrentState.setText("->"+state);
            }
        });
        mIntentFilter = new IntentFilter(StateReceiver.STATE_MESSAGE_ACTION);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, " onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, " onResume");
        registerReceiver(mStateReceiver,mIntentFilter);
        //registerReceiver(mStateReceiver,mIntentFilter,StateReceiver.STATE_MESSAGE_PERMISSION,null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, " onPause");
        unregisterReceiver(mStateReceiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, " onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, " onDestroy");
    }

}
