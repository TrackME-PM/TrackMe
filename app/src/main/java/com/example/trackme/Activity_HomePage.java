package com.example.trackme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.trackme.adapter.ViewPagerAdapter;
import com.example.trackme.databinding.ActivityHomePageBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Activity_HomePage extends AppCompatActivity {


    BottomAppBar bottomAppBar;
    ViewPagerAdapter viewPagerAdapter;

    BottomNavigationView bottomNavigationView;
    ActivityHomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        bottomNavigationView = findViewById(R.id.bottomNavView);
//        bottomAppBar = findViewById(R.id.bottomAppBar);
//
//        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                int id = item.getItemId();
//                if(id == R.id.navigation_home) {
//                    bottomAppBar.setOnMenuItemClickListener();
//                }
//
//                return false;
//            }
//        }
//        {
//            @SuppressLint("NonConstantResourceId")
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                if(id == R.id.navigation_home){
//
//                    case R.id.navigation_home:
//                        //viewPager2.setCurrentItem(0);
//                        break;
//
//                    case R.id.navigation_transaction:
//                        //viewPager2.setCurrentItem(1);
//                        break;
//                }
//                return false;
//            }
//        });

//        bottomAppBar.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                switch(position) {
//                    case 0:
//                        bottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
//                        break;
//
//                    case 1:
//                        bottomNavigationView.getMenu().findItem(R.id.navigation_transaction).setChecked(true);
//                        break;
//                }
//                super.onPageSelected(position);
//            }
//        });
//
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_HomePage.this, AddActivity.class));
            }
        }) ;

        binding.seeAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_HomePage.this, Activity_Transaction.class));
            }
        });

        replaceFragment(new HomeFragment());
        binding.bottomNavView.setBackground(null);

        binding.bottomNavView.setOnItemSelectedListener(item->{
            int id = item.getItemId();
            switch(id) {
                case R.id.navigation_home:
                    replaceFragment(new HomeFragment());
                    break;

                case R.id.navigation_transaction:
                    replaceFragment(new TransactionFragment());
                    break;

            }
            return true;
        });


    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        Fragment fragment = null;
//        switch (item.getItemId()) {
//            case R.id.navigation_home:
//                fragment = new homeFragement();
//                return true;
//            case R.id.navigation_transaction:
//                fragment = new transactionFragment();
//                return true;
//        }
//
//        return false;
//    }
    void loadFragment(Fragment fragment) {
        //to attach fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout, fragment).commit();
    }


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction transactionFragment= fragmentManager.beginTransaction();
        transactionFragment.replace(R.id.bottomAppBar, fragment);
        transactionFragment.commit();
    }

//    public void openTransactionPage(View v) {
//        Intent intent = new Intent(this, Activity_Transaction.class);
//        startActivity(intent);
//
//    }


}

