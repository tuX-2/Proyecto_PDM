package com.example.calcuquesito.activities;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.calcuquesito.R;
import com.example.calcuquesito.fragments.*;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        nav.setOnItemSelectedListener(item -> {
            Fragment selected = null;
            if (item.getItemId() == R.id.nav_productos)
                selected = new ProductosFragment();
            else if (item.getItemId() == R.id.nav_admin)
                selected = new AdminFragment();
            else if (item.getItemId() == R.id.nav_creditos)
                selected = new CreditosFragment();

            if (selected != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, selected)
                        .commit();
            }
            return true;
        });

        // Fragment inicial
        nav.setSelectedItemId(R.id.nav_productos);
    }
}