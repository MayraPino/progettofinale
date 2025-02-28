package com.batch.progettofinale.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class Cliente {

    private String nome;
    private BigDecimal saldo;
    private int anno;
    private String email;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
