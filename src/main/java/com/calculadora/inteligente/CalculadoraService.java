package com.calculadora.inteligente;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CalculadoraService {

    private Mes[] meses = new Mes[12];

    public CalculadoraService() {
        meses[0] = new Mes("Janeiro");
        meses[1] = new Mes("Fevereiro");
        meses[2] = new Mes("Março");
        meses[3] = new Mes("Abril");
        meses[4] = new Mes("Maio");
        meses[5] = new Mes("Junho");
        meses[6] = new Mes("Julho");
        meses[7] = new Mes("Agosto");
        meses[8] = new Mes("Setembro");
        meses[9] = new Mes("Outubro");
        meses[10] = new Mes("Novembro");
        meses[11] = new Mes("Dezembro");

    }

    public void adicionarGasto(String nome, double valor, int parcela, int indiceMes) {
        Gasto gasto = new Gasto(nome, valor, parcela);
        meses[indiceMes].adicionarGasto(gasto);

        for (int i = 1; i < parcela; i++) {
            if (indiceMes + i < 12) {
                Gasto novoGasto = new Gasto(nome, valor, parcela);

                for (int j = 0; j < i; j++) {
                    novoGasto.avancarParcela();
                }

                meses[indiceMes + i].adicionarGasto(novoGasto);
            }
        }
    }

    public List<Gasto> obterGastosDoMes(int indiceMes) {
        return meses[indiceMes].getGastos();
    }


}
