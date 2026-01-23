package calculator;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {

  //Border
  int borderWidth = 360;
  int borderHeight = 540;

  //Color
  Color customAquamarine = new Color(78, 135, 111);
  Color customWhite = new Color(241, 242, 246);
  Color customOnix = new Color(52, 58, 64);
  Color customBabyPink = new Color(243, 197, 192);
  Color customDark = new Color(27, 32, 33);

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
    ,"hist", "="};

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
    frame.setSize(borderWidth, borderHeight);
    //to center the window
    frame.setLocationRelativeTo(null);
    //no change the height and width
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    displayLabel.setBackground(customOnix);
    displayLabel.setForeground(customWhite);
    displayLabel.setFont(new Font("Arial", Font.BOLD, 60));
    displayLabel.setHorizontalAlignment(JLabel.RIGHT);
    displayLabel.setText("0");
    displayLabel.setOpaque(true);

    panel.setLayout(new BorderLayout());
    panel.add(displayLabel);
    frame.add(panel, BorderLayout.NORTH);

    buttonsPanel.setLayout(new GridLayout(5, 4));
    buttonsPanel.setBackground(customOnix);
    frame.add(buttonsPanel);

    for (int i = 0; i < buttonValues.length; i++) {
      JButton button = new JButton();
      String value = buttonValues[i];
      button.setFont(new Font("Arial", Font.PLAIN, 30));
      button.setBorder(new LineBorder(customDark));
      button.setText(value);
      button.setFocusable(false);
      if (value.contains("=")) {
        button.setBackground(customAquamarine);
        button.setForeground(customDark);
      }else if (Arrays.asList(symbols).contains(value)) {
        button.setBackground(customBabyPink);
        button.setForeground(customAquamarine);
      } else {
        button.setBackground(customBabyPink);
        button.setForeground(customDark);
      }
      buttonsPanel.add(button);

      //actions.
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          JButton button = (JButton) e.getSource();
          String buttonText = button.getText();

          //operações
          if (Arrays.asList(symbols).contains(buttonText)) {
              if (buttonText.equals("C")) {
                clearContext("0");
                displayLabel.setText("0");
              } else if (buttonText.equals("%")) {
                double numDisplay = Double.parseDouble(displayLabel.getText());
                displayLabel.setText(String.valueOf(
                  removeZero(numDisplay / 100)
                ));
              } else if (buttonText.equals("⌫")) {
                if(!Objects.equals(displayLabel.getText(), "0") && displayLabel.getText().length() > 1) {
                  String newDisplay = displayLabel.getText().substring(
                    0, displayLabel.getText().length() - 1);
                  displayLabel.setText(newDisplay);
                } else {
                  displayLabel.setText("0");
                }
              } else if (buttonText.equals("hist")) {
                displayLabel.setText("none implemeted");
              } else {

                //OPERAÇÕES

                operator = buttonText;
                if (buttonText.equals("=")) {
                  try {
                    if (A != null) {
                      B = displayLabel.getText()
                        .substring(displayLabel.getText()
                          .indexOf(operatorViewr) + 1 );

                      System.out.println("On conditional equal\n"
                        + operatorViewr + " and " + operator);

                      double aValue = Double.parseDouble(A);
                      double bValue = Double.parseDouble(B);

                      if ("+".equals(operatorViewr)) {
                        displayLabel.setText(removeZero(aValue + bValue));
                        clearContext("0");

                      } else if ("-".equals(operatorViewr)) {
                        displayLabel.setText(removeZero(aValue - bValue));
                        clearContext("0");

                      } else if ("×".equals(operatorViewr)) {
                        displayLabel.setText(removeZero(aValue * bValue));
                        clearContext("0");

                      } else if ("÷".equals(operatorViewr)) {
                        displayLabel.setText(removeZero(aValue / bValue));
                        clearContext("0");
                      }
                    }
                  } catch (Exception ex) {
                    displayLabel.setText("error");
                    throw new RuntimeException();
                  }
                } else {
                  A = displayLabel.getText();
                  displayLabel.setText(A + operator);
                  operatorViewr = operator;
                  System.out.println(operatorViewr + " and " + operator);
                }

              }
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

  void clearContext(String e){
    A = e;
    B = null;
    operator = null;
  };

  String removeZero(double value){
    if ( value % 1 == 0 ) {
      return Integer.toString((int) value);
    }
    return Double.toString(value);
  }
}
