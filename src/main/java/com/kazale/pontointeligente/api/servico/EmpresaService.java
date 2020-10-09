package com.kazale.pontointeligente.api.servico;

import com.kazale.pontointeligente.api.entidade.Empresa;
import com.kazale.pontointeligente.api.repositorio.EmpresaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaDao empresaBd;

    public Optional<Empresa> buscarPorCnpj(String cnpj) {
        Empresa empresa = this.empresaBd.findByCnpj(cnpj);
        return Optional.ofNullable(empresa);
    }

    public Empresa persistir(Empresa empresa) {
        empresa = this.empresaBd.save(empresa);
        return empresa;
    }
}
