package com.communifydam.app.communify;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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

public class AdaptadorAnuncio extends ArrayAdapter<MiniAnuncio>{

    Context context;

    public AdaptadorAnuncio(Context context, int resourceId, List<MiniAnuncio> items){
        super(context, resourceId, items);
        this.context = context;
    }

    public class ViewHolder{
        ImageView image, tipo;

        TextView titulo, anunciante, descripcion, fecha, comunidad;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        MiniAnuncio rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.mini_anuncio, null);
            holder = new ViewHolder();
            holder.image = (ImageView)convertView.findViewById(R.id.image);
            holder.tipo = (ImageView)convertView.findViewById(R.id.imgTipo);
            holder.titulo = (TextView)convertView.findViewById(R.id.txtTitulo);
            holder.anunciante = (TextView)convertView.findViewById(R.id.txtAnunciante);
            holder.descripcion = (TextView)convertView.findViewById(R.id.txtDescripcion);
            holder.fecha = (TextView)convertView.findViewById(R.id.txtTiempo);
            holder.comunidad = (TextView)convertView.findViewById(R.id.txtComunidad);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder)convertView.getTag();

        int img = context.getResources().getIdentifier(rowItem.getImagen(), "drawable", context.getPackageName());
        holder.image.setImageResource(img);
        if (rowItem.getTipo()==1) {
            holder.tipo.setBackground(context.getResources().getDrawable(R.color.colorPrimary, null));
        } else {
            holder.tipo.setBackground(context.getResources().getDrawable(R.color.colorAccent, null));
        }

        holder.comunidad.setText(rowItem.getComunidad());
        holder.titulo.setText(rowItem.getTitulo());
        holder.descripcion.setText(rowItem.getDescripcion());
        holder.anunciante.setText(rowItem.getFecha());
        //holder.fecha.setText(rowItem.getFecha());
        holder.fecha.setText("");

        return convertView;
    }
}