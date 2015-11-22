package caboquinho.junit.exceptionAndParameter;

public class MyClass {
	  public int multiply(int x, int y) throws Exception {
	    if (x > 999) {
	      throw new IllegalArgumentException("X é um inteiro e deve ser menor que 1000");
	    } else if (y > 50){
	    	throw new ArithmeticException("Y é um inteiro e deve ser menor que 50");
	    }
	    return x / y;
	  }
	} 
