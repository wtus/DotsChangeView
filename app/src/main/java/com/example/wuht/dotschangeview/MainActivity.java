package com.example.wuht.dotschangeview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DotsView saf = (DotsView) findViewById(R.id.adsf);
        saf.start();
    }
}
