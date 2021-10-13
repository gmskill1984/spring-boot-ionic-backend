package com.gabriel.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.cursomc.domain.Produto;
//teste
@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

}
