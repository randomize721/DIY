package com.example.diy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView kerajinan,trending,article;
    KerajinanAdapter Kadapter;
    TrendingAdapter Tadapter;
    ArticleAdapter Aadapter;

    private ArrayList<Product> kerajinanList = new ArrayList<>();
    private ArrayList<Product> trendingList = new ArrayList<>();
    private ArrayList<Artikel> articleList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        kerajinan = (RecyclerView) findViewById(R.id.kerajinan);
        trending = (RecyclerView) findViewById(R.id.trending);
        article = (RecyclerView) findViewById(R.id.article);

        kerajinanList.addAll(ProductData.getListData());
        articleList.addAll(ArtikelData.getListData());
        trendingList.addAll(ProductData.getListData());

        Collections.sort(trendingList);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_discover:
                        Intent intent = new Intent(MainActivity.this, DiscoveryMain.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

        showList();
    }

    private void showList(){
        kerajinan.setLayoutManager(new LinearLayoutManager(this));
        trending.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));
        article.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));
        Kadapter = new KerajinanAdapter(this);
        Kadapter.setListProduct(kerajinanList);
        Tadapter = new TrendingAdapter(this);
        Tadapter.setListProduct(trendingList);
        Aadapter = new ArticleAdapter(this);
        Aadapter.setListProduct(articleList);
        kerajinan.setAdapter(Kadapter);
        trending.setAdapter(Tadapter);
        article.setAdapter(Aadapter);
    }

    public void all_article(View v) {
        Intent intent = new Intent(this, ArticleFullList.class);
        startActivity(intent);
    }

    public void discover(View v) {
        Intent intent = new Intent(this, DiscoveryMain.class);
        startActivity(intent);
    }

}
