package com.example.buyticket;
//A109221258 祁箴
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String ticketNum = intent.getStringExtra("ticketNum");
        String gender = intent.getStringExtra("gender");
        String Tickets = intent.getStringExtra("Tickets");
        int allMoney = intent.getIntExtra("AllMoney", 0); // 默认值为0

        if (Tickets.equals(getResources().getString(R.string.regularticket))) {
            allMoney = 500*Integer.parseInt(ticketNum);
        } else if (Tickets.equals(getResources().getString(R.string.childticket))) {
            allMoney = 400*Integer.parseInt(ticketNum);;
        } else if (Tickets.equals(getResources().getString(R.string.studentticket))) {
            allMoney = 250*Integer.parseInt(ticketNum);
        }



        String BorG = "性別：" + gender;
        String message = "您訂購了 " + Tickets + ticketNum + " 張票";
        message = BorG + "\n" + message + "\n總金额：" + allMoney + "元";

        TextView txvPrint = findViewById(R.id.txvPrint);
        txvPrint.setText(message);
    }
}