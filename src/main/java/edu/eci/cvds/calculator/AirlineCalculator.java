package edu.eci.cvds.calculator;

import java.util.Optional;

import edu.eci.cvds.model.SeatCategory;
import edu.eci.cvds.model.BookingOutput;
import edu.eci.cvds.model.BookingResult;

/**
 * Utility class to validate an airline's booking
 */

public class AirlineCalculator implements BookingCalculator {
	
	/**
	 * {@inheritDoc}}
	 */
	
	@Override
	public BookingOutput calculate(Integer seatsNumber, SeatCategory category) {
		// TODO: Add required validations and calculate total price if applies
		if (seatsNumber>=1  &&  seatsNumber<=101) {
			if (SeatCategory.ECONOMY_CLASS.equals(category)) {
				if (seatsNumber>0 && seatsNumber<=50) {
					if(seatsNumber>=5 && seatsNumber<=9 ) {
						return new BookingOutput(BookingResult.SUCCESS, Optional.of(seatsNumber * (category.getPrice()-(category.getPrice()*(float)0.02))));
					}
					else if(seatsNumber>=10 && seatsNumber<=14 ) {
						return new BookingOutput(BookingResult.SUCCESS, Optional.of(seatsNumber * (category.getPrice()-(category.getPrice()*(float)0.1))));
					}
					else if(seatsNumber >= 15 ) {
						return new BookingOutput(BookingResult.SUCCESS, Optional.of(seatsNumber * (category.getPrice()-(category.getPrice()*(float)0.2))));
					}
					else {
						return new BookingOutput(BookingResult.SUCCESS, Optional.of(seatsNumber * category.getPrice()));
					}
				}
				else {
					return new BookingOutput(BookingResult.NOT_ENOUGH_SEATS, Optional.empty());
					}
			}
			else if (SeatCategory.FIRST_CLASS.equals(category)) {
				if (seatsNumber>0 && seatsNumber<=15) {
					
					if(seatsNumber>=5 && seatsNumber<=9 ) {
						return new BookingOutput(BookingResult.SUCCESS, Optional.of(seatsNumber * (category.getPrice()-(category.getPrice()*(float)0.02))));
					}
					else if(seatsNumber>=10 && seatsNumber<=14 ) {
						return new BookingOutput(BookingResult.SUCCESS, Optional.of(seatsNumber * (category.getPrice()-(category.getPrice()*(float)0.1))));
					}
					else if(seatsNumber == 15 ) {
						return new BookingOutput(BookingResult.SUCCESS, Optional.of(seatsNumber * (category.getPrice()-(category.getPrice()*(float)0.2))));
					}
					else {
						return new BookingOutput(BookingResult.SUCCESS, Optional.of(seatsNumber * category.getPrice()));
					}
				}
				else {
					return new BookingOutput(BookingResult.NOT_ENOUGH_SEATS, Optional.empty());
					}
			}
			else if (SeatCategory.EMERGENCY_DOOR.equals(category)) {
				if (seatsNumber>0 && seatsNumber<=8) {
					
					if(seatsNumber>=5 && seatsNumber<=9 ) {
						return new BookingOutput(BookingResult.SUCCESS, Optional.of(seatsNumber * (category.getPrice()-(category.getPrice()*(float)0.02))));
					}
				}
				else {
					return new BookingOutput(BookingResult.NOT_ENOUGH_SEATS, Optional.empty());
					}
			}
			else {
				return new BookingOutput(BookingResult.NOT_ENOUGH_SEATS, Optional.empty());
			}
		}
		return new BookingOutput(BookingResult.NOT_ENOUGH_SEATS, Optional.empty());	
	}
}
