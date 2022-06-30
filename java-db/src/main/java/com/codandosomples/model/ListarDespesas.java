package com.codandosomples.model;

import com.codandosomples.dao.DespesaDao;
import java.util.List;
/*
@author Matheus Macedo Longo
 */
public class ListarDespesas {

    public static void main(String[] args) {
        DespesaDao dao = new DespesaDao();
        List<Despesa> despesas = dao.findByCategoria(Categoria.TRANSPORTE);
        for (Despesa despesa : despesas) {
            System.out.println("ID: " + despesa.getId());
            System.out.println("Categoria: "+ despesa.getCategoria());
            System.out.println("Descrição: " + despesa.getDescricao());
            System.out.println("Valor: " + despesa.getValor());

//        Optional<Despesa> despesaOptional = dao.findById(2L);
//        despesaOptional.ifPresent(despesa -> {
//            System.out.println("ID: " + despesa.getId());
//            System.out.println("Descrição: " + despesa.getDescricao());
//            System.out.println("Valor: " + despesa.getValor());
//                });

            //FindALL
            //      DespesaDao dao = new DespesaDao();
            //      List<Despesa> despesas = dao.findAll();
            //
            //      for (Despesa despesa : despesas){
            //          System.out.println("ID " + despesa.getId());
            //          System.out.println("Descrição: " + despesa.getDescricao());
            //      }

            //ADD
            // Despesa despesa = new Despesa();
            //  despesa.setDescricao("Mercado");
            //  despesa.setCategoria(Categoria.ALIMENTACAO);
            //  despesa.setValor(80);
            // despesa.setData(LocalDate.of(2021,4,12));
            //  Despesa despesaInserida = dao.save(despesa);
            //  System.out.println("Foi inserida a despesa com ID " + despesaInserida.getId() );
        }
    }
}
