package com.batch.progettofinale.writers;

import com.batch.progettofinale.model.Cliente;
import jakarta.annotation.PostConstruct;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBWriter implements ItemWriter<Cliente> {

    private String sqlCreateTable;
    private String sqlInsert;

    private final JdbcTemplate jdbcTemplate;

    public DBWriter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void inizializzazione() {
        sqlCreateTable = "CREATE TABLE IF NOT EXISTS clienti (" +
                "nome VARCHAR(255), " +
                "saldo DECIMAL(10, 2), " +
                "anno INT, " +
                "email VARCHAR(255))";

        sqlInsert = "INSERT INTO clienti (nome, saldo, anno, email) " +
                "VALUES (?, ?, ?, ?)";
    }

    @Override
    public void write(Chunk<? extends Cliente> clienti) throws Exception {
        // Crea la tabella (solo la prima volta)
        jdbcTemplate.update(sqlCreateTable);
        jdbcTemplate.update("TRUNCATE TABLE clienti");

        // Scrivi nella tabella H2
        for (Cliente cliente : clienti) {
            jdbcTemplate.update(sqlInsert,
                    cliente.getNome(),
                    cliente.getSaldo(),
                    cliente.getAnno(),
                    cliente.getEmail());
        }
    }
}
