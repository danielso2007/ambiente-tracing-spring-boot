package br.com.cursos.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

@Component
public class EnvLogger {

    Logger log = Logger.getLogger(EnvLogger.class.getName());

    @PostConstruct
    public void logEnvironmentVariables() {
        log.info("### VARIÁVEIS DE AMBIENTE ###");
        System.getenv().forEach((key, value) -> log.info(key + " = " + value));
    }
}
