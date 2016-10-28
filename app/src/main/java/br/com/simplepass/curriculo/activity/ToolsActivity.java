package br.com.simplepass.curriculo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.List;

import br.com.simplepass.curriculo.R;
import br.com.simplepass.curriculo.adapters.StackAdapter;
import br.com.simplepass.curriculo.domain.StackItem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ToolsActivity extends AppCompatActivity {
    @BindView(R.id.stack_recycler_view) RecyclerView mStackRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        ButterKnife.bind(this);

        mStackRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mStackRecyclerView.setAdapter(new StackAdapter(this, getData()));
    }

    private List<StackItem> getData(){
        List<StackItem> stackItemList = new ArrayList<>();

        stackItemList.add(new StackItem(getString(R.string.english), getString(R.string.english_description), 100));
        stackItemList.add(new StackItem(getString(R.string.spanish), getString(R.string.spanish_description), 80));
        stackItemList.add(new StackItem(getString(R.string.java), getString(R.string.java_description), 95));
        stackItemList.add(new StackItem(getString(R.string.swift), getString(R.string.swift_description), 70));
        stackItemList.add(new StackItem(getString(R.string.spring), getString(R.string.spring_description), 70));
        stackItemList.add(new StackItem(getString(R.string.swagger), getString(R.string.swagger_description), 90));
        stackItemList.add(new StackItem(getString(R.string.rest_clients), getString(R.string.rest_clients_description), 100));
        stackItemList.add(new StackItem(getString(R.string.butterknife), getString(R.string.butterknife_description), 100));
        stackItemList.add(new StackItem(getString(R.string.realm), getString(R.string.realm_description), 100));
        stackItemList.add(new StackItem(getString(R.string.testing), getString(R.string.testing_description), 80));
        stackItemList.add(new StackItem(getString(R.string.json_libs), getString(R.string.json_libs_description), 100));
        stackItemList.add(new StackItem(getString(R.string.push_notification), getString(R.string.push_notification_description), 100));
        stackItemList.add(new StackItem(getString(R.string.git), getString(R.string.git_description), 80));
        stackItemList.add(new StackItem(getString(R.string.html), getString(R.string.html_description), 60));
        stackItemList.add(new StackItem(getString(R.string.css), getString(R.string.css_description), 60));
        stackItemList.add(new StackItem(getString(R.string.php), getString(R.string.php_description), 60));

        return stackItemList;
    }

}
