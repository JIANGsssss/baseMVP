package com.free.basemvp.mvp;

import android.view.View;

import com.free.basemvp.kit.KnifeKit;

import butterknife.Unbinder;


public abstract class XView<P extends IPresent> implements IView<P> {

    private VDelegate vDelegate;
    private P present;
    private Unbinder unbinder;
    private View rootView;

    @Override
    public void bindUI(View rootView) {
        if (rootView == null) {
            unbinder = KnifeKit.bind(this, getP().getRootContext());
        } else {
            unbinder = KnifeKit.bind(this, rootView);
        }
        this.rootView = rootView;
    }

    protected VDelegate getvDelegate() {
        if (vDelegate == null
                && getP() != null
                && getP().getRootContext() != null) {
            vDelegate = VDelegateBase.create(getP().getRootContext());
        }
        return vDelegate;
    }


    @Override
    public void attachP(P present) {
        this.present = present;
    }

    @Override
    public void detach() {

    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    protected P getP() {
        return present;
    }

    @Override
    public View getRootView() {
        return rootView;
    }

}
