package com.calculator.service;

public class MathService {
  public double calculate(double aValue, double bValue, String operator) {
    switch (operator) {
      case "+":
        return aValue + bValue;
      case "-":
        return aValue - bValue;
      case "×":
        return aValue * bValue;
      case "÷":
        if (bValue == 0.0) {
          throw new ArithmeticException("Division by zero");
        }
        return aValue / bValue;
      default: return 0;
    }
  }
}
