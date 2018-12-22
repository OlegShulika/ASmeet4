package ru.olegshulika.asmeet4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "1_MainAct";
    private Button mChangeStateButton;
    private TextView mCurrentState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
        Log.d(TAG, " onCreate");
    }

    void initViews(){
        mChangeStateButton = findViewById(R.id.button_change_state);
        mCurrentState = findViewById(R.id.current_state_value);
        this.setTitle(getString(R.string.app_name));
    }

    void initListeners() {
        mChangeStateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateIntentService.startChangeStateAction(MainActivity.this,
                        System.currentTimeMillis());
            }
        });
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
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, " onPause");
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
