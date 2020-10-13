package com.kazale.pontointeligente.api.controle;

import com.kazale.pontointeligente.api.entidade.Empresa;
import com.kazale.pontointeligente.api.repositorio.EmpresaDao;
import com.kazale.pontointeligente.api.utilitario.Resposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/empresa")
@CrossOrigin(origins = "*")
public class TrataEmpresa {

    @Autowired
    private EmpresaDao empresaBd;

    @GetMapping(value = "/{cnpj}")
    public ResponseEntity<Resposta<Empresa>> buscarPorCnpj (@PathVariable String cnpj) {
        Resposta<Empresa> resposta = new Resposta<Empresa>();
        Optional<Empresa> empresa = Optional.ofNullable(this.empresaBd.findByCnpj(cnpj));
        if (!empresa.isPresent()) {
            resposta.setErro("Empresa não encontrada para o CNPJ " + cnpj);
            return ResponseEntity.badRequest().body(resposta);
        }
        resposta.setDados(empresa.get());
        return ResponseEntity.ok(resposta);
    }

    @PostMapping
    public ResponseEntity<Resposta<Empresa>> insere (@Valid @RequestBody Empresa empresa, BindingResult resultado) {
        Resposta<Empresa> resposta = new Resposta<Empresa>();
        Optional<Empresa> emp = Optional.ofNullable(this.empresaBd.findByCnpj(empresa.getCnpj()));
        if (emp.isPresent()) {
            resposta.setErro("Empresa já cadastrada");
            return ResponseEntity.badRequest().body(resposta);
        }
        if (resultado.hasErrors()) {
            resposta.setErro(resultado.getFieldError().getDefaultMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
        empresa = this.empresaBd.save(empresa);
        resposta.setDados(empresa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cnpj}").buildAndExpand(empresa.getCnpj()).toUri();
        return ResponseEntity.created(uri).body(resposta);
    }

}
