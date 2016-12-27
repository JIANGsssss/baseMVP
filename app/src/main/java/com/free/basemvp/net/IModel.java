package com.free.basemvp.net;



public interface IModel {
    boolean isNull();       //空数据

    boolean isAuthError();  //验证错误

    boolean isBizError();   //义务错误
}
