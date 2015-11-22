package caboquinho.academico.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

import caboquinho.academico.Aluno;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlunoTest {

	public interface SlowTests {}
	public interface IntegrationTest extends SlowTests {}

	Aluno aluno = new Aluno();

	
	@Category(IntegrationTest.class)
	@Test
	public void test1Aprovado() {
		aluno.setNota1(7.0);
		aluno.setNota2(7.0);
		aluno.setNota3(7.0);
		aluno.gerarMedia();
		assertEquals("Aprovado", aluno.getStatus());
	}

	@Category(SlowTests.class)
	@Test
	public void test2RecuperandoNotaAprovado() {
		aluno.setNota1(7.0);
		assertEquals(7.0, aluno.getNota1(), 0);
		aluno.setNota2(7.0);
		assertEquals(7.0, aluno.getNota2(), 0);
		aluno.setNota3(7.0);
		assertEquals(7.0, aluno.getNota3(), 0);
		aluno.gerarMedia();
		assertEquals(7.0, aluno.getMedia(), 0);

		assertEquals("Aprovado", aluno.getStatus());
	}
	
	@Category({IntegrationTest.class,SlowTests.class})
	@Test
	public void test3Reprovado() {
		aluno.setNota1(3.0);
		aluno.setNota2(3.0);
		aluno.setNota3(3.0);
		aluno.gerarMedia();
		assertEquals("Reprovado", aluno.getStatus());
	}

	@Test
	public void test4AlunoProvaFinal() {
		aluno.setNota1(6.0);
		aluno.setNota2(6.0);
		aluno.setNota3(6.5);
		aluno.gerarMedia();
		assertEquals("Prova Final", aluno.getStatus());

	}

	@Test
	public void test5ReprovadoProvaFinal() {
		aluno.setNota1(5.0);
		aluno.setNota2(5.0);
		aluno.setNota3(5.0);
		aluno.gerarMedia();
		aluno.setProvaFinal(3.0);
		assertTrue(aluno.getStatus().equals("Reprovado Prova Final"));

	}

	@Test
	public void test6AprovadoProvaFinal() {
		aluno.setNota1(5.0);
		aluno.setNota2(5.0);
		aluno.setNota3(5.0);
		aluno.gerarMedia();
		aluno.setProvaFinal(5.0);
		assertTrue(aluno.getStatus().equals("Aprovado Prova Final"));

	}
}