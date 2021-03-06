package com.free.basemvp;


import com.free.basemvp.imageloader.ILoader;
import com.free.basemvp.router.Router;

public class XDroidConf {
    // #log
    public static final boolean LOG = true;
    public static final String LOG_TAG = "XDroid";

    // #cache
    public static final String CACHE_SP_NAME = "config";
    public static final String CACHE_DISK_DIR = "cache";

    // #router
    public static final int ROUTER_ANIM_ENTER = Router.RES_NONE;
    public static final int ROUTER_ANIM_EXIT = Router.RES_NONE;

    // #imageloader
    public static final int IL_LOADING_RES = ILoader.Options.RES_NONE;
    public static final int IL_ERROR_RES = ILoader.Options.RES_NONE;

    // # dev model
    public static final boolean DEV = true;
}
