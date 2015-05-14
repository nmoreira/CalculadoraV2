package pt.uc.dei.aor.paj;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CalcTest {

	@InjectMocks
	private Calc calc;
	@Mock
	private Expressao expressao;
	private Entrada entrada;
	
	

	@Test
	public void testClearVirgula() {
		calc.setExisteVirgula(true);	
		calc.clearAll();
	
		Assert.assertEquals(false, calc.isExisteVirgula());
	}
	
	@Test
	public void testClearOperador() {
		calc.setOperadorValido(false);
		calc.clearAll();
	
		Assert.assertEquals(true, calc.isOperadorValido());
	}
	
	@Test
	public void testClearLastDigit(){
		
	    Mockito.when(expressao.remove()).thenReturn("2");	
		calc.setExp("24");
		calc.clearLast();
		
		Assert.assertEquals("2", calc.getExp());
		
	}
	
	@Test
	public void anotherTestClearLastDigit(){
		
		calc.setExp("");
		calc.clearLast();
		
		Assert.assertEquals("", calc.getExp());		
	}
	
	@Test
	public void testReUseExpReturnsMostrador(){
		
		ArrayList<Input> input = new ArrayList<Input>();
		entrada = new Entrada("20+4","24",input ,"0.05");
		calc.reUseExp(entrada);
		
		Assert.assertEquals("20+4", calc.getExp());			
	}
	
	@Test
	public void testReUseResReturnsMostrador(){
		
		ArrayList<Input> input = new ArrayList<Input>();
		entrada = new Entrada("25*9","225",input ,"0.05");
		calc.reUseResult(entrada);
		
		Assert.assertEquals("225", calc.getExp());			
	}
	
	
	
}
