package org.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.example.R;
import org.example.activity.ListarProductosPorCategoriaActivity;
import org.example.api.ConfigApi;
import org.example.entity.service.Categoria;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoriaAdapter extends ArrayAdapter<Categoria> {
    private final String url = ConfigApi.baseUrlE + "/api/documento-almacenado/download/";

    public CategoriaAdapter(@NonNull Context context, int resource, @NonNull List<Categoria> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_categorias, parent, false);
        }
        Categoria c = this.getItem(position);
        ImageView imgCategoria = convertView.findViewById(R.id.imgCategoria);
        TextView txtNombreCategoria= convertView.findViewById(R.id.txtNombreCategoria);

        Picasso picasso = new Picasso.Builder(convertView.getContext())
                .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                .build();
        picasso.load(url + c.getFoto().getFileName())
                .error(R.drawable.image_not_found)
                .into(imgCategoria);
        txtNombreCategoria.setText(c.getNombre());
        convertView.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), ListarProductosPorCategoriaActivity.class);
            i.putExtra("idC", c.getId());
            getContext().startActivity(i);
        });
        return convertView;
    }
}