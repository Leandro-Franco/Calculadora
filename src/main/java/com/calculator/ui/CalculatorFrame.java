package com.calculator.ui;

import com.calculator.constants.AppColors;
import com.calculator.service.MathService;
import com.calculator.utils.RemoveZero;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class CalculatorFrame extends JFrame implements ActionListener {
  RemoveZero r = new RemoveZero();

  private static final int WIDTH = 360;
  private static final int HEIGHT = 540;

  //Buttons Text
  String[] buttonValues = {
    "C", "⌫", "%", "÷",
    "7", "8", "9", "×",
    "4", "5", "6", "-",
    "1",  "2", "3", "+",
    "hist", "0", ".", "="
  };
  private List<String> symbols = Arrays.asList("÷", "×", "-", "+","C", "⌫", "%","hist", "=" );

  private String operator = null;
  private String A = "0";

  // Componentes
  private final JLabel displayLabel = new JLabel();
  private final JPanel buttonsPanel = new JPanel();

  private final MathService mathService = new MathService();

  public CalculatorFrame() {
    setupFrame();
    setupDisplay();
    setupButtons();
    setVisible(true);
  }

  private void setupFrame() {
    setTitle("Calculator");
    setSize(WIDTH, HEIGHT);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
  }

  private void setupDisplay() {
    JPanel displayPanel = new JPanel(new BorderLayout());

    displayLabel.setBackground(AppColors.customOnix);
    displayLabel.setForeground(AppColors.customWhite);
    displayLabel.setFont(new Font("Arial", Font.BOLD, 60));
    displayLabel.setHorizontalAlignment(JLabel.RIGHT);
    displayLabel.setText("0");
    displayLabel.setOpaque(true);

    displayPanel.add(displayLabel);
    add(displayPanel, BorderLayout.NORTH);
  }

  private void setupButtons() {
    buttonsPanel.setLayout(new GridLayout(5, 4));
    buttonsPanel.setBackground(AppColors.customOnix);

    for (String value : buttonValues) {
      JButton button = createButton(value);
      buttonsPanel.add(button);
    }

    add(buttonsPanel, BorderLayout.CENTER);
  }

  private JButton createButton(String value) {
    JButton button = new JButton(value);
    button.setFont(new Font("Arial", Font.PLAIN, 30));
    button.setBorder(new LineBorder(AppColors.customDark));
    button.setFocusable(false);
    button.addActionListener(this);

    if (value.equals("=")) {
      button.setBackground(AppColors.customAquamarine);
      button.setForeground(AppColors.customDark);
    } else if (symbols.contains(value)) {
      button.setBackground(AppColors.customBabyPink);
      button.setForeground(AppColors.customAquamarine);
    } else {
      button.setBackground(AppColors.customBabyPink);
      button.setForeground(AppColors.customDark);
    }

    return button;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand(); // Texto do botão clicado

    // 1. Números e Ponto
    if ("1234567890.".contains(command)) {
      handleNumberInput(command);
    }
    // 2. Operações Especiais (C, Backspace, %)
    else if (command.equals("C")) {
      clearContext();
    } else if (command.equals("⌫")) {
      handleBackspace();
    } else if (command.equals("%")) {
      handlePercentage();
    } else if (command.equals("hist")) {
      displayLabel.setText("Not Impl");
    }
    // 3. Operadores (+ - * / =)
    else {
      handleOperator(command);
    }
  }

  private void handleNumberInput(String value) {
    if (value.equals(".")) {
      if (!displayLabel.getText().contains(".")) {
        displayLabel.setText(displayLabel.getText() + ".");
      }
    } else {
      // Se for número
      if (displayLabel.getText().equals("0")) {
        displayLabel.setText(value);
      } else {
        displayLabel.setText(displayLabel.getText() + value);
      }
    }
  }

  private void handleBackspace() {
    String text = displayLabel.getText();
    if (!text.equals("0") && text.length() > 1) {
      displayLabel.setText(text.substring(0, text.length() - 1));
    } else {
      displayLabel.setText("0");
    }
  }

  private void handlePercentage() {
    try {
      double value = Double.parseDouble(displayLabel.getText());
      double result = mathService.calculatePercentage(value);
      displayLabel.setText(r.removeZero(result));
    } catch (NumberFormatException ex) {
      displayLabel.setText("Error");
    }
  }

  private void clearContext() {
    A = "0";
    operator = null;
    displayLabel.setText("0");
  }

  private void handleOperator(String newOperator) {
    // Se apertou "="
    if (newOperator.equals("=")) {
      if (operator != null) {
        calculateResult();
        operator = null; // Reseta operador após conta
      }
    }
    // Se apertou operadore (+ - * /)
    else {
      // Salva o primeiro número (A) e o operador
      A = displayLabel.getText();
      displayLabel.setText("0"); // Limpa tela pro próximo número
      operator = newOperator;
    }
  }

  private void calculateResult() {
    try {
      double valA = Double.parseDouble(A);
      double valB = Double.parseDouble(displayLabel.getText());

      double result = mathService.calculate(valA, valB, operator);

      displayLabel.setText(r.removeZero(result));
      // O resultado vira o novo "A" caso a pessoa continue calculando
      A = displayLabel.getText();

    } catch (Exception ex) {
      displayLabel.setText("Error");
    }
  }
}
