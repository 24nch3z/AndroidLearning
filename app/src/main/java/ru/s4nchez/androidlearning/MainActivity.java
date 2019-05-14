package ru.s4nchez.androidlearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Logger.INSTANCE.l("Activity 1: onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(v -> startActivity(new Intent(this, Activity2.class)));
    }

    @Override
    protected void onStart() {
        Logger.INSTANCE.l("Activity 1: onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Logger.INSTANCE.l("Activity 1: onResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Logger.INSTANCE.l("Activity 1: onRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Logger.INSTANCE.l("Activity 1: onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Logger.INSTANCE.l("Activity 1: onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Logger.INSTANCE.l("Activity 1: onDestroy");
        super.onDestroy();
    }
}
