package org.example.api;

import org.example.entity.GenericResponse;
import org.example.entity.service.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductoApi {
    String base = "api/productos";

    @GET(base)
    Call<GenericResponse<List<Producto>>> listarProductosRecomendados();

    @GET(base + "/{idC}")
    Call<GenericResponse<List<Producto>>> listarProductosPorCategoria(@Path("idC") int idC);


}
