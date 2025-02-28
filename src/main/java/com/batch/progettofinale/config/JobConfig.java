package com.batch.progettofinale.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    @Autowired
    @Qualifier("csvToDbStep")
    private Step csvToDbStep;

    @Autowired
    @Qualifier("csvToFileStep")
    private Step csvToFileStep;

    @Autowired
    private JobRepository jobRepository;


    @Bean("job1")
    public Job job1(){
        return new JobBuilder("job1", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(csvToDbStep)
                .next(csvToFileStep)
                .build();


    }


}
