package com.andrzej;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by andrzej on 01.06.17.
 * <p>
 * This class contains four methods:
 * 1 - the final method CALCULATOR that first changes the arithmetic expression to RPN, and then RPN to result
 * 2 - resultfromRpn - takes String of RPN expression and prints the result of the equation
 * 3 - infixToRPN - tekst String of regular arithmetic expression and prints the postfix notation RPN - reverse polish notation
 * 4 - getOperatorPriority - created for InfixtoRPN for purpose of ranking the operators
 */
public class RPNexpressCalc {

		//the final method - we declare a string of arithmetic expression and get the final result
	public static void calculator(String arithmExpresssion) {
		int result;
		String temp;

		RPNexpressCalc rpNexpressCalc = new RPNexpressCalc();

		temp = rpNexpressCalc.infixToRPN(arithmExpresssion);

		result = rpNexpressCalc.resultFromRpn(temp);

		System.out.println("The postftix(RPN) expression of the equation [" + arithmExpresssion + "] is [" + temp +
				"] and the result is " + result);

	}

		//get result from RPN expression
	public int resultFromRpn(String rpnExpression) {
		int returnValue = 0;

		String[] splited = rpnExpression.split(" ");
		String operators = "+-*/()";
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

//			System.out.println("RPN expression is [" + rpnExpression + "] and the result is " + returnValue);

		} catch (NumberFormatException e) {
			e.getMessage();
			System.out.println("ERROR! In expression [" + rpnExpression + "] you have to put spaces between the operators and operands");
		} catch (EmptyStackException e) {
			e.getMessage();
			System.out.println("ERROR too many spaces between your expression");
		}
		return returnValue;

	}


	//THIS ONE WAS NOT WORKING
//	public String arithToRPN(String artihExpression) {
//		//method that takes String - typical arithmetic expression and changes it to RPN - reverse polish notation
//
//		String[] splited = artihExpression.split(" ");
//		Stack<String> stackOne = new Stack<String>(); //only operators
//		Stack<String> stackTwo = new Stack<String>(); //only integers
//
//		String operators = "+-*/()";
//
//		String returnString = "";
//
//
//		for (String t : splited) {
//
//			if (!operators.contains(t)) {
//				stackOne.push(t);
//
//
//			} else if (operators.equals("+") || operators.equals("-") || operators.equals("*") || operators.equals("/")) {
//
////				while (!stackOne.empty() && getOperatorPriority(stackOne.peek())) >= getOperatorPriority(t);
//
//
//			}
//				//first condition - if the operator is ")"
//				do {
//					stackTwo.add(stackOne.get(stackOne.size() - 1));
//					stackTwo.remove(stackTwo.size() - 1);
//				} while (stackTwo.get(stackTwo.size() - 1).equals("("));
//				stackTwo.remove(stackTwo.size() - 1);
//
//
//				//second condition - check the rank of the operator, compare them
////			} else if (operators.equals())
//
//
//			}
//
//			//jeżeli operatorem jest nawias zamykający
//
//
//			//drugi specjlany warunek - ranga operatora, sprawdzić co jest ostatnie na stosie, porównuję
//
//
//			//trzeci warunek
//
//
//			System.out.println(stackOne);
//			System.out.println(stackTwo);
//
//
//		}
//
//	return returnString;
//	}


////		System.out.println(stackOne.pop());
//
//
//	//		for (int i = 0; i < splited.length; i++) {
////			System.out.println(splited[i]);
////
////		}



		//create a RPN expression from arithmetic expression
	public String infixToRPN(String arithmExpression) {

		Stack<String> stack = new Stack<String>();

		//instead of creating another stack i created a String that will be used as a second stack
		//using this method allows me to use StringTokenizer
		String secondStack = "";

		StringTokenizer stringTokenizer = new StringTokenizer(arithmExpression, "+-*/()", true);


		try {


			while (stringTokenizer.hasMoreTokens()) {

				//create temporary String that takes the next string from the TOKENIZER
				String tempString = stringTokenizer.nextToken();

				if (tempString.equals("+") || tempString.equals("*") || tempString.equals("-") || tempString.equals("/")) {

					//using peek method on the stack (takes the highest element in the stack and returns it without
					//changing the stack - all elements are the same on the stack afterwards

					while (!stack.empty() && getOperatorPriority(stack.peek()) >= getOperatorPriority(tempString))

						//postifx is the second stack in the String, and we push the element from the first stack to String rpnExpress
						secondStack = secondStack + (stack.pop() + " ");

					stack.push(tempString);
					//checking how big is the stack in current moment of while loop
//					System.out.println("The size of the stack is " + stack.size());

				} else if (tempString.equals("(")) {

					//if we have the opening bracket, we push it to the tempstring String
					stack.push(tempString);

					//if we have a closing bracket and put the while loop inside to look for elements in the stack
					//that are equal to openinng bracket s
				} else if (tempString.equals(")")) {

					while (!stack.peek().equals("("))
						secondStack = secondStack + (stack.pop() + " ");
					//if we have the closing brackets we pop it from the stack
					stack.pop();

					//
				} else

					secondStack = secondStack + (tempString + " ");
			}

			//final method - remove everything left from the stack to secondStack String
			while (!stack.empty())
				secondStack = secondStack + (stack.pop() + " ");


		} catch (NumberFormatException e) {
			e.getMessage();
			System.out.println("ERROR in the expression " + arithmExpression + ". You have to add spaces between " +
					"operators");
		} catch (EmptyStackException e) {
			e.getMessage();
			System.out.println("Empty!");
		}
		//for the sake of calculator method working properly i commented the sout method
//		System.out.println(secondStack);
		return secondStack;

	}


	//method for ranking the operators
	public static int getOperatorPriority(String operator) {
		if (operator.equals("+") || operator.equals("-"))
			return 1;

		else if (operator.equals("*") || operator.equals("/"))
			return 2;

		else if (operator.equals("^"))
			return 3;

		else return 0;

	}


}
