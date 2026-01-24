package com.calculator.utils;

public class RemoveZero {
  public String removeZero(double value){
    if ( value % 1 == 0 ) {
      return Integer.toString((int) value);
    }
    return Double.toString(value);
  }
}
