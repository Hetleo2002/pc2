package com.example.parcial;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context) {
        super(context, "Administracion", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table productos(codigo int primary key,nombre text,precio float)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exist productos");
        onCreate(db);
    }
    public void insertarProducto(int codigo, String nombre, float precio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("codigo", codigo);
        registro.put("nombre", nombre);
        registro.put("precio", precio);
        db.insert("productos", null, registro);

    }
    public ArrayList<String> listarProductos() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor fila = db.rawQuery("select * from productos", null);
        ArrayList<String> productos = new ArrayList<>();
        if (fila.moveToFirst()) {
            do {
                productos.add("Código: " + fila.getInt(0) + ", Nombre: " + fila.getString(1) + ", Precio: " + fila.getFloat(2));
            } while (fila.moveToNext());
        }

        return productos;
    }
}
