package com.free.basemvp.mvp;

import android.view.View;



public interface VDelegate {

    void visible(boolean flag, View view);
    void gone(boolean flag, View view);
    void inVisible(View view);

    void toastShort(String msg);
    void toastLong(String msg);

}
