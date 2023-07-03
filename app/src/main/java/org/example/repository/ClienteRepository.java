package org.example.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.example.api.ClienteApi;
import org.example.api.ConfigApi;
import org.example.entity.GenericResponse;
import org.example.entity.service.Cliente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteRepository {
    private static ClienteRepository repository;
    private final ClienteApi api;

    public static ClienteRepository getInstance(){
        if(repository == null){
            repository = new ClienteRepository();
        }
        return repository;
    }
    private ClienteRepository(){
        api = ConfigApi.getClienteApi();
    }

    public LiveData<GenericResponse<Cliente>> guardarCliente(Cliente c){
        final MutableLiveData<GenericResponse<Cliente>> mld = new MutableLiveData<>();
        this.api.guardarCliente(c).enqueue(new Callback<GenericResponse<Cliente>>() {
            @Override
            public void onResponse(Call<GenericResponse<Cliente>> call, Response<GenericResponse<Cliente>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<Cliente>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                System.out.println("Se ha producido un error : " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mld;
    }
}