package tansu.gunes;

import android.content.Intent;
        import android.media.MediaPlayer;
        import android.os.Bundle;
        import android.os.Handler;
        import android.speech.tts.TextToSpeech;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.view.animation.LinearInterpolator;
        import android.view.animation.RotateAnimation;
        import android.view.animation.ScaleAnimation;
        import android.view.animation.TranslateAnimation;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;


        //  import tansu.gunes.QuestionLibrary;

        import java.util.Locale;



public class taneoyun2 extends AppCompatActivity {

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();
    // public animasyon mAnimasyon = new animasyon();

    private ImageView mQuestionView;
    private ImageView mtebrikView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;

    private String mAnswer;
    private int mQuestionNumber = 0;
    MediaPlayer bg;
    MediaPlayer dogru;
    MediaPlayer yanlis;
    ImageView view;
    //int resimler = R.drawable.balloon;
    private TextToSpeech tts;
    Button buyut;
    Handler handler = new Handler();
    Runnable runnable = new Runnable(){
        @Override
        public void run(){
            yardim();
            handler.postDelayed(this, 5000);
        }
    };
    private boolean flag;
    int sira = 0;

    private void init() {
        this.startActivityForResult(new Intent(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA), 1);
    }

    public class Initializer implements TextToSpeech.OnInitListener {
        @Override
        public void onInit(int status) {
            if (status == TextToSpeech.SUCCESS) {
                flag = true;
                Locale locale = new Locale("tr", "TR");
                if (tts.isLanguageAvailable(locale) >= 0) {
                    tts.setLanguage(locale);
                    tts.setPitch(0.8F);
                    tts.setSpeechRate(1.5F);
                    TextView textView = (TextView) findViewById(R.id.textt);
                    MetniSeslendir(textView.getText().toString());
                } else {
                    Log.w("tts", "Türkçe paketi kurulu deÄŸil");
                }
            }
            if (status == TextToSpeech.ERROR) {
                Log.e("tts", "Hata");
            }
        }
    }

