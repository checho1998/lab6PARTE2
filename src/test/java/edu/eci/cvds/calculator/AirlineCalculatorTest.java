package edu.eci.cvds.calculator;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

import org.junit.Test;

import edu.eci.cvds.calculator.AirlineCalculator;
import edu.eci.cvds.model.BookingOutput;
import edu.eci.cvds.model.BookingResult;
import edu.eci.cvds.model.SeatCategory;

import org.quicktheories.generators.Generate;

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
		qt()
	    .forAll(integers().allPositive()
	          ,Generate.enumValues(SeatCategory.class))
	    .check((seatsNumber,category) ->{
	    BookingOutput calculate = calculator.calculate(seatsNumber,category);
		if (seatsNumber<1 || seatsNumber>100) {
			return  (calculate.getResult()==BookingResult.INVALID && calculate.getCost().isPresent()==false);
		}
	    float precio=seatsNumber * category.getPrice();
        if (seatsNumber>=6 && seatsNumber<=9) {
        	precio-=precio*(0.02);
        }
        else if (seatsNumber>=10 && seatsNumber<=14) {
        	precio-=precio*(0.1);
        }
        else if (seatsNumber>=15) {
        	precio-=precio*(0.2);
        }
        
		if (SeatCategory.ECONOMY_CLASS.equals(category)&& seatsNumber<=50){
			return (calculate.getResult()==BookingResult.SUCCESS && calculate.getCost().get()==precio);

		}
		else if (SeatCategory.FIRST_CLASS.equals(category)&& seatsNumber<=15 && calculate.getCost().get()==precio){
			return (calculate.getResult()==BookingResult.SUCCESS);

		}
		else if (SeatCategory.EMERGENCY_DOOR.equals(category) && seatsNumber<=8 && calculate.getCost().get()==precio){
			return (calculate.getResult()==BookingResult.SUCCESS);
		}	

		else{return (calculate.getResult()==BookingResult.NOT_ENOUGH_SEATS);}
        
	
	    });}
	
	
}
