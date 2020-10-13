package com.kazale.pontointeligente.api.controle;

import com.kazale.pontointeligente.api.entidade.Empresa;
import com.kazale.pontointeligente.api.entidade.Funcionario;
import com.kazale.pontointeligente.api.repositorio.EmpresaDao;
import com.kazale.pontointeligente.api.repositorio.FuncionarioDao;
import com.kazale.pontointeligente.api.utilitario.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
@CrossOrigin(origins = "*")
public class TrataFuncionario {

    @Autowired
    private FuncionarioDao funcionarioBd;

    @Autowired
    private EmpresaDao empresaBd;

    @PutMapping(value = "/{cpf}")
    public ResponseEntity<Resposta<Funcionario>> atualizar (@PathVariable String cpf, @Valid @RequestBody Funcionario funcionario, BindingResult resultado) {
        Resposta<Funcionario> resposta = new Resposta<Funcionario>();
        Funcionario func = this.funcionarioBd.findByCpf(cpf);
        if (!Optional.ofNullable(func).isPresent()) {
            resposta.setErro("Funcionário não encontrado.");
            return ResponseEntity.badRequest().body(resposta);
        }
        if (resultado.hasErrors()) {
            resposta.setErro(resultado.getFieldError().getDefaultMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
        Empresa empresa = this.empresaBd.findById(funcionario.getEmpresa().getId()).get();
        func.setNome(funcionario.getNome());
        func.setEmail(funcionario.getEmail());
        func.setValorHora(funcionario.getValorHora());
        func.setQtdHorasTrabalhadasDia(funcionario.getQtdHorasTrabalhadasDia());
        func.setQtdHorasAlmoco(funcionario.getQtdHorasAlmoco());
        func.setPerfil(funcionario.getPerfil());
        func.setEmpresa(empresa);
        this.funcionarioBd.save(func);
        resposta.setDados(func);
        return ResponseEntity.ok(resposta);
    }

}
