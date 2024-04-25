package com.testeadmissao.testeadmissao.application.useCases.clientUseCases;

import com.testeadmissao.testeadmissao.infrastructure.model.ClientEntity;

public interface IClientUseCase {
    ClientEntity save(ClientEntity clientEntity);
}
