package com.codandosomples.model;

import com.codandosomples.dao.DespesaDao;
/*
@author Matheus Macedo Longo
 */
public class DeletarDespesa {

    public static void main(String[] args) {
        DespesaDao dao = new DespesaDao();

        dao.delete(3l);


    }
}
