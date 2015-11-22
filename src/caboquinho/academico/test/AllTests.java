package caboquinho.academico.test;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import caboquinho.academico.test.AlunoTest.IntegrationTest;
import caboquinho.academico.test.AlunoTest.SlowTests;

@RunWith(Categories.class)
@IncludeCategory(SlowTests.class)
@ExcludeCategory(IntegrationTest.class)
@SuiteClasses({AlunoTest.class})
public class AllTests {

}
