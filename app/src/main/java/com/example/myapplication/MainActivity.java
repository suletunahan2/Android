package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayoutMediator;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity<val> extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    ViewPager2 pager ;
    TabLayout tabLayout;
    TabItem firstitem,seconditem;
    PagerAdapter adapter;
   // public String[] liste={"YEMEK 1 ","YEMEK 2 ","YEMEK 3 ","YEMEK 4 ","YEMEK 5 ","YEMEK 6 ","YEMEK 7 "};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pager =findViewById(R.id.viewpager);
        tabLayout=findViewById(R.id.tablayout);

        pager.setAdapter(new PagerAdapter(this));

        firstitem = findViewById(R.id.firstitem);
        seconditem = findViewById(R.id.seconditem);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //Kullanıcıya gösterilen ListView'a ulaşabilmek için onun bir referansını almak
        //ListView listView=(ListView) findViewById(R.id.listview);
        //adapter tanımlamak
        //this: Context. Yani bağlam. Bu this deyimiyle değer olarak Activity'nin kendisinin dönmesini sağlıyoruz (Activity sınıfının içindeyiz). ArrayAdapter, çalıştığı yerle ilgili bilgiyi Context'e ulaşarak bulur.
        //android.R.layout.simple_list_item_1: resource. Bu parametre, kullanıcının göreceği listenin yerleşim dosyasıdır. Listemiz bu layout dosyasından bina edilecektir.
        //android.R.id.text1: textViewResourceId Bu parametre, layout dosyasındaki TextView'ın adını (id) verdiğimiz yerdir. Her bir veri buradaki bir TextView'a basılır.
        //ArrayAdapter<String> veriadapt=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,liste);
        //bağlanılacak adapter belirlemek
        //listView.setAdapter(veriadapt);




    }


}