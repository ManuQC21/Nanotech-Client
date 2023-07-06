package org.example.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.example.R;
import org.example.api.ConfigApi;
import org.example.entity.service.Producto;

import java.util.List;
import java.util.Locale;

public class ProductoPorCategoriaAdapter extends RecyclerView.Adapter<ProductoPorCategoriaAdapter.ViewHolder> {
    private List<Producto> listadoProductoPorCategoria;

    public ProductoPorCategoriaAdapter(List<Producto> listadoProductoPorCategoria) {
        this.listadoProductoPorCategoria = listadoProductoPorCategoria;
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
    public void updateItems(List<Producto> productosByCategoria){
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
                Toast.makeText(this.itemView.getContext(), "Haz presionado el boton ordenar",Toast.LENGTH_SHORT).show();
            });
        }
    }
}