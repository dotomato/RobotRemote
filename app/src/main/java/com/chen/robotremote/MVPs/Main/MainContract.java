package com.chen.robotremote.MVPs.Main;

import com.chen.robotremote.BasePresenter;
import com.chen.robotremote.BaseView;

/**
 * Created by chen on 17-6-24.
 * Copyright *
 */

public class MainContract {
    interface View extends BaseView<Presenter> {


        void set_video_url(String url);

        void show_setup_ip_window();

    }

    interface Presenter extends BasePresenter {


        void turnleft_button();

        void turnright_button();

        void up_button();

        void down_button();

//        void setup_ip(String url);
    }
}
