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

        void set_webview_js(boolean enable);

        void show_message(String text);

    }

    interface Presenter extends BasePresenter {


        void turnleft();

        void turnright();

        void movefront();

        void moveback();

        void moveleft();

        void moveright();

//        void setup_ip(String url);
    }
}
