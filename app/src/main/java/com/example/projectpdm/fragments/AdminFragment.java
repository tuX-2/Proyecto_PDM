package com.example.projectpdm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.projectpdm.R;
import com.example.projectpdm.singletons.ConfigManager;

public class AdminFragment extends Fragment {

    EditText edtMateriaPrima, edtMateriales, edtManoObra, edtCostos, edtMargen;
    Button btnGuardar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_administracion, container, false);
/*
        edtMateriaPrima = view.findViewById(R.id.edtMateriaPrima);
        edtMateriales = view.findViewById(R.id.edtMateriales);
        edtManoObra = view.findViewById(R.id.edtManoObra);
        edtCostos = view.findViewById(R.id.edtCostos);
        edtMargen = view.findViewById(R.id.edtMargen);
        btnGuardar = view.findViewById(R.id.btnGuardar);

        ConfigManager config = ConfigManager.getInstance(requireContext());
        edtMateriaPrima.setText(String.valueOf(config.getMateriaPrima()));
        edtMateriales.setText(String.valueOf(config.getMateriales()));
        edtManoObra.setText(String.valueOf(config.getManoObra()));
        edtCostos.setText(String.valueOf(config.getCostos()));
        edtMargen.setText(String.valueOf(config.getMargenGanancia()));

        btnGuardar.setOnClickListener(v -> {
            double mp = Double.parseDouble(edtMateriaPrima.getText().toString());
            double mat = Double.parseDouble(edtMateriales.getText().toString());
            double mo = Double.parseDouble(edtManoObra.getText().toString());
            double cos = Double.parseDouble(edtCostos.getText().toString());
            double mar = Double.parseDouble(edtMargen.getText().toString());

            config.actualizarConfiguracion(mp, mat, mo, cos, mar);
        });
*/
        return view;
    }
}
