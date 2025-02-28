package com.batch.progettofinale.config;

import com.batch.progettofinale.processors.SaldoFiltroProcessor;
import com.batch.progettofinale.readers.CsvReader;
import com.batch.progettofinale.writers.CsvWriter;
import com.batch.progettofinale.writers.DBWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class BeansConfig {

    @Value("${csv.input-file}")
    private Resource filepath;

    @Qualifier("jdbcTemplate")
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean("csvReader")
    public CsvReader csvReader() {
        return new CsvReader(filepath);
    }

    @Bean("saldoFiltroProcessor")
    public SaldoFiltroProcessor saldoFiltroProcessor() {
        return new SaldoFiltroProcessor();
    }

    @Bean("dbWriter")
    public DBWriter dbWriter() {
        return new DBWriter(jdbcTemplate);
    }

    @Bean("csvWriter")
    public CsvWriter csvWriter() {
        return new CsvWriter();
    }
}
