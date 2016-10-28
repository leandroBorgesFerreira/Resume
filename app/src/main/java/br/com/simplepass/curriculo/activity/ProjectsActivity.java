package br.com.simplepass.curriculo.activity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;

import java.util.ArrayList;
import java.util.List;

import br.com.simplepass.curriculo.R;
import br.com.simplepass.curriculo.adapters.ProjectsAdapter;
import br.com.simplepass.curriculo.domain.Project;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectsActivity extends AppCompatActivity {

    @BindView(R.id.projects_recycler_view) RecyclerView mProjectsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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


        ButterKnife.bind(this);

        mProjectsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProjectsRecyclerView.setAdapter(new ProjectsAdapter(this, getProjects()));

    }

    private List<Project> getProjects(){
        List<Project> projectList = new ArrayList<>();

        projectList.add(new Project(getString(R.string.simplepass_name),
                getString(R.string.simplepass_description),
                getString(R.string.simplepass_date),
                Uri.parse("market://details?id=com.simplepass.usuario.simplepass&hl=pt_BR"),
                ContextCompat.getDrawable(this, R.mipmap.ic_simplepass)));
        projectList.add(new Project(getString(R.string.simplefuncionario_name),
                getString(R.string.simplefuncionario_description),
                getString(R.string.simplefuncionario_date),
                Uri.parse("market://details?id=com.simplepass.usuario.simplefuncionario2&hl=pt_BR"),
                ContextCompat.getDrawable(this, R.mipmap.ic_simple_funcionario)));
        projectList.add(new Project(getString(R.string.simplecaixa_name),
                getString(R.string.simplecaixa_description),
                getString(R.string.simplecaixa_date),
                Uri.parse("market://details?id=com.simplepass.usuario.simpleadm&hl=pt_BR"),
                ContextCompat.getDrawable(this, R.mipmap.ic_adm)));
        projectList.add(new Project(getString(R.string.cadevan_name),
                getString(R.string.cadevan_description),
                getString(R.string.cadevan_date),
                Uri.parse("market://details?id=br.com.simplepass.cadevan&hl=pt_BR"),
                ContextCompat.getDrawable(this, R.mipmap.ic_cadevan)));
        projectList.add(new Project(getString(R.string.cademotorista_name),
                getString(R.string.cademotorista_desciption),
                getString(R.string.cademotorista_date),
                Uri.parse("market://details?id=br.com.simplepass.cadevanmotorista&hl=pt_BR"),
                ContextCompat.getDrawable(this, R.mipmap.ic_cadevanmotorista)));
        projectList.add(new Project(getString(R.string.cadevan_swift_name),
                getString(R.string.cadevan_swift_description),
                getString(R.string.cadevan_swift_date),
                Uri.parse("market://details?id=br.com.simplepass.cadevan&hl=pt_BR"),
                ContextCompat.getDrawable(this, R.mipmap.ic_cadevan)));
        projectList.add(new Project(getString(R.string.smartphone_detection_name),
                getString(R.string.smartphone_detection_description),
                getString(R.string.smartphone_detection_date),
                Uri.parse("https://youtu.be/hzu8AdGc0M4"),
                null));
        projectList.add(new Project(getString(R.string.blueled_name),
                getString(R.string.blueled_description),
                getString(R.string.blueled_date),
                Uri.parse("https://youtu.be/8cE_Qfp5lMQ"),
                null));

        return projectList;
    }

}
