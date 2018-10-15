package com.packtpub.runtimefragments;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    FragmentOne mFragmentOne;
    FragmentTwo mFragmentTwo;
    int showingFragment=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentOne = new FragmentOne();
        mFragmentTwo = new FragmentTwo();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.frameLayout, mFragmentOne);
//        fragmentTransaction.commit();
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, mFragmentOne).commit();
        showingFragment=1;
    }

    public void switchFragment(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (showingFragment==1) {
            fragmentTransaction.replace(R.id.frameLayout, mFragmentTwo);
            showingFragment = 2;
        } else {
            fragmentTransaction.replace(R.id.frameLayout, mFragmentOne);
            showingFragment=1;
        }
        fragmentTransaction.commit();
    }
}
