package com.example.zeeshan.bibliotecasqlite;

/**
 * Created by zeeshan on 15/02/2018.
 */

public class Libro {

    private int id;
    private int codigo;
    private String titulo;
    private String autor;
    private String comentario;

    public Libro(int id, int codigo, String titulo, String autor, String comentario) {
        this.id = id;
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.comentario = comentario;
    }

    public Libro(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
