package com.example.cengiz.cengo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class MeyveOyun extends AppCompatActivity {
    MediaPlayer bg;
    int sira = 0;
    ArrayList<Integer> sorularMeyve = new ArrayList<>();
    ArrayList<ImageView> secenek;
    int [] meyveler = new int[10];
    private TextToSpeech tts;
    private boolean flag;
    MediaPlayer dogru;
    MediaPlayer yanlis;
    ImageView buyut;
    Handler handler = new Handler();
    Runnable runnable = new Runnable(){
        @Override
        public void run(){
            yardim();
            handler.postDelayed(this, 5000);
        }
    };

    private void init() {
        this.startActivityForResult(new Intent(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA), 1);
    }

    public class Initializer implements TextToSpeech.OnInitListener {
        @Override
        public void onInit(int status) {
            if(status == TextToSpeech.SUCCESS) {
                flag = true;
                Locale locale = new Locale("tr", "TR");
                if(tts.isLanguageAvailable(locale) >= 0) {
                    tts.setLanguage(locale);
                    tts.setPitch(0.8F);
                    tts.setSpeechRate(1.5F);
                    TextView textView = (TextView) findViewById(R.id.secme);
                    MetniSeslendir(textView.getText().toString());
                }
                else {
                    Log.w("tts", "Türkçe paketi kurulu değil");
                }
            }
            if(status == TextToSpeech.ERROR) {
                Log.e("tts", "Hata");
            }
        }
    }

    private void MetniSeslendir(String metin) {
        if(tts != null && flag) {
            tts.speak(metin, TextToSpeech.QUEUE_ADD, null);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
            tts = new TextToSpeech(this, new Initializer());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);

        bg = MediaPlayer.create(MeyveOyun.this, R.raw.muzik);
        bg.setLooping(true);
        bg.start();

        randomize(sorularMeyve);
        sira = 0;
        setContentView(R.layout.activity_meyve_oyun);

        meyveler[0] = R.drawable.seftali;
        meyveler[1] = R.drawable.muz;
        meyveler[2] = R.drawable.nar;
        meyveler[3] = R.drawable.karpuz;
        meyveler[4] = R.drawable.kayisi;
        meyveler[5] = R.drawable.portakal;
        meyveler[6] = R.drawable.cilek;
        meyveler[7] = R.drawable.armut;
        meyveler[8] = R.drawable.erik;
        meyveler[9] = R.drawable.elma;
        devam();
        init();
    }

    @Override
    protected void onDestroy() {
        if(tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    private void randomize(ArrayList<Integer> list) {
        for (int i = 0; i < 10; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
    }

    private void imageChange() {

        ArrayList<Integer> secenekler = new ArrayList<>();
        randomize(secenekler);
        ImageView image1 = (ImageView) findViewById(R.id.secenek1);
        ImageView image2 = (ImageView) findViewById(R.id.secenek2);
        ImageView image3 = (ImageView) findViewById(R.id.secenek3);
        image1.setVisibility(View.VISIBLE);
        image2.setVisibility(View.VISIBLE);
        secenek = new ArrayList<>();
        if(sira > 4) {
            image3.setVisibility(View.VISIBLE);
            secenek.add(image1);
            secenek.add(image2);
            secenek.add(image3);
            Collections.shuffle(secenek);
            try {
                secenek.get(0).setBackgroundResource(meyveler[sorularMeyve.get(sira)]);
                int i = 0;
                if (secenekler.get(i).equals(sorularMeyve.get(sira))) {
                    i++;
                }
                secenek.get(1).setBackgroundResource(meyveler[secenekler.get(i)]);
                i++;
                if (secenekler.get(i).equals(sorularMeyve.get(sira))) {
                    i++;
                }
                secenek.get(2).setBackgroundResource(meyveler[secenekler.get(i)]);
            } catch (IndexOutOfBoundsException i) {};
        }
        else {
            image3.setVisibility(View.INVISIBLE);
            secenek.add(image1);
            secenek.add(image2);
            Collections.shuffle(secenek);
            try {
                secenek.get(0).setBackgroundResource(meyveler[sorularMeyve.get(sira)]);
                int i = 0;
                if (secenekler.get(i).equals(sorularMeyve.get(sira))) {
                    i++;
                }
                secenek.get(1).setBackgroundResource(meyveler[secenekler.get(i)]);
            } catch (IndexOutOfBoundsException i) {};
        }
    }

    public void dogruMu(View view) {

        handler.removeCallbacks(runnable);
        try {
            buyut.clearAnimation();
        } catch(Exception e) {}
        if(secenek.get(0).equals(view)) {
            dogru = MediaPlayer.create(this, R.raw.dogru);
            dogru.setVolume(10.0f, 3.0f);
            dogru.start();
            while(dogru.isPlaying() == true) {
            }
            sira++;
            try {
                dogru = MediaPlayer.create(this, R.raw.tebrik);
                dogru.setVolume(10.0f, 3.0f);
                dogru.start();
                animation();
            } catch(IndexOutOfBoundsException i) {};
        }
        else {
            yanlis = MediaPlayer.create(this, R.raw.yanlis);
            yanlis.setVolume(100.0f, 100.0f);
            yanlis.start();
            while(yanlis.isPlaying() == true) {
            }
            view.setVisibility(View.INVISIBLE);
        }
        handler.postDelayed(runnable, 5000);
    }

    public void devam() {
        System.gc();
        if(sira == 10) {
            finish();
        }
        try {
            dogru.stop();
        } catch(Exception e) {}
        handler.removeCallbacks(runnable);
        TextView textView = (TextView) findViewById(R.id.secme);
        String [] meyve = new String[10];
        meyve[0] = "Şeftaliyi";
        meyve[1] = "Muzu";
        meyve[2] = "Narı";
        meyve[3] = "Karpuzu";
        meyve[4] = "Kayısıyı";
        meyve[5] = "Portakalı";
        meyve[6] = "Çileği ";
        meyve[7] = "Armutu";
        meyve[8] = "Eriği";
        meyve[9] = "Elmayı";
        imageChange();
        try {
            textView.setText(meyve[sorularMeyve.get(sira)] + " seç");
        } catch(ArrayIndexOutOfBoundsException i){};
        MetniSeslendir(textView.getText().toString());
        handler.postDelayed(runnable, 5000);
    }

    protected void onPause(){
        super.onPause();
        bg.release();
    }

    private void yardim() {

        ScaleAnimation anim = new ScaleAnimation(1, 1.2f, 1, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(1);
        anim.setDuration(500);
        if(secenek.get(0).equals(findViewById(R.id.secenek1))) {
            buyut = (ImageView) findViewById(R.id.secenek1);
        }
        else if(secenek.get(0).equals(findViewById(R.id.secenek2))) {
            buyut = (ImageView) findViewById(R.id.secenek2);
        }
        else {
            buyut = (ImageView) findViewById(R.id.secenek3);
        }
        buyut.startAnimation(anim);
    }

    private void animation() {

        ImageView zoom = (ImageView) findViewById(R.id.imageTebrik);
        Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        zoomAnimation.setDuration(3000);
        zoom.startAnimation(zoomAnimation);
        zoomAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                handler.removeCallbacks(runnable);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if(sira >= 10) {
                    finish();
                }
                else  {
                    devam();
                }
            }

        });
    }
}