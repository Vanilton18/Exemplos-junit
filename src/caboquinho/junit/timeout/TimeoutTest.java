package caboquinho.junit.timeout;

import org.junit.Test;

public class TimeoutTest {

	/* Verifico se o teste não custa mais que 2 milissegundos*/
	@Test(timeout = 2000)
	public void testTimeout() throws InterruptedException {
		Thread.sleep(3000);
	}

}
