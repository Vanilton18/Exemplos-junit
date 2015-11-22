package caboquinho.junit.exceptionAndParameter;

import org.junit.Test;

public class TestException {

	MyClass test = new MyClass();
	
	/*
	 * A multiplicação utilizada pelo método multiply da class MyClass
	 * 	deve retornar uma exceção IllegalArgumentException caso o valor de x seja maior que 999.
	 * e caso y seja maior que 50 será lançado uma ArithmeticException
	 */
	
   @Test(expected = IllegalArgumentException.class)
   public void testExceptionX() throws Exception {	
   
	   test.multiply(1000, 20);
	   
   }

   @Test(expected= ArithmeticException.class)
   public void testExceptionY() throws Exception {	
	   test.multiply(999, 80);

   }
   
}