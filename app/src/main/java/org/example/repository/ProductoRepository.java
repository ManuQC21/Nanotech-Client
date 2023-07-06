package org.example.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.example.api.ConfigApi;
import org.example.api.ProductoApi;
import org.example.entity.GenericResponse;
import org.example.entity.service.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoRepository {
    private final ProductoApi api;
    private static ProductoRepository repository;

    public ProductoRepository() {
        this.api = ConfigApi.getProductoApi();
    }

    public static ProductoRepository getInstance() {
        if (repository == null) {
            repository = new ProductoRepository();
        }
        return repository;
    }

    public LiveData<GenericResponse<List<Producto>>> listarProductosRecomendados(){
        final MutableLiveData<GenericResponse<List<Producto>>> mld = new MutableLiveData<>();
        this.api.listarProductosRecomendados().enqueue(new Callback<GenericResponse<List<Producto>>>() {
            @Override
            public void onResponse(Call<GenericResponse<List<Producto>>> call, Response<GenericResponse<List<Producto>>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<List<Producto>>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                t.printStackTrace();
            }
        });
        return mld;
    }

    public LiveData<GenericResponse<List<Producto>>> listarProductosPorCategoria(int idC){
        final MutableLiveData<GenericResponse<List<Producto>>> mld = new MutableLiveData<>();
        this.api.listarProductosPorCategoria(idC).enqueue(new Callback<GenericResponse<List<Producto>>>() {
            @Override
            public void onResponse(Call<GenericResponse<List<Producto>>> call, Response<GenericResponse<List<Producto>>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<List<Producto>>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                t.printStackTrace();
            }
        });
        return mld;
    }
}