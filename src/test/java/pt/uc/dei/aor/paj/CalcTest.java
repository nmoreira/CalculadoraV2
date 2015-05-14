package pt.uc.dei.aor.paj;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CalcTest {

	@InjectMocks
	private Calc calc;
	
	@Mock
	private Expressao expressao;
	

	@Before
	public void firstMethod() {
		calc.setExisteVirgula(true);
		calc.setOperadorValido(true);
		calc.setParentsisAberto(true);
		calc.setVirgulaValida(true);
	}

	@Test
	public void testClearAll() {
		calc.clearAll();
		
	
		Assert.assertEquals(false, calc.isExisteVirgula());
	}
	


}