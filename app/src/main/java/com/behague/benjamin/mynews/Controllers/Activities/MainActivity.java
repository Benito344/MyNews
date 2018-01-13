package com.behague.benjamin.mynews.Controllers.Activities;


import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.behague.benjamin.mynews.Adapter.PageAdapter;
import com.behague.benjamin.mynews.Modal.Article;
import com.behague.benjamin.mynews.Modal.ArticleAdaptaterMost;
import com.behague.benjamin.mynews.Modal.ArticleAdaptaterTop;
import com.behague.benjamin.mynews.Modal.mAsyncTask;
import com.behague.benjamin.mynews.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ViewPager viewPager;

    private final String TOP_URL = "https://api.nytimes.com/svc/topstories/v2/home.json?api-key=";
    private final String MOST_URL = "https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=";
    private final String APIKEY_TOP = "f61e15b5379341758307c696363f35f9";
    private final String APIKEY_MOST = "f61e15b5379341758307c696363f35f9";
    private final String TOP = "TOP";
    private final String MOST = "MOST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureToolbar();
        this.configureDrawerLayout();
        this.configureViewPagerAndTabs();
        this.configureNavigationView();

        viewPager = findViewById(R.id.activity_main_viewpager);
        String urlTop = TOP_URL + APIKEY_TOP;
        String urlMost = MOST_URL + APIKEY_MOST;
        new mAsyncTask(this).execute(urlTop,urlMost,TOP,MOST);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    private void configureToolbar(){
        //Get the toolbar view inside the activity layout
        toolbar = findViewById(R.id.toolbar);
        //Sets the Toolbar
        setSupportActionBar(toolbar);
    }

    private void configureDrawerLayout(){
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureViewPagerAndTabs(){
        //Get ViewPager from layout
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        //Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        // 1 - Get TabLayout from layout
        TabLayout tabs = findViewById(R.id.activity_main_tabs);
        // 2 - Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);
        // 3 - Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    private void configureNavigationView(){
        this.navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_search:
                launchSearchActivity();
                return true;

            case R.id.menu_notifs:
                launchNotifsActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int newItem ;
        int id = item.getItemId();

        switch (id){
            case R.id.activity_main_drawer_topstories:
                newItem = 0;
                viewPager.setCurrentItem(newItem);
                break;

            case R.id.activity_main_drawer_mostpopular:
                newItem = 1;
                viewPager.setCurrentItem(newItem);
                break;

            default:
                newItem = 0;
                viewPager.setCurrentItem(newItem);
                break;
        }

        viewPager.setCurrentItem(newItem);

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void launchSearchActivity(){
        Intent mSearchIntent = new Intent(MainActivity.this, SearchActivity.class);
        this.startActivity(mSearchIntent);
    }

    private void launchNotifsActivity(){
        Intent mNotifsIntent = new Intent(MainActivity.this, NotificationActivity.class);
        this.startActivity(mNotifsIntent);
    }

    public void getArticleList (ArrayList<Article> mListTop, ArrayList<Article> mListMost){
                ArticleAdaptaterTop mAdapatTop = new ArticleAdaptaterTop();
                mAdapatTop.update(mListTop);
                mAdapatTop.notifyDataSetChanged();
                if(mAdapatTop.getItemCount() > 0){
                    ArticleAdaptaterMost mAdaptMost = new ArticleAdaptaterMost();
                    mAdaptMost.update(mListMost);
                    mAdaptMost.notifyDataSetChanged();
                }

    }
}
