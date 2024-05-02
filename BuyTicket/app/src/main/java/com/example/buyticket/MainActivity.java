package com.example.buyticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
//A109221258 祁箴

public class MainActivity extends AppCompatActivity {
    public int money = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btnSure = findViewById(R.id.btnSure);


        EditText edtNumber = findViewById(R.id.edtNumber);

        edtNumber.setText("1");

        RadioGroup rgType = findViewById(R.id.rgType);

        RadioGroup rgGender = findViewById(R.id.rgGender);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                UpdateResult();
            }
        });


        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                UpdateResult();
            }
        });

        edtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                UpdateResult();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateResult();


                Intent intent = new Intent(MainActivity.this, ResultActivity.class);


                String ticketNum = edtNumber.getText().toString();
                intent.putExtra("ticketNum", ticketNum);
                intent.putExtra("AllMoney", money * Integer.parseInt(ticketNum));

                // 添加性別信息到 Intent 中
                RadioButton rdbBoy = findViewById(R.id.rdbBoy);
                RadioButton rdbGirl = findViewById(R.id.rdbGirl);
                if (rdbBoy.isChecked()) {
                    intent.putExtra("gender", getResources().getString(R.string.male));
                } else if (rdbGirl.isChecked()) {
                    intent.putExtra("gender", getResources().getString(R.string.female));
                }

                RadioGroup rgType = findViewById(R.id.rgType);
                if (rgType.getCheckedRadioButtonId() == R.id.rdbAdult) {
                    intent.putExtra("Tickets", getResources().getString(R.string.regularticket));
                    money = 500;
                } else if (rgType.getCheckedRadioButtonId() == R.id.rdbChild) {
                    intent.putExtra("Tickets", getResources().getString(R.string.childticket));
                    money = 400;
                } else if (rgType.getCheckedRadioButtonId() == R.id.rdbStudent) {
                    intent.putExtra("Tickets", getResources().getString(R.string.studentticket));
                    money = 300;
                }

                // 啟動目標 Activity
                startActivity(intent);
            }
        });


        UpdateResult();
    }


    public void UpdateResult() {
        String txvResult = "";


        EditText edtNumber = findViewById(R.id.edtNumber);
        RadioButton rdbBoy = findViewById(R.id.rdbBoy);
        RadioButton rdbGirl = findViewById(R.id.rdbGirl);
        RadioGroup rgType = findViewById(R.id.rgType);


        String TicketNum = edtNumber.getText().toString();
        if (TicketNum.isEmpty()) {
            return;
        }


        if (rdbBoy.isChecked()) {
            txvResult = getResources().getString(R.string.male) + "\n";
        } else if (rdbGirl.isChecked()) {
            txvResult = getResources().getString(R.string.female) + "\n";
        }

        int AllMoney = 0;


        if (rgType.getCheckedRadioButtonId() == R.id.rdbAdult) {
            AllMoney = 500 * Integer.parseInt(TicketNum);
            txvResult += getResources().getString(R.string.regularticket) + TicketNum + "張\n" + AllMoney + "元";
        } else if (rgType.getCheckedRadioButtonId() == R.id.rdbChild) {
            AllMoney = 400 * Integer.parseInt(TicketNum);
            txvResult += getResources().getString(R.string.childticket) + TicketNum + "張\n" + AllMoney + "元";
        } else if (rgType.getCheckedRadioButtonId() == R.id.rdbStudent) {
            AllMoney = 250 * Integer.parseInt(TicketNum); // 使用 250 元作为学生票的价格
            txvResult += getResources().getString(R.string.studentticket) + TicketNum + "張\n" + AllMoney + "元";
        }


        TextView txvOutput = findViewById(R.id.txvResult);

        txvOutput.setText(txvResult);
    }
}
