package com.example.zeeshan.bibliotecasqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.zeeshan.bibliotecasqlite.Utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zeeshan on 24/01/2018.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA); // creamos la tabla
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        // Si existe alguna version antigua la va a eliminar y volver a crear la base de datos
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_LIBROS);
        onCreate(db);
    }

    public ArrayList<Libro> consultarListaContactos() {
        SQLiteDatabase db = this.getReadableDatabase();

        Libro libro = null;

        ArrayList<Libro> listaLibros = new ArrayList<>();

        Cursor cursor =  db.rawQuery("SELECT * FROM " + Utilidades.TABLA_LIBROS, null);

        while(cursor.moveToNext()){
            libro = new Libro();
            libro.setId(cursor.getInt(0));
            libro.setCodigo(cursor.getInt(1));
            libro.setTitulo(cursor.getString(2));
            libro.setAutor(cursor.getString(3));
            libro.setComentario(cursor.getString(4));
            listaLibros.add(libro);
        }

        return  listaLibros;
    }

    public  Libro añadirLibro(int codigo, String titulo, String autor, String comentario){
        // Primero abrimos la conexion

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_CODIGO, codigo);
        values.put(Utilidades.CAMPO_TITULO, titulo);
        values.put(Utilidades.CAMPO_AUTOR, autor);
        values.put(Utilidades.CAMPO_COMENTARIO, comentario);

        Long id = db.insert(Utilidades.TABLA_LIBROS, Utilidades.CAMPO_ID, values);

        db.close();

        //  A LA HORA DE INSERTAR NO ESPECIFICAMOS EL ID DEL ENLACE, Y PONEMOS NULL Y ASI EL SOLO LE PONDRA UN ID CON AUTO-INCREMENTO
        // db.execSQL("INSERT INTO " + Utilidades.TABLA_DIRECCIONES + " values(null,' " + nombre.getText().toString() + "',' " + enlace.getText().toString() + "')");

        Libro lib = new Libro();
        lib.setId((int)(long)id);
        lib.setCodigo(codigo);
        lib.setTitulo(titulo);
        lib.setAutor(autor);
        lib.setComentario(comentario);


        return lib;
    }

    public void eliminarDirrecion(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Utilidades.TABLA_LIBROS, Utilidades.CAMPO_ID + " = " + id , null);
    }
}
