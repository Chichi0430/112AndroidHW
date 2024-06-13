package com.example.caculator;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

enum State {FirstNumberInput, OperatorInputed, NumberInput}
enum OP { None, Add, Sub, Mul, Div}

public class MainActivity extends AppCompatActivity {

    private double theValue = 0; // 将theValue的类型改为double
    private double operand1 = 0, operand2 = 0; // 将操作数的类型改为double
    private OP op = OP.None;
    private State state = State.FirstNumberInput;

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
        double bdouble; // 使用double类型存储数字按钮的值

        EditText edt = findViewById(R.id.display);

        switch (bstr) {
            // 数字按钮被点击时
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
                bdouble = Double.parseDouble(bstr); // 将文字转换为double类型
                // 根据状态进行不同的处理
                switch (state) {
                    case FirstNumberInput:
                        theValue = theValue * 10 + bdouble;
                        break;
                    case OperatorInputed:
                        theValue = bdouble;
                        operand2 = bdouble;
                        state = State.NumberInput;
                        break;
                    case NumberInput:
                        theValue = theValue * 10 + bdouble;
                        break;
                }
                // 根据theValue是否为整数，决定显示整数或小数
                if (theValue == Math.floor(theValue)) {
                    edt.setText(String.valueOf((int) theValue));
                } else {
                    edt.setText(String.valueOf(theValue));
                }
                break;
            case "Clear": // 清除并重设相关变量
                state = State.FirstNumberInput;
                theValue = 0;
                edt.setText("0");
                op = OP.None;
                operand2 = operand1 = 0;
                break;
            case "Back": // 退格键
                theValue = (int) (theValue / 10);
                // 根据theValue是否为整数，决定显示整数或小数
                if (theValue == Math.floor(theValue)) {
                    edt.setText(String.valueOf((int) theValue));
                } else {
                    edt.setText(String.valueOf(theValue));
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/": // 当operator被点击时
                switch (state) {
                    case FirstNumberInput:
                        operand1 = theValue;
                        operand2 = theValue;
                        switch (bstr) {
                            case "+":
                                op = OP.Add;
                                break;
                            case "-":
                                op = OP.Sub;
                                break;
                            case "*":
                                op = OP.Mul;
                                break;
                            case "/":
                                op = OP.Div;
                                break;
                        }
                        state = State.OperatorInputed;
                        break;
                    case OperatorInputed:
                        switch (bstr) {
                            case "+":
                                op = OP.Add;
                                break;
                            case "-":
                                op = OP.Sub;
                                break;
                            case "*":
                                op = OP.Mul;
                                break;
                            case "/":
                                op = OP.Div;
                                break;
                        }
                        operand2 = theValue;
                        break;
                    case NumberInput:
                        operand2 = theValue;
                        switch (op) {
                            case Add:
                                theValue = operand1 + operand2;
                                break;
                            case Sub:
                                theValue = operand1 - operand2;
                                break;
                            case Mul:
                                theValue = operand1 * operand2;
                                break;
                            case Div:
                                theValue = operand1 / operand2;
                                break;
                        }
                        operand1 = theValue;
                        switch (bstr) {
                            case "+":
                                op = OP.Add;
                                break;
                            case "-":
                                op = OP.Sub;
                                break;
                            case "*":
                                op = OP.Mul;
                                break;
                            case "/":
                                op = OP.Div;
                                break;
                        }
                        state = State.OperatorInputed;
                        // 根据theValue是否为整数，决定显示整数或小数
                        if (theValue == Math.floor(theValue)) {
                            edt.setText(String.valueOf((int) theValue));
                        } else {
                            edt.setText(String.valueOf(theValue));
                        }
                        break;
                }
                break;
            case "=": // 当＝号被点击时，根据当前状态进行不同的处理
                if (state == State.OperatorInputed) {
                    switch (op) {
                        case Add:
                            theValue = operand1 + operand2;
                            break;
                        case Sub:
                            theValue = operand1 - operand2;
                            break;
                        case Mul:
                            theValue = operand1 * operand2;
                            break;
                        case Div:
                            theValue = operand1 / operand2;
                            break;
                    }
                    operand1 = theValue;
                } else if (state == State.NumberInput) {
                    operand2 = theValue;
                    switch (op) {
                        case Add:
                            theValue = operand1 + operand2;
                            break;
                        case Sub:
                            theValue = operand1 - operand2;
                            break;
                        case Mul:
                            theValue = operand1 * operand2;
                            break;
                        case Div:
                            theValue = operand1 / operand2;
                            break;
                    }
                    operand1 = theValue;
                    state = State.OperatorInputed;
                }
                // 根据theValue是否为整数，决定显示整数或小数
                if (theValue == Math.floor(theValue)) {
                    edt.setText(String.valueOf((int) theValue));
                } else {
                    edt.setText(String.valueOf(theValue));
                }
                break;
        }
    }
}
