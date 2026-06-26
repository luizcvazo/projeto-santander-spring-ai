package com.calculadora.inteligente;

import java.util.ArrayList;

public class Mes {

    String nome;
    ArrayList<Gasto> gastos;

    public Mes(String nome){
        this.nome = nome;
        this.gastos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Gasto> getGastos(){
        return gastos;
    }

    public void adicionarGasto(Gasto gasto) {
        gastos.add(gasto);
    }

    public void zerarGastos() {
        gastos.clear();
    }
}
