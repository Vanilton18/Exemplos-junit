package caboquinho.academico;

public class Aluno {

	private Double nota1;
	private Double nota2;
	private Double nota3;
	private double media;
	private String status = "";

	public Double getNota1() {
		return nota1;
	}

	public void setNota1(Double nota1) {
		this.nota1 = nota1;
	}

	public Double getNota2() {
		return nota2;
	}

	public void setNota2(Double nota2) {
		this.nota2 = nota2;
	}

	public Double getNota3() {
		return nota3;
	}

	public void setNota3(Double nota3) {
		this.nota3 = nota3;
	}

	public Double getMedia() {
		return media;
	}

	public void gerarMedia() {
		this.media = (nota1 + nota2 + nota3) / 3;
		setStatus();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus() {
		if (media < 4) {
			status = "Reprovado";
		} else if (media >= 7) {
			status = "Aprovado";
		} else {
			status = "Prova Final";
		}
	}

	public void setProvaFinal(Double provaFinal) {
		if ((media >= 4) && (media < 7)) {
			Double notaFinal = (media + provaFinal) / 2;
			if (notaFinal < 5) {
				status = "Reprovado Prova Final";
			} else {
				status = "Aprovado Prova Final";
			}
			media = notaFinal;
		}
	}

}