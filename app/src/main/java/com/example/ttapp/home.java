package com.example.ttapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.Inflater;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton fab;
    PagerAdapter pgadapter;
    ArrayList<String> tabnames;

    todayclass todayclass;
    todaydeadline todaydeadline;
    FirebaseAuth fAuth;


    ViewPager vpgr;
    TabLayout tblyt;
    TabItem tabitem1;
    TabItem tabitem2;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fab = findViewById(R.id.FAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),CreateTimetable.class);
                startActivity(intent);
            }
        });

        vpgr = findViewById(R.id.vpgr);
        tblyt = findViewById(R.id.tblyt);
        tabitem1 = findViewById(R.id.tl);
        tabitem2 = findViewById(R.id.tdl);

        tabnames = new ArrayList<>();
        tabnames.add("Today's class");
        tabnames.add("Todaydeadline");

        fAuth= FirebaseAuth.getInstance();
        todayclass = new todayclass();
        todaydeadline = new todaydeadline();

        pgadapter = new PagerAdapter(getSupportFragmentManager(),2);
        pgadapter.addfragment(todayclass,"Today's class");
        pgadapter.addfragment(todaydeadline,"Today's deadline");
        vpgr.setAdapter(pgadapter);

        toolbar = findViewById(R.id.main_toolbar);

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openNavDrawer,R.string.closeNavDrawer);




        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cs204_resources:
                Toast.makeText(home.this,"cs204 selected",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(),ListOfPDFs.class);
                intent1.putExtra("course",1);
                startActivity(intent1);
                return true;
            case R.id.cs205_resources:
                Toast.makeText(home.this,"cs205 selected",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext(),ListOfPDFs.class);
                intent2.putExtra("course",2);
                startActivity(intent2);
                return true;
            case R.id.cs206_resources:
                Toast.makeText(home.this,"cs206 selected",Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(getApplicationContext(),ListOfPDFs.class);
                intent3.putExtra("course",3);
                startActivity(intent3);
                return true;
            case R.id.ma201_resources:
                Toast.makeText(home.this,"ma201 selected",Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(getApplicationContext(),ListOfPDFs.class);
                intent4.putExtra("course",4);
                startActivity(intent4);
                return true;
            case R.id.hs201_resources:
                Toast.makeText(home.this,"hs201 selected",Toast.LENGTH_SHORT).show();
                Intent intent5 = new Intent(getApplicationContext(),ListOfPDFs.class);
                intent5.putExtra("course",5);
                startActivity(intent5);
                return true;
            case  R.id.my_notes:
                Intent intent = new Intent(getApplicationContext(),UploadActivity.class);
                startActivity(intent);
                return true;
            case R.id.books:
                Intent intent6 = new Intent(getApplicationContext(),booksandnotesadder.class);
                startActivity(intent6);
            case R.id.sign_out:

                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage("Are you sure?")
                        .setTitle("Sign out")
                        .setIcon(R.drawable.ic_baseline_error_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                fAuth.signOut();
                                Intent intent  = new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(home.this,"ok",Toast.LENGTH_SHORT).show();
                            }
                        });
                alert.show();

                return true;
            default:
                return true;
        }

    };

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}