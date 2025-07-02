package com.example.projectpdm.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectpdm.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

public class QuesoBolaActivity extends AppCompatActivity {
    TextView resultado;

    private TextInputEditText cantLeche, cantQuesoP,
            tiempLechFri, tiempLechCal, tiempCuaj,
            tiempDesu, tiempAmasa, ganancia;

    private CheckBox lechFria, lechCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queso_bola);

        // Inicializar vistas
        cantLeche = findViewById(R.id.etLecheUtilizada);
        cantQuesoP = findViewById(R.id.etQuesoProducido);
        lechFria = findViewById(R.id.cbLecheFria);
        lechCal = findViewById(R.id.cbLecheCaliente);
        tiempLechFri = findViewById(R.id.etTiempoLecheFria);
        tiempLechCal = findViewById(R.id.etTiempoLecheCaliente);
        tiempCuaj = findViewById(R.id.etTiempoCuajado);
        tiempDesu = findViewById(R.id.etTiempoDesuerado);
        tiempAmasa = findViewById(R.id.etTiempoAmasado);
        ganancia = findViewById(R.id.etGanancia);

        // Configurar estado inicial de los campos de tiempo
        configurarEstadoInicial();

        // Configurar listeners para los checkboxes
        configurarCheckBoxListeners();

        Button btnBack = findViewById(R.id.btnRegreso);
        btnBack.setOnClickListener(v -> finish());

        Button btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificarCampos()) {
                    calcularYMostrarResultado();
                } else {
                    advertencia();
                }
            }
        });
    }

    private void configurarEstadoInicial() {
        // Configurar estado inicial basado en si los checkboxes están marcados
        tiempLechFri.setEnabled(lechFria.isChecked());
        tiempLechCal.setEnabled(lechCal.isChecked());
    }

    private void configurarCheckBoxListeners() {
        lechFria.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tiempLechFri.setEnabled(isChecked);
                if (!isChecked) {
                    tiempLechFri.setText(""); // Limpiar campo si se desmarca
                }
            }
        });

        lechCal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tiempLechCal.setEnabled(isChecked);
                if (!isChecked) {
                    tiempLechCal.setText(""); // Limpiar campo si se desmarca
                }
            }
        });
    }

    private boolean verificarCampos() {
        // Verificar campos obligatorios
        if (cantLeche.getText().toString().trim().isEmpty()) {
            return false;
        }
        if (cantQuesoP.getText().toString().trim().isEmpty()) {
            return false;
        }
        if (tiempCuaj.getText().toString().trim().isEmpty()) {
            return false;
        }
        if (tiempDesu.getText().toString().trim().isEmpty()) {
            return false;
        }
        if (tiempAmasa.getText().toString().trim().isEmpty()) {
            return false;
        }
        if (ganancia.getText().toString().trim().isEmpty()) {
            return false;
        }

        // Verificar campos condicionales de tiempo
        if (lechFria.isChecked() && tiempLechFri.getText().toString().trim().isEmpty()) {
            return false;
        }
        if (lechCal.isChecked() && tiempLechCal.getText().toString().trim().isEmpty()) {
            return false;
        }

        // Al menos uno de los checkboxes debe estar marcado
        if (!lechFria.isChecked() && !lechCal.isChecked()) {
            return false;
        }

        return true;
    }

    private void advertencia() {
        new AlertDialog.Builder(this)
                .setTitle("Campos incompletos")
                .setMessage("Por favor rellene todos los campos obligatorios y seleccione al menos un tipo de leche")
                .setIcon(R.drawable.info_24px)
                .setPositiveButton("OK", null)
                .show();
    }

    private void calcularYMostrarResultado() {
        try {
            // Obtener valores de los campos
            double lecheUtilizada = Double.parseDouble(cantLeche.getText().toString().trim());
            double quesoProducido = Double.parseDouble(cantQuesoP.getText().toString().trim());
            double tiempoCuajado = Double.parseDouble(tiempCuaj.getText().toString().trim());
            double tiempoDesuerado = Double.parseDouble(tiempDesu.getText().toString().trim());
            double tiempoAmasado = Double.parseDouble(tiempAmasa.getText().toString().trim());
            double porcentajeGanancia = Double.parseDouble(ganancia.getText().toString().trim());

            // Obtener tiempos de leche según selección
            double tiempoLecheFria = 0;
            double tiempoLecheCaliente = 0;

            if (lechFria.isChecked()) {
                tiempoLecheFria = Double.parseDouble(tiempLechFri.getText().toString().trim());
            }
            if (lechCal.isChecked()) {
                tiempoLecheCaliente = Double.parseDouble(tiempLechCal.getText().toString().trim());
            }

            // Realizar cálculos (aquí puedes implementar tu lógica de cálculo)
            double costoProduccion = calcularCostoProduccion(lecheUtilizada, quesoProducido,
                    tiempoLecheFria, tiempoLecheCaliente, tiempoCuajado,
                    tiempoDesuerado, tiempoAmasado);

            double precioVenta = calcularPrecioVenta(costoProduccion, porcentajeGanancia);

            mostrarBottomSheet(costoProduccion, precioVenta);

        } catch (NumberFormatException e) {
            new AlertDialog.Builder(this)
                    .setTitle("Error de formato")
                    .setMessage("Por favor ingrese valores numéricos válidos")
                    .setIcon(R.drawable.info_24px)
                    .setPositiveButton("OK", null)
                    .show();
        }
    }

    private double calcularCostoProduccion(double lecheUtilizada, double quesoProducido,
                                           double tiempoLecheFria, double tiempoLecheCaliente,
                                           double tiempoCuajado, double tiempoDesuerado,
                                           double tiempoAmasado) {
        // Implementar lógica de cálculo del costo de producción
        // Este es un ejemplo básico, ajusta según tus necesidades
        double tiempoTotal = tiempoLecheFria + tiempoLecheCaliente + tiempoCuajado +
                tiempoDesuerado + tiempoAmasado;
        double costoManoObra = tiempoTotal * 50; // Ejemplo: 50 pesos por hora
        double costoMateriaPrima = lecheUtilizada * 15; // Ejemplo: 15 pesos por litro de leche

        return costoManoObra + costoMateriaPrima;
    }

    private double calcularPrecioVenta(double costoProduccion, double porcentajeGanancia) {
        return costoProduccion * (1 + porcentajeGanancia / 100);
    }

    private void mostrarBottomSheet(double costoProduccion, double precioVenta) {
        // Crear el BottomSheetDialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

        // Inflar el layout personalizado
        View bottomSheetView = LayoutInflater.from(this)
                .inflate(R.layout.bottom_sheet_info, null);

        // Referenciar elementos del layout
        ImageView iconoInfo = bottomSheetView.findViewById(R.id.icono_info);
        TextView tituloInfo = bottomSheetView.findViewById(R.id.titulo_info);
        TextView descripcionInfo = bottomSheetView.findViewById(R.id.descripcion_info);
        Button btnCerrar = bottomSheetView.findViewById(R.id.btn_cerrar);

        // Configurar contenido con los resultados calculados
        tituloInfo.setText("Resultados del Cálculo");
        descripcionInfo.setText(String.format(
                "Costo de Producción: $%.2f\nPrecio de Venta: $%.2f\nGanancia: $%.2f",
                costoProduccion,
                precioVenta,
                precioVenta - costoProduccion
        ));

        // Configurar botón cerrar
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        // Configurar y mostrar el dialog
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
}
