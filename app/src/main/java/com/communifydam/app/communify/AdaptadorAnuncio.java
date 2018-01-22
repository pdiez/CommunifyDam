package com.communifydam.app.communify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 2912 on 16/01/2018.
 */

public class AdaptadorAnuncio extends ArrayAdapter<Anuncio>{

    Context context;

    public AdaptadorAnuncio(Context context, int resourceId, List<Anuncio> items){
        super(context, resourceId, items);
        this.context = context;
    }

    public class ViewHolder{
        ImageView image;

        TextView titulo, anunciante, descripcion, numero;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        Anuncio rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.mini_anuncio, null);
            holder = new ViewHolder();
            holder.image = (ImageView)convertView.findViewById(R.id.image);
            holder.titulo = (TextView)convertView.findViewById(R.id.txtTitulo);
            holder.anunciante = (TextView)convertView.findViewById(R.id.txtAnunciante);
            holder.descripcion = (TextView)convertView.findViewById(R.id.txtDescripcion);
            holder.numero = (TextView)convertView.findViewById(R.id.txtNumero);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder)convertView.getTag();

        holder.image.setImageResource(rowItem.getImageId());
        holder.titulo.setText(rowItem.getTitle());
        holder.descripcion.setText(rowItem.getDesc());
        holder.anunciante.setText(rowItem.getAnunciante());
        holder.numero.setText(rowItem.getNumero());

        return convertView;
    }
}
