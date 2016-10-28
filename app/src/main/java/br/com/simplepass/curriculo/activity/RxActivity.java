package br.com.simplepass.curriculo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;

import br.com.simplepass.curriculo.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RxActivity extends AppCompatActivity {
    @BindView(R.id.sum_a) EditText sumA;
    @BindView(R.id.sum_b) EditText sumB;
    @BindView(R.id.result) TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        RxTextView.textChangeEvents(sumA).subscribe(e -> {
            result.setText(sumA.getText().toString() + sumB.getText().toString());
        });
        RxTextView.textChangeEvents(sumB).subscribe(e -> {
            result.setText(sumA.getText().toString() + sumB.getText().toString());
        });


    }

}
