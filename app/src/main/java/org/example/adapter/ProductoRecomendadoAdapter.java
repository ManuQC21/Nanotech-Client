package org.example.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.example.R;
import org.example.activity.DetalleProductoActivity;
import org.example.api.ConfigApi;
import org.example.communication.Communication;
import org.example.entity.service.Producto;
import org.example.utils.DateSerializer;
import org.example.utils.TimeSerializer;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ProductoRecomendadoAdapter extends RecyclerView.Adapter<ProductoRecomendadoAdapter.ViewHolder> {

    private List<Producto> producto;
    private final Communication communication;

    public ProductoRecomendadoAdapter(List<Producto> producto, Communication communication) {
        this.producto = producto;
        this.communication = communication;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productos, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(this.producto.get(position));
    }

    @Override
    public int getItemCount() {
        return this.producto.size();
    }

    public void updateItems(List<Producto> producto) {
        this.producto.clear();
        this.producto.addAll(producto);
        this.notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setItem(final Producto p) {
            ImageView imgProducto = itemView.findViewById(R.id.imgProducto);
            TextView nameProducto = itemView.findViewById(R.id.nameProducto);
            Button btnOrdenar = itemView.findViewById(R.id.btnOrdenar);

            String url = ConfigApi.baseUrlE + "/api/documento-almacenado/download/" + p.getFoto().getFileName();

            Picasso picasso = new Picasso.Builder(itemView.getContext())
                    .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                    .build();
            picasso.load(url)
                    //.networkPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .error(R.drawable.image_not_found)
                    .into(imgProducto);
            nameProducto.setText(p.getNombre());
            btnOrdenar.setOnClickListener(v -> {
                Toast.makeText(itemView.getContext(), "Hola Mundo", Toast.LENGTH_SHORT).show();
            });
            //Inicializar la vista del detalle del platillo
            itemView.setOnClickListener(v -> {
                final Intent i = new Intent(itemView.getContext(), DetalleProductoActivity.class);
                final Gson g = new GsonBuilder()
                        .registerTypeAdapter(Date.class, new DateSerializer())
                        .registerTypeAdapter(Time.class, new TimeSerializer())
                        .create();
                i.putExtra("detalleProducto", g.toJson(p));
                communication.showDetails(i);
            });
        }

        public void successMessage(String message) {
            new SweetAlertDialog(itemView.getContext(),
                    SweetAlertDialog.SUCCESS_TYPE).setTitleText("Buen Trabajo!")
                    .setContentText(message).show();
        }
    }
}
