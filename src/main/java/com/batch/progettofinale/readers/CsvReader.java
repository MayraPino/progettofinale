package com.batch.progettofinale.readers;

import com.batch.progettofinale.model.Cliente;
import jakarta.annotation.PostConstruct;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class CsvReader  extends FlatFileItemReader<Cliente>{

    public CsvReader(Resource filePath) {
        this.filePath = filePath;
    }

    @Value("${csv.input-file}")
    private final Resource filePath;

    @PostConstruct
    public void inizializzazione(){
        setResource(filePath);
        setLinesToSkip(1);
        DefaultLineMapper<Cliente> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(";");
        tokenizer.setNames("nome", "saldo", "anno", "email");
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
            setTargetType(Cliente.class);
        }});
        setLineMapper(lineMapper);

    }

    @Override
    public Cliente read() throws Exception {
        return super.read();
    }






    }


