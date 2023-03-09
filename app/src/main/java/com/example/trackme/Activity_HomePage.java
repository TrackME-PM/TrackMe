package com.example.trackme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackme.adapter.CardAdapter;
import com.example.trackme.adapter.ViewPagerAdapter;
import com.example.trackme.databinding.ActivityHomePageBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Activity_HomePage extends AppCompatActivity {


    BottomAppBar bottomAppBar;
    ViewPagerAdapter viewPagerAdapter;

    BottomNavigationView bottomNavigationView;
    TextView userName;
    ActivityHomePageBinding binding;

    CardAdapter cardAdapter;
    List<cards> cardsList;
    RecyclerView recyclerView;
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

        userName = findViewById(R.id.textViewProfile);
        userName.setText("Igmite Solutions");

        cardsList = new ArrayList<>();
        cards card1 = new cards("Tea",500,"23-02-2023", 1);
        cards card2 = new cards("Pantry",800,"28-02-2023", 1);
        cards card3 = new cards("Project",500,"01-03-2023", 0);
        cards card4 = new cards("Staff",1000,"27-04-2023", 1);
        cards card5 = new cards("Tea",1000,"27-04-2023", 1);
        cards card6 = new cards("Pantry",1000,"27-04-2023", 1);
        cards card7 = new cards("Project",1000,"27-04-2023", 1);


        cardsList.add(card1);
        cardsList.add(card2);
        cardsList.add(card3);


        recyclerView = findViewById(R.id.itemsRecycler);
        cardAdapter = new CardAdapter(Activity_HomePage.this, cardsList);
        recyclerView.setAdapter(cardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Activity_HomePage.this));

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_HomePage.this, AddActivity.class));
                finish();
            }
        }) ;

        binding.seeAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_HomePage.this, Activity_Transaction.class));
                finish();
            }
        });

        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_HomePage.this, Activity_UserProfile.class));
                finish();
            }
        });

        binding.bottomNavView.setBackground(null);

        binding.bottomNavView.setOnItemSelectedListener(item->{
            int id = item.getItemId();
            switch(id) {
                case R.id.navigation_home:
                    break;

                case R.id.navigation_transaction:
                    startActivity(new Intent(Activity_HomePage.this, Activity_Transaction.class));
                    finish();
                    break;

                default:

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

//    public void openUserPage(View v) {
//        Intent intent = new Intent(this, Activity_UserProfile.class);
//        startActivity(intent);
//    }


}

