package com.free.basemvp.net;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;



public interface NetProvider {
    String configBaseUrl();

    Interceptor[] configInterceptors();

    void configHttps(OkHttpClient.Builder builder);

    CookieJar configCookie();

    RequestHandler configHandler();

    long configConnectTimeoutMills();

    long configReadTimeoutMills();

    boolean configLogEnable();

    boolean handleError(NetError error);
}
