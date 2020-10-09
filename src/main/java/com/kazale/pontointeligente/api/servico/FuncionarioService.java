package com.kazale.pontointeligente.api.servico;

import com.kazale.pontointeligente.api.entidade.Funcionario;
import com.kazale.pontointeligente.api.repositorio.FuncionarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioDao funcionarioBd;

    public Funcionario persistir(Funcionario funcionario) {
        funcionario = this.funcionarioBd.save(funcionario);
        return funcionario;
    }

    public Optional<Funcionario> buscarPorCpf(String cpf) {
        Funcionario funcionario = this.funcionarioBd.findByCpf(cpf);
        return Optional.ofNullable(funcionario);
    }

    public Optional<Funcionario> buscarPorEmail(String email) {
        Funcionario funcionario = this.funcionarioBd.findByEmail(email);
        return Optional.ofNullable(funcionario);
    }

    public Optional<Funcionario> buscarPorId(Integer id) {
        Optional<Funcionario> funcionario = this.funcionarioBd.findById(id);
        return funcionario;
    }

}
