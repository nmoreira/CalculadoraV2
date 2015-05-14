package pt.uc.dei.aor.paj.calc;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pt.uc.dei.aor.paj.calc.Calc;

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