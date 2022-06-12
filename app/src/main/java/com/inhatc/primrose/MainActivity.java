package com.inhatc.primrose;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private RecyclerView recyclerName;
    private RecyclerView recyclerMean;
    private DatabaseReference mDatabase;
    private FirebaseDatabase Database;
    private FirebaseAuth mAuth;
    private ArrayList<Flower> ArrayList;
    private RecyclerAdapter Adapter;
    private RecyclerAdapter nameAdapter;
    private RecyclerAdapter meanAdapter;
    private RecyclerView.LayoutManager LayoutManager;
    private RecyclerView.LayoutManager NameLayoutManager;
    private RecyclerView.LayoutManager MeanLayoutManager;
    private Button search_name_btn;
    private ArrayList<Flower> snameList;
    private String sname;
    private Button search_mean_btn;
    private ArrayList<Flower> smeanList;
    private ArrayList<Flower> allList;
    private String smean;
    private EditText search_mean;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Intent intent1 = new Intent(this,LoginActivity.class);
            startActivity(intent1);
        }
    }

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
        snameList = new ArrayList<>();
        smeanList = new ArrayList<>();
        allList = new ArrayList<>();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){ //파이어베이스 실행이되는 순간 캡쳐
                    Flower flower = dataSnapshot.getValue(Flower.class);
                    ArrayList.add(flower);
                }
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

        myTabHost.setCurrentTab(0);

        image_info = (ImageView) findViewById(R.id.imageView);
        image_info.setImageResource(R.drawable.info_primrose);


        search_name_btn = findViewById(R.id.searchNameBtn);

        recyclerName = findViewById(R.id.RecyclerNameSearch);
        recyclerName.setHasFixedSize(true);
        NameLayoutManager = new LinearLayoutManager(this);
        recyclerName.setLayoutManager(NameLayoutManager);

        search_name_btn.setOnClickListener(new View.OnClickListener() {
            // 입력받는 방법을 관리하는 Manager객체를 요청하여 InputMethodManager에 반환
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            @Override
            public void onClick(View view) {
                EditText search_name = findViewById(R.id.searchName);
                sname = search_name.getText().toString();
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){ //파이어베이스 실행이되는 순간 캡쳐
                            Flower flower = dataSnapshot.getValue(Flower.class);
                            allList.add(flower);
                        }
                        for( Flower f : allList){
                            if(f.getFname().contains(sname)){
                                snameList.add(f);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                nameAdapter = new RecyclerAdapter(snameList,MainActivity.this);
                nameAdapter.setListener(MainActivity.this);
                recyclerName.setAdapter(nameAdapter); //리사이클러 어댑터 클래스랑 연결

                search_name.setText("");
                imm.hideSoftInputFromWindow(search_name.getWindowToken(),0); // 키보드 내림
                allList.clear();
                snameList.clear();
            }
        });

        search_mean_btn = findViewById(R.id.searchMeanBtn);
        recyclerMean = findViewById(R.id.RecyclerMeaningSearch);
        recyclerMean.setHasFixedSize(true);
        MeanLayoutManager = new LinearLayoutManager(this);
        recyclerMean.setLayoutManager(MeanLayoutManager);

        search_mean_btn.setOnClickListener(new View.OnClickListener() {
            // 입력받는 방법을 관리하는 Manager객체를 요청하여 InputMethodManager에 반환
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            @Override
            public void onClick(View view) {
                EditText search_mean = findViewById(R.id.searchMean);
                smean = search_mean.getText().toString();
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){ //파이어베이스 실행이되는 순간 캡쳐
                            Flower flower = dataSnapshot.getValue(Flower.class);
                                allList.add(flower);
                        }
                        for( Flower f : allList){
                            if(f.getFloriography().contains(smean)){
                                smeanList.add(f);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                meanAdapter = new RecyclerAdapter(smeanList,MainActivity.this);
                meanAdapter.setListener(MainActivity.this);
                recyclerMean.setAdapter(meanAdapter); //리사이클러 어댑터 클래스랑 연결

                search_mean.setText("");
                imm.hideSoftInputFromWindow(search_mean.getWindowToken(),0); // 키보드 내림
                allList.clear();
                smeanList.clear();
            }

        });
    }

    @Override
    public void onItemClickedAt(Integer position) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(this,LoginActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.info:

                break;
            case R.id.map:
                Intent intent3 = new Intent(this,GoogleMapsActivity.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
