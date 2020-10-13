package com.kazale.pontointeligente.api.utilitario;

import java.util.ArrayList;
import java.util.List;

public class Resposta<T> {

    private T dados;
    private String erro;

    public T getDados() {
        return this.dados;
    }

    public void setDados(T dados) {
        this.dados = dados;
    }

    public String getErro() {
        return this.erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}
