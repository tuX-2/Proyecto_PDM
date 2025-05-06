package com.example.projectpdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class TiposdeQueso extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    CardView btnQF;
    CardView btnQP;
    CardView btnQQ;
    CardView btnQM;
    CardView btnQC;
    CardView btnQB;
    CardView btnYG;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tiposde_queso);

        // Configurar el sistema de insets para el edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            Intent intent = getIntent();
            return insets;
        });

        // Configurar el DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Configurar el botón de menú hamburguesa
        ImageButton menuButton = findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Queso Fresco
        btnQF = findViewById(R.id.tipo1);
        btnQF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TiposdeQueso.this, CalculadoraQF.class);
                startActivity(intent);
            }
        });

        // Queso Panela
        btnQP = findViewById(R.id.tipo2);
        btnQP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TiposdeQueso.this, CalculadoraQP.class);
                startActivity(intent);
            }
        });

        // Quesillo
        btnQQ = findViewById(R.id.tipo3);
        btnQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TiposdeQueso.this, CalculadoraQQ.class);
                startActivity(intent);
            }
        });

        // Queso Manchego
        btnQM = findViewById(R.id.tipo4);
        btnQM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TiposdeQueso.this, CalculadoraQM.class);
                startActivity(intent);
            }
        });

        // Queso Chihuahua
        btnQC = findViewById(R.id.tipo5);
        btnQC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TiposdeQueso.this, CalculadoraQC.class);
                startActivity(intent);
            }
        });

        // Queso Bola
        btnQB = findViewById(R.id.tipo6);
        btnQB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TiposdeQueso.this, CalculadoraQB.class);
                startActivity(intent);
            }
        });

        // Yogurt
        btnYG = findViewById(R.id.tipo7);
        btnYG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TiposdeQueso.this, CalculadoraYG.class);
                startActivity(intent);
            }
        });

        // TODO añadir tipos de queso
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Manejar la navegación del menú
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Ir a la pantalla principal (MainActivity)
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Limpia la pila de actividades
            startActivity(intent);
        } else if (id == R.id.nav_credits) {
            // Ir a la pantalla de créditos
            Intent intent = new Intent(this, Pantalla_creditos.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        getOnBackPressedDispatcher().addCallback(this, new androidx.activity.OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    this.setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });
    }
}