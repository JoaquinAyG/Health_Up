package study.project.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import study.project.R;

public class SplashActivity extends AppCompatActivity {
    TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        openApp();
        texto = findViewById(R.id.health);

        ImageView vital = findViewById(R.id.vital);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.blink);
        vital.startAnimation(myanim);

        //TODO()

        //    Glide for loading girls
        ImageView mSea = findViewById(R.id.fondo);

        Glide.with(this)
                .load("https://images.unsplash.com/photo-1627842467534-7a5d24b6fd89?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80")
//                .load(R.drawable.girl)
                .transition(DrawableTransitionOptions.withCrossFade(100))
                .centerCrop()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.bluex)))
//                .circleCrop()
                .into(mSea);

        Animation myanim2 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        texto.startAnimation(myanim2);



    }
    private void openApp() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity
                        .this, OnBoardingActivity.class);
                startActivity(intent);
            }
        }, 5000);


    }
}