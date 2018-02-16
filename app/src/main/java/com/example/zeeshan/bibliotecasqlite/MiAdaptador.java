package com.example.zeeshan.bibliotecasqlite;

/**
 * Created by zeeshan on 28/10/2017.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by zeeshan on 24/10/2017.
 */

public class MiAdaptador extends BaseAdapter {

    // Declare Variables
    private Context context;
    private int layout;
    private List<Libro> list;

    public MiAdaptador(Context context, int layout, List<Libro> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Libro getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        /**
         * En este caso vamos a implementar el patron ViewHolder
         * para mejorar el rendimiento.
         */

        View row = convertView;

        if(convertView == null){
            /**
             * Va acceder aqui solo la primera vez, ya que va a ser nulo.
             * Cuando acceda aqui inflamos la vista y adjuntamos las referencias del layout
             * en una nueva instancia de nuestro ViewHolde, y la insertamos dentro del converview
             * para reciclar su uso.
             */
            row = LayoutInflater.from(parent.getContext()).inflate(layout, null);

            TextView codigo = (TextView) row.findViewById(R.id.tv_codigo);
            TextView titulo = (TextView) row.findViewById(R.id.tv_titulo);
            TextView autor = (TextView) row.findViewById(R.id.tv_autor);
            TextView comentario = (TextView) row.findViewById(R.id.tv_comentario);


            ViewHolder holder = new ViewHolder(codigo, titulo, autor, comentario);
            row.setTag(holder);


        } else {
            /**
             * Obtenemos la referencia que anteriormente pusimos dentro del convertview
             * Y asi, recilamos su uso sin necesidad de buscar de nuevo.
             */
            row = convertView;
        }

        // cogo el ViewHolder con el metodo getTag, ya que antes metimos con el setTag()
        ViewHolder holder = (ViewHolder) row.getTag();

        final Libro libActual = getItem(position);

        holder.codigo.setText(libActual.getCodigo() + "");
        holder.titulo.setText(libActual.getTitulo());
        holder.autor.setText(libActual.getAutor());
        holder.comentario.setText(libActual.getComentario());

        return row;
    }

    static class ViewHolder{
        private TextView codigo;
        private TextView titulo;
        private TextView autor;
        private TextView comentario;

        ViewHolder(TextView codigo, TextView titulo, TextView autor, TextView comentario){
            this.codigo = codigo;
            this.titulo = titulo;
            this.autor = autor;
            this.comentario = comentario;
        }
    }
}
