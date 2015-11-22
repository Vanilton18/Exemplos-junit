package caboquinho.servicos.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import caboquinho.entidades.Cliente;
import caboquinho.servicos.ServicoCliente;
import caboquinho.util.CpfUtil;

public class ServicoClienteTest {
	public interface SlowTests {
	}

	static ServicoCliente servico;
	Cliente cliente;

	@BeforeClass
	public static void criarBanco() {
		servico = new ServicoCliente();
		servico.getBd().criarBancoDados("teste");

	}

	@Before
	public void setUp() throws Exception {
		servico = new ServicoCliente();
		List<String> colunas = new ArrayList<String>();
		colunas.add("cpf varchar(11)");
		colunas.add("nome varchar(50)");
		colunas.add("conta varchar(10)");
		colunas.add("idade int");
		servico.getBd().criarTabela("cliente", colunas);

	}

	@Category(SlowTests.class)
	@Test
	public void test1Inserir() {
		Cliente cliente = new Cliente("Nome do Cliente", "99999999999", "0001", 30);
		servico.inserir(cliente);
		Assert.assertFalse(servico.listarCliente().isEmpty());
		Assert.assertEquals(servico.listarCliente().get(0).getCpf(), "99999999999");

	}

	@Category(SlowTests.class)
	@Test
	public void testEditar() {

		Cliente cliente1 = new Cliente("Paulo", "11122233349", "15355189", 24);
		servico.inserir(cliente1);
		cliente1.setNome("leroi");
		servico.editar(cliente1);
		Assert.assertEquals("leroi", servico.listarCliente().get(0).getNome());

	}

	@Category(SlowTests.class)
	@Test()
	public void testRemover() {
		Cliente cliente = new Cliente("Edgar", "62462462400", "624", 23);
		servico.inserir(cliente);
		servico.remover("62462462400");
		Assert.assertTrue(servico.listarCliente().isEmpty());

	}

	@Category(SlowTests.class)
	@Test
	public void testListar1000Cliente() {
		for (int i = 0; i < 1000; i++) {
			Cliente cliente = new Cliente("Fulano" + i, CpfUtil.geraCPF(), "" + i, i);
			servico.inserir(cliente);
		}
		int count = 0;
		for (Cliente c : servico.listarCliente()) {
			System.out.println(c.toString() + "\n");
			count++;
		}
		Assert.assertTrue(count == 1000);
	}

	@Category(SlowTests.class)
	@Test
	public void testPesquisaCliente() {
		for (int i = 0; i < 1000; i++) {
			Cliente cliente = new Cliente("Fulano" + i, String.valueOf(i), "" + i, i);
			servico.inserir(cliente);
		}

		Assert.assertEquals("Fulano800", servico.listarCliente().get(800).getNome());
		Assert.assertEquals("Fulano800", servico.encontrarCliente("800").getNome());
	}

	@After
	public void tearDown() throws Exception {
		removerClientes();
		servico.getBd().finalizar();
		servico = null;

	}

	public void removerClientes() {

		for (Cliente cliente : servico.listarCliente()) {
			servico.remover(cliente.getCpf());
		}

	}

}
