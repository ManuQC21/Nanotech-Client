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
import org.example.entity.service.DetallePedido;
import org.example.entity.service.Producto;
import org.example.utils.Carrito;
import org.example.utils.DateSerializer;
import org.example.utils.TimeSerializer;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ProductoPorCategoriaAdapter extends RecyclerView.Adapter<ProductoPorCategoriaAdapter.ViewHolder> {
    private List<Producto> listadoProductoPorCategoria;
    private Communication communication;
    public ProductoPorCategoriaAdapter(List<Producto> listadoProductoPorCategoria, Communication communication) {
        this.listadoProductoPorCategoria = listadoProductoPorCategoria;
        this.communication = communication;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productores_por_categoria, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(this.listadoProductoPorCategoria.get(position));

    }

    @Override
    public int getItemCount() {
        return this.listadoProductoPorCategoria.size();
    }

    public void updateItems(List<Producto> productosByCategoria) {
        this.listadoProductoPorCategoria.clear();
        this.listadoProductoPorCategoria.addAll(productosByCategoria);
        this.notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgProductoC;
        private final TextView nameProducto, txtPriceProductoC;
        private final Button btnOrdenarPC;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgProductoC = itemView.findViewById(R.id.imgProductoC);
            this.nameProducto = itemView.findViewById(R.id.nameProducto);
            this.txtPriceProductoC = itemView.findViewById(R.id.txtPriceProductoC);
            this.btnOrdenarPC = itemView.findViewById(R.id.btnOrdenarPC);
        }

        public void setItem(final Producto p) {
            String url = ConfigApi.baseUrlE + "/api/documento-almacenado/download/" + p.getFoto().getFileName();

            Picasso picasso = new Picasso.Builder(itemView.getContext())
                    .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                    .build();
            picasso.load(url)
                    //.networkPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE) //No lo almacena el la caché ni en el disco
                    .error(R.drawable.image_not_found)
                    .into(imgProductoC);
            nameProducto.setText(p.getNombre());
            txtPriceProductoC.setText(String.format(Locale.ENGLISH, "S/%.2f", p.getPrecio()));
            btnOrdenarPC.setOnClickListener(v -> {
                DetallePedido detallePedido = new DetallePedido();
                detallePedido.setProducto(p);
                detallePedido.setCantidad(1);
                detallePedido.setPrecio(p.getPrecio());
                successMessage(Carrito.agregarProductos(detallePedido));
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
                    SweetAlertDialog.SUCCESS_TYPE).setTitleText("! Disfrute su Producto !")
                    .setContentText(message).show();
        }
        public void warningMessage(String message) {
            new SweetAlertDialog(itemView.getContext(),
                    SweetAlertDialog.WARNING_TYPE).setTitleText("Para ordenar Primero visualiza la información del producto!")
                    .setContentText(message).show();
        }
        public void errorMessage(String message) {
            new SweetAlertDialog(itemView.getContext(),
                    SweetAlertDialog.ERROR_TYPE).setTitleText("Buen Trabajo!")
                    .setContentText(message).show();
        }
    }

}