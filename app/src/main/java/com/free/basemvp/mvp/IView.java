package com.free.basemvp.mvp;

import android.view.View;



public interface IView<P extends IPresent> {

    void bindUI(View rootView);

    void bindEvent();

    int getLayoutId();

    int getOptionsMenuId();

    View getRootView();

    void attachP(P present);

    void detach();

}
