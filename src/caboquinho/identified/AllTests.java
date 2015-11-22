package caboquinho.identified;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import caboquinho.identified.test.IdentifierTest;
import caboquinho.junit.exceptionAndParameter.ParameterizedTestFields;
import caboquinho.junit.exceptionAndParameter.TestException;

@RunWith(Suite.class)
@SuiteClasses({ IdentifierTest.class, ParameterizedTestFields.class, TestException.class })
public class AllTests {

}
