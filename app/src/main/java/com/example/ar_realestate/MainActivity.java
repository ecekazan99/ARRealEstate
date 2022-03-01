package com.example.ar_realestate;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ar_realestate.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    static  public Database database;
    static public SQLiteDatabase db;
    private ActivityMainBinding binding;

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        /// filter çalışıyor mu diye denemek için öylesine oluşturuldu
      /*  binding.buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new FilterAdvFragment());
            }
        });*/

        try{
            database=new Database(this);
            db=database.getWritableDatabase();


        }
        catch (Exception e){
            e.printStackTrace();
        }

        replaceFragment(new HomeFragment());

       // getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main, new HomeFragment()).commit();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        binding.navView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_addadvertisement:
                    replaceFragment(new DashboardFragment());
                    /*Intent intentAdd = new Intent(getApplicationContext(), Add_AdvActivity.class);
                    finish();
                    startActivity(intentAdd);
                     */

                    break;
                case R.id.navigation_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.navigation_notifications:
                    replaceFragment(new LoginFragment());
                    break;
            }
            return true;
        });

        //  getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_bar,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.topBar_filter:
                replaceFragment(new FilterAdvFragment());
                break;
            case R.id.topBar_order:
                /*replaceFragment(new LoginFragment());
                break;*/
        }

        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,fragment);
        fragmentTransaction.commit();
    }

}