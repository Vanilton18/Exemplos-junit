package caboquinho.entidades;

public class Cliente {
	
	private String nome;
	private String cpf;
	private String conta;
	private int idade;
	
		
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public Cliente(String nome, String cpf, String conta, int idade) {
		this.nome = nome;
		this.cpf = cpf;
		this.conta= conta;
		this.idade = idade;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@Override
	public String toString() {
	  
	   return "Nome: "+nome+" Cpf: "+cpf+" conta: "+conta+" Idade: "+idade ;
	}
	 
}
