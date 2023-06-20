package br.com.zavala.dao;

import java.util.List;

import br.com.zavala.domain.Produto;

public interface IProdutoDAO {

	public Integer cadastrar(Produto produto) throws Exception;
	
	public Integer atualizar(Produto produto) throws Exception;
	
	public Produto buscar(Long sku) throws Exception;
	
	public List<Produto> buscarTodos() throws Exception;
	
	public Integer excluir(Produto produto) throws Exception;
}
