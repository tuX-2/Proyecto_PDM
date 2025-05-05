package com.example.projectpdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class Pantalla_Menu_administracion extends AppCompatActivity {

    MaterialButton btnMateriaPrima, btnMateriales, btnManoObra, btnCostoGastos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_menu_administracion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainAdministracion), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            Intent intent = getIntent();
            return insets;
        });

        btnMateriaPrima = findViewById(R.id.btn_materia_prima);
        btnMateriales = findViewById(R.id.btn_materiales);
        btnManoObra = findViewById(R.id.btn_mano_obra);
        btnCostoGastos = findViewById(R.id.btn_costos_gastos);

        btnMateriaPrima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnMateriales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnManoObra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCostoGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}