package com.calculator;

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

public class Calculator {

  ClearContext c = new ClearContext();
  RemoveZero r = new RemoveZero();
  AppColors colors = new AppColors();
  MathService math =new MathService();

  //Border
  int borderWidth = 360;
  int borderHeight = 540;


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

  //operadores e valores
  String A = "0";
  String B = null;
  String operator = null;
  String operatorViewr = null;

  JFrame frame =  new JFrame("Calculator");
  JLabel displayLabel = new JLabel();
  JPanel panel = new JPanel();
  JPanel buttonsPanel = new JPanel();

    public Calculator() {
      //SETUP FRAME
      frame.setSize(borderWidth, borderHeight);
      //to center the window
      frame.setLocationRelativeTo(null);
      //no change the height and width
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLayout(new BorderLayout());

      //SETUP LABEL
      displayLabel.setBackground(colors.customOnix);
      displayLabel.setForeground(colors.customWhite);
      displayLabel.setFont(new Font("Arial", Font.BOLD, 60));
      displayLabel.setHorizontalAlignment(JLabel.RIGHT);
      displayLabel.setText("0");
      displayLabel.setOpaque(true);


      panel.setLayout(new BorderLayout());
      panel.add(displayLabel);
      frame.add(panel, BorderLayout.NORTH);


      //SETUP BUTTONS
      buttonsPanel.setLayout(new GridLayout(5, 4));
      buttonsPanel.setBackground(colors.customOnix);
      frame.add(buttonsPanel);


      //CREATE BUTTONS
      for (int i = 0; i < buttonValues.length; i++) {
        JButton button = new JButton();
        String value = buttonValues[i];
        button.setFont(new Font("Arial", Font.PLAIN, 30));
        button.setBorder(new LineBorder(colors.customDark));
        button.setText(value);
        button.setFocusable(false);
        if (value.contains("=")) {
          button.setBackground(colors.customAquamarine);
          button.setForeground(colors.customDark);
        }else if (Arrays.asList(symbols).contains(value)) {
          button.setBackground(colors.customBabyPink);
          button.setForeground(colors.customAquamarine);
        } else {
          button.setBackground(colors.customBabyPink);
          button.setForeground(colors.customDark);
        }
        buttonsPanel.add(button);

        //actions. //ACTION PERFORMED
        button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();

            //operações
            if (Arrays.asList(symbols).contains(buttonText)) {
                if (buttonText.equals("C")) {
                  c.clearContext("0");
                  displayLabel.setText("0");

                  //MATH PERCENTAGE
                } else if (buttonText.equals("%")) {
                  double numDisplay = Double.parseDouble(displayLabel.getText());
                  displayLabel.setText(String.valueOf(
                    r.removeZero(math.calculatePercentage(numDisplay))
                  ));

                  //HANDLE BACKSPACE
                } else if (buttonText.equals("⌫")) {
                  if(!Objects.equals(displayLabel.getText(), "0") && displayLabel.getText().length() > 1) {
                    String newDisplay = displayLabel.getText().substring(
                      0, displayLabel.getText().length() - 1);
                    displayLabel.setText(newDisplay);
                  } else {
                    displayLabel.setText("0");
                  }


                  //HANDLE HIST
                } else if (buttonText.equals("hist")) {
                  displayLabel.setText("none implemeted");
                } else {

                  //OPERAÇÕES //HANDLE OPERATOR
                  operator = buttonText;
                  if (buttonText.equals("=")) {
                    try {
                      if (A != null) {
                        B = displayLabel.getText()
                          .substring(displayLabel.getText()
                            .indexOf(operatorViewr) + 1 );

                        //CALCULATE RESULT
                        double result = math.calculate(A, B, operatorViewr);
                        displayLabel.setText(String.valueOf(r.removeZero(result)));
                        c.clearContext("0");

                      }
                    } catch (Exception ex) {
                      displayLabel.setText("error");
                      throw new RuntimeException();
                    }
                  } else {
                    A = displayLabel.getText();
                    displayLabel.setText(A + operator);
                    operatorViewr = operator;
                  }

                }


              //HANDLE NUMBERS AND DOT
            } else if (buttonText.equals(".")) {
              if (!displayLabel.getText().contains(buttonText)) {
                  displayLabel.setText(displayLabel.getText() + buttonText);
                }else {
                  displayLabel.setText(displayLabel.getText());
                }
            } else if ("1234567890".contains(buttonText)) {
                if (displayLabel.getText().equals("0")) {
                  displayLabel.setText(buttonText);
                } else {
                  displayLabel.setText(displayLabel.getText() + buttonText);
                }
            }
          }
        });
      }
      frame.setVisible(true);
    }
}
