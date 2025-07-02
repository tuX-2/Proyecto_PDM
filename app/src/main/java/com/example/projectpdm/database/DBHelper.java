package com.example.calcuquesito.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "lacteos.db";
    private static final int DATABASE_VERSION = 2; // Incrementado por el cambio de estructura

    // Nombres de las tablas separadas
    public static final String TABLE_MATERIA_PRIMA = "materia_prima";
    public static final String TABLE_MATERIALES = "materiales";
    public static final String TABLE_MANO_OBRA = "mano_obra";
    public static final String TABLE_COSTOS = "costos";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla para materia prima
        String createMateriaPrimaTable = "CREATE TABLE " + TABLE_MATERIA_PRIMA + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, " +
                "marca TEXT, " +
                "contenido REAL, " +
                "unidades TEXT, " +
                "precio REAL)";
        db.execSQL(createMateriaPrimaTable);

        // Crear tabla para materiales
        String createMaterialesTable = "CREATE TABLE " + TABLE_MATERIALES + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, " +
                "marca TEXT, " +
                "costoAdquisicion REAL, " +
                "valordeDesecho REAL, " +
                "vidaUtil REAL)";
        db.execSQL(createMaterialesTable);

        // Crear tabla para mano de obra
        String createManoObraTable = "CREATE TABLE " + TABLE_MANO_OBRA + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, " +
                "sueldo REAL, " +
                "tiempoTrabajo REAL)";
        db.execSQL(createManoObraTable);

        // Crear tabla para costos
        String createCostosTable = "CREATE TABLE " + TABLE_COSTOS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "insumo TEXT, " +
                "marca TEXT, " +
                "contenido INTEGER, " +
                "unidades TEXT, " +
                "precio REAL)";
        db.execSQL(createCostosTable);

        // Insertar valores iniciales en cada tabla
        //insertInitialValues(db);
    }

    private void insertInitialValues(SQLiteDatabase db) {
        // Insertar valor inicial para materia prima
        db.execSQL("INSERT INTO " + TABLE_MATERIA_PRIMA + " (valor, descripcion) " +
                "VALUES (50.0, 'Valor inicial de materia prima')");

        // Insertar valor inicial para materiales
        db.execSQL("INSERT INTO " + TABLE_MATERIALES + " (valor, descripcion) " +
                "VALUES (10.0, 'Valor inicial de materiales')");

        // Insertar valor inicial para mano de obra
        db.execSQL("INSERT INTO " + TABLE_MANO_OBRA + " (valor, descripcion) " +
                "VALUES (15.0, 'Valor inicial de mano de obra')");

        // Insertar valor inicial para costos
        db.execSQL("INSERT INTO " + TABLE_COSTOS + " (valor, descripcion) " +
                "VALUES (20.0, 'Valor inicial de costos')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            // Migrar datos de la tabla antigua si existe
            migrateFromOldStructure(db);
        }

        // Eliminar tablas existentes y recrear
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIA_PRIMA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIALES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANO_OBRA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COSTOS);

        // También eliminar la tabla antigua si existe
        db.execSQL("DROP TABLE IF EXISTS configuracion");

        onCreate(db);
    }

    private void migrateFromOldStructure(SQLiteDatabase db) {
        // Verificar si la tabla antigua existe y migrar los datos
        try {
            // Obtener el último registro de la tabla configuracion
            String query = "SELECT materia_prima, materiales, mano_obra, costos FROM configuracion ORDER BY id DESC LIMIT 1";
            android.database.Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                double materiaPrima = cursor.getDouble(0);
                double materiales = cursor.getDouble(1);
                double manoObra = cursor.getDouble(2);
                double costos = cursor.getDouble(3);

                // Crear las nuevas tablas
                onCreate(db);

                // Insertar los valores migrados (esto reemplazará los valores iniciales)
                db.execSQL("UPDATE " + TABLE_MATERIA_PRIMA + " SET valor = " + materiaPrima + " WHERE id = 1");
                db.execSQL("UPDATE " + TABLE_MATERIALES + " SET valor = " + materiales + " WHERE id = 1");
                db.execSQL("UPDATE " + TABLE_MANO_OBRA + " SET valor = " + manoObra + " WHERE id = 1");
                db.execSQL("UPDATE " + TABLE_COSTOS + " SET valor = " + costos + " WHERE id = 1");
            }
            cursor.close();
        } catch (Exception e) {
            // Si hay algún error, simplemente crear las tablas con valores por defecto
            // El log del error se puede agregar aquí si es necesario
        }
    }

    // Métodos de utilidad para obtener el valor actual de cada tabla
    public double getCurrentMateriaPrima() {
        SQLiteDatabase db = this.getReadableDatabase();
        android.database.Cursor cursor = db.rawQuery("SELECT valor FROM " + TABLE_MATERIA_PRIMA + " ORDER BY id DESC LIMIT 1", null);
        double value = 50.0; // valor por defecto
        if (cursor.moveToFirst()) {
            value = cursor.getDouble(0);
        }
        cursor.close();
        return value;
    }

    public double getCurrentMateriales() {
        SQLiteDatabase db = this.getReadableDatabase();
        android.database.Cursor cursor = db.rawQuery("SELECT valor FROM " + TABLE_MATERIALES + " ORDER BY id DESC LIMIT 1", null);
        double value = 10.0; // valor por defecto
        if (cursor.moveToFirst()) {
            value = cursor.getDouble(0);
        }
        cursor.close();
        return value;
    }

    public double getCurrentManoObra() {
        SQLiteDatabase db = this.getReadableDatabase();
        android.database.Cursor cursor = db.rawQuery("SELECT valor FROM " + TABLE_MANO_OBRA + " ORDER BY id DESC LIMIT 1", null);
        double value = 15.0; // valor por defecto
        if (cursor.moveToFirst()) {
            value = cursor.getDouble(0);
        }
        cursor.close();
        return value;
    }

    public double getCurrentCostos() {
        SQLiteDatabase db = this.getReadableDatabase();
        android.database.Cursor cursor = db.rawQuery("SELECT valor FROM " + TABLE_COSTOS + " ORDER BY id DESC LIMIT 1", null);
        double value = 20.0; // valor por defecto
        if (cursor.moveToFirst()) {
            value = cursor.getDouble(0);
        }
        cursor.close();
        return value;
    }

    // Métodos para insertar nuevos valores en cada tabla
    public void insertMateriaPrima(double valor, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_MATERIA_PRIMA + " (valor, descripcion) VALUES (?, ?)",
                new Object[]{valor, descripcion});
    }

    public void insertMateriales(double valor, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_MATERIALES + " (valor, descripcion) VALUES (?, ?)",
                new Object[]{valor, descripcion});
    }

    public void insertManoObra(double valor, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_MANO_OBRA + " (valor, descripcion) VALUES (?, ?)",
                new Object[]{valor, descripcion});
    }

    public void insertCostos(double valor, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_COSTOS + " (valor, descripcion) VALUES (?, ?)",
                new Object[]{valor, descripcion});
    }

}