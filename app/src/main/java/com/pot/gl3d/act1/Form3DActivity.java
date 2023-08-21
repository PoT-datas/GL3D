package com.pot.gl3d.act1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pot.gl3d.R;

public class Form3DActivity extends AppCompatActivity {

    MusicView musicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3dform);

        musicView = (MusicView) findViewById(R.id.musicView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        musicView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        musicView.onPause();
    }
}
