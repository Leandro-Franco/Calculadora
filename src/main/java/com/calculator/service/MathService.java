package com.calculator.service;

public class MathService {
  public double calculate(String A, String B, String operator) {
    double aValue = Double.parseDouble(A);
    double bValue = Double.parseDouble(B);

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

  public double calculatePercentage(double value) {
    return value / 100;
  }
}
