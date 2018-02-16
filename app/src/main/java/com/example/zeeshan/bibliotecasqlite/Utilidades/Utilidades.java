package com.example.zeeshan.bibliotecasqlite.Utilidades;

/**
 * Created by zeeshan on 24/01/2018.
 */

public class Utilidades {

    // Constantes de campos de la tabla
    public static final String TABLA_LIBROS = "libros";
    public static final String CAMPO_ID = "_id";
    public static final String CAMPO_CODIGO = "codigo";
    public static final String CAMPO_TITULO = "titulo";
    public static final String CAMPO_AUTOR = "autor";
    public static final String CAMPO_COMENTARIO = "comentario";



    public static final String CREAR_TABLA = "CREATE TABLE " + TABLA_LIBROS + " (" +  CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CAMPO_CODIGO + " INTEGER, " + CAMPO_TITULO +  " TEXT, " + CAMPO_AUTOR + " TEXT, " + CAMPO_COMENTARIO + " TEXT)";

}
