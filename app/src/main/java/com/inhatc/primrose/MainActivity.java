package com.inhatc.primrose;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TabHost;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.Listener {
    TabHost myTabHost = null;
    TabHost.TabSpec myTabSpec;
    private ImageView image_info;
    private RecyclerView recyclerView1;
    private DatabaseReference mDatabase;
    private FirebaseDatabase Database;
    private ArrayList<Flower> ArrayList;
    private RecyclerAdapter Adapter;
    private RecyclerView.LayoutManager LayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView1 = findViewById(R.id.Recycler_all);
        recyclerView1.setHasFixedSize(true);
        LayoutManager = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(LayoutManager);
        Database = FirebaseDatabase.getInstance();
        mDatabase = Database.getReference("Flower");
        ArrayList = new ArrayList<>();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){ //파이어베이스 실행이되는 순간 캡쳐
                    Flower flower = dataSnapshot.getValue(Flower.class);
                    ArrayList.add(flower);
                }
                System.out.println(ArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Adapter = new RecyclerAdapter(ArrayList,this);
        Adapter.setListener(this);
        recyclerView1.setAdapter(Adapter); //리사이클러 어댑터 클래스랑 연결
        //리사이클러 어댑터 클래스는 item.xml에 연결해서 텍스트에 자료넣어주는것

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

    @Override
    public void onItemClickedAt(Integer position) {

    }
}
