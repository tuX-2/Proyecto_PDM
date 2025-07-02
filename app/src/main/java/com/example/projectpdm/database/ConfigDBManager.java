package com.example.calcuquesito.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ConfigDBManager {
    private final DBHelper dbHelper;

    public ConfigDBManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    public double[] getConfiguracion() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM configuracion LIMIT 1", null);
        double[] valores = new double[5];
        if (cursor.moveToFirst()) {
            valores[0] = cursor.getDouble(cursor.getColumnIndexOrThrow("materia_prima"));
            valores[1] = cursor.getDouble(cursor.getColumnIndexOrThrow("materiales"));
            valores[2] = cursor.getDouble(cursor.getColumnIndexOrThrow("mano_obra"));
            valores[3] = cursor.getDouble(cursor.getColumnIndexOrThrow("costos"));
            valores[4] = cursor.getDouble(cursor.getColumnIndexOrThrow("margen"));
        }
        cursor.close();
        db.close();
        return valores;
    }

    public void actualizarConfiguracion(double mp, double mat, double mo, double costos, double margen) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("materia_prima", mp);
        values.put("materiales", mat);
        values.put("mano_obra", mo);
        values.put("costos", costos);
        values.put("margen", margen);
        db.update("configuracion", values, "id = 1", null);
        db.close();
    }
}
