package com.example.pluginme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.qihoo360.replugin.loader.a.PluginActivity;

/**
 * Description:
 * Author     : jone
 * Date       : 2018/4/17 22:31
 */

public class ResultActivity extends PluginActivity {
    private static final String TAG = "ResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();
        intent.putExtra("data", "result");
        setResult(100, intent);
        Toast.makeText(this, "ResultActivity", Toast.LENGTH_SHORT).show();
        finish();
    }
}
