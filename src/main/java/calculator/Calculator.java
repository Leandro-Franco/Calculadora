package calculator;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
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
    ,"hist"};

  //operadores e valores
  String A = "0";
  String B = null;
  String operator = null;

  JFrame frame =  new JFrame("Calculator");
  JLabel displayLabel = new JLabel();
  JPanel panel = new JPanel();
  JPanel buttonsPanel = new JPanel();

  public Calculator() {
    frame.setVisible(true);
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
      if (Arrays.asList(symbols).contains(value)) {
        button.setBackground(customBabyPink);
        button.setForeground(customAquamarine);
      }else if (value.contains("=")) {
        button.setBackground(customAquamarine);
        button.setForeground(customDark);
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
          if (Arrays.asList(symbols).contains(buttonText)) {
              if (buttonText.equals("C")){
                clearContext();
                displayLabel.setText("0");
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
  }

  void clearContext(){
    A = "0";
    B = null;
    operator = null;
  };
}
