package com.example.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class CalculatorController {
    private String operator = "";
    @FXML
    private Label mathExpressionLabel;
    @FXML
    private Label oldMathExpressionLabel;

    private String formatDouble(double number) {
        if (number == (int) number) {
            return String.valueOf((int) number);
        }
        else {
            return String.valueOf(number);
        }
    }

    public void addOperatorToExpression(ActionEvent event) {
        String operator = (String) ((Node) event.getSource()).getUserData();
        String currentMathExpression = mathExpressionLabel.getText();
        oldMathExpressionLabel.setText(currentMathExpression);
        this.operator = operator;
        clearExpression();
    }

    public void addCommaToExpression() {
        String currentMathExpression = mathExpressionLabel.getText();
        if (currentMathExpression.contains(".")) return;

        mathExpressionLabel.setText(currentMathExpression + ".");
    }

    public void addNegativeToExpression() {
        String currentMathExpression = mathExpressionLabel.getText();
        if (currentMathExpression.contains("-")) return;

        mathExpressionLabel.setText("-" + currentMathExpression);
    }

    public void addNumberToExpression(ActionEvent event) {
        String number = (String) ((Node) event.getSource()).getUserData();
        String currentMathExpression = mathExpressionLabel.getText();

        mathExpressionLabel.setText(currentMathExpression + number);
    }

    public void clearExpression() {
        mathExpressionLabel.setText("");
    }

    private void clearOldExpression() {
        oldMathExpressionLabel.setText("");
    }

    public void clearAllExpressions() {
        clearOldExpression();
        clearExpression();
        operator = "";
    }

    public void calculateExpression() {
        if (oldMathExpressionLabel.getText().isEmpty() | mathExpressionLabel.getText().isEmpty()) return;

        double firstExpression = Double.parseDouble(oldMathExpressionLabel.getText());
        double secondExpression = Double.parseDouble(mathExpressionLabel.getText());
        double result = 0;

        switch (operator) {
            case "+" -> result = firstExpression + secondExpression;
            case "-" -> result = firstExpression - secondExpression;
            case "*" -> result = firstExpression * secondExpression;
            case "/" -> {
                if (secondExpression != 0) {
                    result = firstExpression / secondExpression;
                }
            }
            case "%" -> result = (firstExpression) / 100 * secondExpression;
        }

        oldMathExpressionLabel.setText(formatDouble(firstExpression) + " " + operator + " " + formatDouble(secondExpression) + " =");
        mathExpressionLabel.setText(formatDouble(result));
    }
}

// add hint to labels of hover and then mini info show with full number cause number can be longer than window