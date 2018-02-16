package com.example.zeeshan.bibliotecasqlite;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends Activity {

    private EditText codigo;
    private EditText titulo;
    private EditText autor;
    private EditText comentario;

    private ConexionSQLiteHelper con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        codigo = (EditText) findViewById(R.id.codigo);
        titulo = (EditText) findViewById(R.id.titulo);
        autor = (EditText) findViewById(R.id.autor);
        comentario = (EditText) findViewById(R.id.comentario);

        con = new ConexionSQLiteHelper(this, "bd_libros", null,  1);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.btnDescartar:
                finish();

            case R.id.btnGuardar:
                guardarLibro();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void guardarLibro() {
        Libro libroNuevo = con.añadirLibro(Integer.parseInt(codigo.getText().toString()), titulo.getText().toString(), autor.getText().toString(), comentario.getText().toString());
        MainActivity.listaLibros.add(libroNuevo);

        MainActivity.adapter.notifyDataSetChanged();
        finish();
    }
}
