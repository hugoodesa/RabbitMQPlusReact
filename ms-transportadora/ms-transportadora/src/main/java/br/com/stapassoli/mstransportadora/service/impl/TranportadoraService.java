package br.com.stapassoli.mstransportadora.service.impl;

import br.com.stapassoli.mstransportadora.service.BasicService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TranportadoraService extends BasicService {
    protected TranportadoraService(JpaRepository repository) {
        super(repository);
    }
}
