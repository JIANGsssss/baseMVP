package com.free.basemvp.mvp;

import android.content.Context;
import android.os.Bundle;


public interface IPresent<V>{

    boolean useEventBus();

    V newV();

    Context getRootContext();

    void initData(Bundle savedInstanceState);

}
