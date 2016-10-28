package br.com.simplepass.curriculo.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.LinearLayout;

import br.com.simplepass.curriculo.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalInfoActivity extends AppCompatActivity {

    @BindView(R.id.container_view) LinearLayout mInfoContainer;

    private static final int ANIMATION_DURATION = 400;
    private static final int ANIMATION_DELAY = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Explode());
            getWindow().getEnterTransition().setStartDelay(200);
            getWindow().setExitTransition(new Explode());
        }

        /*for (int i = 0; i < mInfoContainer.getChildCount(); i++) {
            View v = mInfoContainer.getChildAt(i);
            ViewPropertyAnimator animator = v.animate()
                    .scaleX(1).scaleY(1)
                    .setDuration(ANIMATION_DURATION);

            animator.setStartDelay(ANIMATION_DELAY);
            animator.start();
        }*/
    }

}