    private void MetniSeslendir(String metin) {
        if (tts != null && flag) {
            tts.speak(metin, TextToSpeech.QUEUE_ADD, null);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
            tts = new TextToSpeech(this, new Initializer());
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        bg.release();

        // finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        bg = MediaPlayer.create(taneoyun2.this, R.raw.muzik);
        bg.setLooping(true);
        bg.start();
        init();
        setContentView(R.layout.activity_taneoyun2);

        mQuestionView = (ImageView) findViewById(R.id.question);
        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);

        updateQuestion();

        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for Button goes in here

                try {
                    buyut.clearAnimation();
                } catch(Exception e) {}

                if (mButtonChoice1.getText() == mAnswer) {

                    //Toast.makeText(taneoyun2.this, "correct", Toast.LENGTH_SHORT).show();

                    dogru = MediaPlayer.create(taneoyun2.this, R.raw.tebrik);
                    dogru.setVolume(10.0f, 3.0f);
                    dogru.start();
                    animation();

                    // MetniSeslendir(mAnswer);
                    // MetniSeslendir("Aferin Dogru cevap");


                } else {
                    //  Toast.makeText(taneoyun2.this, "wrong", Toast.LENGTH_SHORT).show();
                    yanlis = MediaPlayer.create(taneoyun2.this, R.raw.yanlis);
                    yanlis.setVolume(10.0f, 3.0f);
                    yanlis.start();

                    mButtonChoice1.setVisibility(mButtonChoice1.INVISIBLE);
                    //updateQuestion();
                }

            }
        });

        //End of Button Listener for Button1

        //Start of Button Listener for Button2
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for Button goes in here
                try {
                    buyut.clearAnimation();
                } catch(Exception e) {}

                if (mButtonChoice2.getText() == mAnswer) {


                    //This line of code is optiona
                    //Toast.makeText(taneoyun2.this, "correct", Toast.LENGTH_SHORT).show();

                    dogru = MediaPlayer.create(taneoyun2.this, R.raw.tebrik);
                    dogru.setVolume(10.0f, 3.0f);
                    dogru.start();
                    animation();


                    // MetniSeslendir("Aferin DoÃ„Å¸ru cevap");
                    // MetniSeslendir(mAnswer);



                } else {
                    //Toast.makeText(taneoyun2.this, "wrong", Toast.LENGTH_SHORT).show();
                    yanlis = MediaPlayer.create(taneoyun2.this, R.raw.yanlis);
                    yanlis.setVolume(10.0f, 3.0f);
                    yanlis.start();

                    mButtonChoice2.setVisibility(mButtonChoice2.INVISIBLE);
                    // updateQuestion();
                }

            }
        });

        //End of Button Listener for Button2


        //Start of Button Listener for Button3
        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for Button goes in here

                try {
                    buyut.clearAnimation();
                } catch(Exception e) {}

                if (mButtonChoice3.getText() == mAnswer) {


                    //This line of code is optiona
                    //Toast.makeText(taneoyun2.this, "correct", Toast.LENGTH_SHORT).show();

                    dogru = MediaPlayer.create(taneoyun2.this, R.raw.tebrik);
                    dogru.setVolume(10.0f, 3.0f);
                    dogru.start();
                    animation();


                    // MetniSeslendir("Aferin DoÃ„Å¸ru cevap");
                    // MetniSeslendir(mAnswer);


                } else {
                    //  Toast.makeText(taneoyun2.this, "wrong", Toast.LENGTH_SHORT).show();
                    yanlis = MediaPlayer.create(taneoyun2.this, R.raw.yanlis);
                    yanlis.setVolume(10.0f, 3.0f);
                    yanlis.start();
                    mButtonChoice3.setVisibility(mButtonChoice3.INVISIBLE);

                    //updateQuestion();
                }

            }
        });

        //End of Button Listener for Button3


    }

    private void updateQuestion() {
        handler.removeCallbacks(runnable);

        if (sira == 11) {
            finish();
        }
        mQuestionView.setImageResource(mQuestionLibrary.getQuestion(mQuestionNumber));
        mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
        mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
        mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));

        mQuestionView.setVisibility(mQuestionView.VISIBLE);
        mButtonChoice1.setVisibility(mButtonChoice1.VISIBLE);
        mButtonChoice2.setVisibility(mButtonChoice2.VISIBLE);
        mButtonChoice3.setVisibility(mButtonChoice3.VISIBLE);

        mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);

       /* try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // this part is executed when an exception (in this example InterruptedException) occurs
        }*/
        MetniSeslendir(mQuestionLibrary.getMetin(sira));
        TextView textView = (TextView) findViewById(R.id.textt);
        textView.setText(mQuestionLibrary.getMetin(sira)+"?");
        mQuestionNumber++;
        sira++;
        handler.postDelayed(runnable, 5000);

    }


    //update question kÄ±smÄ±nda olcak


    private void yardim() {

        ScaleAnimation anim = new ScaleAnimation(1, 1.2f, 1, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(1);
        anim.setDuration(500);
        if (mButtonChoice1.getText() == mAnswer) {
            buyut = (Button) findViewById(R.id.choice1);
        } else if (mButtonChoice2.getText() == mAnswer) {
            buyut = (Button) findViewById(R.id.choice2);
        } else {
            buyut = (Button) findViewById(R.id.choice3);
        }
        buyut.startAnimation(anim);

    }

   /* private void animation(){

        ImageView zoom = (ImageView) findViewById(R.id.imageTebrik);
        Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        zoom.startAnimation(zoomAnimation);
        zoomAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mQuestionView.setVisibility(mQuestionView.INVISIBLE);
                mButtonChoice1.setVisibility(mButtonChoice1.INVISIBLE);
                mButtonChoice2.setVisibility(mButtonChoice2.INVISIBLE);
                mButtonChoice3.setVisibility(mButtonChoice3.INVISIBLE);
                handler.removeCallbacks(runnable);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                updateQuestion();
                //handler.removeCallbacks(runnable);
            }

        });


    }

  */  private void animation() {


        TranslateAnimation animate;
        ImageView balloonView = (ImageView) findViewById(R.id.imageTebrik);
        animate = new TranslateAnimation(0, 0, 0, -balloonView.getHeight());


        animate.setDuration(4000);
        animate.setRepeatCount(0);
        balloonView.startAnimation(animate);
        animate.setFillAfter(true);

        animate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
              /*  mQuestionView.setVisibility(mQuestionView.INVISIBLE);
                mButtonChoice1.setVisibility(mButtonChoice1.INVISIBLE);
                mButtonChoice2.setVisibility(mButtonChoice2.INVISIBLE);
                mButtonChoice3.setVisibility(mButtonChoice3.INVISIBLE);*/
                handler.removeCallbacks(runnable);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                updateQuestion();
                //handler.removeCallbacks(runnable);
            }

        });




      }
    }