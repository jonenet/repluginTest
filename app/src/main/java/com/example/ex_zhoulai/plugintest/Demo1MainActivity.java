package com.example.ex_zhoulai.plugintest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;
import com.qihoo360.replugin.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Demo1MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.tv_start_plugin_start_inner_1, R.id.tv_start_plugin_inner_my, R.id.tv_start_plugin_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_start_plugin_start_inner_1:
                //可以打开插件，说明主件是没有问题的
                RePlugin.startActivity(this, RePlugin.createIntent("com.qihoo360.replugin.sample.demo1", "com.qihoo360.replugin.sample.demo1.MainActivity"));
                break;
            case R.id.tv_start_plugin_action:
                //RePlugin打开插件组件除了添加action还需要添加别名
                Intent intentAction = new Intent();
                intentAction.setAction("com.example.pluginme.action_start");
//                intentAction.addCategory(Intent.CATEGORY_DEFAULT);
                RePlugin.startActivity(this, intentAction, "pluginme", null);
//                RePlugin.startActivity(this, intentAction);
                break;
            case R.id.tv_start_plugin_inner_my:
                //可以跳转，说明插件是OK的
//                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        simulateInstallExternalPlugin();
//                    }
//                }, 1000);

                //可以传递数据，内置插件如果要升级，可以通过修改版本号升级，已安装用户可使用 install 从外部安装升级
//                RePlugin.install("file:///android_asset/pluginme.jar");
                Intent intent = new Intent();
                //        plugintest
                intent.setComponent(new ComponentName("pluginme", "com.example.pluginme.ResultActivity"));

                int REQUEST_CODE_DEMO1 = 10;
//                RePlugin.startActivity(this, intent);
                RePlugin.startActivityForResult(this, intent, REQUEST_CODE_DEMO1, null);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 100 && resultCode == RESULT_OK) {
        if (null != data) {
            String result = data.getStringExtra("data");
            Toast.makeText(Demo1MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
//        }
    }

    /**
     * 模拟安装或升级（覆盖安装）外置插件
     * 注意：为方便演示，外置插件临时放置到Host的assets/external目录下，具体说明见README</p>
     */
    private void simulateInstallExternalPlugin() {
        String demo3Apk = "pluginme.apk";
        String demo3apkPath = "external" + File.separator + demo3Apk;

        // 文件是否已经存在？直接删除重来
        String pluginFilePath = getFilesDir().getAbsolutePath() + File.separator + demo3Apk;
        File pluginFile = new File(pluginFilePath);
        if (pluginFile.exists()) {
            FileUtils.deleteQuietly(pluginFile);
        }

        // 开始复制
        copyAssetsFileToAppFiles(demo3apkPath, demo3Apk);
        PluginInfo info = null;
//        if (pluginFile.exists()) {
        RePlugin.uninstall("com.example.pluginme");
        info = RePlugin.install(pluginFilePath);
        if (info != null) {
            Intent intent = RePlugin.createIntent(info.getName(), "com.example.pluginme.MainActivity");
            intent.putExtra("host_str1", "cccc");
            intent.putExtra("host_str2", "dddd");
            intent.putExtra("host_str3", "ffff");
            RePlugin.startActivity(Demo1MainActivity.this, intent);
        } else {
            Toast.makeText(Demo1MainActivity.this, "install external plugin failed", Toast.LENGTH_SHORT).show();
        }
//        }
    }

    /**
     * 从assets目录中复制某文件内容
     *
     * @param assetFileName assets目录下的Apk源文件路径
     * @param newFileName   复制到/data/data/package_name/files/目录下文件名
     */
    private void copyAssetsFileToAppFiles(String assetFileName, String newFileName) {
        InputStream is = null;
        FileOutputStream fos = null;
        int buffsize = 1024;

        try {
            is = this.getAssets().open(assetFileName);
            fos = this.openFileOutput(newFileName, Context.MODE_PRIVATE);
            int byteCount = 0;
            byte[] buffer = new byte[buffsize];
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
