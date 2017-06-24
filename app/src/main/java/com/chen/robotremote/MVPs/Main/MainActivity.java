package com.chen.robotremote.MVPs.Main;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.chen.robotremote.PrefrenceManager;
import com.chen.robotremote.R;
import com.chen.robotremote.Server.Server;
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

        PrefrenceManager.loadPrefrence(this);
        Server.setSeverHost(PrefrenceManager.hostname);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("树莓派IP地址");
                final EditText editText = new EditText(this);
                editText.setText(PrefrenceManager.hostname);
                builder.setView(editText)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PrefrenceManager.hostname = editText.getText().toString();
                                PrefrenceManager.storerefrence(MainActivity.this);
                                Server.setSeverHost(PrefrenceManager.hostname);
                            }
                        })
                        .setNegativeButton("取消", null).show();
                break;

            case R.id.setup_video_url:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this).setTitle("设置视频页面");
                final EditText editText2 = new EditText(this);
                editText2.setText(PrefrenceManager.videourlname);
                builder2.setView(editText2)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PrefrenceManager.videourlname = editText2.getText().toString();
                                PrefrenceManager.storerefrence(MainActivity.this);
                                mMainFragment.set_video_url(PrefrenceManager.videourlname);
                            }
                        })
                        .setNegativeButton("取消", null).show();

            case R.id.setup_js:
                PrefrenceManager.enableJavascript = !PrefrenceManager.enableJavascript;
                PrefrenceManager.storerefrence(this);
                mMainFragment.set_webview_js(PrefrenceManager.enableJavascript);
        }
        return true;
    }
}
