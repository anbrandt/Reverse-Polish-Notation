package com.andrzej;

import java.util.Stack;

/**
 * Created by andrzej on 01.06.17.
 */
public class RPNexpressCalc {


	public int resultFromRpn(String rpnExpression) {
		int returnValue = 0;

		String[] splited = rpnExpression.split(" ");
		String operators = "+-*/";
		Stack<String> stack = new Stack<String>();


		try {
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

			System.out.println("RPN expression is [" + rpnExpression + "] and the result is " + returnValue);

		} catch (NumberFormatException e) {
			e.getMessage();
			System.out.println("ERROR! In expression [" + rpnExpression + "] you have to put spaces between the operators and operands");
		}
		return returnValue;

	}


}
