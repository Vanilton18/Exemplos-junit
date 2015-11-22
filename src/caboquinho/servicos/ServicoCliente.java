package caboquinho.servicos;

import java.util.List;

import caboquinho.bd.BancoDados;
import caboquinho.entidades.Cliente;

public class ServicoCliente {

	private BancoDados bd;

	public BancoDados getBd() {
		return bd;
	}

	public void setBd(BancoDados bd) {
		this.bd = bd;
	}

	public ServicoCliente() {
		bd = new BancoDados();
	}

	public void inserir(Cliente cliente) {
		bd.inserirCliente(cliente);
	}

	public void editar(Cliente cliente) {
		bd.editarClientes(cliente);
	}

	public void remover(String cpf) {

		bd.removerClientes(cpf);
	}

	public List<Cliente> listarCliente() {

		return bd.listarClientes();
	}

	public Cliente encontrarCliente(String cpfCliente) {
		return bd.encontrarCliente(cpfCliente);
	}

}
