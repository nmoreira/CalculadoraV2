package pt.uc.dei.aor.paj;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalcTest {

	private Calc calc;
	
	

	@Before
	public void firstMethod() {
		calc = new Calc();
	}

	@Test
	public void testInsereVirgula() {
		calc.virgulaValida = false;
		calc.operadorValido = false;
		calc.existeVirgula = true;
		calc.setExp("10");
		calc.insereVirgula(",");
		Assert.assertEquals("10", calc.getExp());
	}
	


}