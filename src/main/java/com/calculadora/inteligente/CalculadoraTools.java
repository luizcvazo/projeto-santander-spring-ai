package com.calculadora.inteligente;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalculadoraTools {

    private final CalculadoraService calculadoraService;

    public CalculadoraTools(CalculadoraService calculadoraService) {
        this.calculadoraService = calculadoraService;
    }

    @Tool(name = "adicionar_gasto", description = "Cadastra um novo gasto ou despesa. Parametros: nome do gasto, valor, quantidade de parcelas (use 1 para a vista) e indiceMes onde Janeiro=0 e Dezembro=11.")
    public String adicionarGasto(String nome, double valor, int parcela, int indiceMes) {
        calculadoraService.adicionarGasto(nome, valor, parcela, indiceMes);
        return "Gasto adicionado com sucesso.";
    }

    @Tool(name = "obter_gastos_do_mes", description = "Obtem a lista de todos os gastos cadastrados em um mes especifico. Parametro indiceMes: Janeiro=0 e Dezembro=11.")
    public List<Gasto> obterGastosDoMes(int indiceMes) {
        return calculadoraService.obterGastosDoMes(indiceMes);
    }
}
