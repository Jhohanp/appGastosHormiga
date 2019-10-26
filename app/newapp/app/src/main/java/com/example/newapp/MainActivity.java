package com.example.newapp;

import android.os.Bundle;

import com.example.newapp.fragments.AddFondoFragment;
import com.example.newapp.fragments.AddGastoFragment;
import com.example.newapp.fragments.HomeFragment;
import com.example.newapp.fragments.VerFondoFragment;
import com.example.newapp.fragments.VerGastoFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.newapp.ui.main.SectionsPagerAdapter;

import static com.example.newapp.R.id.tabs;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, AddGastoFragment.OnFragmentInteractionListener, VerGastoFragment.OnFragmentInteractionListener, AddFondoFragment.OnFragmentInteractionListener, VerFondoFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}