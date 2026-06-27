package com.calculadora.inteligente;

public record GastosRequest(
        String nome,
        double valor,
        int parcela,
        int indiceMes
) {

}
