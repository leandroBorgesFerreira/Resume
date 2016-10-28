package br.com.simplepass.curriculo.activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.simplepass.curriculo.R;
import br.com.simplepass.curriculo.adapters.ResumeItemsAdapter;
import br.com.simplepass.curriculo.domain.ResumeItem;
import br.com.simplepass.curriculo.drawer.DrawerItemClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;

    @BindView(R.id.root_layout) FrameLayout rootLayout;

    public static final String EXTRA_CIRCULAR_REVEAL_X = "br.com.simplepass.curriculo.EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "br.com.simplepass.curriculo.EXTRA_CIRCULAR_REVEAL_Y";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(null);
        }

        Intent intent = getIntent();

        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {
            rootLayout.setVisibility(View.INVISIBLE);

            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        circularRevealActivity(intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0),
                                intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0));
                        rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        } else {
            rootLayout.setVisibility(View.VISIBLE);
        }

        drawerInit();

        RecyclerView resumeListRecyclerView = (RecyclerView) findViewById(R.id.abilities_recycler_view);
        resumeListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        resumeListRecyclerView.setAdapter(new ResumeItemsAdapter(this, getResumeItems()));
    }

    private void circularRevealActivity(int x, int y) {
        float finalRadius = Math.max(rootLayout.getWidth(), rootLayout.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0, finalRadius);
        circularReveal.setDuration(1200);

        // make the view visible and start the animation
        rootLayout.setVisibility(View.VISIBLE);

        circularReveal.start();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void drawerInit(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(
                new DrawerItemClickListener(this, mDrawerLayout));

        //View headerView = navigationView.getHeaderView(0);
    }

    private List<ResumeItem> getResumeItems(){
        List<ResumeItem> resumeItemList = new ArrayList<>();
        resumeItemList.add(new ResumeItem(getString(R.string.personal_info),
                getString(R.string.personal_info_comment),
                new Intent(this, PersonalInfoActivity.class),
                ResourcesCompat.getDrawable(getResources(), R.drawable.paper_boat, null)));

        resumeItemList.add(new ResumeItem(getString(R.string.made_projects),
                getString(R.string.made_projects_comment),
                new Intent(this, ProjectsActivity.class),
                ResourcesCompat.getDrawable(getResources(), R.drawable.project, null)));

        resumeItemList.add(new ResumeItem(getString(R.string.schoolarship),
                getString(R.string.schoolarship_coment),
                new Intent(this, AcademicActivity.class),
                ResourcesCompat.getDrawable(getResources(), R.drawable.study, null)));

        resumeItemList.add(new ResumeItem(getString(R.string.stack),
                getString(R.string.stack_comment),
                new Intent(this, ToolsActivity.class),
                ResourcesCompat.getDrawable(getResources(), R.drawable.tools, null)));

        return resumeItemList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawers();
        } else{
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
