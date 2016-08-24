package edu.zx.news.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class HttpVolley {
    Context mContext;

    public static <T> void addRequest(Context context, Request<T> request) {
        RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(request);
    }
}
