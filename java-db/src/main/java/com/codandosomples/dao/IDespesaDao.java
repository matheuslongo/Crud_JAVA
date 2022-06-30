package com.codandosomples.dao;

import com.codandosomples.model.Categoria;
import com.codandosomples.model.Despesa;

import java.util.List;
import java.util.Optional;
/*
@author Matheus Macedo Longo
 */
public interface IDespesaDao {
     Despesa save(Despesa despesa);
     Despesa update(Despesa despesa);
     void delete (Long id);
     List<Despesa> findAll();
     Optional<Despesa> findById(Long Optional);
    List<Despesa> findByCategoria(Categoria categoria);


}
