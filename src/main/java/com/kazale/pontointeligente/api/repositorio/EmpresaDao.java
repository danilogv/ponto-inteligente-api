package com.kazale.pontointeligente.api.repositorio;

import com.kazale.pontointeligente.api.entidade.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EmpresaDao extends JpaRepository<Empresa,Integer> {

    @Transactional(readOnly = true)
    Empresa findByCnpj(String cnpj);

}
