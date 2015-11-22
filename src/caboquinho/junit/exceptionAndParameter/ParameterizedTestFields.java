package caboquinho.junit.exceptionAndParameter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class ParameterizedTestFields {

	// Campos usados pelos parametros
	@Parameter
	public int m1;
	@Parameter(value = 1)
	public int m2;
	@Parameter(value = 2)
	public int m3;

	// Criando os dados do Teste
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { 1, 2, 3 }, { 5, 3, 4 }, { 121, 4, 4 }, { 1000, 1, 4 } };
		return Arrays.asList(data);
	}

	/*Testa o conjuntos de dados retornado no array, para cada conjunto será executado um teste */
	@Test
	public void testMultiplyException() {
		MyClass tester = new MyClass();
		assertEquals("Result", m1 * m2 * m3, tester.multiply(m1, m2, m3));
	}

	//Class a ser testada
	class MyClass {
		public int multiply(int i, int j, int k) {
			return i * j * k;
		}
	}

}