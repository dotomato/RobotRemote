package com.chen.robotremote.Server;

import com.chen.robotremote.Server.ServerDataType.BaseResult;
import com.chen.robotremote.Server.ServerDataType.THResult;

import org.jetbrains.annotations.NotNull;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by chen on 17-6-24.
 * Copyright *
 */

public class Server {

    public static String server_host="http://127.0.0.1:80";
    private static MyserverInterface mServer=null;


    public interface MyserverInterface {

        @GET("/movefront")
        Observable<BaseResult> movefront();

        @GET("/moveback")
        Observable<BaseResult> moveback();

        @GET("/moveleft")
        Observable<BaseResult> moveleft();

        @GET("/moveright")
        Observable<BaseResult> moveright();

        @GET("/turnleft")
        Observable<BaseResult> turnleft();

        @GET("/turnright")
        Observable<BaseResult> turnright();

        @GET("/sing")
        Observable<BaseResult> sing();

        @GET("/dance")
        Observable<BaseResult> dance();

        @GET("/lighton")
        Observable<BaseResult> lighton();

        @GET("/lightoff")
        Observable<BaseResult> lightoff();

        @GET("/niconiconi")
        Observable<BaseResult> niconiconi();

        @GET("/getth")
        Observable<THResult> getth();

        @GET("/speech")
        Observable<BaseResult> speech(@Query("content") String content);

//        @Headers({"Content-Type: application/json", "Accept: application/json"})
//        @POST("/speech")
//        Observable<BaseResult> speech(@Body PointData2 var);

    }



    public static void setSeverHost(@NotNull String host){
        server_host = host;
        mServer = null;
    }

    public static MyserverInterface getApi(){
        if (mServer==null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(server_host)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            mServer = retrofit.create(MyserverInterface.class);
        }
        return mServer;
    }

}
