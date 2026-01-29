package com.calculator.ui;

import com.calculator.constants.AppColors;
import com.calculator.service.MathService;
import com.calculator.utils.ClearContext;
import com.calculator.utils.RemoveZero;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class CalculatorFrame extends JFrame implements ActionListener {
  ClearContext c = new ClearContext();
  RemoveZero r = new RemoveZero();
  AppColors colors = new AppColors();
  MathService math =new MathService();

  //Buttons Text
  String[] buttonValues = {
    "C", "⌫", "%", "÷",
    "7", "8", "9", "×",
    "4", "5", "6", "-",
    "1",  "2", "3", "+",
    "hist", "0", ".", "="
  };
  String[] symbols = {
    "÷", "×", "-", "+"
    ,"C", "⌫", "%", "÷"
    ,"hist", "=" };

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
