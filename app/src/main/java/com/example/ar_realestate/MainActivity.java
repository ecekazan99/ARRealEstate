package com.example.ar_realestate;

import android.content.Intent;
import java.sql.Connection;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.ar_realestate.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public Connection con;
    static  public Database database;
    static public SQLiteDatabase db;
    private ActivityMainBinding binding;
    private ActionBar actionBar;
    private Button btn;
    static  public  BottomNavigationView navViewToolbar;
    static public Boolean incrPriceClick=false;
    static public Boolean decrsPriceClick=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try{
            database=new Database(this);
            db=database.getWritableDatabase();

        }
        catch (Exception e){
            e.printStackTrace();
        }

        replaceFragment(new HomeFragment());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navViewToolbar = findViewById(R.id.nav_view2);

        btn=(Button)findViewById(R.id.buttonOrderHide);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, btn);

                popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId()==R.id.inc_price)
                        {
                            incrPriceClick=true;
                            replaceFragment(new HomeFragment());
                        }
                        else if(menuItem.getItemId()==R.id.dcrs_price)
                        {
                            decrsPriceClick=true;
                            replaceFragment(new HomeFragment());
                        }
                        return true;
                    }
                });

                popupMenu.show();
            }
        });


        binding.navView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_ar:
                    replaceFragment(new ARFragment());
                    break;
                case R.id.navigation_addadvertisement:
                    navViewToolbar.setVisibility(View.INVISIBLE);
                    replaceFragment(new AddAdvFragment());
                    break;
                case R.id.navigation_home:
                    navViewToolbar.setVisibility(View.VISIBLE);
                    MyAdvertisementFragment.clickMyAdvDetail=false;
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.navigation_notifications:
                    Intent intent=getIntent();
                    User user=(User)intent.getSerializableExtra("UserInformation");
                    if(user==null){
                        replaceFragment(new LoginFragment());
                    }
                    else{
                        replaceFragment(new MyAccountFragment());
                    }
                    navViewToolbar.setVisibility(View.INVISIBLE);
                    break;
            }
            return true;
        });

        binding.navView2.setOnItemSelectedListener(item_toolbar ->{
            switch (item_toolbar.getItemId()) {
                case R.id.toolbar_filter:
                    navViewToolbar.setVisibility(View.INVISIBLE);
                    replaceFragment(new FilterAdvFragment());
                    break;
                case R.id.toolbar_Order:

            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,fragment);
        fragmentTransaction.commit();
    }

}