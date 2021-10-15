package com.gabriel.cursomc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabriel.cursomc.domain.Categoria;
import com.gabriel.cursomc.domain.Cidade;
import com.gabriel.cursomc.domain.Cliente;
import com.gabriel.cursomc.domain.Endereco;
import com.gabriel.cursomc.domain.Estado;
import com.gabriel.cursomc.domain.Pagamento;
import com.gabriel.cursomc.domain.PagamentoComBoleto;
import com.gabriel.cursomc.domain.PagamentoComCartao;
import com.gabriel.cursomc.domain.Pedido;
import com.gabriel.cursomc.domain.Produto;
import com.gabriel.cursomc.domain.enums.EstadoPagamento;
import com.gabriel.cursomc.domain.enums.TipoCliente;
import com.gabriel.cursomc.repositories.CategoriaRepository;
import com.gabriel.cursomc.repositories.CidadeRepository;
import com.gabriel.cursomc.repositories.ClienteRepository;
import com.gabriel.cursomc.repositories.EnderecoRepository;
import com.gabriel.cursomc.repositories.EstadoRepository;
import com.gabriel.cursomc.repositories.PagamentoRepository;
import com.gabriel.cursomc.repositories.PedidoRepository;
import com.gabriel.cursomc.repositories.ProdutoRepository;
//
@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
		
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	
	@Autowired
	private PedidoRepository pedidoRepository;

	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "Escritorio", 10000.00);
		Produto p3 = new Produto(null, "office", 200.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "uberlandia ", est1);
		Cidade c2 = new Cidade(null, "São paulo  ", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2,c3));
		
		
		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("55588888", "8787878787"));
		
		Endereco e1 = new Endereco(null, " Rua Quero", " 300", " casa", " jardim ", " 322228", c3, cli1);
		Endereco e2 = new Endereco(null, " Rua tu", " 300", " casa", " uuyyy ", " 12121", c2, cli1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("15/10/21 06:22"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("12/10/21 10:22"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/12/21 2:20"), null);
		ped2.setPagamento(pagto2);
		
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));		
		
	}

}
