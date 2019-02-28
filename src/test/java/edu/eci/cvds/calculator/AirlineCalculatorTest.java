package edu.eci.cvds.calculator;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

import org.junit.Test;

import edu.eci.cvds.calculator.AirlineCalculator;

/**
 * Test class for {@linkplain AirlineCalculator} class
 */
public class AirlineCalculatorTest {

	/**
	 * The class under test.
	 */
	private AirlineCalculator calculator = new AirlineCalculator();

	/**
	 * {@inheritDoc}}
	 */
	@Test
	public void calculateTest() {
		qt().forAll(integers().between(1,100),integers().between(1,100))
		.check(
				(a,b)->{
			if(b==1) {
				AirlineCalculator cal = calculator.calculate(a, ECONOMY_CLASS);
				}
			else if (b==2) {
				AirlineCalculator cal =  calculator.calculate(a, FIRST_CLASS);
				}
			else {
				AirlineCalculator cal =  calculator.calculate(a, EMERGENCY_DOOR);
				}
			if (a>100 || a <0) {
				return cal == new BookingOutput(BookingResult.NOT_ENOUGH_SEATS, Optional.empty()) ;
			}
			else if(a<5) {
				return cal == new BookingOutput(BookingResult.NOT_ENOUGH_SEATS, Optional.empty());
			}
			else if(a>= 5 && a<10) {
				return cal == new BookingOutput(BookingResult.SUCCESS, Optional.of(seatsNumber * (category.getPrice()-(category.getPrice()*(float)0.02))));
			}
			else if (a>=10 && a<15) {
				return cal == new BookingOutput(BookingResult.SUCCESS, Optional.of(seatsNumber * (category.getPrice()-(category.getPrice()*(float)0.1))));
			}
			else {
				return cal = new BookingOutput(BookingResult.SUCCESS, Optional.of(seatsNumber * (category.getPrice()-(category.getPrice()*(float)0.2))));
			}
			
		}
		);
	}
	
	
}
