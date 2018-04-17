package com.example.ex_zhoulai.plugintest.global;

import android.content.Context;

import com.example.ex_zhoulai.plugintest.BuildConfig;
import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.RePluginApplication;
import com.qihoo360.replugin.RePluginCallbacks;
import com.qihoo360.replugin.RePluginConfig;

/**
 * Created by ex-zhoulai on 2018/4/17.
 *
 */

public class MyApplication extends RePluginApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        RePlugin.enableDebugger(base, BuildConfig.DEBUG);
        //如果没有继承RePluginApplication定义自定义插件需要如下操作
        //RePlugin.App.attachBaseContext(base, c);
    }

    @Override
    protected RePluginConfig createConfig() {
        return super.createConfig();
    }

    @Override
    protected RePluginCallbacks createCallbacks() {
        return super.createCallbacks();
    }


}
