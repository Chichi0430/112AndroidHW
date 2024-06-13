package com.example.caculator;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;

enum State {FirstNumberInput, OperatorInputed, NumberInput}
enum OP { None, Add, Sub, Mul, Div}

public class MainActivity extends AppCompatActivity {

    private BigDecimal theValue = BigDecimal.ZERO;
    private BigDecimal operand1 = BigDecimal.ZERO, operand2 = BigDecimal.ZERO;
    private OP op = OP.None;
    private State state = State.FirstNumberInput;
    private boolean isDecimal = false;
    private BigDecimal decimalMultiplier = BigDecimal.valueOf(0.1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        GridLayout keysGL = findViewById(R.id.keys);

        int kbHeight = (int) (keysGL.getHeight() / keysGL.getRowCount());
        int kbWidth = (int) (keysGL.getWidth() / keysGL.getColumnCount());

        Button btn;

        for (int i = 0; i < keysGL.getChildCount(); i++) {
            btn = (Button) keysGL.getChildAt(i);
            btn.setHeight(kbHeight);
            btn.setWidth(kbWidth);
            btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
        }
    }

    public void processKeyInput(View view) {
        Button b = (Button) view;
        String bstr = b.getText().toString();

        EditText edt = findViewById(R.id.display);

        switch (bstr) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                BigDecimal bdecimal = new BigDecimal(bstr);
                switch (state) {
                    case FirstNumberInput:
                        if (isDecimal) {
                            theValue = theValue.add(bdecimal.multiply(decimalMultiplier));
                            decimalMultiplier = decimalMultiplier.multiply(BigDecimal.valueOf(0.1));
                        } else {
                            theValue = theValue.multiply(BigDecimal.TEN).add(bdecimal);
                        }
                        break;
                    case OperatorInputed:
                        theValue = bdecimal;
                        if (isDecimal) {
                            theValue = theValue.multiply(decimalMultiplier);
                            decimalMultiplier = decimalMultiplier.multiply(BigDecimal.valueOf(0.1));
                        }
                        operand2 = theValue;
                        state = State.NumberInput;
                        break;
                    case NumberInput:
                        if (isDecimal) {
                            theValue = theValue.add(bdecimal.multiply(decimalMultiplier));
                            decimalMultiplier = decimalMultiplier.multiply(BigDecimal.valueOf(0.1));
                        } else {
                            theValue = theValue.multiply(BigDecimal.TEN).add(bdecimal);
                        }
                        break;
                }
                edt.setText(formatDisplayValue(theValue));
                break;
            case ".":
                if (!isDecimal) {
                    isDecimal = true;
                    if (state == State.FirstNumberInput || state == State.NumberInput) {
                        edt.setText(edt.getText().toString() + ".");
                    }
                }
                break;
            case "Clear":
                state = State.FirstNumberInput;
                theValue = BigDecimal.ZERO;
                edt.setText("0");
                op = OP.None;
                operand2 = operand1 = BigDecimal.ZERO;
                isDecimal = false;
                decimalMultiplier = BigDecimal.valueOf(0.1);
                break;
            case "Back":
                if (isDecimal) {
                    theValue = theValue.setScale(theValue.scale() - 1, BigDecimal.ROUND_DOWN);
                    decimalMultiplier = decimalMultiplier.multiply(BigDecimal.TEN);
                    if (decimalMultiplier.compareTo(BigDecimal.ONE) == 0) {
                        isDecimal = false;
                    }
                } else {
                    theValue = theValue.divide(BigDecimal.TEN, BigDecimal.ROUND_DOWN);
                }
                edt.setText(formatDisplayValue(theValue));
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                switch (state) {
                    case FirstNumberInput:
                        operand1 = theValue;
                        operand2 = theValue;
                        op = getOperator(bstr);
                        state = State.OperatorInputed;
                        break;
                    case OperatorInputed:
                        op = getOperator(bstr);
                        operand2 = theValue;
                        break;
                    case NumberInput:
                        operand2 = theValue;
                        theValue = calculateResult(operand1, operand2, op);
                        operand1 = theValue;
                        op = getOperator(bstr);
                        state = State.OperatorInputed;
                        edt.setText(formatDisplayValue(theValue));
                        break;
                }
                isDecimal = false;
                decimalMultiplier = BigDecimal.valueOf(0.1);
                break;
            case "=":
                if (state == State.OperatorInputed || state == State.NumberInput) {
                    operand2 = theValue;
                    theValue = calculateResult(operand1, operand2, op);
                    operand1 = theValue;
                    state = State.OperatorInputed;
                    edt.setText(formatDisplayValue(theValue));
                }
                isDecimal = false;
                decimalMultiplier = BigDecimal.valueOf(0.1);
                break;
        }
    }

    private OP getOperator(String opStr) {
        switch (opStr) {
            case "+":
                return OP.Add;
            case "-":
                return OP.Sub;
            case "*":
                return OP.Mul;
            case "/":
                return OP.Div;
            default:
                return OP.None;
        }
    }

    private BigDecimal calculateResult(BigDecimal operand1, BigDecimal operand2, OP op) {
        switch (op) {
            case Add:
                return operand1.add(operand2);
            case Sub:
                return operand1.subtract(operand2);
            case Mul:
                return operand1.multiply(operand2);
            case Div:
                return operand1.divide(operand2, BigDecimal.ROUND_HALF_UP);
            default:
                return BigDecimal.ZERO;
        }
    }

    private String formatDisplayValue(BigDecimal value) {
        if (value.scale() > 0) {
            return value.stripTrailingZeros().toPlainString();
        } else {
            return value.toPlainString();
        }
    }
}
