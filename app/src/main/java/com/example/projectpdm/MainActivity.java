package com.example.projectpdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public Button btnTiposQueso, btnCreditos;
    public Button btnAdministracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            // Boton de Tipos de queso
            btnTiposQueso = v.findViewById(R.id.btnTiposQueso);
            btnCreditos = v.findViewById(R.id.button_creditos);
            btnAdministracion = v.findViewById(R.id.button_admin);

            btnTiposQueso.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, TiposdeQueso.class);
                    startActivity(intent);
                }
            });
          
            btnCreditos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Pantalla_creditos.class);
                    startActivity(intent);
                }
            });
            btnAdministracion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Pantalla_Menu_administracion.class);
                    startActivity(intent);
                }
            });
            return insets;
    });
}
}