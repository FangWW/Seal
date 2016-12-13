package com.seal;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public SealView mSealView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSealView = (SealView) findViewById(R.id.v_s);
        mSealView.setRightString("嘿嘿");
        mSealView.setLeftString("哈哈");
    }
}
