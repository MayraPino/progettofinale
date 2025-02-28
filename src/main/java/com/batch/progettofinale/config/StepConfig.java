package com.batch.progettofinale.config;
import com.batch.progettofinale.processors.SaldoFiltroProcessor;
import com.batch.progettofinale.model.Cliente;
import com.batch.progettofinale.readers.CsvReader;
import com.batch.progettofinale.writers.CsvWriter;
import com.batch.progettofinale.writers.DBWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class StepConfig {

    @Autowired
    CsvWriter csvWriter;

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;


    @Autowired
    @Qualifier("saldoFiltroProcessor")
    private SaldoFiltroProcessor saldoFiltroProcessor;




    @Autowired
    @Qualifier("dbwriter")
    private DBWriter DBWriter;

    @Autowired
    @Qualifier("csvReader")
    private CsvReader csvReader;

    @Bean("csvToDbStep")
    public Step csvToDbStep() {
        return new StepBuilder("csvToDbStep", jobRepository)
                .<Cliente, Cliente>chunk(10, platformTransactionManager)
                .reader(csvReader)
                .processor(saldoFiltroProcessor)
                .writer(DBWriter)
                .build();

    }


     //Step per scrivere i dati nei file CSV separati per anno
    @Bean("csvToFileStep")
    public Step csvToFileStep() {
       return new StepBuilder("csvToFileStep", jobRepository)
                .<Cliente, Cliente>chunk(10, platformTransactionManager)
               .reader(csvReader)
                .processor(saldoFiltroProcessor)
                .writer(csvWriter)  // Scrittura nei file CSV separati per anno
                .build();
    }


}







