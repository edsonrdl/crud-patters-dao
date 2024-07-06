package com.crudpatternsdao.crudpatternsdao.application.usecase.clienteusecase;

import com.crudpatternsdao.crudpatternsdao.infrastructure.Model.ClientEntity;

public interface IClientUseCase {
    ClientEntity save(ClientEntity clientEntity);
}
