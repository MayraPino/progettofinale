package com.batch.progettofinale.writers;

import com.batch.progettofinale.model.Cliente;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class CsvWriter implements ItemWriter<Cliente> {

    private Map<Integer, FileWriter> yearFileWriters = new HashMap<>();

    @Value("${output.directory}")
    private String outputDirectory;  // Per configurare la directory di output per i file CSV

    @PostConstruct
    public void inizializzazione() {
        // Verifica che la directory di output esista, altrimenti crea la directory
        File dir = new File(outputDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Override
    public void write(Chunk<? extends Cliente> clienti) throws Exception {
        // Scrivi nei file CSV separati per anno
        for (Cliente cliente : clienti) {
            // Ottieni il writer per l'anno
            FileWriter fileWriter = getFileWriterForYear(cliente.getAnno());

            // Formatta il saldo come stringa con due decimali
            String saldoFormatted = cliente.getSaldo().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();

            // Scrivi i dati nel file CSV
            fileWriter.append(cliente.getNome()).append(",")
                    .append(saldoFormatted).append(",")
                    .append(String.valueOf(cliente.getAnno())).append(",")
                    .append(cliente.getEmail()).append("\n");
        }
    }

    private FileWriter getFileWriterForYear(int year) throws IOException {
        // Se non esiste già un file writer per questo anno, creane uno
        if (!yearFileWriters.containsKey(year)) {
            FileWriter fileWriter = new FileWriter(outputDirectory + "/output_clienti_" + year + ".csv", true);
            fileWriter.append("Nome, Saldo, Anno, Email\n"); // Aggiungi intestazione
            yearFileWriters.put(year, fileWriter);
        }
        return yearFileWriters.get(year);
    }

    // Assicurati di chiudere i file quando finisce il job
    public void closeWriters() throws IOException {
        for (FileWriter writer : yearFileWriters.values()) {
            writer.close();
        }
    }
}
