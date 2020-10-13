package com.kazale.pontointeligente.api.controle;

import com.kazale.pontointeligente.api.entidade.Empresa;
import com.kazale.pontointeligente.api.entidade.Funcionario;
import com.kazale.pontointeligente.api.entidade.Lancamento;
import com.kazale.pontointeligente.api.repositorio.FuncionarioDao;
import com.kazale.pontointeligente.api.repositorio.LancamentoDao;
import com.kazale.pontointeligente.api.utilitario.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamento")
@CrossOrigin(origins = "*")
public class TrataLancamento {

    @Autowired
    private LancamentoDao lancamentoBd;

    @Autowired
    private FuncionarioDao funcionarioDao;

    @GetMapping(value = "/funcionario/{funcionarioId}")
    public ResponseEntity<Resposta<List<Lancamento>>> buscarPorFuncionario (@PathVariable Integer funcionarioId) {
        Resposta<List<Lancamento>> resposta = new Resposta<List<Lancamento>>();
        if (!funcionarioDao.findById(funcionarioId).isPresent()) {
            resposta.setErro("Funcionário não encontrado");
            return ResponseEntity.badRequest().body(resposta);
        }
        Optional<List<Lancamento>> lancamentos = Optional.ofNullable(this.lancamentoBd.findByFuncionarioId(funcionarioId));
        resposta.setDados(lancamentos.get());
        return ResponseEntity.ok(resposta);
    }

    @PostMapping
    public ResponseEntity<Resposta<Lancamento>> insere (@Valid @RequestBody Lancamento lancamento, BindingResult resultado) {
        Resposta<Lancamento> resposta = new Resposta<Lancamento>();
        if (resultado.hasErrors()) {
            resposta.setErro(resultado.getFieldError().getDefaultMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
        lancamento = this.lancamentoBd.save(lancamento);
        resposta.setDados(lancamento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/funcionario/{id}").buildAndExpand(lancamento.getFuncionario().getId()).toUri();
        return ResponseEntity.created(uri).body(resposta);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Resposta<Lancamento>> atualizar (@PathVariable Integer id, @Valid @RequestBody Lancamento lancamento, BindingResult resultado) {
        Resposta<Lancamento> resposta = new Resposta<Lancamento>();
        Optional<Lancamento> lanc = this.lancamentoBd.findById(id);
        if (!lanc.isPresent()) {
            resposta.setErro("Lançamento não encontrado.");
            return ResponseEntity.badRequest().body(resposta);
        }
        if (resultado.hasErrors()) {
            resposta.setErro(resultado.getFieldError().getDefaultMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
        Funcionario funcionario = this.funcionarioDao.findById(lancamento.getFuncionario().getId()).get();
        lanc.get().setData(new Date());
        lanc.get().setDescricao(lancamento.getDescricao());
        lanc.get().setLocalizacao(lancamento.getLocalizacao());
        lanc.get().setTipo(lancamento.getTipo());
        lanc.get().setFuncionario(funcionario);
        this.lancamentoBd.save(lanc.get());
        resposta.setDados(lanc.get());
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Resposta<Lancamento>> remover (@PathVariable Integer id) {
        Resposta<Lancamento> resposta = new Resposta<Lancamento>();
        if (!this.lancamentoBd.findById(id).isPresent()) {
            resposta.setErro("Lançamento não encontrado.");
            return ResponseEntity.badRequest().body(resposta);
        }
        this.lancamentoBd.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
