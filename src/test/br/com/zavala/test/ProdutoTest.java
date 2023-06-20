package br.com.zavala.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.zavala.dao.ProdutoDAO;
import br.com.zavala.dao.IProdutoDAO;
import br.com.zavala.domain.Produto;

public class ProdutoTest {

	private IProdutoDAO produtoDao;
	
	@Test
	public void cadastrarTest() throws Exception {
		produtoDao = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setSku(100L);
		produto.setDescricao("Produto 100");
		produto.setUnidade("Pacote");
		produto.setValor_unitario(12.3);
		//cadastrar
		Integer regCadastro = produtoDao.cadastrar(produto);
		Assert.assertTrue(regCadastro == 1);
		//buscar
		Produto produtoBD = produtoDao.buscar(100L);
		Assert.assertNotNull(produtoBD);
		Assert.assertEquals(produto.getDescricao(),produtoBD.getDescricao());
		//excluir
		Integer regDel = produtoDao.excluir(produtoBD);
		Assert.assertTrue(regDel == 1);
	}
	
	@Test
	public void buscarTest() throws Exception {
		produtoDao = new ProdutoDAO();
		
		//Busca produto 100 (não existe ainda)
		Produto produtoBD = produtoDao.buscar(100L);
		Assert.assertNull(produtoBD);
		//cadastra cliente 100
		Produto produto = new Produto();
		produto.setSku(100L);
		produto.setDescricao("Produto 100");
		produto.setUnidade("Unidade");
		produto.setValor_unitario(45.76);
		Integer countCad = produtoDao.cadastrar(produto);
		Assert.assertTrue(countCad == 1);
		//busca produto 100
		produtoBD = produtoDao.buscar(100L);
		Assert.assertNotNull(produtoBD);
		Assert.assertEquals(produto.getSku(), produtoBD.getSku());
		Assert.assertEquals(produto.getDescricao(), produtoBD.getDescricao());
		//deleta cliente
		Integer countDel = produtoDao.excluir(produtoBD);
		Assert.assertTrue(countDel == 1);
	}
	
	@Test
	public void excluirTest() throws Exception {
		produtoDao = new ProdutoDAO();
		//cadastrar produto 300
		Produto produto = new Produto();
		produto.setSku(300L);
		produto.setDescricao("Produto 300");
		produto.setUnidade("Unidade");
		produto.setValor_unitario(77.90);
		Integer countCad = produtoDao.cadastrar(produto);
		Assert.assertTrue(countCad == 1);
		//buscar produto 300
		Produto produtoBD = produtoDao.buscar(300L);
		Assert.assertNotNull(produtoBD);
		Assert.assertEquals(produto.getSku(), produtoBD.getSku());
		Assert.assertEquals(produto.getDescricao(), produtoBD.getDescricao());
		//excluir produto 300
		Integer countDel = produtoDao.excluir(produtoBD);
		Assert.assertTrue(countDel == 1);
	}
	
	@Test
	public void buscarTodosTest() throws Exception {
		produtoDao = new ProdutoDAO();
		//cadastrar produtos
		Produto produto = new Produto();
		produto.setSku(100L);
		produto.setDescricao("Produto 100");
		produto.setUnidade("Unidade");
		produto.setValor_unitario(21.45);
		Integer countCad = produtoDao.cadastrar(produto);
		Assert.assertTrue(countCad == 1);
		
		Produto produto2 = new Produto();
		produto2.setSku(200L);
		produto2.setDescricao("Produto 200");
		produto2.setUnidade("Unidade");
		produto2.setValor_unitario(776.98);
		Integer countCad2 = produtoDao.cadastrar(produto2);
		Assert.assertTrue(countCad2 == 1);
		//
		List<Produto> list = produtoDao.buscarTodos();
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());  
		
		int countDel = 0;
		for (Produto prod : list) {
			produtoDao.excluir(prod);
			countDel++;
		}
		Assert.assertEquals(list.size(), countDel);
		
		list = produtoDao.buscarTodos();
		Assert.assertEquals(list.size(), 0);	
	}
	
	@Test
	public void atualizarTest() throws Exception {
		produtoDao = new ProdutoDAO();
		//cadastrar produto
		Produto produto = new Produto();
		produto.setSku(100L);
		produto.setDescricao("Produto 100");
		produto.setUnidade("Unidade");
		produto.setValor_unitario(12.34);
		Integer countCad = produtoDao.cadastrar(produto);
		Assert.assertTrue(countCad == 1);
		//Buscar produto
		Produto produtoBD = produtoDao.buscar(100L);
		Assert.assertNotNull(produtoBD);
		Assert.assertEquals(produto.getSku(), produtoBD.getSku());
		Assert.assertEquals(produto.getDescricao(), produtoBD.getDescricao());
		//atualizar produto
		produtoBD.setSku(200L);
		produtoBD.setDescricao("Produto 200");
		produtoBD.setUnidade("Outra unidade");
		Integer countUpdate = produtoDao.atualizar(produtoBD);
		Assert.assertTrue(countUpdate == 1);
		//buscar produto com dados antigos
		Produto produtoBD1 = produtoDao.buscar(100L);
		Assert.assertNull(produtoBD1);
		//buscar produto com dados atualizados
		Produto produtoBD2 = produtoDao.buscar(200L);
		Assert.assertNotNull(produtoBD2);
		//compara dados
		Assert.assertEquals(produtoBD.getSku(), produtoBD2.getSku());
		Assert.assertEquals(produtoBD.getDescricao(), produtoBD2.getDescricao());
		
		List<Produto> list = produtoDao.buscarTodos();
		for (Produto prod : list) {
			produtoDao.excluir(prod);
		}
		
	}
	
	
}
