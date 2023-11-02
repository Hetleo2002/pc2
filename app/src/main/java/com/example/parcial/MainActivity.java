package com.example.parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtCodigo, txtNombre, txtPrecio;
    AdminSQLiteOpenHelper admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCodigo = findViewById(R.id.txtCodigo);
        txtNombre = findViewById(R.id.txtNombre);
        txtPrecio = findViewById(R.id.txtPrecio);

        admin = new AdminSQLiteOpenHelper(this);

    }
    @Override
    protected void onResume() {
        super.onResume();
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }
    public void registrarProducto(View view){
        int codigo= Integer.parseInt(txtCodigo.getText().toString());
        String nombre=txtNombre.getText().toString();
        float precio=Float.parseFloat(txtPrecio.getText().toString());

        admin.insertarProducto(codigo,nombre,precio);

        Toast.makeText(this,"Se registro el producto",Toast.LENGTH_SHORT).show();
    }
    public void mostrarLista(View view){
    ArrayList<String> productos = admin.listarProductos();

        Intent intent =new Intent(this,MainActivity2.class);

        intent.putStringArrayListExtra("productos",productos);
        startActivity(intent);
    }
}
