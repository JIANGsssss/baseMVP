package com.free.basemvp.mvp;



public interface IPresent<V> {
    void attachV(V view);

    void detachV();
}
