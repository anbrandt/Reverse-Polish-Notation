package com.andrzej;

import java.util.Scanner;
import java.util.Stack;



public class Main {


	public static void main(String[] args) {
		//with the method created in RPNexpressCalc class
		RPNexpressCalc rpnexpress = new RPNexpressCalc();
		//this method returns the result of the RPN expression and takes the String to constructor
		//but the RPN expression REQUIRES SPACES BETWEEN INTEGERS AND OPERATORS
		
		rpnexpress.resultFromRpn("5 1 2 + 4 * + 3 -");


		//take the String, that is already an RPN statement/expression
		String rpnExpression = "12 2 3 4 * 10 5 / + * +";
		String rpneExpression2 = "2 3 + 5 *";
		String rpnExpression3 = "5 1 2 + 4 * + 3 -";

		//split this string by spaces
		String[] splited = rpnExpression.split(" ");

		//create an int value that will keep the result of RPN expression
		int returnValue = 0;

		//save the operators in the separate string
		String operators = "+-*/";

		//create new Stack - we use two stack method over here - push and pop
		Stack<String> stack = new Stack<String>();

		//create a temp object called String t that goes through all of the splited table of strings, (which are still objects anyway)

		for (String t : splited) {

			//the first condition - if there are no operators in the String t than push it to the stack
			if (!operators.contains(t)) {
				stack.push(t);

				//else - use two temp value for numbers surrounding the operator - a and b
			} else {
				int a = Integer.valueOf(stack.pop());
				int b = Integer.valueOf(stack.pop());

				//important - every operator in the String operators has his own Int right now
				int index = operators.indexOf(t);

				//switching by all operators
				switch (index) {
					case 0:
						stack.push(String.valueOf(a + b));
						break;
					case 1:
						stack.push(String.valueOf(b - a));
						break;
					case 2:
						stack.push(String.valueOf(a * b));
						break;
					case 3:
						stack.push(String.valueOf(b / a));
						break;
				}
			}
		}
			//use with debugger to see the process thoroughly
		returnValue = Integer.valueOf(stack.pop());

		System.out.println(returnValue);
	}
}

