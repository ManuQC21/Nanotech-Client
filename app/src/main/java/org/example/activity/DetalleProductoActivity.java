package org.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.example.R;
import org.example.api.ConfigApi;
import org.example.entity.service.DetallePedido;
import org.example.entity.service.Producto;
import org.example.utils.DateSerializer;
import org.example.utils.TimeSerializer;

import java.sql.Date;
import java.sql.Time;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DetalleProductoActivity extends AppCompatActivity {

    private ImageView imgProductoDetalle;
    private Button btnAgregarCarrito, btnComprar;
    private TextView tvNameProductoDetalle, tvMedidaProductoDetalle, tvStockProductoDetalle, tvPrecioProductoDetalle, tvDescripcionProductoDetalle;
    final Gson g = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateSerializer())
            .registerTypeAdapter(Time.class, new TimeSerializer())
            .create();
    Producto producto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        init();
        loadData();
    }
    private void init() {
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_volver_atras);
        toolbar.setNavigationOnClickListener(v -> {//Reemplazo con lamba
            this.finish();
            this.overridePendingTransition(R.anim.rigth_in, R.anim.rigth_out);
        });
        this.imgProductoDetalle = findViewById(R.id.imgProductoDetalle);
        this.btnAgregarCarrito = findViewById(R.id.btnAgregarCarrito);
        this.btnComprar = findViewById(R.id.btnComprar);
        this.tvNameProductoDetalle = findViewById(R.id.tvNameProductoDetalle);
        this.tvMedidaProductoDetalle = findViewById(R.id.tvMedidaProductoDetalle);
        this.tvStockProductoDetalle = findViewById(R.id.tvStockProductoDetalle);
        this.tvPrecioProductoDetalle = findViewById(R.id.tvPrecioProductoDetalle);
        this.tvDescripcionProductoDetalle = findViewById(R.id.tvDescripcionProductoDetalle);

    }

    private void loadData() {
        final String detalleString = this.getIntent().getStringExtra("detalleProducto");
        if (detalleString != null) {
            producto = g.fromJson(detalleString, Producto.class);
            this.tvNameProductoDetalle.setText(producto.getNombre());
            this.tvMedidaProductoDetalle.setText(producto.getMedida());
            this.tvStockProductoDetalle.setText(String.format(Locale.ENGLISH, "%d",producto.getStock()));
            this.tvPrecioProductoDetalle.setText(String.format(Locale.ENGLISH, "S/%.2f",producto.getPrecio()));
            this.tvDescripcionProductoDetalle.setText(producto.getDescripcion());
            String url = ConfigApi.baseUrlE + "/api/documento-almacenado/download/" + producto.getFoto().getFileName();
            Picasso picasso = new Picasso.Builder(this)
                    .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                    .build();
            picasso.load(url)
                    .error(R.drawable.image_not_found)
                    .into(this.imgProductoDetalle);
        } else {
            System.out.println("Error al obtener los detalles del platillo");
        }

}}