package tansu.gunes;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import java.util.Locale;

public class MeyveTamami extends AppCompatActivity {
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;
    private TextToSpeech tts;
    private boolean flag;

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
    protected void onDestroy() {
        if(tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meyve_tamami);
        init();
    }

    public void oku(View view) {
        switch (view.getId()) {
            case R.id.seftali:
                final View thumb1View = findViewById(R.id.seftali);
                zoomImageFromThumb(thumb1View, R.drawable.seftali);
                MetniSeslendir("Şeftağli");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.cilek:
                final View thumb2View = findViewById(R.id.cilek);
                zoomImageFromThumb(thumb2View, R.drawable.cilek);
                MetniSeslendir("Çilek");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.muz:
                final View thumb3View = findViewById(R.id.muz);
                zoomImageFromThumb(thumb3View, R.drawable.muz);
                MetniSeslendir("Muz");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.nar:
                final View thumb4View = findViewById(R.id.nar);
                zoomImageFromThumb(thumb4View, R.drawable.nar);
                MetniSeslendir("Nar");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.karpuz:
                final View thumb5View = findViewById(R.id.karpuz);
                zoomImageFromThumb(thumb5View, R.drawable.karpuz);
                MetniSeslendir("Karpuz");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.erik:
                final View thumb6View = findViewById(R.id.erik);
                zoomImageFromThumb(thumb6View, R.drawable.erik);
                MetniSeslendir("Erik");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.elma:
                final View thumb7View = findViewById(R.id.elma);
                zoomImageFromThumb(thumb7View, R.drawable.elma);
                MetniSeslendir("Elma");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.armut:
                final View thumb8View = findViewById(R.id.armut);
                zoomImageFromThumb(thumb8View, R.drawable.armut);
                MetniSeslendir("Armut");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.kayisi:
                final View thumb9View = findViewById(R.id.kayisi);
                zoomImageFromThumb(thumb9View, R.drawable.kayisi);
                MetniSeslendir("Kayisi");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.portakal:
                final View thumb10View = findViewById(R.id.portakal);
                zoomImageFromThumb(thumb10View, R.drawable.portakal);
                MetniSeslendir("Portakal");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
        }
    }

    private void zoomImageFromThumb(final View thumbView, int imageResId) {
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }
        final ImageView expandedImageView = (ImageView) findViewById(R.id.expanded_image);
        expandedImageView.setImageResource(imageResId);

        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.activity_meyve_tamami).getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);


        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {

            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {

            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }


        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);


        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);


        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left,
                        finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top,
                        finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;


        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }


                AnimatorSet set = new AnimatorSet();
                set
                        .play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView, View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView, View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }
}
