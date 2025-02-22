package com.daniel.my_crud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${datasource.customs.query-timeout}")
    private int queryTimeout;

    // Configura el DataSource utilizando los valores de 'datasource.my-connection' en el application.yml
    @Bean
    @ConfigurationProperties(prefix = "datasource.my-connection")
    public DataSource crudDataSource() {
        return DataSourceBuilder.create().build();
    }

    // Configura JdbcTemplate con el DataSource configurado y asigna el tiempo de espera de consulta
    @Bean
    public JdbcTemplate crudJdbcTemplate(DataSource crudDataSource) {
        var jdbcTemplate = new JdbcTemplate(crudDataSource);
        jdbcTemplate.setQueryTimeout(queryTimeout);  // Establece el tiempo de espera para las consultas
        return jdbcTemplate;
    }

    // Configura NamedParameterJdbcTemplate usando el JdbcTemplate configurado
    @Bean
    public NamedParameterJdbcTemplate crudNamedParameterJdbcTemplate(JdbcTemplate crudJdbcTemplate) {
        return new NamedParameterJdbcTemplate(crudJdbcTemplate);
    }
}

