package com.chen.robotremote.MVPs.Main;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.chen.robotremote.R;
import com.chen.robotremote.Utils.MyUtils;

import net.steamcrafted.materialiconlib.MaterialMenuInflater;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    private Toolbar mToolbar;
    private MainFragment mMainFragment;
    private MainPresenter mMainPresenter;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mToolbar.setOnMenuItemClickListener(this);

        mMainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (mMainFragment == null) {
            // Create the fragment
            mMainFragment = new MainFragment();
            MyUtils.addFragmentToActivity(
                    getSupportFragmentManager(), mMainFragment, R.id.contentFrame);
        }

        // Create the presenter
        mMainPresenter = new MainPresenter(mMainFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MaterialMenuInflater
                .with(this)
                .setDefaultColor(Color.WHITE)
                .inflate(R.menu.main_toolbar_menu, menu);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.setup_ip:
                mMainFragment.show_setup_ip_window();
                break;
        }
        return true;
    }
}
