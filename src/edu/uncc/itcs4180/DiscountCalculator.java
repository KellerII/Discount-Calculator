/*
 * James Keller
 * ITCS 4180 - 091
 * HW2 - Discount Calculator
 * 2/11/14
 */

package edu.uncc.itcs4180;

import java.text.DecimalFormat;
import java.text.NumberFormat;

//Calculator class that handles the you saved and you payed calculations
//for the main activity
public class DiscountCalculator {
	private double userInput;
	private double discount;
	
	//Formatting object for method outputs
	NumberFormat formatter = new DecimalFormat("#0.00");     
	
	//Constructor
	public DiscountCalculator () {
		userInput = 0.0;
		discount = .10;
	}
	
	//Setter
	public void setUserInput(double input) {
		userInput = input;
	}
	
	//Setter
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	//Calculates and returns a String representation of how much the user
	//should pay
	public String calcPay() {
		double saved = userInput - (userInput * discount);
		formatter.format(saved);
		String youSaved = String.valueOf(formatter.format(saved));
		return "$" + youSaved;
	}
	
	//Calculates and returns a String representation of how much the user
	//saved
	public String calcSaved() {
		double amount = userInput * discount;
		String youPay = String.valueOf(formatter.format(amount));
		return "$" + youPay;
	}

}
