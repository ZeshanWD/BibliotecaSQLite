package com.example.zeeshan.bibliotecasqlite;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/*

SI TIENES PROBLEMAS CON EL ALERTDIALOG Y EL TEMA OSCURO
TIENES QUE IMPORTAR EL SIGUIENTE PAQUETE:
import android.app.AlertDialog


 */

public class MainActivity extends Activity {

    public static ArrayList<Libro> listaLibros;
    private TextView vacio;
    static ConexionSQLiteHelper con;
    private ListView listView;
    static MiAdaptador adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaLibros = new ArrayList<Libro>();
        listView = (ListView) findViewById(R.id.list_view);
        vacio = (TextView) findViewById(R.id.vacio);

        con = new ConexionSQLiteHelper(this, "bd_libros", null,  1);

        // Consultar Base de datos
        listaLibros = con.consultarListaContactos();

        // lISTvIEW
        adapter = new MiAdaptador(this, R.layout.list_item, listaLibros);
        listView.setAdapter(adapter);
        listView.setEmptyView(vacio);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final int posicion = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Desea eliminar el elemento " + listaLibros.get(position).getCodigo())
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Aceptar la operacion
                                con.eliminarDirrecion(listaLibros.get(posicion).getId());
                                listaLibros.remove(posicion);
                                adapter.notifyDataSetChanged();

                            }
                        });
                builder.create().show();
            }
        });


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.btnAñadir:
                    añadirLibro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void añadirLibro() {
        Intent añadirIntent = new Intent(MainActivity.this, AddActivity.class);
        startActivityForResult(añadirIntent, 2020);
    }
}
