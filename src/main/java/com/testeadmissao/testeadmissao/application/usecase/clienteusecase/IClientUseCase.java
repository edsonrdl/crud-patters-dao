package com.testeadmissao.testeadmissao.application.usecase.clienteusecase;

import com.testeadmissao.testeadmissao.infrastructure.model.ClientEntity;

public interface IClientUseCase {
    ClientEntity save(ClientEntity clientEntity);
}
