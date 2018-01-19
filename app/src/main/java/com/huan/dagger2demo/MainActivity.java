package com.huan.dagger2demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.huan.dagger2demo.base.BaseActivity;
import com.huan.dagger2demo.di.test.DaggerTest;
import com.huan.dagger2demo.di.test.DaggerTest2;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    DaggerTest2 daggerTest2;

    @Inject
    DaggerTest daggerTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Main", "daggertest=" + daggerTest + ";daggertest2=" + daggerTest2);
        Button bt = (Button) findViewById(R.id.bt_test_activity);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
    }
}
