package monkeyGrid;
import static org.junit.Assert.*;

import org.junit.Test;



public class Test_MonkeyGrid {

	@Test
	public void test_sumOfDigits() {
		
		MonkeyGrid mg = new MonkeyGrid(0);
		assertEquals(11, 	mg.sumOfDigits(-74));
		assertEquals(21, 	mg.sumOfDigits(-34527));
		assertEquals(16, 	mg.sumOfDigits(-88));
		assertEquals(2, 	mg.sumOfDigits(1000000001));
		
	}
	
	@Test
	public void test_breadthFirstSearch(){
		
		MonkeyGrid mg;
		
		// Until 8, the pattern is easily calculated, can be auto-tested
		for ( int i=2 ; i<=8 ; i++){
			int triangular = (int)((i*(i+1))/2);
			int expected = (int) Math.pow((double)(2*i+1), (double)2)  - 4*triangular ;
			
			mg = new MonkeyGrid(i);
			assertEquals(expected, mg.breadthFirstSearch());
			
			System.out.println(	"Max: " + Integer.toString(i) +
								" Expected: " + Integer.toString(expected) + 
								" Result: " + Integer.toString(mg.breadthFirstSearch()));
		}	
		
	}
	

}
