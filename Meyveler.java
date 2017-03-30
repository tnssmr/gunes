package com.example.cengiz.cengo;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Meyveler extends AppCompatActivity {

    Button mOgren;
    Button mOyna;
    Button mTum;
    final Context context = this;
    MediaPlayer bg;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            System.gc();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_meyveler);

            bg = MediaPlayer.create(Meyveler.this, R.raw.muzik);
            bg.setLooping(true);
            bg.start();

            mOgren = (Button) findViewById(R.id.meyve_ogren);

            mOgren.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(context, MeyvelerOgretme.class);
                    startActivity(intent);
                }
            });

            mOyna = (Button)findViewById(R.id.meyve_oyna);
            mOyna.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(context, MeyveOyun.class);
                    startActivity(intent);
                }
            });

            mTum = (Button)findViewById(R.id.meyve_tum);
            mTum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(context, MeyveTamami.class);
                    startActivity(intent);
                }
            });


        }

    protected void onPause(){
        super.onPause();
        bg.release();
       // finish();
    }

}
