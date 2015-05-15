package pt.uc.dei.aor.paj;


import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CalcTest {

	@InjectMocks
	private Calc calc;
	

	
	private Entrada entrada ;
	@Mock
	private Input input;
	
	@Mock
	private Estatistica est;
	
	@Mock
	private Historico hist;
	

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
		calc.read("num2");
		calc.read("num4");
		calc.read("mult");
		calc.clearLast();
		Assert.assertEquals(true, calc.isOperadorValido());	
	}
	
	
	@Test
	public void testInsertOperadorAfterClearLastDigit(){
		calc.read("num2");
		calc.read("num4");
		calc.read("sub");
		calc.read("num2");
		calc.clearLast();
		Assert.assertEquals(true, calc.isOperadorValido());
	}
	
	@Test
	public void testClearLastDigit(){
		calc.read("num2");
		calc.read("num4");
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
	
	@Test
	public void testRead(){
		calc.read("num1");
		Assert.assertEquals("1", calc.getMostrador());
	}
	
	@Test
	public void testTanRad(){
		calc.setRadianos(false);
		calc.read("tan");
		calc.read("num1");
		calc.read("num0");
		calc.read("fechapar");
		calc.read("igual");
		Assert.assertEquals("0.17632698070846498", calc.getMostrador());
	}
	
	
}
