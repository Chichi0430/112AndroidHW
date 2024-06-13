//A111222027 林廷叡
package com.example.radiobuttonHW;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNumber;
    private TextView lblOutput;
    private RadioGroup rgType, rgGender;
    private RadioButton rdbBoy, rdbGirl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber = findViewById(R.id.editTextNumber);
        lblOutput = findViewById(R.id.lblOutput);
        rgType = findViewById(R.id.rgType);
        rgGender = findViewById(R.id.rgGender);
        rdbBoy = findViewById(R.id.rdbBoy);
        rdbGirl = findViewById(R.id.rdbGirl);

        editTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateOutput();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateOutput();
            }
        });

        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateOutput();
            }
        });

        Button confirmBtn = findViewById(R.id.confirmBtn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantityText = editTextNumber.getText().toString();
                if (quantityText.isEmpty() || quantityText.equals("0")) {  // 檢查是否沒有輸入或輸入為0
                    lblOutput.setText("請輸入購買張數");
                } else {
                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                    intent.putExtra("ticketInfo", lblOutput.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    private void updateOutput() {
        String quantityText = editTextNumber.getText().toString();
        int quantity = 0;
        boolean quantityEntered = !quantityText.isEmpty();

        try {
            quantity = Integer.parseInt(quantityText);
        } catch (NumberFormatException e) {
            // 不處理錯誤，允許數量為0進行顯示
        }

        String gender = "";
        if (rdbBoy.isChecked()) {
            gender = getString(R.string.male);
        } else if (rdbGirl.isChecked()) {
            gender = getString(R.string.female);
        }

        int ticketPrice = 0;
        String ticketType = "";
        int checkedId = rgType.getCheckedRadioButtonId();
        if (checkedId == R.id.rdbAdult) {
            ticketType = getString(R.string.regularticket);
            ticketPrice = 500;
        } else if (checkedId == R.id.rdbChild) {
            ticketType = getString(R.string.childticket);
            ticketPrice = 250;
        } else if (checkedId == R.id.rdbStudent) {
            ticketType = getString(R.string.studentticket);
            ticketPrice = 400;
        }

        int totalCost = ticketPrice * quantity;

        if (quantityEntered) {
            lblOutput.setText(String.format("%s\n%s: %d 張\n金額 %d 元", gender, ticketType, quantity, totalCost));
        } else {
            lblOutput.setText(String.format("%s\n%s", gender, ticketType));
        }
    }


}
