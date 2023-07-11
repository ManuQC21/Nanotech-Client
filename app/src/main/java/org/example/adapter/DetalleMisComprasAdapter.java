package org.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.example.R;
import org.example.api.ConfigApi;
import org.example.entity.service.DetallePedido;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class DetalleMisComprasAdapter extends RecyclerView.Adapter<DetalleMisComprasAdapter.ViewHolder> {
    private final List<DetallePedido> detalles;

    public DetalleMisComprasAdapter(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detalle_mis_compras, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(this.detalles.get(position));
    }

    @Override
    public int getItemCount() {
        return detalles.size();
    }

    public void updateItems(List<DetallePedido> detalles) {
        this.detalles.clear();
        this.detalles.addAll(detalles);
        this.notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgdetalle;
        private TextView txtValueCodDetailPurchases, txtValueProducto, txtValueQuantity, txtValuePrecioProducto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgdetalle = itemView.findViewById(R.id.imgdetalle);
            this.txtValueCodDetailPurchases = itemView.findViewById(R.id.txtValueCodDetailPurchases);
            this.txtValueProducto = itemView.findViewById(R.id.txtValueProducto);
            this.txtValueQuantity = itemView.findViewById(R.id.txtValueQuantity);
            this.txtValuePrecioProducto = itemView.findViewById(R.id.txtValuePrecioProducto);
        }

        public void setItem(final DetallePedido detalle) {
                String url = ConfigApi.baseUrlE + "/api/documento-almacenado/download/" + detalle.getProducto().getFoto().getFileName();

                Picasso picasso = new Picasso.Builder(itemView.getContext())
                        .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                        .build();
                picasso.load(url)
                        //.networkPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .error(R.drawable.image_not_found)
                        .into(imgdetalle);
                txtValueCodDetailPurchases.setText("C000" + Integer.toString(detalle.getPedido().getId()));
                txtValueProducto.setText(detalle.getProducto().getNombre());
                txtValueQuantity.setText(Integer.toString(detalle.getCantidad()));
                txtValuePrecioProducto.setText(String.format(Locale.ENGLISH, "S/%.2f", detalle.getPrecio()));
            }
            }
        }

