package com.kazale.pontointeligente.api.entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kazale.pontointeligente.api.enumeracao.Perfil;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    @NotEmpty(message = "Nome não pode ser vazio.")
    @Length(max = 255, message = "Nome deve conter no máximo 255 caracteres")
    private String nome;

    @Column(name = "email", nullable = false)
    @NotEmpty(message = "Email não pode ser vazio.")
    @Length(max = 255, message = "Email deve conter no máximo 255 caracteres")
    @Email(message="Email inválido")
    private String email;

    @Column(name = "cpf", nullable = false)
    @NotEmpty(message = "CPF não pode ser vazio")
    @Length(min = 11, max = 11, message = "CPF deve conter 11 caracteres")
    private String cpf;

    @Column(name = "valor_hora", nullable = true)
    private BigDecimal valorHora;

    @Column(name = "qtd_horas_trabalhadas_dia", nullable = true)
    private Double qtdHorasTrabalhadasDia;

    @Column(name = "qtd_horas_almoco", nullable = true)
    private Double qtdHorasAlmoco;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    @NotEmpty(message = "Perfil não pode ser vazio")
    @Length(max = 255, message = "Perfil deve conter no máximo 255 caracteres")
    private Perfil perfil;

    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private Date dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @JsonIgnore
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<Lancamento> lancamentos;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getValorHora() {
        return this.valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }

    public Double getQtdHorasTrabalhadasDia() {
        return this.qtdHorasTrabalhadasDia;
    }

    public void setQtdHorasTrabalhadasDia(Double qtdHorasTrabalhadasDia) {
        this.qtdHorasTrabalhadasDia = qtdHorasTrabalhadasDia;
    }

    public Double getQtdHorasAlmoco() {
        return this.qtdHorasAlmoco;
    }

    public void setQtdHorasAlmoco(Double qtdHorasAlmoco) {
        this.qtdHorasAlmoco = qtdHorasAlmoco;
    }

    public Perfil getPerfil() {
        return this.perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Date getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAtualizacao() {
        return this.dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Lancamento> getLancamentos() {
        return this.lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    @PreUpdate
    public void alteraData() {
        this.dataAtualizacao = new Date();
    }

    @PrePersist
    public void insereData() {
        Date dataAtual = new Date();
        this.dataCriacao = dataAtual;
        this.dataAtualizacao = dataAtual;
    }

}
