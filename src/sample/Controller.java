package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

public class Controller {

    private BigDecimal left;
    private String selectedOperator;
    private boolean numberInputting;

    @FXML
    private TextField op;
    @FXML
    private TextField result;

    @FXML
    protected void handleOnAnyButtonClicked(ActionEvent evt) throws Exception {
        Button button = (Button)evt.getSource();
        final String buttonText = button.getText();

        if (buttonText.equals("C") || buttonText.equals("CE")) {
            if (buttonText.equals("CE")) {
                left = BigDecimal.ZERO;
            }

            selectedOperator = "";
            numberInputting = false;
            op.setText("0");
            result.setText("= "+"");
            return;
        }

        if (buttonText.matches("[0-9\\.]")) {
            if (!numberInputting) {
                numberInputting = true;
                op.clear();
            }
            op.appendText(buttonText);
            return;
        }

        if (buttonText.equals("+")) {
            left = new BigDecimal(op.getText());
            selectedOperator = buttonText;
            numberInputting = false;
            return;
        }

        if (buttonText.equals("-")) {
            left = new BigDecimal(op.getText());
            selectedOperator = buttonText;
            numberInputting = false;
            op.setText("-");
            return;
        }

        if (buttonText.equals("*")) {
            left = new BigDecimal(op.getText());
            selectedOperator = buttonText;
            numberInputting = false;
            return;
        }

        if (buttonText.equals("/")) {
            left = new BigDecimal(op.getText());
            selectedOperator = buttonText;
            numberInputting = false;
            return;
        }
        if (buttonText.equals("±")) {
            left = new BigDecimal(op.getText());
            selectedOperator = buttonText;
            numberInputting = false;
            op.setText(left.negate().toString());
            return;
        }

        if (buttonText.equals("%")) {
            left = new BigDecimal(op.getText());
            selectedOperator = buttonText;
            numberInputting = false;
            final BigDecimal right = numberInputting ? new BigDecimal(op.getText()) : left;
            left = calculate(selectedOperator, left, right);
            result.setText("= "+left.toString());
            numberInputting = false;
            return;
        }

        if (buttonText.equals("X²")) {
            left = new BigDecimal(op.getText());
            selectedOperator = buttonText;
            numberInputting = false;
            final BigDecimal right = numberInputting ? new BigDecimal(op.getText()) : left;
            left = calculate(selectedOperator, left, right);
            result.setText("= "+left.toString());
            numberInputting = false;
            return;
        }


        if (buttonText.equals("1/x")) {
            left = new BigDecimal(op.getText());
            selectedOperator = buttonText;
            numberInputting = false;
            try{
            final BigDecimal right = numberInputting ? new BigDecimal(op.getText()) : left;
            left = calculate(selectedOperator, left, right);
            result.setText("= "+left.toString());
            numberInputting = false;
            return;}catch (ArithmeticException ex){
                System.err.println("Division sur 0");
            }
        }

        if (buttonText.equals("π")) {
            if (!numberInputting) {
                numberInputting = true;
                op.clear();
            }
            op.setText("3.14159265359");
            return;
        }

        if (buttonText.equals("=")) try {
            final BigDecimal right;
                right = new BigDecimal(op.getText());
                //right = left;
            left = calculate(selectedOperator, left, right);
            result.setText("= " + left.toString());
            numberInputting = false;
            return;
        } catch (ArithmeticException e) {
            System.err.println("DIVISION SUR ZERO");
        }
    }

    @FXML
    /**-----------------Redirection Vers Calculatrice-----------------------------------*/
    public void Calculatrice(ActionEvent actionEvent) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("calculat.fxml"));
        Scene scene = new Scene(page,Color.TRANSPARENT);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    @FXML
    /**-----------------Redirection Vers About-----------------------------------*/
    public void About(ActionEvent actionEvent) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("About.fxml"));
        Scene scene = new Scene(page, Color.TRANSPARENT);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();

    }



    static BigDecimal calculate(String operator, BigDecimal left, BigDecimal right) {
        switch (operator) {
            case "+":
                return left.add(right);
            case "-":
                return left.subtract(right);
            case "*":
                return left.multiply(right);
            case "/":
                return left.divide(right);
            case "%":
                return left.divide(valueOf(100));
            case "X²":
                return left.pow(2);
            case "1/x":
                BigDecimal a=new BigDecimal("1");
                return a.divide(left);
            default:
        }
        return right;
    }

}
