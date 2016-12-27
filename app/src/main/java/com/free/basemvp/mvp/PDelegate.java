package com.free.basemvp.mvp;



public interface PDelegate {

    void resume();
    void pause();
    void destory();

    void toastShort(String msg);
    void toastLong(String msg);

}
