package com.example.calcuquesito.singletons;

import android.content.Context;
import com.example.calcuquesito.database.ConfigDBManager;

public class ConfigManager {
    private static ConfigManager instance;
    private ConfigDBManager db;

    private double materiaPrima, materiales, manoObra, costos, margen;

    private ConfigManager(Context context) {
        db = new ConfigDBManager(context);
        cargarDesdeBD();
    }

    public static ConfigManager getInstance(Context context) {
        if (instance == null) {
            instance = new ConfigManager(context);
        }
        return instance;
    }

    private void cargarDesdeBD() {
        double[] valores = db.getConfiguracion();
        materiaPrima = valores[0];
        materiales = valores[1];
        manoObra = valores[2];
        costos = valores[3];
        margen = valores[4];
    }

    public void actualizarConfiguracion(double mp, double mat, double mo, double cos, double mar) {
        db.actualizarConfiguracion(mp, mat, mo, cos, mar);
        cargarDesdeBD();
    }

    // Getters
    public double getMateriaPrima() { return materiaPrima; }
    public double getMateriales() { return materiales; }
    public double getManoObra() { return manoObra; }
    public double getCostos() { return costos; }
    public double getMargenGanancia() { return margen; }
}

