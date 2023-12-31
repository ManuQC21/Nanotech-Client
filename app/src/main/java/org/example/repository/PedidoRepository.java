package org.example.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import org.example.api.ConfigApi;
import org.example.api.PedidoApi;
import org.example.entity.GenericResponse;
import org.example.entity.service.dto.GenerarPedidoDTO;
import org.example.entity.service.dto.PedidoConDetallesDTO;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidoRepository {
    private final PedidoApi api;
    private static PedidoRepository repository;

    public PedidoRepository() {
        this.api = ConfigApi.getPedidoApi();
    }

    public static PedidoRepository getInstance() {
        if (repository == null) {
            repository = new PedidoRepository();
        }
        return repository;
    }

    public LiveData<GenericResponse<List<PedidoConDetallesDTO>>> listarPedidosPorCliente(int idCli){
        final MutableLiveData<GenericResponse<List<PedidoConDetallesDTO>>> mld = new MutableLiveData<>();
        this.api.listarPedidosPorCliente(idCli).enqueue(new Callback<GenericResponse<List<PedidoConDetallesDTO>>>() {
            @Override
            public void onResponse(Call<GenericResponse<List<PedidoConDetallesDTO>>> call, Response<GenericResponse<List<PedidoConDetallesDTO>>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<List<PedidoConDetallesDTO>>> call, Throwable t) {
                mld.setValue(new GenericResponse());
                System.out.println("Error al obtener los pedidos: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mld;
    }
    public LiveData<GenericResponse<GenerarPedidoDTO>> save(GenerarPedidoDTO dto) {
        MutableLiveData<GenericResponse<GenerarPedidoDTO>> data = new MutableLiveData<>();
        api.guardarPedido(dto).enqueue(new Callback<GenericResponse<GenerarPedidoDTO>>() {
            @Override
            public void onResponse(Call<GenericResponse<GenerarPedidoDTO>> call, Response<GenericResponse<GenerarPedidoDTO>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GenericResponse<GenerarPedidoDTO>> call, Throwable t) {
                data.setValue(new GenericResponse<>());
                t.printStackTrace();
            }
        });
        return data;
    }
}