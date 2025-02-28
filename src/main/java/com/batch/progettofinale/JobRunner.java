package com.batch.progettofinale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class JobRunner implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JobRunner.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger(JobRunner.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("job1")
    private Job job1;

    @Override
    public void run(String... args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(job1, jobParameters);
    }

}


