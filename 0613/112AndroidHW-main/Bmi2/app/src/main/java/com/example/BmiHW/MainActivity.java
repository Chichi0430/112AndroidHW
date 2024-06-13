package com.example.BmiHW;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtHeight = findViewById(R.id.edtHeight);
        EditText edtWeight = findViewById(R.id.edtWeight);

        txvShow = findViewById(R.id.txvShow);
        txvShow.setTextSize(36);

        Button btnCalc = findViewById(R.id.btnCalc);
        Button btnClear = findViewById(R.id.btnClear);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnCalc) {
                    String heightStr = edtHeight.getText().toString();
                    String weightStr = edtWeight.getText().toString();

                    // 檢查是否輸入了數字
                    if (heightStr.isEmpty() || weightStr.isEmpty()) {
                        txvShow.setTextColor(Color.RED);
                        txvShow.setText("請輸入數字");
                        return;
                    }

                    double height = Double.parseDouble(heightStr);
                    double weight = Double.parseDouble(weightStr);

                    // 檢查是否輸入了有效的數值
                    if (height == 0 || weight == 0) {
                        txvShow.setTextColor(Color.RED);
                        txvShow.setText("請輸入正確數值");
                        return;
                    }

                    double bmi = weight / Math.pow(height / 100.0, 2);

                    if (bmi >= 24)
                        txvShow.setTextColor(Color.RED);
                    else if (bmi < 18.5)
                        txvShow.setTextColor(Color.BLUE);
                    else
                        txvShow.setTextColor(Color.GREEN);

                    txvShow.setText(String.format("%.2f", bmi));
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtHeight.setText("0");
                edtWeight.setText("0");
                txvShow.setText("");
            }
        });
    }
}
