package org.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import org.example.R;
import org.example.adapter.ProductoPorCategoriaAdapter;
import org.example.communication.Communication;
import org.example.entity.service.Producto;
import org.example.viewmodel.ProductoViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListarProductosPorCategoriaActivity extends AppCompatActivity implements Communication {

    private ProductoViewModel productoViewModel;
    private ProductoPorCategoriaAdapter adapter;
    private List<Producto> producto = new ArrayList<>();
    private RecyclerView rcvProductosPorCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_productos_por_categoria);
        init();
        initViewModel();
        initAdapter();
        loadData();


    }
    private void init() {
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_volver_atras);
        toolbar.setNavigationOnClickListener(v -> {
            this.finish();
            this.overridePendingTransition(R.anim.rigth_in, R.anim.rigth_out);
        });
    }
    private void initViewModel() {
        final ViewModelProvider vmp = new ViewModelProvider(this);
        this.productoViewModel = vmp.get(ProductoViewModel.class);
    }
    
    private void initAdapter() {
        adapter = new ProductoPorCategoriaAdapter(producto, this);
        rcvProductosPorCategoria = findViewById(R.id.rcvProductosPorCategoria);
        rcvProductosPorCategoria.setAdapter(adapter);
        rcvProductosPorCategoria.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadData() {
        int idC = getIntent().getIntExtra("idC", 0);//Recibimos el idCategoria
        productoViewModel.listarProductosPorCategoria(idC).observe(this, response -> {
            adapter.updateItems(response.getBody());
        });
    }


    @Override
    public void showDetails(Intent i) {
        startActivity(i);
        overridePendingTransition(R.anim.above_in, R.anim.above_out);
    }

}