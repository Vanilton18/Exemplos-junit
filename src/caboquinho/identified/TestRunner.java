package caboquinho.identified;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import caboquinho.identified.test.IdentifierTest;
import caboquinho.junit.exceptionAndParameter.ParameterizedTestFields;
import caboquinho.junit.exceptionAndParameter.TestException;

public class TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(IdentifierTest.class,ParameterizedTestFields.class,TestException.class);
      for (Failure failure : result.getFailures()) {
    	  System.out.println(failure.getDescription());
      }
      System.out.println(result.getFailureCount()+" cts falharam");
      System.out.println(result.getRunCount()+"cts executados");
      System.out.println(result.getRunTime()+"ms" );
      System.out.println(result.wasSuccessful());
   }
} 