package org.example.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import org.example.entity.GenericResponse;
import org.example.entity.service.dto.GenerarPedidoDTO;
import org.example.entity.service.dto.PedidoConDetallesDTO;
import org.example.repository.PedidoRepository;
import java.util.List;

public class PedidoViewModel extends AndroidViewModel {
    private final PedidoRepository repository;

    public PedidoViewModel(@NonNull Application application) {
        super(application);
        this.repository = PedidoRepository.getInstance();
    }
    public LiveData<GenericResponse<List<PedidoConDetallesDTO>>> listarPedidosPorCliente(int idCli){
        return this.repository.listarPedidosPorCliente(idCli);
    }
    public LiveData<GenericResponse<GenerarPedidoDTO>> guardarPedido(GenerarPedidoDTO dto){
        return repository.save(dto);
    }
}