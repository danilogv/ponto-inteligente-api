package com.kazale.pontointeligente.api.utilitario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Senha {

    private static final Logger log = LoggerFactory.getLogger(Senha.class);

    public static String gerarBCrypt(String senha) {
        if (senha == null)
            return senha;
        log.info("Gerando hash com o BCrypt");
        BCryptPasswordEncoder hash = new BCryptPasswordEncoder();
        return hash.encode(senha);
    }


}
