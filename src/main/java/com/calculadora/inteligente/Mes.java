package com.calculadora.inteligente;

import java.util.ArrayList;
import java.util.List;

public class Mes {

    private final String nome;
    private final ArrayList<Gasto> gastos;

    public Mes(String nome) {
        this.nome = nome;
        this.gastos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void adicionarGasto(Gasto gasto) {
        gastos.add(gasto);
    }

    public void zerarGastos() {
        gastos.clear();
    }
}
