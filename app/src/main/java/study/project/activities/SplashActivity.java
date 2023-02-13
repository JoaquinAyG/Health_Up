package study.project.activities;

import static study.project.utils.GeneralFunctionsKt.forceDarkMode;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Objects;

import study.project.R;

public class SplashActivity extends AppCompatActivity {
    TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        forceDarkMode();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        openApp();
        Objects.requireNonNull(getSupportActionBar()).hide();

        texto = findViewById(R.id.health);

        ImageView vital = findViewById(R.id.vital);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.blink);
        vital.startAnimation(myanim);

        Animation myanim2 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        texto.startAnimation(myanim2);
    }
    private void openApp() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashActivity
                    .this, OnBoardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            finish();

        }, 5000);


    }
}