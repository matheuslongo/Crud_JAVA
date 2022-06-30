package com.codandosomples.model;

import com.codandosomples.dao.DespesaDao;

import java.time.LocalDate;
import java.util.Optional;
/*
@author Matheus Macedo Longo
 */
public class AtualizarDespesa {
    public static void main(String[] args) {
        DespesaDao dao = new DespesaDao();
        Optional<Despesa> despesaOptional = dao.findById(5L);

        Despesa despesa = despesaOptional.get();
        System.out.println(despesa.getId());
        System.out.println(despesa.getDescricao());
        System.out.println(despesa.getData());
        System.out.println(despesa.getValor());
        System.out.println(despesa.getCategoria());

        despesa.setDescricao("Gasto com Dentista");
        despesa.setData(LocalDate.of(2021,6,14));
        despesa.setValor(250);
        despesa.setCategoria(Categoria.OUTROS);

        dao.update(despesa);

    }
}
