package com.chen.robotremote.Server;

import com.chen.robotremote.Server.ServerDataType.BaseResult;

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

    public static String server_host="http://192.168.1.101:5000";
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

//        @Headers({"Content-Type: application/json", "Accept: application/json"})
//        @POST(baseurl + "/newpoint")
//        Observable<BaseResult> newPoint(@Body PointData2 var);

    }



    public static void setSeverHost(String host){
        server_host = host;
        mServer = null;
    }

    public static MyserverInterface getApi(){
        if (server_host==null) {
//            Exception e = new Exception();
//            throw e;
            mServer = null;
            return null;
        }
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
