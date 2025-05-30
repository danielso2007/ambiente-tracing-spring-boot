package br.com.cursos.infrastructure.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

@Component
public class EnvLogger {

    Logger log = Logger.getLogger(EnvLogger.class.getName());

    /**
     * Apenas para testes da aplciação. Não ir para produção.
     */
    @PostConstruct
    public void logEnvironmentVariables() {
        log.info("### VARIÁVEIS DE AMBIENTE ###");
        System.getenv().forEach((key, value) -> log.info(key + " = " + value));
    }
}
