package br.com.cursos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class CursoApplication {

    private static final Logger logger = LoggerFactory.getLogger(CursoApplication.class);

    public static void main(String[] args) {
        logger.info("Iniciando a API de Cursos");
        SpringApplication.run(CursoApplication.class, args);
        logger.info("API para cadastro para cursos.");
    }


}
