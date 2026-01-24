package com.calculator.utils;

public class ClearContext {
  String A = "0";
  String B = null;
  String operator = null;
  public void clearContext(String e){
    A = e;
    B = null;
    operator = null;
  };
}
