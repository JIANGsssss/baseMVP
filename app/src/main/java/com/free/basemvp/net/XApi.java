package com.free.basemvp.net;

import com.free.basemvp.kit.Kits;

import java.util.concurrent.TimeUnit;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class XApi {
    private static NetProvider provider = null;
    private Retrofit retrofit = null;
    private OkHttpClient client = null;

    public static final long connectTimeoutMills = 10 * 1000l;
    public static final long readTimeoutMills = 10 * 1000l;

    private static XApi instance;

    private XApi() {

    }

    public static XApi getInstance() {
        if (instance == null) {
            synchronized (XApi.class) {
                if (instance == null) {
                    instance = new XApi();
                }
            }
        }
        return instance;
    }


    public static <S> S get(Class<S> service) {
        return getInstance().getRetrofit(true).create(service);
    }

    public static void registerProvider(NetProvider provider) {
        XApi.provider = provider;
    }


    public Retrofit getRetrofit(boolean useRx) {
        checkProvider();

        if (retrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(provider.configBaseUrl())
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create());
            if (useRx) {
                builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
            }
            retrofit = builder.build();
        }
        return retrofit;
    }

    public OkHttpClient getClient() {
        checkProvider();

        if (client == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            builder.connectTimeout(provider.configConnectTimeoutMills() != 0
                    ? provider.configConnectTimeoutMills()
                    : connectTimeoutMills, TimeUnit.MILLISECONDS);
            builder.readTimeout(provider.configReadTimeoutMills() != 0
                    ? provider.configReadTimeoutMills() : readTimeoutMills, TimeUnit.MILLISECONDS);

            CookieJar cookieJar = provider.configCookie();
            if (cookieJar != null) {
                builder.cookieJar(cookieJar);
            }
            provider.configHttps(builder);

            RequestHandler handler = provider.configHandler();
            if (handler != null) {
                builder.addInterceptor(new XInterceptor(handler));
            }

            Interceptor[] interceptors = provider.configInterceptors();
            if (!Kits.Empty.check(interceptors)) {
                for (Interceptor interceptor : interceptors) {
                    builder.addInterceptor(interceptor);
                }
            }

            if (provider.configLogEnable()) {
                HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
                logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(logInterceptor);
            }

            client = builder.build();
        }
        return client;
    }


    private static void checkProvider() {
        if (provider == null
                || Kits.Empty.check(provider.configBaseUrl())) {
            throw new IllegalStateException("must register provider first");
        }
    }

    public static NetProvider getProvider() {
        return provider;
    }


    public static Observable<IModel> transform(Observable<IModel> observable) {
        return observable.flatMap(new Func1<IModel, Observable<IModel>>() {
            @Override
            public Observable<IModel> call(IModel model) {
                if (model == null || model.isNull()) {
                    return Observable.error(new NetError(null, NetError.NoDataError));
                } else if (model.isAuthError()) {
                    return Observable.error(new NetError(null, NetError.AuthError));
                } else if (model.isBizError()) {
                    return Observable.error(new NetError(null, NetError.BusinessError));
                } else {
                    return Observable.just(model);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
