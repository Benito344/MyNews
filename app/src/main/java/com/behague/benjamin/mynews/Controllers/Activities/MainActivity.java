package com.behague.benjamin.mynews.Controllers.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.behague.benjamin.mynews.Adapter.PageAdapter;
import com.behague.benjamin.mynews.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureToolbar();
        this.configureViewPagerAndTabs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    private void configureToolbar(){
        //Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        //Sets the Toolbar
        setSupportActionBar(toolbar);
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
}
