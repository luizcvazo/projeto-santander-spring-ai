package com.calculadora.inteligente;

public class Gasto {

    private final String nome;
    private final double valor;
    private final int parcela;
    private int parcelaAtual;

    public Gasto(String nome, double valor, int parcela) {
        this.nome = nome;
        this.valor = valor;
        this.parcela = parcela;
        this.parcelaAtual = 1;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public int getParcelas() {
        return parcela;
    }

    public int getParcelaAtual() {
        return parcelaAtual;
    }

    public void avancarParcela() {
        parcelaAtual++;
    }
}

