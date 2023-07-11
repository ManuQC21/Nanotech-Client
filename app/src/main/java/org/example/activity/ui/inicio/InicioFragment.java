package org.example.activity.ui.inicio;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.example.R;
import org.example.adapter.CategoriaAdapter;
import org.example.adapter.ProductoRecomendadoAdapter;
import org.example.adapter.SliderAdapter;
import org.example.communication.Communication;
import org.example.entity.SliderItem;
import org.example.entity.service.Producto;
import org.example.viewmodel.CategoriaViewModel;
import org.example.viewmodel.ProductoViewModel;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class InicioFragment extends Fragment implements Communication {

    private CategoriaViewModel categoriaViewModel;


    private ProductoViewModel productoViewModel;
    private RecyclerView rcvProductosRecomendados;
    private ProductoRecomendadoAdapter adapter;
    private List<Producto> productos = new ArrayList<>();


    private GridView gvCategorias;
    private CategoriaAdapter categoriaAdapter;
    private SliderView svCarrusel;
    private SliderAdapter sliderAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        initAdapter();
        loadData();
    }

    private void init(View v) {
        svCarrusel = v.findViewById(R.id.svCarrusel);
        ViewModelProvider vmp = new ViewModelProvider(this);
        //Categorías
        categoriaViewModel = vmp.get(CategoriaViewModel.class);
        gvCategorias = v.findViewById(R.id.gvCategorias);
        //Productos
        rcvProductosRecomendados = v.findViewById(R.id.rcvProductosRecomendados);
        rcvProductosRecomendados.setLayoutManager(new GridLayoutManager(getContext(), 1));
        productoViewModel = vmp.get(ProductoViewModel.class);


    }

    private void initAdapter() {
        //Carrusel de Imágenes
        sliderAdapter = new SliderAdapter(getContext());
        svCarrusel.setSliderAdapter(sliderAdapter);
        svCarrusel.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        svCarrusel.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        svCarrusel.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        svCarrusel.setIndicatorSelectedColor(Color.WHITE);
        svCarrusel.setIndicatorUnselectedColor(Color.GRAY);
        svCarrusel.setScrollTimeInSec(4); //set scroll delay in seconds :
        svCarrusel.startAutoCycle();
        //Categorías
        categoriaAdapter = new CategoriaAdapter(getContext(), R.layout.item_categorias, new ArrayList<>());
        gvCategorias.setAdapter(categoriaAdapter);
        //Productos
        adapter = new ProductoRecomendadoAdapter(productos, this);
        rcvProductosRecomendados.setAdapter(adapter);
    }

    private void loadData() {

        List<SliderItem> lista = new ArrayList<>();
        lista.add(new SliderItem(R.drawable.top, "Los Mejores Celulares"));
        lista.add(new SliderItem(R.drawable.pc, "Los Mejores Componentes para Computadoras"));
        lista.add(new SliderItem(R.drawable.laptop, "Las Mejores Laptops"));
        lista.add(new SliderItem(R.drawable.cel, "Las Mejores Marcas"));
        sliderAdapter.updateItem(lista);
        categoriaViewModel.listarCategoriasActivas().observe(getViewLifecycleOwner(), response -> {
            if(response.getRpta() == 1){
                categoriaAdapter.clear();
                categoriaAdapter.addAll(response.getBody());
                categoriaAdapter.notifyDataSetChanged();
            }else{
                System.out.println("Error al obtener las categorías activas");
            }
        });
        productoViewModel.listarProductosRecomendados().observe(getViewLifecycleOwner(),response -> {
            adapter.updateItems(response.getBody());
        });

    }

    @Override
    public void showDetails(Intent i) {
        getActivity().startActivity(i);
        getActivity().overridePendingTransition(R.anim.left_in,R.anim.left_out);
    }
}
