package com.batch.progettofinale.processors;

import com.batch.progettofinale.model.Cliente;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;


public class SaldoFiltroProcessor implements ItemProcessor<Cliente, Cliente> {
    @Override
    public Cliente process(Cliente cliente) throws Exception {
        // Confronta il saldo con 1000 usando BigDecimal
        BigDecimal limiteSaldo = new BigDecimal("1000");  // Usa BigDecimal per rappresentare 1000

        if (cliente.getSaldo().compareTo(limiteSaldo) >1000) {
            return cliente;  // Mantieni solo i clienti con saldo > 1000
        }
        return null;  // Filtra quelli con saldo <= 1000
    }
    }

