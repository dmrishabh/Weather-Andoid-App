package com.example.weather;

import android.content.Context;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
// haven't used this class in future build i'll do so ..
public class Singleton {


    private static Singleton mInstance;

    private RequestQueue mRequestQueue;


    public Singleton(Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());

    }

    public static synchronized Singleton getInstance(View.OnClickListener context){

        if (mInstance == null){
            mInstance = new Singleton((Context) context);
        }
        return mInstance;
    }

    public RequestQueue getmRequestQueue(){
        return mRequestQueue;
    }

}


