package br.com.simplepass.curriculo.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import br.com.simplepass.curriculo.R;
import br.com.simplepass.curriculo.adapters.AcademicsAdapter;
import br.com.simplepass.curriculo.domain.AcademicItem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AcademicActivity extends AppCompatActivity {
    @BindView(R.id.academic_items_recycler_view) RecyclerView academicRecyclerView;
    @BindView(R.id.academic_items_container_card_view) CardView academicsContainer;
    @BindView(R.id.academic_appbarlayout) AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Explode());
            getWindow().getEnterTransition().setStartDelay(150);
            getWindow().setExitTransition(new Explode());
        }

        academicRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        academicRecyclerView.setAdapter(new AcademicsAdapter(getAcademicData()));
    }


    private List<AcademicItem> getAcademicData(){
        List<AcademicItem> academicItemList = new ArrayList<>();

        /*academicItemList.add(new AcademicItem(getString(R.string.ufmg_name), "DEZ/2014", ContextCompat.getDrawable(this, R.drawable.ic_ufmg2)));
        academicItemList.add(new AcademicItem(getString(R.string.marista_name), "DEZ/2007", ContextCompat.getDrawable(this, R.drawable.ic_marista)));
        academicItemList.add(new AcademicItem(getString(R.string.cna_name), "JUL/2007", ContextCompat.getDrawable(this, R.drawable.ic_cna)));
        academicItemList.add(new AcademicItem(getString(R.string.cenex_name), "DEZ/2014", ContextCompat.getDrawable(this, R.drawable.ic_cenex)));*/

        academicItemList.add(new AcademicItem(getString(R.string.ufmg_name), "DEZ/2014"));
        academicItemList.add(new AcademicItem(getString(R.string.marista_name), "DEZ/2007"));
        academicItemList.add(new AcademicItem(getString(R.string.cna_name), "JUL/2007"));
        academicItemList.add(new AcademicItem(getString(R.string.cenex_name), "DEZ/2014"));

        return academicItemList;
    }

}
