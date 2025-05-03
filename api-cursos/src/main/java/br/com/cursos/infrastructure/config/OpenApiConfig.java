package br.com.cursos.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${info.app.version}")
    private String apiVersion;

    @Bean
    public OpenAPI apiInfo() {
        Contact contact = new Contact();
        contact.setName("Daniel Oliveira");
        contact.setUrl("https://github.com/danielso2007/ambiente-tracing-spring-boot");
        contact.setEmail("danielso2007@gmail.com");

        License license = new License();
        license.setName("Apache 2.0");
        license.setUrl("http://www.apache.org/licenses/LICENSE-2.0.html");

        Server dev = new Server();
        dev.setUrl("http://localhost:8080");
        dev.setDescription("DEV Server");

        Server prod = new Server();
        prod.setUrl("http://localhost:8080");
        prod.setDescription("PROD Server");

        List<Server> servers = new ArrayList<>();
        servers.add(dev);
        servers.add(prod);

        Components components = new Components();

        return new OpenAPI()
                .components(components)
                .info(new Info()
                        .title("API de Cursos")
                        .description("API para o gerenciamento de cursos.")
                        .version(apiVersion)
                        .termsOfService("https://smartbear.com/terms-of-use/")
                        .contact(contact)
                        .license(license))
                .servers(servers);
    }

}
