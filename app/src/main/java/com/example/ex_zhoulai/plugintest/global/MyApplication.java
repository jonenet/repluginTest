package com.example.ex_zhoulai.plugintest.global;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.ex_zhoulai.plugintest.BuildConfig;
import com.qihoo360.replugin.ContextInjector;
import com.qihoo360.replugin.PluginDexClassLoader;
import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.RePluginApplication;
import com.qihoo360.replugin.RePluginCallbacks;
import com.qihoo360.replugin.RePluginClassLoader;
import com.qihoo360.replugin.RePluginConfig;
import com.qihoo360.replugin.model.PluginInfo;

import java.io.InputStream;

/**
 * Created by ex-zhoulai on 2018/4/17.
 *
 */

public class MyApplication extends RePluginApplication {

    private static final String TAG = "MyApplication";

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
        return new RePluginCallbacks(getApplicationContext()){
            @Override
            public RePluginClassLoader createClassLoader(ClassLoader parent, ClassLoader original) {
                Log.e(TAG, "createClassLoader: ");
                return super.createClassLoader(parent, original);
            }

            @Override
            public PluginDexClassLoader createPluginClassLoader(PluginInfo pi, String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
                Log.e(TAG, "createPluginClassLoader: ");
                return super.createPluginClassLoader(pi, dexPath, optimizedDirectory, librarySearchPath, parent);
            }

            @Override
            public boolean onPluginNotExistsForActivity(Context context, String plugin, Intent intent, int process) {
                Log.e(TAG, "onPluginNotExistsForActivity: ");
                return super.onPluginNotExistsForActivity(context, plugin, intent, process);
            }

            @Override
            public boolean onLoadLargePluginForActivity(Context context, String plugin, Intent intent, int process) {
                Log.e(TAG, "onLoadLargePluginForActivity: ");
                return super.onLoadLargePluginForActivity(context, plugin, intent, process);
            }

            @Override
            public SharedPreferences getSharedPreferences(Context context, String name, int mode) {
                Log.e(TAG, "getSharedPreferences: ");
                return super.getSharedPreferences(context, name, mode);
            }

            @Override
            public InputStream openLatestFile(Context context, String filename) {
                Log.e(TAG, "openLatestFile: ");
                return super.openLatestFile(context, filename);
            }

            @Override
            public ContextInjector createContextInjector() {
                Log.e(TAG, "createContextInjector: ");
                return super.createContextInjector();
            }

            @Override
            public boolean isPluginBlocked(PluginInfo pluginInfo) {
                Log.e(TAG, "isPluginBlocked: ");
                return super.isPluginBlocked(pluginInfo);
            }

            @Override
            public void initPnPluginOverride() {
                Log.e(TAG, "initPnPluginOverride: ");
                super.initPnPluginOverride();
            }
        };
    }


}
