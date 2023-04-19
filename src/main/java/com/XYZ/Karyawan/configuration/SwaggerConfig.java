package com.XYZ.Karyawan.configuration;

import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Value("${server.port:8080}")
    private int serverPort;
    @Bean
    public OpenAPI userServiceApi(@Value("Swagger UI for Final Project Virtual Internship Program") String appDescription,
                                  @Value("v1.0.0") String appVersion
    ) {
        Server server = new Server();
        server.setUrl("http://localhost:8080/");
        List<Server> listOfServer = new ArrayList<>();
        listOfServer.add(server);

        return new OpenAPI()
                .info(new Info()
                        .title("AEON x Rakamin Virtual Internship Program ")
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService("https://github.com/lathief")
                ).servers(listOfServer);
    }
}
