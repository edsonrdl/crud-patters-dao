package com.testeadmissao.testeadmissao.application.usecase.clienteusecase;
import java.util.List;
import org.springframework.stereotype.Service;
import com.testeadmissao.testeadmissao.domain.interfaces.useCases.IGenericDAO;
import com.testeadmissao.testeadmissao.infrastructure.dao.ClientDAOImpl;
import com.testeadmissao.testeadmissao.infrastructure.model.ClientEntity;

import jakarta.transaction.Transactional;


@Service
public class ClientUseCase implements IClientUseCase{

    private final ClientDAOImpl _ClientDAOImpl;
   
    public ClientUseCase(ClientDAOImpl  clientDAOImpl) {
        this._ClientDAOImpl = clientDAOImpl;
    }

    // @Override
    // public ClientEntity findById(Long id) {
    //     return clientDAO.findById(id);
    // }

    // @Override
    // public List<ClientEntity> findAll() {
    //     return clientDAO.findAll();
    // }

    @Transactional
    public ClientEntity save(ClientEntity clientEntity) {
        return _ClientDAOImpl.save(clientEntity);
    }
 
    // @Override
    // public void update(ClientEntity clientEntity) {
    //     clientDAO.update(clientEntity);
    // }
    

//     @Override
//     public void delete(Long id) {
//         clientDAO.delete(id);
//     }
}
