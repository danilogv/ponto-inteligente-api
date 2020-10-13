package com.kazale.pontointeligente.api.repositorio;

import com.kazale.pontointeligente.api.entidade.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;

@NamedQueries(
        {
                @NamedQuery(name = "LancamentoDao.findByFuncionario", query = "SELECT l FROM Lancamento l WHERE l.funcionario.id = :funcionarioId")
        }
)
public interface LancamentoDao extends JpaRepository<Lancamento,Integer> {

    @Transactional(readOnly = true)
    List<Lancamento> findByFuncionarioId(@Param("funcionarioId") Integer funcionarioId);

}
