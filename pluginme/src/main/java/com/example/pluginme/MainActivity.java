package com.example.pluginme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.qihoo360.replugin.loader.a.PluginActivity;

public class MainActivity extends PluginActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TextView tvText1 = (TextView) findViewById(R.id.tv_text1);
//        Intent intent = getIntent();
//        if (null != intent) {
//            String host_str1 = intent.getStringExtra("host_str1");
//            String host_str2 = intent.getStringExtra("host_str2");
////            String host_str3 = intent.getStringExtra("host_str3");
//            if (null != host_str2) {
//                tvText1.setText(host_str1 + host_str2);
        Intent intent2 = new Intent();
        intent2.putExtra("result", "result");
        setResult(10, intent2);
//        finish();
//            } else {
//                tvText1.setText("str null");
//            }
//        } else {
//            tvText1.setText("failed");
//        }
    }
}
