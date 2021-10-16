package com.gabriel.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.cursomc.domain.Pedido;
import com.gabriel.cursomc.repositories.PedidoRepository;
//
@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	
	public Pedido buscar(Integer id) {
		
		Optional<Pedido> obj = repo.findById(id); 
		return obj.orElse(null);
		
	}

}