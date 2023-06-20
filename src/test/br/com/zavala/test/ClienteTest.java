package br.com.zavala.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.zavala.dao.ClienteDAO;
import br.com.zavala.dao.IClienteDAO;
import br.com.zavala.domain.Cliente;

public class ClienteTest {
	
	private IClienteDAO clienteDao;
	
	@Test
	public void cadastrarTest() throws Exception {
		clienteDao = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCpf("10");
		cliente.setNome("Ramiro Arce");
		cliente.setCidade("Recife");
		cliente.setEstado("PE");
		Integer countCad = clienteDao.cadastrar(cliente);
		Assert.assertTrue(countCad == 1);
		
		Cliente clienteBD = clienteDao.buscar("10");
		Assert.assertNotNull(clienteBD);
		//Assert.assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		Assert.assertEquals(cliente.getCpf(), clienteBD.getCpf());
		Assert.assertEquals(cliente.getNome(), clienteBD.getNome());
		
		Integer countDel = clienteDao.excluir(clienteBD);
		Assert.assertTrue(countDel == 1);
	}
	
	@Test
	public void buscarTest() throws Exception {
		clienteDao = new ClienteDAO();
		
		//Busca cliente 10 (não existe ainda)
		Cliente clienteBD = clienteDao.buscar("10");
		Assert.assertNull(clienteBD);
		//cadastra cliente 10
		Cliente cliente = new Cliente();
		cliente.setCpf("10");
		cliente.setNome("Ramiro Arce");
		cliente.setCidade("Recife");
		cliente.setEstado("PE");
		Integer countCad = clienteDao.cadastrar(cliente);
		Assert.assertTrue(countCad == 1);
		//busca cliente 10
		clienteBD = clienteDao.buscar("10");
		Assert.assertNotNull(clienteBD);
		Assert.assertEquals(cliente.getCpf(), clienteBD.getCpf());
		Assert.assertEquals(cliente.getNome(), clienteBD.getNome());
		//deleta cliente
		Integer countDel = clienteDao.excluir(clienteBD);
		Assert.assertTrue(countDel == 1);
	}
	
	@Test
	public void excluirTest() throws Exception {
		clienteDao = new ClienteDAO();
		//cadastrar cliente 20
		Cliente cliente = new Cliente();
		cliente.setCpf("20");
		cliente.setNome("Ramiro Arce");
		cliente.setCidade("Recife");
		cliente.setEstado("PE");
		Integer countCad = clienteDao.cadastrar(cliente);
		Assert.assertTrue(countCad == 1);
		//buscar cliente 20
		Cliente clienteBD = clienteDao.buscar("20");
		Assert.assertNotNull(clienteBD);
		Assert.assertEquals(cliente.getCpf(), clienteBD.getCpf());
		Assert.assertEquals(cliente.getNome(), clienteBD.getNome());
		//excluir cliente 20
		Integer countDel = clienteDao.excluir(clienteBD);
		Assert.assertTrue(countDel == 1);
	}
	
	@Test
	public void buscarTodosTest() throws Exception {
		clienteDao = new ClienteDAO();
		//cadastrar clientes
		Cliente cliente = new Cliente();
		cliente.setCpf("10");
		cliente.setNome("Ramiro Zavala");
		cliente.setCidade("Recife");
		cliente.setEstado("PE");
		Integer countCad = clienteDao.cadastrar(cliente);
		Assert.assertTrue(countCad == 1);
		
		Cliente cliente2 = new Cliente();
		cliente2.setCpf("20");
		cliente2.setNome("Ramiro Arce");
		cliente2.setCidade("Recife");
		cliente2.setEstado("PE");
		Integer countCad2 = clienteDao.cadastrar(cliente2);
		Assert.assertTrue(countCad2 == 1);
		//
		List<Cliente> list = clienteDao.buscarTodos();
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());  
		
		int countDel = 0;
		for (Cliente cli : list) {
			clienteDao.excluir(cli);
			countDel++;
		}
		Assert.assertEquals(list.size(), countDel);
		
		list = clienteDao.buscarTodos();
		Assert.assertEquals(list.size(), 0);	
	}
	
	@Test
	public void atualizarTest() throws Exception {
		clienteDao = new ClienteDAO();
		//cadastrar cliente
		Cliente cliente = new Cliente();
		cliente.setCpf("10");
		cliente.setNome("Ramiro Arce");
		cliente.setCidade("Recife");
		cliente.setEstado("PE");
		Integer countCad = clienteDao.cadastrar(cliente);
		Assert.assertTrue(countCad == 1);
		//Buscar cliente
		Cliente clienteBD = clienteDao.buscar("10");
		Assert.assertNotNull(clienteBD);
		Assert.assertEquals(cliente.getCpf(), clienteBD.getCpf());
		Assert.assertEquals(cliente.getNome(), clienteBD.getNome());
		//atualizar cliente
		clienteBD.setCpf("20");
		clienteBD.setNome("Outro nome");
		Integer countUpdate = clienteDao.atualizar(clienteBD);
		Assert.assertTrue(countUpdate == 1);
		//buscar cliente com dados antigos
		Cliente clienteBD1 = clienteDao.buscar("10");
		Assert.assertNull(clienteBD1);
		//buscar cliente com dados atualizados
		Cliente clienteBD2 = clienteDao.buscar("20");
		Assert.assertNotNull(clienteBD2);
		//compara dados
		Assert.assertEquals(clienteBD.getCodigo(), clienteBD2.getCodigo());
		Assert.assertEquals(clienteBD.getCpf(), clienteBD2.getCpf());
		Assert.assertEquals(clienteBD.getNome(), clienteBD2.getNome());
		
		List<Cliente> list = clienteDao.buscarTodos();
		for (Cliente cli : list) {
			clienteDao.excluir(cli);
		}
		
	}

}
