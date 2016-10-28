package br.com.simplepass.curriculo.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Observable;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import br.com.simplepass.curriculo.R;
import br.com.simplepass.curriculo.ui.CircularProgressButton2;
import br.com.simplepass.curriculo.utils.Constants;


public class LoginActivity extends AppCompatActivity {

    private static final int LOADING_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final SharedPreferences preferences = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);

        if(preferences.getBoolean(Constants.PREFS_KEYS.LOGED_ID, false)){
            startActivity(new Intent(this, MainActivity.class));
        }

        final CircularProgressButton2 btnEnter = (CircularProgressButton2) findViewById(R.id.btn_login);

        btnEnter.setOnClickListener((View v) -> {
            btnEnter.startProgress();

            Handler handler = new Handler();

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(Constants.PREFS_KEYS.LOGED_ID, true);
            editor.apply();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int x = btnEnter.getLeft() + btnEnter.getWidth()/2;
                int y = btnEnter.getTop() + btnEnter.getHeight()/2;

                intent.putExtra(MainActivity.EXTRA_CIRCULAR_REVEAL_X, x);
                intent.putExtra(MainActivity.EXTRA_CIRCULAR_REVEAL_Y, y);
            } else {
                startActivity(intent);
            }

            Runnable runnable = () -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                            LoginActivity.this);
                    startActivity(intent, activityOptions.toBundle());
                } else{
                    startActivity(intent);
                }
                btnEnter.stopAnimation();
            };

            handler.postDelayed(runnable, LOADING_TIME);
        });


    }

}
