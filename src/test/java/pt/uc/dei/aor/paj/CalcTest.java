package pt.uc.dei.aor.paj;


import java.util.ArrayList;


import org.junit.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
	
	@Test
	public void testParentsis(){
		calc.read("abrepar");
		calc.read("num1");
		calc.read("num0");
		calc.read("fechapar");
		Assert.assertEquals(" (10) ", calc.getMostrador());
	}
	
	@Test
	public void testTrocaSinal(){
		calc.read("num1");
		calc.read("num0");
		calc.read("maismenos");
		Assert.assertEquals("-10", calc.getMostrador());
	}
	
	 @Test
	    public void testSoma() {
	        calc.read("num1");
	        calc.read("soma");
	        calc.read("num2");
	        calc.read("igual");
	        Assert.assertEquals("3", calc.getMostrador());
	    }
	    
	    @Test
	    public void testSubtracao() {
	        calc.read("num3");
	        calc.read("sub");
	        calc.read("num2");
	        calc.read("igual");
	        Assert.assertEquals("1", calc.getMostrador());
	    }
	    
	    @Test
	    public void testDivisao() {
	        calc.read("num4");
	        calc.read("div");
	        calc.read("num2");
	        calc.read("igual");
	        Assert.assertEquals("2", calc.getMostrador());
	    }
	    
	    @Test
	    public void testMultiplicacao() {
	        calc.read("num4");
	        calc.read("mult");
	        calc.read("num2");
	        calc.read("igual");
	        Assert.assertEquals("8", calc.getMostrador());
	    }
	    
	    @Test
	    public void testDivisaoZero() {
	        calc.read("num4");
	        calc.read("div");
	        calc.read("num0");
	        calc.read("igual");
	        Assert.assertEquals("erros na expressao", calc.getMostrador());
	    }
	    
	    @Test
	    public void testOperadoresMultiplos() {
	        calc.read("num4");
	        calc.read("div");
	        calc.read("mult");
	        calc.read("igual");
	        Assert.assertEquals("4 / ", calc.getMostrador());
	    }
	    
	    @Test
	    public void testRaizNegativa() {
	        calc.read("raizq");
	        calc.read("sub");
	        calc.read("num4");
	        calc.read("fechapar");
	        calc.read("igual");
	        Assert.assertEquals("NaN", calc.getMostrador());
	    }
	    
	    @Test
		public void testFatorial(){
			calc.read("num3");
			calc.read("num6");
			calc.read("fact");
			calc.read("igual");
			assertThat(calc.getMostrador(), is("3.719933267899012E41"));	
		}
	    
	    @Test
		public void testXExpY(){
			calc.read("num3");
			calc.read("num6");
			calc.read("xexpy");
			calc.read("num4");
			calc.read("igual");
			assertThat(calc.getMostrador(), is("1679616"));	
		}
	    
	    @Test
	  		public void testSqrt(){
	  			calc.read("raizq");
	  			calc.read("num4");
	  			calc.read("num4");
	  			calc.read("fechapar");
	  			calc.read("igual");
	  			assertThat(calc.getMostrador(), is("6.6332495807108"));	
	  		}
	    
	    @Test
  		public void testEexpX(){
  			calc.read("eexpx");
  			calc.read("num4");
  			calc.read("num4");
  			calc.read("igual");
  			assertThat(calc.getMostrador(), is("1.2851600114359278E19"));	
  		}
	    
	    @Test
  		public void testLog2(){
  			calc.read("log2");
  			calc.read("neper");
  			calc.read("fechapar");
  			calc.read("igual");
  			assertThat(calc.getMostrador(), is("1.4426950408889634"));	
  		}
	    
	    @Test
  		public void testPercent(){
  			calc.read("num3");
  			calc.read("num5");
  			calc.read("percent");
  			assertThat(calc.getMostrador(), is("0.35"));	
  		}
}
