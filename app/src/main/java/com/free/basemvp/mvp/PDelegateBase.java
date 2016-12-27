package com.free.basemvp.mvp;

import android.content.Context;
import android.widget.Toast;



public class PDelegateBase implements PDelegate {

    private Context context;

    private PDelegateBase(Context context) {
        this.context = context;
    }

    public static PDelegateBase create(Context context) {
        return new PDelegateBase(context);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destory() {

    }

    @Override
    public void toastShort(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastLong(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
