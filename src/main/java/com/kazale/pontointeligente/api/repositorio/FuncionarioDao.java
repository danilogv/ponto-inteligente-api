package com.kazale.pontointeligente.api.repositorio;

import com.kazale.pontointeligente.api.entidade.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FuncionarioDao extends JpaRepository<Funcionario,Integer> {

    @Transactional(readOnly = true)
    Funcionario findByCpf(String cpf);

    @Transactional(readOnly = true)
    Funcionario findByEmail(String email);

    @Transactional(readOnly = true)
    Funcionario findByCpfOrEmail(String cpf, String email);

}
