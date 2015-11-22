package caboquinho.junit.asserts;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import caboquinho.identified.Identifier;

public class TestAssertions {

	// test data
	Identifier id1 = new Identifier();
	Identifier id2 = new Identifier();
	String str1 = new String("abc");
	String str2 = new String("abc");
	String str3 = null;
	String str4 = "abc";
	String str5 = "abc";
	int val1 = 5;
	int val2 = 6;
	String[] expectedArray = { "one", "two", "three" };
	String[] resultArray = { "one", "two", "three" };

	@Test
	public void testAssertivaEqualsObjetos() {
		assertEquals(id1, id2);
	}

	@Test
	public void testAssertivacomEquals() {
		// Verificando se strings sao iguais
		assertEquals(str1, str2);
	}

	@Test
	public void testAssertivacomTrue() {
		// Verifica condição verdadeira
		assertTrue(val1 < val2);
	}

	@Test
	public void testAssertivacomFalse() {
		// Verifica condição falsa
		assertFalse(val1 > val2);
	}

	@Test
	public void testNaoNulo() {
		// Verifica se o objeto não � nulo
		assertNotNull(str1);
	}

	@Test
	public void testNulo() {
		// Verifica se o objeto é nulo
		assertNull(str3);
	}

	@Test
	public void testReferenciaTipoObjetos() {
		// Verifique se duas refer�ncias de objeto apontar para o mesmo objeto e
		// tipo
		assertSame(str4, str5);
	}

	@Test
	public void testInreferenciaObjetos() {
		// Verifique se duas refer�ncias de objeto apontam para o mesmo objeto
		// Verifique se duas refer�ncias de objeto não apontam para o mesmo
		// objeto
		assertNotSame(str1, str5);
	}

	@Test
	public void testArrays() {
		// Verifica os valores de dois arrays s�o iguais
		assertArrayEquals(expectedArray, resultArray);
	}

}