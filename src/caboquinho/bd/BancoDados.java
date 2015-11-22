package caboquinho.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import caboquinho.entidades.Cliente;

public class BancoDados {
	public String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	public String protocol = "jdbc:derby:";
	private Connection conn;
	private String nomeBancoDados = "derby";

	/**
	 * Cria as conexoes e iniciar o Derby DB
	 * 
	 * @param nomeBancoDados
	 *            - Nome do banco de dados
	 * 
	 **/
	public void criarBancoDados(String nomeBancoDados) {

		try {
			Class.forName(driver).newInstance();

			Properties props = new Properties();

			conn = DriverManager.getConnection(protocol + nomeBancoDados + ";create=true", props);
			conn.setAutoCommit(false);
			this.nomeBancoDados = nomeBancoDados;

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	public void criarTabela(String nomeTabela, List<String> listaColunas) {
		Statement s = null;

		try {
			if (conn == null) {
				criarBancoDados(nomeBancoDados);
			}
			s = conn.createStatement();

			String colunas = "(";
			ListIterator<String> i = listaColunas.listIterator();
			while (i.hasNext()) {
				colunas += i.next();

				if (i.hasNext())
					colunas += ",";
			}

			colunas += ")";

			String query = " create table " + nomeTabela + colunas;
			System.out.println("Query executada: " + query);
			s.execute(query);

			// criando a tabela cliente
			// s.execute("create table cliente(cpf varchar(11), nome
			// varchar(50),conta varchar(10), idade int)");
			// s.execute("create table serasa(cpf varchar(11))");
			// System.out.println("Criado a tabela cliente e serasa");

		} catch (SQLException e) {

		} finally {

			try {
				s.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public void deletarTabela(String tabela) throws InterruptedException {
		Statement s = null;

		try {
			if (conn == null) {
				criarBancoDados(nomeBancoDados);
			}
			s = conn.createStatement();

			// criando a tabela cliente
			s.execute("drop table " + tabela);

			System.out.println("Deletando a tabela cliente");
			
			conn.commit();

		} catch (SQLException e) {

		} finally {

			try {
				s.close();

			} catch (SQLException e) {
			}

		}
	}

	public void editarClientes(Cliente cliente) {
		Statement s = null;
		PreparedStatement psUpdate = null;

		try {
			if (conn == null) {
				criarBancoDados(nomeBancoDados);
			}
			s = conn.createStatement();

			psUpdate = conn.prepareStatement("UPDATE cliente SET nome=?,conta=?, idade=? WHERE cpf=?");

			psUpdate.setString(1, cliente.getNome());
			psUpdate.setString(2, cliente.getConta());
			psUpdate.setInt(3, cliente.getIdade());
			psUpdate.setString(4, cliente.getCpf());
			psUpdate.executeUpdate();

			conn.commit();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				s.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				psUpdate.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public void removerClientes(String cpf) {
		Statement s = null;
		PreparedStatement psUpdate = null;

		try {
			if (conn == null) {
				criarBancoDados(nomeBancoDados);
			}
			s = conn.createStatement();

			psUpdate = conn.prepareStatement("DELETE FROM cliente WHERE cpf=?");
			psUpdate.setString(1, cpf);
			psUpdate.executeUpdate();

			conn.commit();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				s.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				psUpdate.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public void inserirCliente(Cliente cliente) {
		Statement s = null;
		PreparedStatement psInsert = null;

		try {
			if (conn == null) {
				criarBancoDados(nomeBancoDados);
			}
			s = conn.createStatement();

			psInsert = conn.prepareStatement("insert into cliente values (?, ?,?,?)");

			psInsert.setString(1, cliente.getCpf());
			psInsert.setString(2, cliente.getNome());
			psInsert.setString(3, cliente.getConta());
			psInsert.setInt(4, cliente.getIdade());

			psInsert.executeUpdate();

			conn.commit();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				s.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				psInsert.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public List<Cliente> listarClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		ResultSet rs = null;
		Statement s = null;
		try {

			if (conn == null) {
				criarBancoDados(nomeBancoDados);
			}

			s = conn.createStatement();

			rs = s.executeQuery(" SELECT cpf, nome,conta,idade FROM cliente");

			while (rs.next()) {

				clientes.add(new Cliente(rs.getString(2), rs.getString(1), rs.getString(3), rs.getInt(4)));

			}

		} catch (SQLException e) {
		}

		return clientes;

	}

	public List<String> getListaSerasa() {
		List<String> cpfs = new ArrayList<String>();
		ResultSet rs = null;
		Statement s = null;
		try {

			if (conn == null) {
				criarBancoDados(nomeBancoDados);
			}

			s = conn.createStatement();

			rs = s.executeQuery(" SELECT cpf FROM serasa");

			while (rs.next()) {

				cpfs.add(rs.getString(1));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		finalizar();
		return cpfs;

	}

	public void finalizar() {

		try {
			// the shutdown=true attribute shuts down Derby
			DriverManager.getConnection("jdbc:derby:;shutdown=true");

			// To shut down a specific database only, but keep the
			// engine running (for example for connecting to other
			// databases), specify a database in the connection URL:
			// DriverManager.getConnection("jdbc:derby:" + dbName +
			// ";shutdown=true");
		} catch (SQLException se) {
			if (((se.getErrorCode() == 50000) && ("XJ015".equals(se.getSQLState())))) {
				// we got the expected exception
				System.out.println("Derby finalizou normalmente.");
				// Note that for single database shutdown, the expected
				// SQL state is "08006", and the error code is 45000.
			} else {
				// if the error code or SQLState is different, we have
				// an unexpected exception (shutdown failed)
				System.err.println("Derby n�o finalizou normalmente.");

			}
		}
	}

	public Cliente encontrarCliente(String cpfCliente) {
		Cliente cliente = null;
		ResultSet rs = null;
		Statement s = null;
		try {

			if (conn == null) {
				criarBancoDados(nomeBancoDados);
			}

			s = conn.createStatement();

			rs = s.executeQuery(" SELECT cpf, nome,conta,idade FROM cliente WHERE cpf = '" + cpfCliente + "'");

			if (rs.next()) {

				cliente = new Cliente(rs.getString(2), rs.getString(1), rs.getString(3), rs.getInt(4));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return cliente;
	}

}
