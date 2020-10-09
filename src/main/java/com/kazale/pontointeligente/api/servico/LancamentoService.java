package com.kazale.pontointeligente.api.servico;

import com.kazale.pontointeligente.api.entidade.Lancamento;
import com.kazale.pontointeligente.api.repositorio.LancamentoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoDao lancamentoBd;

    public Page<Lancamento> buscarPorFuncionario(Integer funcionarioId, PageRequest paginacao) {
        Page<Lancamento> lancamento = this.lancamentoBd.findByFuncionario(funcionarioId, paginacao);
        return lancamento;
    }

    public Optional<Lancamento> buscarPorId(Integer id) {
        Optional<Lancamento> lancamento = this.lancamentoBd.findById(id);
        return lancamento;
    }

    public Lancamento persistir(Lancamento lancamento) {
        lancamento = this.lancamentoBd.save(lancamento);
        return lancamento;
    }

    public void remover(Integer id) {
        this.lancamentoBd.deleteById(id);
    }

}
