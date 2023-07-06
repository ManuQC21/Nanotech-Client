package org.example.viewmodel;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.example.entity.GenericResponse;
import org.example.entity.service.Producto;
import org.example.repository.ProductoRepository;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ProductoViewModel extends AndroidViewModel {

    private final ProductoRepository repository;

    public ProductoViewModel(@NotNull Application application) {
        super(application);
        repository = ProductoRepository.getInstance();
    }


    public LiveData<GenericResponse<List<Producto>>> listarProductosRecomendados(){
        return this.repository.listarProductosRecomendados();
    }
    public LiveData<GenericResponse<List<Producto>>> listarProductosPorCategoria(int idC){
        return this.repository.listarProductosPorCategoria(idC);
    }
}
