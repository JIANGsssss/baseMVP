package com.free.basemvp.base;


public abstract class ListItemCallback<T> {

    public void onItemClick(int position, T model, int tag) {}

    public void onItemLongClick(int position, T model, int tag) {}
}
