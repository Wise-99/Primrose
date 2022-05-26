package com.inhatc.primrose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
    TabHost myTabHost = null;
    TabHost.TabSpec myTabSpec;
    private ImageView image_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar 생성
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myTabHost = (TabHost)findViewById(R.id.tabhost);
        myTabHost.setup();

        myTabSpec = myTabHost.newTabSpec("info").setIndicator("소개").setContent(R.id.tab1);
        myTabHost.addTab(myTabSpec);

        myTabSpec = myTabHost.newTabSpec("all").setIndicator("전체").setContent(R.id.tab2);
        myTabHost.addTab(myTabSpec);

        myTabSpec = myTabHost.newTabSpec("name_search").setIndicator("꽃 검색").setContent(R.id.tab3);
        myTabHost.addTab(myTabSpec);

        myTabSpec = myTabHost.newTabSpec("meaning_search").setIndicator("꽃말"+"\n"+"검색").setContent(R.id.tab4);
        myTabHost.addTab(myTabSpec);

        myTabSpec = myTabHost.newTabSpec("map").setIndicator("꽃집").setContent(R.id.tab5);
        myTabHost.addTab(myTabSpec);

        myTabHost.setCurrentTab(0);

        image_info = (ImageView) findViewById(R.id.imageView);
        image_info.setImageResource(R.drawable.info_primrose);
    }
}

