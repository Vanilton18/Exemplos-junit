package caboquinho.servicos.test;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import caboquinho.servicos.test.ServicoClienteTest.SlowTests;

@RunWith(Categories.class)
@IncludeCategory(SlowTests.class)
@SuiteClasses(ServicoClienteTest.class)
public class ServicoAllTest {

}
