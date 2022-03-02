package com.example.ar_realestate;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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
    private Button btn;

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

       // getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main, new HomeFragment()).commit();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        BottomNavigationView navViewToolbar = findViewById(R.id.nav_view2);
        System.out.println("Error 1");
        btn=(Button)findViewById(R.id.buttonOrderHide);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, btn);

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked
                        Toast.makeText(MainActivity.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                // Showing the popup menu
                popupMenu.show();
            }
        });

        System.out.println("Error 2");
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        binding.navView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_addadvertisement:
                    replaceFragment(new DashboardFragment());
                    break;
                case R.id.navigation_home:
                    navViewToolbar.setVisibility(View.VISIBLE);
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.navigation_notifications:
                    Intent intent=getIntent();
                    User user=(User)intent.getSerializableExtra("UserInformation");

                    if(user==null)
                        replaceFragment(new LoginFragment());
                    else
                        replaceFragment(new UserProfileFragment());

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

        //  getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,fragment);
        fragmentTransaction.commit();
    }

}