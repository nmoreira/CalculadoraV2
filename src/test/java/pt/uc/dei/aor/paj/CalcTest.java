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
	private Entrada entrada ;
	@Mock
	private Input input;
	

	@Test
	public void testClearAllconditionVirgula() {
		calc.setExisteVirgula(true);	
		calc.clearAll();
	
		Assert.assertEquals(false, calc.isExisteVirgula());
	}
	
	@Test
	public void testClearALLconditionOperador() {
		calc.setOperadorValido(false);
		calc.clearAll();
	
		Assert.assertEquals(true, calc.isOperadorValido());
	}
	
	@Test
	public void testInsertOperadorAfterClearLastOperator(){
		
		
	
		calc.setExp("24*");
		Mockito.when(expressao.peekLastInput()).thenReturn(input);
		Mockito.when(input.getTipo()).thenReturn("op");
		
		Mockito.when(expressao.remove()).thenReturn("24");
		calc.clearLast();
		
		Assert.assertEquals(true, calc.isOperadorValido());
		
	}
	
	
	@Test
	public void testInsertOperadorAfterClearLastDigit(){
		
		calc.setExp("24-2");
		Mockito.when(expressao.peekLastInput()).thenReturn(input);
		Mockito.when(input.getTipo()).thenReturn("nm");
	    Mockito.when(expressao.remove()).thenReturn("24-");	

		calc.clearLast();
		
		Assert.assertEquals(false, calc.isOperadorValido());
		
	}
	
	@Test
	public void testClearLastDigit(){
		
		calc.setExp("24");
		Mockito.when(expressao.peekLastInput()).thenReturn(input);
		Mockito.when(input.getTipo()).thenReturn("nm");
	    Mockito.when(expressao.remove()).thenReturn("");	

		calc.clearLast();
		
		Assert.assertEquals("", calc.getExp());
		
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
