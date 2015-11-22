package caboquinho.identified.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import caboquinho.identified.Identifier;

public class IdentifierTest {
	/*
	 * "Um identificador v�lido deve come�ar com uma letra e conter apenas
	 * letras ou d�gitos. Al�m disso, deve ter no m�nimo um e no m�ximo seis
	 * caracteres de comprimento."
	 */

	Identifier id;
	
	@BeforeClass
	public static void antes(){
		System.out.println("BeforeClass");
	}

	@Before
	public void precondicao() {
		id = new Identifier();
		System.out.println("Before");

	}

	@Test
	public void valid1() {

		assertTrue("O id n�o � valido come�a com n�mero", id.validateIdentifier("v1"));

	}

	@Test
	public void valid2() {
		assertTrue(id.validateIdentifier("1"));
	}

	@Test
	public void valid3() {
		assertTrue(id.validateIdentifier("abdcd1"));
	}

	@Test
	public void validaIdMaisQueSeisCaracteres() {
		assertFalse(id.validateIdentifier("aaaaaa2"));
	}

	@Test
	public void testaStringNula() {
		assertFalse(id.validateIdentifier(null));
	}

	@Test
	public void testaStringVaziaVazio() {
		assertFalse(id.validateIdentifier(""));
	}

	@Test
	public void testaStringEspacoLetra() {
		assertFalse(id.validateIdentifier("   v"));
	}

	@Test
	public void testaStringCaractereEspecial() {
		assertFalse(id.validateIdentifier("@#$%�"));
	}

	@Test
	public void testaStringSomenteLetras() {
		assertTrue(id.validateIdentifier("ABCDEF"));
	}

	@Test
	public void testaStringSomenteAcento() {
		assertFalse(id.validateIdentifier("�"));
	}
	
	@After
	public void after(){
		System.out.println("After");
	}
	
	@AfterClass
	public static void acabou(){
		System.out.println("AfterClass");
	}
}
