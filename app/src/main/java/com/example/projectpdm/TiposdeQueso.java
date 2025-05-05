package com.example.projectpdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class TiposdeQueso extends AppCompatActivity {

    CardView btnQF;
    CardView btnQP;
    CardView btnQQ;
    CardView btnQM;
    CardView btnQC;
    CardView btnQB;
    CardView btnYG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tiposde_queso);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            Intent intent = getIntent();
            return insets;
        });

        btnQF = findViewById(R.id.tipo1);
        btnQP = findViewById(R.id.tipo2);
        btnQQ = findViewById(R.id.tipo3);
        btnQM = findViewById(R.id.tipo4);
        btnQC = findViewById(R.id.tipo5);
        btnQB = findViewById(R.id.tipo6);
        btnYG = findViewById(R.id.tipo7);

        btnQF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TiposdeQueso.this, CalculadoraQF.class);
                startActivity(intent);
            }
        });

        btnQP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TiposdeQueso.this, CalculadoraQP.class);
                startActivity(intent);
            }
        });

        btnQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TiposdeQueso.this, CalculadoraQQ.class);
                startActivity(intent);
            }
        });

        btnQM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TiposdeQueso.this, CalculadoraQM.class);
                startActivity(intent);
            }
        });

        btnQC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TiposdeQueso.this, CalculadoraQC.class);
                startActivity(intent);
            }
        });

        btnQB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TiposdeQueso.this, CalculadoraQB.class);
                startActivity(intent);
            }
        });

        btnYG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TiposdeQueso.this, CalculadoraYG.class);
                startActivity(intent);
            }
        });
    }
}