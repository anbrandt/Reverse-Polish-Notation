package com.andrzej;

import java.util.Scanner;
import java.util.Stack;



public class Main {


	public static void main(String[] args) {

		//take the String, that is already an RPN statement/expression
		String rpnExpression = "12 2 3 4 * 10 5 / + * +";

		//split this string by spaces
		String[] splited = rpnExpression.split(" ");

		//create an int value that will keep the result of RPN expression
		int returnValue = 0;

		//save the operators in the separate string
		String operators = "+-*/";

		//create new Stack - we use two stack method over here - push and pop
		Stack<String> stack = new Stack<String>();

		//create a temp object called String t that goes through all of the splited table of strings
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

		returnValue = Integer.valueOf(stack.pop());

		System.out.println(returnValue);
	}
}

