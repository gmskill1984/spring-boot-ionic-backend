package com.gabriel.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import com.gabriel.cursomc.domain.Categoria;
import com.gabriel.cursomc.repositories.CategoriaRepository;
import com.gabriel.cursomc.services.exception.DataIntegrityException;
import com.gabriel.cursomc.services.exception.ObjectNotFoundException;


//
@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	
	public Categoria find(Integer id) {
		
		Optional<Categoria> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				
				
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		
	}
	
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
     find(obj.getId());
	return repo.save(obj);
}	
	
	public void delete (Integer id ) {
		
		find(id);
		try {
		repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não e possivel excluir");
		}
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	
	
	public Page<Categoria> findPage(Integer page, Integer linesPage, String orderby, String direction ){
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderby);
		
		return repo.findAll(pageRequest);		
		
		
	}
}
