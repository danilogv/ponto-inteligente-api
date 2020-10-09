package com.kazale.pontointeligente.api.repositorio;

import com.kazale.pontointeligente.api.entidade.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;

@NamedQueries(
        {
                @NamedQuery(name = "LancamentoDAO.findByFuncionario", query = "SELECT l FROM Lancamento l WHERE l.funcionario.id = :funcionario_id")
        }
)
public interface LancamentoDao extends JpaRepository<Lancamento,Integer> {

    @Transactional(readOnly = true)
    List<Lancamento> findByFuncionario(@Param("funcionarioId") Integer funcionarioId);

    @Transactional(readOnly = true)
    Page<Lancamento> findByFuncionario(@Param("funcionarioId") Integer funcionarioId, Pageable pagina);

}
