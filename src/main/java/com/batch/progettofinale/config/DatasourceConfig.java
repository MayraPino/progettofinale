package com.batch.progettofinale.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import org.h2.jdbcx.JdbcConnectionPool;


@Configuration
public class DatasourceConfig {

    @Value("${spring.datasource.url}")
    String h2url;

    @Bean("datasource")
    /*@ConfigurationProperties(prefix = "spring.datasource")
    @Primary*/
    public DataSource dataSource() {
        JdbcConnectionPool connectionPool = JdbcConnectionPool.create(h2url, "sa", "");

        return connectionPool;
    }

   // public DataSource dataSource() {
     //   return DataSourceBuilder.create()
       //         .url("jdbc:h2:mem:testdb") // Sostituisci con la tua URL di connessione
         //       .driverClassName("org.h2.Driver") // Sostituisci con il tuo driver
           //     .username("sa") // Sostituisci con il tuo nome utente
             //   .password("password") // Sostituisci con la tua password
               // .build();



    @Bean("jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("datasource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }




}
