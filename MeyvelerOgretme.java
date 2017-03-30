package com.example.cengiz.cengo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MeyvelerOgretme extends AppCompatActivity {

    int sira = 0;
    private TextToSpeech tts;
    private boolean flag;
    MediaPlayer bg;
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
                    MetniSeslendir("Şeftağli");
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
        setContentView(R.layout.activity_meyveler_ogretme);
        bg = MediaPlayer.create(MeyvelerOgretme.this, R.raw.muzik);
        bg.setLooping(true);
        bg.start();
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

    public void sonraki_sayfa(View view) {
        System.gc();
        ImageView image = (ImageView)findViewById(R.id.resim_meyve);
        ImageView image2 = (ImageView)findViewById(R.id.imageButtonGeri);
        image2.setVisibility(View.VISIBLE);
        String meyve;
        sira++;
        if(sira == 10) {
            finish();
        }
        switch (sira) {
            case 0:
                meyve = "şeftali";
                image.setImageResource(R.drawable.seftali);
                image2.setVisibility(View.INVISIBLE);
                break;
            case 1:
                meyve = "armut";
                image.setImageResource(R.drawable.armut);
                break;
            case 2:
                meyve = "elma";
                image.setImageResource(R.drawable.elma);
                break;
            case 3:
                meyve = "erik";
                image.setImageResource(R.drawable.erik);
                break;
            case 4:
                meyve = "karpuz";
                image.setImageResource(R.drawable.karpuz);
                break;
            case 5:
                meyve = "kayısı";
                image.setImageResource(R.drawable.kayisi);
                break;
            case 6:
                meyve = "muz";
                image.setImageResource(R.drawable.muz);
                break;
            case 7:
                meyve = "nar";
                image.setImageResource(R.drawable.nar);
                break;
            case 8:
                meyve = "portakal";
                image.setImageResource(R.drawable.portakal);
                break;
            case 9:
                meyve = "çilek";
                image.setImageResource(R.drawable.cilek);
                break;
            default:
                meyve = "";
        }
        TextView textView = (TextView) findViewById(R.id.text_meyve);
        textView.setText(meyve);
        MetniSeslendir(textView.getText().toString());
    }

    public void onceki_sayfa(View view) {
        sira = sira - 2;
        sonraki_sayfa(view);
    }
    protected void onPause(){
        super.onPause();
        bg.release();
       // finish();
    }
}
