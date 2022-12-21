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

        ImageView mfondo = findViewById(R.id.fondo);
        Glide.with(this)
                .load(R.drawable.vitals)
                .transition(DrawableTransitionOptions.withCrossFade(100))
                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.purple_200)))
                .into(mfondo);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.fadein);
        mfondo.startAnimation(myanim);

        Animation myanim2 = AnimationUtils.loadAnimation(this, R.anim.desaparecer);
        texto.startAnimation(myanim2);



    }
    private void openApp() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity
                        .this, MainActivity.class);
                startActivity(intent);
            }
        }, 5000);


    }
}